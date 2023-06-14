package specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.http.ContentType.JSON;

public class Spec {

    public static RequestSpecification requestSpec = with()
            .filter(new AllureRestAssured())
            .baseUri("http://jservice.io")
            .log().all();

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(BODY)
            .expectStatusCode(200)
            .expectContentType(JSON)
            .build();

    public static ResponseSpecification responseError500Spec = new ResponseSpecBuilder()
            .log(BODY)
            .expectStatusCode(500)
            .expectContentType(JSON)
            .build();
}
