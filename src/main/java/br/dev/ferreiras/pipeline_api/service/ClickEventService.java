package br.dev.ferreiras.pipeline_api.service;

import br.dev.ferreiras.pipeline_api.dto.ClickEventDto;
import br.dev.ferreiras.pipeline_api.model.ClickEventEntity;
import br.dev.ferreiras.pipeline_api.model.FormDataEmbeddable;
import br.dev.ferreiras.pipeline_api.repository.ClickEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClickEventService {

    private final ClickEventRepository repository;
    private final ClickEventKafkaProducer kafkaProducer;

    public ClickEventService(ClickEventRepository repository, ClickEventKafkaProducer kafkaProducer) {
        this.repository = repository;
        this.kafkaProducer = kafkaProducer;
    }
    @Transactional
    public ClickEventEntity saveClickEvent(ClickEventDto dto) {
        ClickEventEntity entity = new ClickEventEntity();

        entity.setTimestamp(dto.getTimestamp());
        entity.setButtonId(dto.getButtonId());
        entity.setUserAgent(dto.getUserAgent());
        entity.setCookieData(dto.getCookieData());
        entity.setReferer(dto.getReferer());
        entity.setCity(dto.getCity());
        entity.setRegion(dto.getRegion());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setCountryCode(dto.getCountryCode());
        entity.setClientIp(dto.getClientIp());

        FormDataEmbeddable form = new FormDataEmbeddable();
        form.setUsername(dto.getFormData().getUsername());
        form.setEmail(dto.getFormData().getEmail());
        form.setAddress(dto.getFormData().getAddress());
        form.setCountry(dto.getFormData().getCountry());

        entity.setFormData(form);


        ClickEventEntity saved = repository.save(entity);

        kafkaProducer.sendClickEvent(dto);

        return saved;
    }
}

