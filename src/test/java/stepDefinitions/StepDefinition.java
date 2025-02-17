package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;



public class StepDefinition extends Utils {
    RequestSpecification requestSpecification1;
    ResponseSpecification responseSpecification;
    Response response;
    TestDataBuild testDataBuild = new TestDataBuild();
    static String place_id;




    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        requestSpecification1 = given().spec(requestSpecification())
                .body(testDataBuild.addPlacePayload(name, language, address));
    }



    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {

        // Constructor will be called with value of resource which you pass
        APIResources apiResources = APIResources.valueOf(resource);
        System.out.println(apiResources.getResource());

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200).
                expectContentType(ContentType.JSON)
                .build();

        if (requestSpecification1 == null) {
            throw new IllegalStateException("Request specification is not initialized.");
        }

        if(httpMethod.equals("POST")){
            response =  requestSpecification1.when().post(apiResources.getResource());
        }else if(httpMethod.equals("GET")){
            response =  requestSpecification1.when().get(apiResources.getResource());
        }

        if (response == null) {
            throw new IllegalStateException("Response is null.");
        }
    }




    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(200, response.getStatusCode());

      }



    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(expectedValue, getJsonPath(response, key));
    }


    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {

        // request spec
        place_id = getJsonPath(response, "place_id");

        requestSpecification1 = given().spec(requestSpecification())
                .queryParam("place_id", place_id);

        user_calls_with_http_request(resource, "GET");
        String actualName = getJsonPath(response, "name");
        Assert.assertEquals(expectedName, actualName);

    }




    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions
        requestSpecification1 = given().spec(requestSpecification())
                .body(testDataBuild.deletePlacePayload(place_id));

    }

















}
