package me.fani.michael.persistence.dao;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import liquibase.integration.commandline.LiquibaseCommandLine;
import me.fani.michael.App;
import me.fani.michael.persistence.entity.Category;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

//import static com.wix.mysql.mbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_27;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = App.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-it.properties")
public class CategoryRepositoryTest {

    static EmbeddedMysql mysqld;

    @Autowired
    CategoryRepository sut;

    @BeforeAll
    public static void init() {
        MysqldConfig config = aMysqldConfig(v5_7_27)
                .withCharset(UTF8)
                .withPort(2215)
                .withTimeZone("Europe/Chisinau")
                .withTimeout(2, TimeUnit.MINUTES)
                .withServerVariable("max_connect_errors", 600)
                .build();

        mysqld.anEmbeddedMysql(config)
                .start();

        // TODO: try to configure via LiquibaseAutoConfiguration
        String[] args = {
                "update",
                "--changelog-file=src/main/dbschema/changelog.xml",
                "--url=jdbc:mysql://localhost:2215/sys",
                "--username=root"
        };
        LiquibaseCommandLine cli = new LiquibaseCommandLine();
        cli.execute(args);
    }

    @AfterAll
    public static void teardown() {
        mysqld.stop();
    }

    @Test
    public void testFindAll() {
        var res1 = sut.findAll();

        var newCategory = new Category();
        newCategory.setName("testCategory1");
        newCategory.setParentId(1L);
        var savedCategory = sut.save(newCategory);

        var res2 = sut.findAll();

        assertEquals(res2.size(), res1.size() + 1);

        var selectedCategory = sut.findById(savedCategory.getId());
        assertTrue(selectedCategory.isPresent());
        assertEquals(selectedCategory.get().getName(), "testCategory1");

    }
}
