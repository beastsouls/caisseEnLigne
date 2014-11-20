package projet.client.repository;

import org.springframework.data.repository.CrudRepository;

import projet.CodePromo.model.CodePromo;
import projet.client.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{
	public Client findByName(String name);
}
