package projet.produit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Produit {
	
	@Id
	@GeneratedValue
	private long id = -1;
	
	@Size(min=2, max=30)
	private String name;
	
	private String Typeproduit;
	
	@NotNull
    @Min(1)
	private double Prix;
	
	@Size(min=2, max=3000)
	private String description;
	
	@NotNull
    @Min(0)
	private int stock;
	
//	private int qt;
	
	private int total =0;
	
	private int cpt;
	

	public int getCpt() {
		return cpt;
	}
	public void setCpt(int cpt) {
		this.cpt = cpt;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
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
	public String getTypeproduit() {
		return Typeproduit;
	}
	public void setTypeproduit(String typeproduit) {
		Typeproduit = typeproduit;
	}
	public double getPrix() {
		return Prix;
	}
	public void setPrix(double prix) {
		Prix = prix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Produit(String name, String typeproduit, double prix, String description, int stock) {
		this.name = name;
		Typeproduit = typeproduit;
		Prix = prix;
		this.description = description;
		this.stock = stock;
	}
	
	public Produit(){}
	
	public Produit(String name){
		this.name = name;
	}
	
//	public int getQt() {
//		return qt;
//	}
//	public void setQt(int qt) {
//		this.qt = qt;
//	}
	
}
