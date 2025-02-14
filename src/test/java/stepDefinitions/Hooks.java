package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

        // write a code that will give you place_id
        // execute this code only when place_id is null

        StepDefinition stepDefinition = new StepDefinition();

        if(StepDefinition.place_id == null){
            stepDefinition.add_place_payload_with("Emiliano", "Spanish", "canelones 2024");
            stepDefinition.user_calls_with_http_request("AddPlaceAPI", "POST");
            stepDefinition.verify_place_id_created_maps_to_using("Emiliano", "GetPlaceAPI");

        }
    }


}
