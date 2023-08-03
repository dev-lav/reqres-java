package users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import shared.BaseTest;

import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest extends BaseTest {

    @Test
    public void SuccessUpdateUser()
    {
        RequestSpecification request = RestAssured.given();

        String name = "John Axel";
        String job = "QA Tester";

        JSONObject params = new JSONObject();
        params.put("name", name);
        params.put("job", job);

        request.body(params.toString());

        request.header(CONTENT_TYPE, APPLICATION_JSON);

        int id_user = Integer.parseInt(context.getAttribute("id_user").toString());

        Response response = request.put("/api/users/"+id_user);

        response.then().assertThat()
                .statusCode(200)
                .body("name", equalTo(name))
                .body("job", equalTo(job));
    }
}
