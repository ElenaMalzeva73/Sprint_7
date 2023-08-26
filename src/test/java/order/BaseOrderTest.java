package order;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import yandex.constant.ApiScooter;

public class BaseOrderTest {
    protected OrderCreds orderCreds;
    protected int track;
    @Before
    public void setUP(){
        RestAssured.baseURI = ApiScooter.BASE_URL;
        orderCreds = new OrderCreds();

    }
    @After
    public  void tearDown() {
        orderCreds.cancelOrder(track);
    }
}
