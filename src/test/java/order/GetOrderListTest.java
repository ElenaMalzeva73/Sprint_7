package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTest extends BaseOrderTest {
    @Test
    @DisplayName("Получение списка заказов")
    public void getOrderList(){
         orderCreds = new OrderCreds();
        ValidatableResponse responseOrderList = orderCreds.getOrderList();
        responseOrderList.assertThat()
                .statusCode(200)
                .body("orders",notNullValue());
    }
}
