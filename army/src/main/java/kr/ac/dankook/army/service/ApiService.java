package kr.ac.dankook.army.service;

import kr.ac.dankook.army.dto.common.Coordinates;
import kr.ac.dankook.army.dto.request.LocationRequest;
import kr.ac.dankook.army.dto.response.ApiCertificateLocationResponse;
import kr.ac.dankook.army.dto.response.RouteResponse;
import kr.ac.dankook.army.exception.ApiJsonParsingException;
import kr.ac.dankook.army.exception.ErrorCode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {

    @Value("${api.tmap.key}")
    private String TMAP_APP_KEY;
    @Value("${api.tmap.address-to-local.url}")
    private String TMAP_ADDRESS_TO_LOCAL_URL;
    @Value("${api.kakao.local-key}")
    private String KAKAO_APP_KEY;
    @Value("${api.kakao.search-by-keyword.uri}")
    private String KAKAO_SEARCH_AROUND_URL;
    @Value("${api.tmap.car-route.url}")
    private String TMAP_CAR_ROUTE_URL;
    @Value("${api.tmap.road-route.url}")
    private String TMAP_ROAD_ROUTE_URL;


    public Coordinates getCoordinateByFullAddress(String address){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("appKey",TMAP_APP_KEY);
        httpHeaders.set("Content-Type","application/json");
        httpHeaders.set("Accept","application/json");
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(TMAP_ADDRESS_TO_LOCAL_URL)
                .queryParam("fullAddr", address)
                .build();
        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, entity,String.class);
        String body = response.getBody();
        try{
            JSONObject jsonObject = new JSONObject(body);
            JSONArray coordinateInfo = jsonObject.getJSONObject("coordinateInfo").getJSONArray("coordinate");
            JSONObject coordinateObject = (JSONObject) coordinateInfo.get(0);
            String latitude = coordinateObject.getString("newLon");
            String longitude = coordinateObject.getString("newLat");
            return new Coordinates(latitude,longitude);
        }catch(JSONException e){
            throw new ApiJsonParsingException(ErrorCode.API_JSON_PARSING_ERROR);
        }
    }

    private String getKakaoApiBodyString(Coordinates coordinates, String query){
        RestTemplate restTemplate = new RestTemplate();

        String apiKey = "KakaoAK " + KAKAO_APP_KEY;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",apiKey);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        // 근처 CBT 시험장 검색
        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(KAKAO_SEARCH_AROUND_URL)
                .queryParam("query",query)
                .queryParam("x",coordinates.getLatitude())
                .queryParam("y",coordinates.getLongitude())
                .queryParam("size",3)
                .queryParam("radius",20000)
                .build();
        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, entity,String.class);

        return response.getBody();
    }

    private List<ApiCertificateLocationResponse> parsingKakaoApiJsonData(JSONObject json){

        List<ApiCertificateLocationResponse> apiResultData = new ArrayList<>();
        try{
            JSONArray documents = json.getJSONArray("documents");
            for(Object placeObject : documents){
                JSONObject place = (JSONObject) placeObject;
                String placeLatitude = place.getString("x");
                String placeLongitude = place.getString("y");
                String placeName = place.getString("place_name");
                String addressName = place.getString("address_name");
                String phoneNumber = place.getString("phone");

                apiResultData.add(new ApiCertificateLocationResponse(
                        placeName,placeLatitude,placeLongitude,
                        addressName,phoneNumber
                ));
            }
        }catch(JSONException e){
            throw new ApiJsonParsingException(ErrorCode.API_JSON_PARSING_ERROR);
        }
        return apiResultData;
    }
    public List<ApiCertificateLocationResponse> searchAroundParkingPlace(Coordinates coordinates) {

        List<ApiCertificateLocationResponse> apiResultData;
        JSONObject json = new JSONObject(getKakaoApiBodyString(coordinates,"CBT"));

        apiResultData = parsingKakaoApiJsonData(json);
        if (apiResultData.isEmpty()){
            JSONObject reJson = new JSONObject(getKakaoApiBodyString(coordinates,"시험장"));
            apiResultData = parsingKakaoApiJsonData(reJson);
        }
        return apiResultData;
    }

    public RouteResponse getAllRouteInformation(LocationRequest location){

        int roadTotalTime = 0;
        int carTotalTime = 0;
        String distance = "";
        int taxiFare = 0;
        int totalFare = 0;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("appKey",TMAP_APP_KEY);
        httpHeaders.set("Content-Type","application/json");
        httpHeaders.set("Accept","application/json");
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        UriComponents roadUriComponents = UriComponentsBuilder
                .fromHttpUrl(TMAP_ROAD_ROUTE_URL)
                .queryParam("startX",location.getOriginX())
                .queryParam("startY",location.getOriginY())
                .queryParam("endX",location.getDestinationX())
                .queryParam("endY",location.getDestinationY())
                .queryParam("startName", "startLocation")
                .queryParam("endName","endLocation")
                .build();
        UriComponents carUriComponents = UriComponentsBuilder
                .fromHttpUrl(TMAP_CAR_ROUTE_URL)
                .queryParam("startX",location.getOriginX())
                .queryParam("startY",location.getOriginY())
                .queryParam("endX",location.getDestinationX())
                .queryParam("endY",location.getDestinationY())
                .queryParam("startName", "startLocation")
                .queryParam("endName","endLocation")
                .build();

        ResponseEntity<String> roadResponse = restTemplate.exchange(roadUriComponents.toString(), HttpMethod.GET, entity,String.class);
        ResponseEntity<String> carResponse = restTemplate.exchange(carUriComponents.toString(), HttpMethod.GET, entity,String.class);
        String roadDataBody = roadResponse.getBody();
        String carDataBody = carResponse.getBody();
        try{
            JSONObject roadJson = new JSONObject(roadDataBody);
            JSONArray roadJsonArray = roadJson.getJSONArray("features");
            JSONObject propertiesObject = roadJsonArray.getJSONObject(0);
            int totalTime = propertiesObject.getJSONObject("properties").getInt("totalTime");
            roadTotalTime = Math.round(totalTime / 60.0f);

            JSONObject carJson = new JSONObject(carDataBody);
            JSONArray carJsonArray = carJson.getJSONArray("features");
            propertiesObject = carJsonArray.getJSONObject(0).getJSONObject("properties");

            taxiFare = propertiesObject.getInt("taxiFare");
            carTotalTime = propertiesObject.getInt("totalTime") / 60;
            totalFare = propertiesObject.getInt("totalFare");
            int distanceByInteger = propertiesObject.getInt("totalDistance");
            if (distanceByInteger >= 1000){
                distanceByInteger = Math.round(distanceByInteger / 1000.f);
                distance = distanceByInteger+"km";
            }else {
                distance = distanceByInteger + "m";
            }
        }catch(JSONException e) {
            throw  new ApiJsonParsingException(ErrorCode.API_JSON_PARSING_ERROR);
        }
        return new RouteResponse(distance,taxiFare,carTotalTime,roadTotalTime,totalFare);
    }
}
