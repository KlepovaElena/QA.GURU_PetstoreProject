package petstore.tests;

import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import petstore.Config.ApiConfig;

public class TestBase {

    static ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class);

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = apiConfig.baseUrl();
        RestAssured.basePath = apiConfig.basePath();
    }
}
