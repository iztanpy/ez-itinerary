package Parsers;

import Classes.Locations;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;


public class Parser {
    public static Locations parse(String jsonStr) {
        String name = null;
        Double latitude = null;
        Double longtitude = null;

        try {
            Map<String, Object> mapping = new ObjectMapper().readValue(jsonStr, HashMap.class);
            List<Object> details = (List) mapping.get("candidates");
            // get the first search result
            Map<String, Object> information = (Map) details.get(0);
            name = (String) information.get("name");
            Map<String, Object> location = (Map) information.get("geometry");
            Map<String, Object> latLong = (Map) location.get("location");
            latitude = (Double) latLong.get("lat");
            longtitude = (Double) latLong.get("lng");
        }
        catch (IOException e) {
            System.out.print(e);
        }

        return new Locations(name, latitude, longtitude);
    }
}
