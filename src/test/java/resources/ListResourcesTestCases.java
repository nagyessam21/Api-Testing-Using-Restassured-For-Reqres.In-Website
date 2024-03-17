package resources;

import static io.restassured.RestAssured.*;

import basetest.BaseTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class ListResourcesTestCases extends BaseTest {

    //verify when try to get resources , resources in page 1 are shown
    @Test
    public void resourcesInPageOne()
    {
        given()
                .spec(request)
        .when()
                .get("unknown")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .assertThat().body("page",is(equalTo(1)),
                "per_page",is(equalTo(6)),
                "total",is(equalTo(12)),
                "data[2].name",is(equalTo("true red")));
    }

    //verify when try to get resources in page 2 , resources in page 2 are shown
    @Test
    public void resourcesInPageTwo()
    {
        given()
                .spec(request)
        .when()
                .get("unknown?page=2")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .assertThat().body("page",is(equalTo(2)),
                "per_page",is(equalTo(6)),
                "total",is(equalTo(12)),
                "data[2].name",is(equalTo("blue iris")));
    }

    //verify when try to get resources with id , this resource is only shown
    @Test
    public void resourceWithId()
    {
        given()
                .spec(request)
        .when()
                .get("unknown/5")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .time(lessThan(3000L))
                .assertThat().body("data.id",is(equalTo(5)),
                "data.name",is(equalTo("tigerlily")),
                "support.url",is(equalTo("https://reqres.in/#support-heading")));
    }

    //verify when try to get resource with id which doesn't exist , an error appears and status code is 404
    @Test
    public void resourceIsNotFound()
    {
        given()
                .spec(request)
        .when()
                .get("unknown/20")
        .then()
                .log().all()
                .assertThat().statusCode(404)
                .time(lessThan(3000L));
    }
}
