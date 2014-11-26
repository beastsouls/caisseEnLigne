package projet.CodePromo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CodePromo {
	
	@Id
	@GeneratedValue
	private long id = -1;
	
	
	@NotNull
	private int montant;
	
	@NotNull
	@DateTimeFormat(style = "S-" , iso = DateTimeFormat.ISO.NONE)
	private Date debut;
	
	@NotNull
	@DateTimeFormat(style = "S-" , iso = DateTimeFormat.ISO.NONE)
	private Date fin;
	
	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDebutFormat()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(debut);
	}
	
	public String getFinFormat()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(fin);
	}

	@NotEmpty
	@NotNull
	@Size(min=7, max=7)
	private String code;
	
	public  CodePromo() {}

	public CodePromo(int montant, Date debut, Date fin, String code) throws ParseException {
		super();
		
		this.montant = montant;
		this.debut = debut;
		this.fin =  fin;
		this.code = code;
	}

	
}
