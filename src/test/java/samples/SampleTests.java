package samples;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.*;

public class SampleTests {
    Properties prop = new Properties();

    @BeforeTest
    public void getPropertiesFile() throws IOException {
    	// ToDo: Figure out relative path, this was not working 
        FileInputStream fis = new FileInputStream("src/main/java/resources/config.properties");
        prop.load(fis);
    }


    @Test
    public void castlemockTest() {
        baseURI = prop.getProperty("baseURI");
        given().
                get("/mock/rest/project/mhAjF9/application/izoQVd/users").
                then().
                assertThat().statusCode(200);
    }

}