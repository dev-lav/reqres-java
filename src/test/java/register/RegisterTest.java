package register;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import shared.BaseTest;

public class RegisterTest extends BaseTest {

    @Test(priority = 1)
    public void SuccessRegister()
    {
        RequestSpecification request = RestAssured.given();

        String email = "eve.holt@reqres.in";
        String password = "pistol";

        JSONObject params = new JSONObject();
        params.put("email", email);
        params.put("password", password);

        request.body(params.toString());

        request.header(CONTENT_TYPE, APPLICATION_JSON);

        Response response = request.post("/api/register");

        response.then().assertThat()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void FailedRegister()
    {
        RequestSpecification request = RestAssured.given();

        String email = "sydney@fife";

        JSONObject params = new JSONObject();
        params.put("email", email);

        request.body(params.toString());

        request.header(CONTENT_TYPE, APPLICATION_JSON);

        Response response = request.post("/api/register");

        response.then().assertThat()
                .statusCode(400);
    }
}
