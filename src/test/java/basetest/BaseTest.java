package basetest;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class BaseTest {
    protected RequestSpecification request;

    @BeforeMethod
    public void setUpGiven()
    {
        request=given()
                .baseUri("https://reqres.in/api/");
    }
}
