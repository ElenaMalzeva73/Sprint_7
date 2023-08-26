package order;


import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
@RunWith(Parameterized.class)
public class OrderCredsTest extends BaseOrderTest{
    private final List<String> color;
    public OrderCredsTest(List<String> color){
        this.color =  color;
    }
    @Parameterized.Parameters(name = "Цвет самоката: {0}")
    public static Object[][] getScooterColor(){
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GRAY")},
                {List.of("BLACK,GRAY")},
                {List.of()},
        };
    }
    @Test
    @DisplayName("Выбор цвета самоката при заказе")
    public void choiceOfScooterColorWhenPlacingAnOrder(){
        Order order = new Order(color);
        ValidatableResponse responseCreateOrder = orderCreds.createNewOrder(order);
        track = responseCreateOrder.extract().path("track");
        responseCreateOrder.assertThat()
                .statusCode(201)
                .body("track", is(notNullValue()));
    }
}