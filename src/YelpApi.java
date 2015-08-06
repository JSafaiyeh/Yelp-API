/**
 * Created by Jason Safaiyeh Github: JSafaiyeh
 */

import org.json.JSONArray;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YelpApi {

    //Default search values
    private static final String API_HOST = "http://api.yelp.com";
    private static final String SEARCH_PATH = "/v2/search?";

    private Token accessToken;
    private OAuthService service;

    public YelpApi(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service = new ServiceBuilder()
                .provider(TwoStepOAuth.class)
                .apiKey(consumerKey)
                .apiSecret(consumerSecret)
                .build();

        this.accessToken = new Token(token, tokenSecret);
    }

    public ArrayList<Business> searchLocation(String location) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("location", location);
        String json = sendRequestAndGetResponse(request);
        return queryApi(json);
    }

    public ArrayList<Business> searchLocation(String location, HashMap<String, String> searchParameters) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("location", location);
        for (Map.Entry<String, String> entry : searchParameters.entrySet()) {
            request.addQuerystringParameter(entry.getKey(), entry.getValue());
        }
        String json = sendRequestAndGetResponse(request);
        return queryApi(json);
    }

    public ArrayList<Business> searchCoordinates(double latitude, double longitude) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("ll", String.valueOf(latitude) + "," + String.valueOf(longitude));
        String json = sendRequestAndGetResponse(request);
        return queryApi(json);
    }

    public ArrayList<Business> searchCoordinates(double latitude, double longitude, HashMap<String, String> searchParameters) {
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("ll", String.valueOf(latitude) + "," + String.valueOf(longitude));
        for (Map.Entry<String, String> entry : searchParameters.entrySet()) {
            request.addQuerystringParameter(entry.getKey(), entry.getValue());
        }
        String json = sendRequestAndGetResponse(request);
        return queryApi(json);
    }

    private OAuthRequest createOAuthRequest(String path) {
        return new OAuthRequest(Verb.GET, API_HOST + path);
    }

    private String sendRequestAndGetResponse(OAuthRequest request) {
        this.service.signRequest(this.accessToken, request);
        Response response = request.send();
        return response.getBody();
    }

    private ArrayList<Business> queryApi(String json) {

        ArrayList<Business> businesses = new ArrayList<>();

        JSONObject obj = new JSONObject(json);
        JSONArray businessesArray = obj.getJSONArray("businesses");

        for (int i = 0; i < businessesArray.length(); i++) {
            JSONObject curObj = businessesArray.getJSONObject(i);
            String name = curObj.getString("name");

            JSONArray address = curObj.getJSONObject("location").getJSONArray("display_address");
            List<String> addressList = new ArrayList<>();
            for (int j = 0; j < address.length(); j++) {
                addressList.add(address.getString(j));
            }

            Business business = new Business(name, addressList);
            business.setPhotoUrl(curObj.getString("image_url"));
            business.setYelpMobileUrl(curObj.getString("mobile_url"));
            business.setReviewCount(curObj.getInt("review_count"));
            business.setRating(curObj.getDouble("rating"));
            business.setRatingSmallImgUrl(curObj.getString("rating_img_url_small"));
            business.setRatingMediumImgUrl(curObj.getString("rating_img_url"));
            business.setRatingLargeImgUrl(curObj.getString("rating_img_url_large"));

            try {
                business.setPhoneNumber(curObj.getString("display_phone"));
            } catch (Exception e) {
                System.err.println( name + " has no phone number");
            }

            try {
                business.setDistance(curObj.getDouble("distance"));
            } catch (Exception e) {
                System.err.println(name + " has no distance (coordinates not provided)");
            }

            try {
                business.setSnippetText(curObj.getString("snippet_text"));
            } catch (Exception e) {
                System.err.println(name + " has no snippet text.");
            }

            businesses.add(business);
        }


        return businesses;
    }
}
