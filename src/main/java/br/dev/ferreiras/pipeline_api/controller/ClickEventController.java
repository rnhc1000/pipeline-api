package br.dev.ferreiras.pipeline_api.controller;

import br.dev.ferreiras.pipeline_api.dto.ClickEventDto;
import br.dev.ferreiras.pipeline_api.model.ClickEventEntity;
import br.dev.ferreiras.pipeline_api.service.ClickEventService;
import br.dev.ferreiras.pipeline_api.service.GeoLocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v2")
public class ClickEventController {

    private final ClickEventService service;
    private final GeoLocationService geoLocationService;

    private static final Logger logger = LoggerFactory.getLogger(ClickEventController.class);

    public ClickEventController(ClickEventService service, GeoLocationService geoLocationService) {
        this.service = service;
        this.geoLocationService = geoLocationService;
    }

    @PostMapping("/click-events")
    public ResponseEntity<?> handleClickEvent(@RequestBody ClickEventDto event, HttpServletRequest request) {

        String clientIp = extractClientIp(request);

        logger.info("::: Client IP -> {} :::", clientIp);
        event.setClientIp(clientIp);

        geoLocationService.enrichWithGeoLocation(event);

        ClickEventEntity saved = service.saveClickEvent(event);
        return ResponseEntity.ok(saved);
    }

    private String extractClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0]; // proxy/load balancer support
        }
        return request.getRemoteAddr();
    }

}

