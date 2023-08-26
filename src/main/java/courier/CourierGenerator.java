package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public static Courier randomCourier(){
        return new Courier()
                .withLogin(RandomStringUtils.randomAlphabetic(7))
                .withPassword(RandomStringUtils.randomAlphabetic(7))
                .withFirstName(RandomStringUtils.randomAlphabetic(7));
    }
    public static Courier randomCourierNoLogin(){
        return new Courier()
                .withPassword(RandomStringUtils.randomAlphabetic(7))
                .withFirstName(RandomStringUtils.randomAlphabetic(7));
    }
    public static Courier randomCourierNoPassword(){
        return new Courier()
                .withLogin(RandomStringUtils.randomAlphabetic(7))
                .withFirstName(RandomStringUtils.randomAlphabetic(7));
    }
    public static Courier randomCourierNoPasswordNoLogin() {
        return new Courier()
                .withFirstName(RandomStringUtils.randomAlphabetic(7));
    }
}
