package upp.demo.elastic.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import upp.demo.dto.Coordinates;
import upp.demo.dto.Coordinate;
import upp.demo.model.User;

@Service
public class GeolocationService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiURL = "http://api.positionstack.com/v1/forward";
    private final String apiKey = "836321488646472ead5a7633b7c04878";

    public Coordinate getLocation(User beta) {
        String requestUrl = String
                .format("%s?access_key=%s&query=%s&limit=1", apiURL, apiKey, String.format("%s, %s", beta.getCity(), beta.getCountry()));
        try {
            ResponseEntity<Coordinates> coordinate = restTemplate.getForEntity(requestUrl, Coordinates.class);
            return coordinate.getBody().getData().get(0);

        } catch (Exception e) {
            System.out.println("Failed to get coordinates, setting default values");
            return new Coordinate(45.0, 45.0);
        }
    }

}
