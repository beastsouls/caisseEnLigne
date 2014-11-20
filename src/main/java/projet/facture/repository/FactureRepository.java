package projet.facture.repository;

import org.springframework.data.repository.CrudRepository;

import projet.facture.model.FactureModel;

public interface FactureRepository extends CrudRepository<FactureModel, Long> {

}
