package shared;

import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String SCHEMA_BASE_PATH = "src/resources/schema";

    public ITestContext context;

    @BeforeClass
    public void setup(ITestContext testContext)
    {
        context = testContext;
        RestAssured.baseURI= "https://reqres.in";
    }
}
