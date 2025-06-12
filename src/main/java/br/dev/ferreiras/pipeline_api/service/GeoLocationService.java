package br.dev.ferreiras.pipeline_api.service;

import br.dev.ferreiras.pipeline_api.dto.ClickEventDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GeoLocationService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void enrichWithGeoLocation(ClickEventDto event) {
        String ip = event.getClientIp();

        if (ip == null || ip.startsWith("192.") || ip.startsWith("127.")) {
            event.setCity("Imperatriz");
            event.setRegion("MA");
            event.setLatitude(5.5238);
            event.setLongitude(47.4762);
            event.setCountryCode("BR");

            return;
        }

        try {
            String url = "https://ip-api.com/json/" + ip;
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                Map<?, ?> data = response.getBody();
                assert data != null;
                event.setCountryCode((String) data.get("countryCode"));
                event.setCity((String) data.get("city"));
                event.setRegion((String) data.get("regionName"));
                event.setLatitude((Double) data.get("lat"));
                event.setLongitude((Double) data.get("lon"));
            }
        } catch (Exception ex) {
            System.err.println("Failed to get geolocation: " + ex.getMessage());
        }
    }
}
