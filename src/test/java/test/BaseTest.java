package test;

import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    static int value;

    @BeforeAll
    static void dataForTests() {
        value = 400;
    }
}
