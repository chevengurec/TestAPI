package api;

import dto.EntityResponse;
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
@DisplayName("GET")
public class GetEntityByIDTestGET extends BaseTest {

    @Test
    @Story("Получение сущности")
    @Description("Позитивный тест: получение сущности по id")
    @DisplayName("Позитивный тест: получение сущности по id")
    void shouldGetEntityByID() {

        Long id1 = testDataHelper.createTestEntity();

        EntityResponse entity = given()
                .spec(spec)
                .pathParams("id", id1)
                .when()
                .get(config.get())
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getObject(".", EntityResponse.class);

        Long id2 = entity.getId();
        assertEquals(id1, id2);
    }

}
