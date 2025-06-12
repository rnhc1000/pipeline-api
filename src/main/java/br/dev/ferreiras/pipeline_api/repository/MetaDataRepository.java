package br.dev.ferreiras.pipeline_api.repository;

import br.dev.ferreiras.pipeline_api.model.MetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaDataRepository  extends JpaRepository<MetaData, Long> {
}
