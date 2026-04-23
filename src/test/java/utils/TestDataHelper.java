package utils;

import dto.Entity;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class TestDataHelper {

    private final RequestSpecification spec;
    private final String createEndpoint;

    public TestDataHelper(RequestSpecification spec, String createEndpoint) {
        this.spec = spec;
        this.createEndpoint = createEndpoint;
    }

    public Long createTestEntity() {

        Entity.Addition addition = Entity.Addition.builder()
                .additionalInfo("Тестовая информация")
                .additionalNumber(100)
                .build();

        List<Integer> importantNumbers = Arrays.asList(1, 2, 3);  // ← добавить

        Entity request = Entity.builder()
                .title("Тестовая сущность")
                .verified(true)
                .addition(addition)
                .importantNumbers(importantNumbers)  // ← добавить
                .build();

        String response = given()
                .spec(spec)
                .body(request)
                .post(createEndpoint)
                .then()
                .statusCode(200)
                .extract()
                .asString();

        return Long.parseLong(response.trim());
    }

    public Long createTestEntity(Entity request) {

        Entity.Addition addition = Entity.Addition.builder()
                .additionalInfo("Тестовая информация")
                .additionalNumber(100)
                .build();

        List<Integer> importantNumbers = Arrays.asList(1, 2, 3);  // ← добавить

        request = Entity.builder()
                .title("Тестовая сущность")
                .verified(true)
                .addition(addition)
                .importantNumbers(importantNumbers)  // ← добавить
                .build();

        String response = given()
                .spec(spec)
                .body(request)
                .post(createEndpoint)
                .then()
                .statusCode(200)
                .extract()
                .asString();

        return Long.parseLong(response.trim());
    }

}
