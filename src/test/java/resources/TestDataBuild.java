package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {


    public AddPlace addPlacePayload(String name, String language, String address) {
        // Add Place Payload

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setWebsite("https://rahulshettyacademy.com");
        addPlace.setName(name);
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        addPlace.setTypes(myList);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);
        return addPlace;
    }


public String deletePlacePayload(String place_id) {
    // Delete Place Payload

    return "{\r\n  \"place_id\": \"" + place_id + "\"\r\n}";
}
}
