package api;

import dto.Entity;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
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

        List<Entity> entities = given()
                .spec(spec)
                .when()
                .get(config.getAll())
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getList("entity", Entity.class);

        assertNotNull(entities);

    }
}
