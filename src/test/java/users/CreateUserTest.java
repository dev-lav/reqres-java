package users;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;
import shared.BaseTest;

import java.io.File;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class CreateUserTest extends BaseTest {

    @Test
    public void SuccessCreateUser()
    {
        RequestSpecification request = RestAssured.given();

        String name = "John Doe";
        String job = "QA Engineer";

        JSONObject params = new JSONObject();
        params.put("name", name);
        params.put("job", job);

        request.body(params.toString());

        request.header(CONTENT_TYPE, APPLICATION_JSON);

        Response response = request.post("/api/users");

        String crateUserSchemaPath = SCHEMA_BASE_PATH+"/users/createuser.json";

        response.then().assertThat()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(crateUserSchemaPath)))
                .body("name", equalTo(name))
                .body("job", equalTo(job));

        int id_user = response.jsonPath().getInt("id");
        context.setAttribute("id_user", id_user);
    }
}
