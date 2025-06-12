package br.dev.ferreiras.pipeline_api.dto;

import java.time.Instant;

public record MetaDataDto(
        String cookieKey,
        String cookieValue,
        Instant timeStamp,
        String buttonId,
        String userAgent) {
}
