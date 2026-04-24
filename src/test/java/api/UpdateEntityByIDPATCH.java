package api;

import dto.EntityRequest;
import dto.EntityResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

        EntityRequest.Addition updAddition = EntityRequest.Addition
                .builder()
                .additionalInfo("Поменяли эту инфу")
                .additionalNumber(1)
                .build();

        EntityRequest updEntity = EntityRequest
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

        EntityResponse newEntity = given()
                .spec(spec)
                .pathParams("id", id)
                .when()
                .get(config.get())
                .then()
                .statusCode(200)
                .extract()
                .jsonPath().getObject(".", EntityResponse.class);

        assertFalse(newEntity.getVerified());
        assertEquals("Меняем заголовок", newEntity.getTitle());
        assertNotNull(newEntity.getAddition());
        assertNotNull(newEntity.getAddition().getId());
    }


}
