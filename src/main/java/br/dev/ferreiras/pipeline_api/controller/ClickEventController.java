package br.dev.ferreiras.pipeline_api.controller;

import br.dev.ferreiras.pipeline_api.dto.ClickEventDto;
import br.dev.ferreiras.pipeline_api.model.ClickEventEntity;
import br.dev.ferreiras.pipeline_api.service.ClickEventService;
import br.dev.ferreiras.pipeline_api.service.GeoLocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "Based on a web form, collect events performed by an user, such as clicks, data, etc ",
            description = "Putting in place a baseline to collect web analytics ",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Events processed", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClickEventDto.class))),
                    @ApiResponse(responseCode = "401", description = "Not authorized!", content = @Content),
                    @ApiResponse(responseCode = "422", description = "Operation not allowed!", content = @Content),
            })
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

