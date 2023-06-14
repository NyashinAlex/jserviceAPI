package test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static specs.Spec.*;

public class CluesTests extends BaseTest{

    @Test
    @Epic("Clues")
    @Feature("value")
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
    @Feature("value")
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
    @Feature("value")
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
    @Test
    @Epic("Clues")
    @Feature("category")
    @DisplayName("Подсказка по существующей \"category\"")
    void cluesCategory() {

        step("Получить Подсказки с существующей Категорей", () -> {
            Response response = given()
                    .spec(requestSpec)
                    .get("/api/clues?category="+ category)
                    .then()
                    .spec(responseSpec)
                    .extract().response();

            step("Проверить, что все значения category_id в ответе равны " + category, () -> {
                List<Integer>  categories = response.jsonPath().getList("category_id");
                assertThat(categories.stream().allMatch(val -> val == category), equalTo(true));
            });

            step("Проверить, что все значения category.id в ответе равны " + category, () -> {
                List<Integer>  categories = response.jsonPath().getList("category.id");
                assertThat(categories.stream().allMatch(val -> val == category), equalTo(true));
            });
        });
    }
    @Test
    @Epic("Clues")
    @Feature("category")
    @DisplayName("Подсказка c \"category\"=0")
    void cluesCategoryNull() {

        step("Получить Подсказки с существующей Категорей", () -> {
            JsonPath answer = given()
                    .spec(requestSpec)
                    .get("/api/clues?category=0")
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
    @Feature("category")
    @DisplayName("Подсказка c \"category\" = нечисловое значение")
    void cluesCategoryError500() {

        step("Получить Подсказки с существующей Категорей", () -> {
            JsonPath answer = given()
                    .spec(requestSpec)
                    .get("/api/clues?category=" + categoryString)
                    .then()
                    .spec(responseError500Spec)
                    .extract().jsonPath();

            step("Проверить, что в ответ приходит 500 ошибка + текст об ошибки", () -> {
                assertThat(answer.get("status"), equalTo(500));
                assertThat(answer.get("error"), equalTo("Internal Server Error"));
            });
        });
    }
}