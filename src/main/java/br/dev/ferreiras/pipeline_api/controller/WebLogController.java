package br.dev.ferreiras.pipeline_api.controller;

import br.dev.ferreiras.pipeline_api.dto.UserDataDto;
import br.dev.ferreiras.pipeline_api.dto.WebLogEvent;
import br.dev.ferreiras.pipeline_api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v2")
public class WebLogController {


    private final UserService userService;

    public WebLogController(UserService userService) {
        this.userService = userService;
    }

    private static final Logger logger = LoggerFactory.getLogger(WebLogController.class);

    @PostMapping("/click")
    public ResponseEntity<Void> getActions(@RequestBody WebLogEvent webLogEvent) {

        logger.info("Payload: {}", webLogEvent);
        return ResponseEntity.ok().build();
    }
/**
 * {
 *     "timestamp": "2025-05-27T01:37:31.103Z",
 *     "buttonId": "loginButton",
 *     "userAgent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36",
 *     "cookieData": {
 *         "_pk_id.1.8765": "aa6ffbee4f6a63f1.1746475570."
 *     },
 *     "formData": {
 *         "username": "guacadmin",
 *         "email": "rnhc1000@gmail.com",
 *         "address": "Rua Aquiles Lisboa, 76",
 *         "country": "BR"
 *     }
 * }
 */

}
