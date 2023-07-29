package me.fani.michael.service;

import me.fani.michael.App;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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
        Mockito.when(authServiceMock.getAuthenticatedUserName())
                .thenReturn("testUser");

        var result = sut.allCart();
        Assert.assertEquals(result, null);
    }
}
