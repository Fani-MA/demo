package me.fani.michael.service;

import jakarta.transaction.Transactional;
import me.fani.michael.App;
import me.fani.michael.BaseDbTest;
import me.fani.michael.persistence.dao.CartRepo;
import me.fani.michael.persistence.dao.CategoryRepository;
import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.invocation.InterceptedInvocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = App.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-it.properties")
public class CartServiceTest extends BaseDbTest {

    @Autowired
    CartService sut; // system under testing

    @MockBean
    AuthService authServiceMock;

    @Autowired
    CategoryRepository categoryRepository;

    @SpyBean
    ProductService productService;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    CartRepo cartRepo;

    @Autowired
    UserRepo userRepo;

    @Test
    public void testNothing() {
        Mockito.when(authServiceMock.getAuthenticatedUserName())
                .thenReturn("testUser");

        var result = sut.allCart();
        Assert.assertEquals(result, null);

    }

    @Test
    public void testCartOptimisticLock() {

        CountDownLatch spoilCdl = new CountDownLatch(1);
        CountDownLatch preSaveCdl = new CountDownLatch(1);

        long productId;

        Mockito.when(authServiceMock.getAuthenticatedUserName())
                .thenReturn("testUser");

        Mockito.doAnswer(new Answer<Product>() {
            @Override
            public Product answer(InvocationOnMock invocation) throws Throwable {
                Product arg = invocation.getArgument(0, Product.class);
                if (arg.getQuantity() == 8) {
                    spoilCdl.countDown();
                    System.out.println(">>> Releasing spoiler");
                    preSaveCdl.await();
                    System.out.println(">>> Continue to save Releasing spoiler");
                }
                return (Product) invocation.callRealMethod();
            }
        }).when(productService).save(Mockito.any(Product.class));

        var testUser = new User();
        testUser.setUsername("testUser");
        testUser.setEmail("a@a.com");
        testUser.setPassword("1111");
        testUser.setRole(Role.USER);
        testUser.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        testUser = userRepo.save(testUser);

        var testCategory = new Category();
        testCategory.setName("testCategory");
        categoryRepository.save(testCategory);

        var testProduct = new Product();
        testProduct.setName("test_123");
        testProduct.setPrice(5.0);
        testProduct.setQuantity(10);
        testProduct.setCategory(testCategory);
        testProduct = productRepo.save(testProduct);
        productId = testProduct.getId();

        new Thread(() -> {
            try {
                spoilCdl.await();
                System.out.println(">>> Starting to spoil");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            productService.setQuantity(productId, 5);
            preSaveCdl.countDown();
        }).start();

        var cart = new Cart();
        cart.setUserId(testUser.getId());
        cart.setProductId(testProduct.getId());
        cart.setAmount(2);
        cart = cartRepo.save(cart);

        sut.buyAll();

        var fetchedProduct = productService.getById(productId);
        System.out.println("new quantity: " + fetchedProduct.getQuantity());
    }
}
