package Connectors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class GetPlaceInformation {
    public static final String API_KEY = "AIzaSyC0FwITA3cBrD4SBMPW2OgkRqANzBskndU";
    public static final String LOCATION = " Singapore";

    public static String getPlaceInfo(String userInput) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=" + userInput + LOCATION + "&inputtype=textquery&fields=formatted_address%2Cname%2Crating%2Copening_hours%2Cgeometry&key=" + API_KEY)
                .method("GET", null)
                .build();
        String jsonStr = null;
        try {
            Response response = client.newCall(request).execute();
            jsonStr = response.body().string();
        } catch (IOException e) {
            System.out.print(e);
        }
        return jsonStr;
    }
}
