package me.fani.michael.service;

import me.fani.michael.App;
import me.fani.michael.persistence.dao.CategoryRepositoryTest;
import me.fani.michael.persistence.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = App.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-it.properties")
public class CartServiceTest {

    @Autowired
    CartService sut; // system under testing

    @MockBean
    AuthService authServiceMock;

    @Test
    public void testNothing() {
        CategoryRepositoryTest.init();
        Mockito.when(authServiceMock.getAuthenticatedUserName())
                .thenReturn("testuser");


        var result = sut.allCart();
        assertEquals(result, null);
    }

    @Test
    void buyProduct() {

    }
}
