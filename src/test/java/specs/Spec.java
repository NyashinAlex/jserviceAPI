package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.with;

public class Spec {

    public static RequestSpecification requestSpec = with()
            .filter(new AllureRestAssured())
            .baseUri("https://api.spoonacular.com")
            .log().all();
}
