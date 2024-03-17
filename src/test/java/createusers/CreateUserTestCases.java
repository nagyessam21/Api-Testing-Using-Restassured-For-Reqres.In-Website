package createusers;

import basetest.BaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import createuserdataprovider.CreateUserDataProvider;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateUserTestCases extends BaseTest {

    //verify when try to create user by name and job , user is created successfully and id is shown is response
    @Test
    public void createUserSuccessfullyByJobAndName()
    {
        CreateUserDataProvider createUserData=new CreateUserDataProvider("Nagy","Engineer");
        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(createUserData)
        .when()
                .post("users")
        .then()
                .assertThat().statusCode(201)
                .time(lessThan(3000L))
                .assertThat().body("name",is(equalTo("Nagy")),
                "job",is(equalTo("Engineer")),
                "id",is(not(empty())))
                .log().all();
    }

    //verify when create user by only name , user is created successfully and id is shown in response
    @Test
    public void createUserByNameOnly()
    {
        CreateUserDataProvider createUserData=new CreateUserDataProvider();
        createUserData.setName("Nagy");
        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(createUserData)
        .when()
                .post("users")
        .then()
                .assertThat().statusCode(201)
                .time(lessThan(3000L))
                .assertThat().body("name",is(equalTo("Nagy")),
                "job",is(equalTo(null)),
                "id",is(not(empty())))
                .log().all();
    }

    //verify when create user by only job , user is created successfully and id is shown in response
    @Test
    public void createUserByJobOnly()
    {
        CreateUserDataProvider createUserData=new CreateUserDataProvider();
        createUserData.setJob("Engineer");
        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(createUserData)
        .when()
                .post("users")
        .then()
                .assertThat().statusCode(201)
                .time(lessThan(3000L))
                .assertThat().body("name",is(equalTo(null)),
                "job",is(equalTo("Engineer")),
                "id",is(not(empty())))
                .log().all();
    }

    //verify when create user without name or job , user is created successfully and id is shown in response
    @Test
    public void createUserWithoutNameOrJob()
    {
        CreateUserDataProvider createUserData=new CreateUserDataProvider();
        given()
                .spec(request)
                .contentType(ContentType.JSON)
                .body(createUserData)
        .when()
                .post("users")
        .then()
                .assertThat().statusCode(201)
                .time(lessThan(3000L))
                .assertThat().body("name",is(equalTo(null)),
                "job",is(equalTo(null)),
                "id",is(not(empty())))
                .log().all();
    }
}
