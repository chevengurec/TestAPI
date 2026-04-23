package api;

import config.Configuration;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import utils.TestDataHelper;

public class BaseTest {

    protected static final Configuration config = ConfigFactory.create(Configuration.class, System.getenv());

    protected static final RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri(config.baseUrl())
            .setContentType(ContentType.JSON)
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .addFilter(new AllureRestAssured())
            .build();
    protected static final TestDataHelper testDataHelper = new TestDataHelper(spec, config.create());
}
