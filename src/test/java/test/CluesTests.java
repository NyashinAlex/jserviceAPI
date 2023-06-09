package test;

import io.qameta.allure.Epic;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec;

public class CluesTests extends BaseTest{

    @Test
    @Epic("Clues")
    @DisplayName("Подсказка с фиксированым значением \"values\"")
    void cluesValues() {

        step("Получить Подсказки с Ценой = " + value, () -> {
            List<Integer> values = given()
                    .spec(requestSpec)
                    .get("/api/clues?value=" + value)
                    .then()
                    .spec(responseSpec)
                    .extract().jsonPath().getList("value");

            step("Проверить, что все значения values в ответе равны " + value, () -> {
                assertThat(values.stream().allMatch(val -> val == value), equalTo(true));
            });
        });
    }
    @Test
    @Epic("Clues")
    @DisplayName("Подсказка с значением \"values\" = 0")
    void cluesValuesNull() {

        step("Получить Подсказки с Ценой = 0", () -> {
            JsonPath answer = given()
                    .spec(requestSpec)
                    .get("/api/clues?value=" + 0)
                    .then()
                    .spec(responseSpec)
                    .extract().jsonPath();


            step("Проверить, что ответ пустой", () -> {
                assertThat(answer.getList("").size(), equalTo(0));
            });
        });
    }
    @Test
    @Epic("Clues")
    @DisplayName("Подсказка с отрицательным значением \"values\"")
    void cluesValuesMinus() {

        step("Получить Подсказки с отрицательной Ценой", () -> {
            JsonPath answer = given()
                    .spec(requestSpec)
                    .get("/api/clues?value=" + "-" + value)
                    .then()
                    .spec(responseSpec)
                    .extract().jsonPath();

            step("Проверить, что ответ пустой", () -> {
                assertThat(answer.getList("").size(), equalTo(0));
            });
        });
    }
}