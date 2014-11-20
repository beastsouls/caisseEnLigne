package projet.employe.repository;

import org.springframework.data.repository.CrudRepository;

import projet.employe.model.Employe;

public interface EmployeRepository extends CrudRepository<Employe, Long>{

}
