package users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import shared.BaseTest;

public class ListUserTest extends BaseTest {

    @Test
    public void SuccessGetListUsers()
    {
        RequestSpecification request = RestAssured.given();

        Response response = request.get("/api/users");

        response.then().assertThat()
                .statusCode(200);

        int id_user = response.jsonPath().getInt("data[0].id");
        context.setAttribute("id_user", id_user);
    }
}
