package endPoints;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import payload.User;
import routes.Routes;

import static io.restassured.RestAssured.given;

public class UserEndpoints {


    public static Response createUser(User userpayload){
        return given()
               .contentType(ContentType.JSON)
               .accept(ContentType.JSON)
               .body(userpayload)
               .when().post(Routes.post_user);
 }

    public static Response getUserByName(String username){
        return given().contentType(ContentType.JSON)
             .accept(ContentType.JSON)
             .pathParam("username",username)
             .when().get(Routes.get_user);
 }

    public static Response upadteUser(String username,User user){
        return given().
                contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("username",username)
                .body(user)
            .when().put(Routes.update_user);
 }

    public static Response deleteUser(String username){
   Response response=given().contentType(ContentType.JSON)
           .accept(ContentType.JSON)
           .pathParam("username",username)
           .when().delete(Routes.delete_user);
           return response;
 }
}
