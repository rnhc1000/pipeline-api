package br.dev.ferreiras.pipeline_api.dto;

import java.util.List;

public record UserDataDto(
        String name,
        String email,
        String address,
        String country,
        List<MetaDataDto> meta) {
}
