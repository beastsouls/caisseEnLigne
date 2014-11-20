package projet.client.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Client {
	
	@Id
	@GeneratedValue
	private long id = -1;
	
	@Size(min=2, max=30)
	private String name;
	
	@Size(min=2, max=30)
	private String role;
	
	@Size(min=2, max=30)
	private String compagnie;
	
	@NotEmpty(message="Email obligatoire")
	@Email
	private String Email;
	
	@Size(min=2, max=30)
	private String Adresse;
	
	public long getId() {
		return id;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getCompagnie() {
		return compagnie;
	}
	public void setCompagnie(String compagnie) {
		this.compagnie = compagnie;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String adresse) {
		Adresse = adresse;
	}
	
	
	public  Client(String name, String role, String compagnie, String email, String adresse)
	{
		this.role=role;
		this.Adresse=adresse;
		this.compagnie=compagnie;
		this.name=name;
		this.Email=email;
	}
	
	
	public  Client() {}

	
}
