package api;


import dto.EntityRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("API Тестирование")
@Feature("CRUD операции")
@DisplayName("POST")
public class CreateEntityTestPOST extends BaseTest {

    @Test
    @Story("Создание сущности")
    @Description("Позитивный тест: создание сущности со всеми полями")
    @DisplayName("Позитивный тест: создание сущности со всеми полями")
    void shouldCreateEntity() {
        EntityRequest.Addition addition = EntityRequest.Addition
                .builder()
                .additionalInfo("Очень важная добавочная информация!!!111")
                .additionalNumber(100500)
                .build();

        List<Integer> importantNumbers = Arrays.asList(1, 2, 3, 4, 5);

        EntityRequest request = EntityRequest.builder()
                .title("Тестовая сущность")
                .verified(true)
                .addition(addition)
                .importantNumbers(importantNumbers)
                .build();

        Long createdId = testDataHelper.createTestEntity(request);

        assertNotNull(createdId);
        assertTrue(createdId > 0);
        System.out.println("Создана сущность с ID: " + createdId);

    }
}
