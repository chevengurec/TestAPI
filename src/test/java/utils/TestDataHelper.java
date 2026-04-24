package utils;

import dto.EntityRequest;
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
        EntityRequest.Addition addition = EntityRequest.Addition.builder()
                .additionalInfo("Тестовая информация")
                .additionalNumber(100)
                .build();

        List<Integer> importantNumbers = Arrays.asList(1, 2, 3);

        EntityRequest request = EntityRequest.builder()
                .title("Тестовая сущность")
                .verified(true)
                .addition(addition)
                .importantNumbers(importantNumbers)
                .build();

        return createTestEntity(request);
    }

    public Long createTestEntity(EntityRequest request) {
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
