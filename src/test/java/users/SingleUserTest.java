package users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.testng.annotations.Test;
import shared.BaseTest;

public class SingleUserTest extends BaseTest {

    @Test
    public void SuccessGetSingleUser()
    {
        RequestSpecification request = RestAssured.given();

        int id_user = Integer.parseInt(context.getAttribute("id_user")
                .toString());
        Response response = request.get("/api/users/"+id_user);

        response.then().assertThat()
                .statusCode(200)
                .body("data.id", Is.is(id_user));
    }
}
