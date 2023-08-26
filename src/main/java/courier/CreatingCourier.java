package courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import yandex.constant.ApiScooter;

import static io.restassured.RestAssured.given;

public class CreatingCourier {
    @Step("Создание курьера")
    public  Response create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(ApiScooter.POST_COURIER_CREATE);

    }
    @Step("Логин курьера в системе")
    public  Response courierLogin(CourierCreds creds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(creds)
                .when()
                .post (ApiScooter.POST_COURIER_LOGIN);


    }
    @Step("Удаление курьера")
    public static void courierDelete(String id) {
        if (id != null)
            given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(id)
                    .when()
                    .delete(ApiScooter.DELETE_COURIER + id);
    }

}

