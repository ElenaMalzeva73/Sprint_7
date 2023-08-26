package courier;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import yandex.constant.ApiScooter;
import static courier.CourierCreds.credsFrom;
import static courier.CourierGenerator.randomCourier;
import static org.junit.Assert.assertEquals;

public class LoginCourierTest  {
     CourierCreds  courierCreds;
     private CreatingCourier creatingCourier;
    private Courier courier;
    String id;

   @Before
   @Step("Создание тестовых данных")
    public void setUp() {
        RestAssured.baseURI = ApiScooter.BASE_URL;
        creatingCourier = new CreatingCourier();
        courier = randomCourier();
        Response response = creatingCourier.create(courier);
        assertEquals(id,HttpStatus.SC_CREATED,response.statusCode());
    }
    @Test
    @DisplayName("Успешный логин")
    public void courierLoginOkValidData() {
        Response responseCourierLogin = creatingCourier.courierLogin(credsFrom(courier));
         assertEquals("ok",HttpStatus.SC_OK,responseCourierLogin.statusCode());
    }
    @Test
    @DisplayName("Запрос без пароля")
    public void courierLoginEmptyPassword(){
        CourierCreds courierCredsEmptyPassword = new CourierCreds(courier.getLogin(), "");
        courierCreds = credsFrom(courier);
        Response response  = creatingCourier.courierLogin(courierCredsEmptyPassword);
        assertEquals("Недостаточно данных для входа",HttpStatus.SC_BAD_REQUEST,response.statusCode());
    }
    @Test
    @DisplayName("Запрос без логина")
    public void courierLoginEmptyLogin(){
        CourierCreds courierCredsEmptyLogin = new CourierCreds("", courier.getPassword());
        courierCreds = credsFrom(courier);
        Response response  = creatingCourier.courierLogin(courierCredsEmptyLogin);
        assertEquals("Недостаточно данных для входа",HttpStatus.SC_BAD_REQUEST,response.statusCode());


    }

    @Test
    @DisplayName("Запрос с несуществующей парой логин-пароль")
    public void courierLoginNoCreateLoginPassword() {
        CourierCreds courierCredsNoCreateLoginPassword = new CourierCreds("fghgfghfg", "1325");
        courierCreds = credsFrom(courier);
        Response response = creatingCourier.courierLogin(courierCredsNoCreateLoginPassword);
        assertEquals("Учетная запись не найдена", HttpStatus.SC_NOT_FOUND, response.statusCode());
    }

    @After
    @Step("Удаления курьера")
    public void tearDown() {
        CreatingCourier.courierDelete(id);
    }
}
