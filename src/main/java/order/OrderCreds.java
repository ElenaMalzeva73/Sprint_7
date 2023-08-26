package order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import yandex.constant.ApiScooter;
import static io.restassured.RestAssured.given;

public class OrderCreds {
    @Step("Создание заказа")
    public ValidatableResponse createNewOrder(Order createOrder) {
        return given()
                .header("Content-type", "application/json")//requestSpec()
                .body(createOrder)
                .when()
                .post(ApiScooter.ORDER_POST_CREATE)
                .then();
    }
    @Step("Получение списка заказов")
    public  ValidatableResponse getOrderList() {
        return given()//requestSpec()
                .when()
                .get(ApiScooter.ORDER_POST_CREATE )
                .then();
    }
    @Step("Отмена заказа")
    public void cancelOrder(int track){
        given()//requestSpec()
                .body(track)
                .when()
                .put(ApiScooter.PUT_ORDER_CANCEL)
                .then();

    }

}


