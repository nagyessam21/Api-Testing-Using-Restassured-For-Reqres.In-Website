package updateuser;

import basetest.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import createuserdataprovider.CreateUserDataProvider;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdateUserTestCases extends BaseTest {

    //verify when update name&job for user , user is updated
    @Test
    public void updateUserNameAndJob()
    {
        CreateUserDataProvider createUserData=new CreateUserDataProvider("Peter","Translator");
        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(createUserData)
        .when()
                .put("users/2")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .log().all();
    }

    //verify when update name of user , user is updated
    @Test
    public void updateUserNameOnly()
    {
        CreateUserDataProvider createUserData=new CreateUserDataProvider();
        createUserData.setName("Peter");
        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(createUserData)
        .when()
                .put("users/2")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .log().all();
    }

    //verify when update user job , user is updated
    @Test
    public void updateUserJobOnly()
    {
        CreateUserDataProvider createUserData=new CreateUserDataProvider();
        createUserData.setJob("Translator");
        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(createUserData)
        .when()
                .put("users/2")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .log().all();
    }
}
