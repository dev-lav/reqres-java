package login;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import shared.BaseTest;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void SuccessLogin()
    {
        RequestSpecification request = RestAssured.given();

        String email = "eve.holt@reqres.in";
        String password = "cityslicka";

        JSONObject params = new JSONObject();
        params.put("email", email);
        params.put("password", password);

        request.body(params.toString());

        //request.header(CONTENT_TYPE, APPLICATION_JSON);
        request.contentType(ContentType.JSON);

        Response response = request.post("/api/login");

        response.then().assertThat()
                .statusCode(200);

        String token = response.jsonPath().getString("token");
        context.setAttribute("token", token);
        System.out.println(token);
    }

    @Test(priority = 2)
    public void FailedLogin()
    {
        RequestSpecification request = RestAssured.given();

        String email = "eve.holt@reqres.in";

        JSONObject params = new JSONObject();
        params.put("email", email);

        request.body(params.toString());

        request.header(CONTENT_TYPE, APPLICATION_JSON);

        Response response = request.post("/api/login");

        response.then().assertThat()
                .statusCode(400);
    }
}
