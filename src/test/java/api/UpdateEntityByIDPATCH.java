package api;

import dto.Entity;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@Epic("API Тестирование")
@Feature("CRUD операции")
@DisplayName("PATCH")
public class UpdateEntityByIDPATCH extends BaseTest {

    @Test
    @Story("Редактирование сущности")
    @Description("Позитивный тест: редактирование сущности")
    @DisplayName("Позитивный тест: редактирование сущности")
    void shouldUpdateEntityById() {

        Long id = testDataHelper.createTestEntity();

        Entity.Addition updAddition = Entity.Addition
                .builder()
                .additionalInfo("Поменяли эту инфу")
                .additionalNumber(1)
                .build();

        Entity updEntity = Entity
                .builder()
                .title("Меняем заголовок")
                .verified(false)
                .addition(updAddition)
                .importantNumbers(Arrays.asList(10, 20, 30, 40, 50))
                .build();

        given()
                .spec(spec)
                .pathParam("id", id)
                .body(updEntity)
                .patch(config.patch())
                .then()
                .statusCode(204);

        Entity newEntity = given()
                .spec(spec)
                .pathParams("id", id)
                .when()
                .get(config.get())
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getObject(".", Entity.class);

        assertFalse(newEntity.getVerified());
        assertEquals("Меняем заголовок", newEntity.getTitle());
        assertNotNull(newEntity.getAddition());
        assertNotNull(newEntity.getAddition().getId());
    }


}
