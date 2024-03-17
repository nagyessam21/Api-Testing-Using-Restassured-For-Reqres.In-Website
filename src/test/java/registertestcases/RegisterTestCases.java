package registertestcases;

import static io.restassured.RestAssured.*;

import basetest.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;

public class RegisterTestCases extends BaseTest {

    //verify when register with email and password , user registered successfully and token is shown in response
    @Test
    public void registerWithEmailAndPassword()
    {
        File dataProvider=new File("src/test/resources/registerandlogin.json");
        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(dataProvider)
                .when()
                .post("register")
                .then()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .assertThat().body("token",is(not(empty())))
                .log().all();
    }
    //verify when try to register with email only , an error appears for user inform him password is missing
    @Test
    public void registerWithEmailOnly()
    {
        HashMap<String,String> emailOnly=new HashMap<>();
        emailOnly.put("email","eve.holt@reqres.in");
        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(emailOnly)
                .when()
                .post("register")
                .then()
                .assertThat().statusCode(400)
                .time(lessThan(3000L))
                .assertThat().body("error",is(equalTo("Missing password")))
                .log().all();
    }

    //verify when try to register with password only , an error appears for user inform him username or email is missing
    @Test
    public void registerWithPasswordOnly()
    {
        HashMap<String,String> emailOnly=new HashMap<>();
        emailOnly.put("password","pistol");
        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(emailOnly)
        .when()
                .post("register")
         .then()
                .assertThat().statusCode(400)
                .time(lessThan(3000L))
                .assertThat().body("error",is(equalTo("Missing email or username")))
                .log().all();
    }
}
