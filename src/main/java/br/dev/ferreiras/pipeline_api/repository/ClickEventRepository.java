package br.dev.ferreiras.pipeline_api.repository;

import br.dev.ferreiras.pipeline_api.model.ClickEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEventEntity, Long> {
}
