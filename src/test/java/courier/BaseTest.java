 package courier;
import org.junit.After;
import org.junit.Before;
import io.restassured.RestAssured;
import yandex.constant.ApiScooter;

public class BaseTest {
    String id;
    @Before
    public void setUp() {
        RestAssured.baseURI = ApiScooter.BASE_URL;
    }
    @After
    public void tearDown() {
        CreatingCourier.courierDelete(id);
    }
}

