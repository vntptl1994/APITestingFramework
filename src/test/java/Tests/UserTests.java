package Tests;

import com.github.javafaker.Faker;
import endPoints.UserEndpoints;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import payload.User;
import utilities.ExtentManager;

public class UserTests {
    Faker faker;
    User user;
    Logger logger=LogManager.getLogger(UserTests.class);
    @BeforeSuite
    public void beforesuite(){
        ExtentManager.setExtent();
        }
    @BeforeTest
    public void setupUser(){
        faker=new Faker();
        user=new User();
        user.setId(faker.idNumber().hashCode());
        user.setEmail(faker.internet().safeEmailAddress());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setUsername(faker.name().username());
        user.setPassword(faker.internet().password(5,10));
        user.setPhone(faker.phoneNumber().cellPhone());
        logger.info("*******Generating Fake data***********");
    }
    @Test(priority = 1)
    public void testPostUser(){
       Response response=UserEndpoints.createUser(user);
       response.then().log().all();
       Assert.assertEquals(response.getStatusCode(),200);
       logger.info("************Creating new user***********");
    }
    @Test(priority = 2)
    public void getUser(){
        Response response=UserEndpoints.getUserByName(this.user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("**********getting single user by name*********8");
    }
    @Test(priority = 3)
    public void updateUser(){
        Response response=UserEndpoints.upadteUser(this.user.getUsername(),user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("**************updating user**********");
    }
    @Test(priority = 4)
    public void deleteUser(){
        Response response=UserEndpoints.deleteUser(user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("**************deleting user**********8");
    }
    @AfterSuite
    public void aftersuite(){

        ExtentManager.endReport();

    }
}
