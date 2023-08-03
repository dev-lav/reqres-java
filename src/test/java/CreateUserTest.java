import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.module.jsv.JsonSchemaValidator;

import java.io.File;

public class CreateUserTest {

    int id_user;

    @Test(dataProvider = "data-users")
    public void SuccessCreateUser(String name, String job)
    {
        RestAssured.baseURI= "https://reqres.in";
        RequestSpecification request = RestAssured.given();

        //initiate JSON Object
        JSONObject params = new JSONObject();
        params.put("name", "John Doe");
        params.put("job", "QA");

        request.body(params.toString());
        request.header("Content-Type", "application/json");

//        request.log().all();

        Response response = request.post("/api/users");

        /*Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), name);
        Assert.assertEquals(response.jsonPath().getString("job"), job);*/

        //System.out.println(response.getBody().asString());

        String crateUserSchemaPath = "src/resources/schema/users/createuser.json";

        response.then().assertThat()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchema(new File(crateUserSchemaPath)))
                .body("name", Is.is(name))
                .body("job", Is.is(job));

        //id_user = response.jsonPath().getInt("id");
        //System.out.println("id_user : "+ id_user);
    }

    @Test(dataProvider = "data-users")
    public void TestingDataProvider(String name, String job)
    {
        System.out.println("Name: "+name);
        System.out.println("Job: "+job);
    }

    @DataProvider(name = "data-users")
    Object[][] DataUsers()
    {
        Object[][] users = new Object[][]
                {
                        {"John Doe", "Backend Engineer"},
                        {"John Axel", "Frontend Engineer"}
                };

        return users;
    }

    @DataProvider(name = "user-login")
    Object[][] DataUsersLogin()
    {
        Object[][] users = new Object[][]
                // email, password, case
                {
                        {"email1@example.com", "password", "success"},
                        {"email1@example.com", "passwordsalah", "failed"},
                        {"email2@example.com", "password", "failed"},
                        {"emailsalah@example.com", "passwordsalah", "failed"},
                        {"emailblock@example.com", "password", "success"},
                };

        return users;
    }

    @Test(dataProvider = "user-login")
    public void TestingDataProviderLogin(String email, String password, String statustest)
    {
        System.out.println("Email: "+email);
        System.out.println("Password: "+password);
    }




/*
    @Test
    public void SuccessUpdateUser()
    {
        RestAssured.baseURI= "https://reqres.in";
        RequestSpecification request = RestAssured.given();

        // data
        String name = "morpheus update";
        String job = "leader update";

        //initiate JSON Object
        JSONObject params = new JSONObject();
        params.put("name", name);
        params.put("job", job);

        request.body(params.toString());
        request.header("Content-Type", "application/json");

        Response response = request.put("/api/users/"+id_user);

        *//*Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), name);
        Assert.assertEquals(response.jsonPath().getString("job"), job);*//*

        //System.out.println(response.getBody().asString());

        String crateUserSchemaPath = "src/resources/schema/users/createuser.json";

        response.then().assertThat()
                .statusCode(200)
//                .body(JsonSchemaValidator.matchesJsonSchema(new File(crateUserSchemaPath)))
                .body("name", Is.is(name))
                .body("job", Is.is(job));

//        id_user = response.jsonPath().getInt("id");
//        System.out.println("id_user : "+ id_user);
    }*/
}
