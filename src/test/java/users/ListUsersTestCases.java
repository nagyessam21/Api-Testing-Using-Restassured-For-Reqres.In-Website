package users;

import static io.restassured.RestAssured.*;

import basetest.BaseTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class ListUsersTestCases extends BaseTest {

    //verify when try to get users , users in page one are shown
    @Test
    public void listUsersInPageOne ()
    {
        given()
                .spec(request)
                .baseUri("https://reqres.in/api/")
        .when()
                .get("users")
        .then()
                .assertThat().statusCode(200)
                .time(lessThan(300L))
                .assertThat().body("page",is(equalTo(1)),
                "per_page",is(equalTo(6)),
                "data",hasSize(6),
                "total",is(equalTo(12)),
                "data[1].email",is(equalTo("janet.weaver@reqres.in")))
                .log().all();
    }

    //verify when try to get users in page two , users in page two are shown
    @Test
    public void listUsersInPageTwo()
    {
        given()
                .spec(request)
        .when()
                .get("users?page=2")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .assertThat().body("page",is(equalTo(2)),
                "per_page",is(equalTo(6)),
                "total",is(equalTo(12)),
                "data[1].first_name",is(equalTo("Lindsay")));
    }

    //verify when try to get user by id , this user is shown only
    @Test
    public void listUserById()
    {
        given()
                .spec(request)
        .when()
                .get("users/7")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .assertThat().body("data.id",is(equalTo(7)),
                "data.email",is(equalTo("michael.lawson@reqres.in")),
                "support.url",is(equalTo("https://reqres.in/#support-heading")));
    }

    //verify when try to get user with id doesn't exist , an error appear
    @Test
    public void userIsNotFound()
    {
        given()
                .spec(request)
        .when()
                .get("user/34")
        .then()
                .log().all()
                .assertThat().statusCode(404)
                .time(lessThan(3000L));
    }
}