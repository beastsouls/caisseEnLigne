package projet.produit.model;


public class ProduitQuantity {
	
	private Produit elementPanier;
	private int quantity = 0;
	private double somme = 0;
	private double sommeTotalFacture = 0; 
	
	
	public double getSommeTotalFacture() {
		return sommeTotalFacture;
	}
	public void setSommeTotalFacture(double sommeTotalFacture) {
		this.sommeTotalFacture = sommeTotalFacture;
	}
	public double getSomme() {
		return somme;
	}
	public void setSomme(double somme) {
		this.somme = somme;
	}
	public Produit getElementPanier() {
		return elementPanier;
	}
	public void setElementPanier(Produit elementPanier) {
		this.elementPanier = elementPanier;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
