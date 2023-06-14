package test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    Faker faker = new Faker();

    int value, category;
    String categoryString;

    @BeforeEach
    void dataForTests() {
        value = 400;
        category = faker.number().numberBetween(1,5000);
        categoryString = faker.internet().emailAddress();
    }
}
