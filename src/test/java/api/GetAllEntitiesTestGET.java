package api;

import dto.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("API Тестирование")
@Feature("CRUD операции")
@DisplayName("GET")
public class GetAllEntitiesTestGET extends BaseTest {

    @Test
    @Story("Получение всех сущностей")
    @Description("Позитивный тест: получение сущностей")
    @DisplayName("Позитивный тест: получение сущностей")
    void shouldGetAllEntitys() {

        List<EntityResponse> entities = given()
                .spec(spec)
                .when()
                .get(config.getAll())
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("entity", EntityResponse.class);

        assertNotNull(entities);

    }
}
