package basesetup;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;

public class BaseSetup {

    public static RequestSpecification spec;

    @BeforeSuite(groups = "smoke")
    public void setup() {
        switch (ConfigReader.getProperty("environment")) {
            case "prod":
                spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("baseUrl")).build();
                break;
            case "preprod":
                spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("preprodBaseUrl")).build();

                break;

            case "uat":
                spec = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperty("uatBaseUrl")).build();

                break;

            default:
        }

    }


}


