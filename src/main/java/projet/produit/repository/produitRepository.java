package projet.produit.repository;


import org.springframework.data.repository.CrudRepository;

import projet.produit.model.Produit;

public interface produitRepository extends CrudRepository<Produit, Long>{

}
