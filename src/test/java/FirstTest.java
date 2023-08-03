import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FirstTest {

    @Test
    public void first()
    {
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = given();

        Response response = request.param("page", 1)
                .queryParam("limit", 10).get("/api/users");

        response.then().assertThat().statusCode(200);
    }
}
