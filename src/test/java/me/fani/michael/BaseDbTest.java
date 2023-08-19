package me.fani.michael;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import liquibase.integration.commandline.LiquibaseCommandLine;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.util.concurrent.TimeUnit;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_27;

public class BaseDbTest {

    static EmbeddedMysql mysqld;

    @BeforeClass
    public static void init() {
        MysqldConfig config = aMysqldConfig(v5_7_27)
                .withCharset(UTF8)
                .withPort(2215)
                .withTimeZone("Europe/Chisinau")
                .withTimeout(2, TimeUnit.MINUTES)
                .withServerVariable("max_connect_errors", 600)
                .build();

        mysqld = anEmbeddedMysql(config)
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

    @AfterClass
    public static void teardown() {
        mysqld.stop();
    }


}
