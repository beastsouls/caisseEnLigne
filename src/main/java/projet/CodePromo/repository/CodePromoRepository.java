package projet.CodePromo.repository;

import org.springframework.data.repository.CrudRepository;

import projet.CodePromo.model.CodePromo;


public interface CodePromoRepository extends CrudRepository<CodePromo, Long>{
	public CodePromo findByCode(String code);
	
}
