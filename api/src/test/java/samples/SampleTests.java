package samples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class SampleTests extends ApiTest {

    @Parameters({"responseContentsPath"})
    @Test
    public void castlemockTest(String responseContentsPath) throws IOException {
        String body = given().header("Accept", "application/json").get("/mock/rest/project/mhAjF9/application/izoQVd/users").then().assertThat().statusCode(200).body(not(containsString("error"))).extract().body().asString();

        // TODO: compare the response body to a json file.
    }

}