package api;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Epic("API Тестирование")
@Feature("CRUD операции")
@DisplayName("DELETE")
public class DeleteEntityByIDTestDELETE extends BaseTest{

    @Test
    @Story("Удаление сущности")
    @Description("Позитивный тест: удаление сущности")
    @DisplayName("Позитивный тест: удаление сущности")
    void shouldDeleteEntityByID() {
        Long id = testDataHelper.createTestEntity();

        given()
                .spec(spec)
                .pathParams("id", id)
                .delete(config.delete())
                .then()
                .statusCode(204);

        int statusCode = given()
                .spec(spec)
                .pathParam("id", id)
                .get(config.get())
                .then()
                .extract()
                .statusCode();

        assertEquals(500, statusCode, "При GET запросе удалённой сущности должен быть статус 404");

    }


}
