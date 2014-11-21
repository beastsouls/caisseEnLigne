package projet.facture.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FactureModel {
	
	

	@Id
	@GeneratedValue
	private long id = -1;

//	private String panier;

	// montant de la caisse
	private double montant;
	private  String Nomclient;
	private double tva;
	private double prixht;
	

	public String getNomclient() {
		return Nomclient;
	}

	public void setNomclient(String nomclient) {
		Nomclient = nomclient;
	}

	// moyen de paiement
	
	private String moyen;
	private ArrayList<Integer> quantite=new ArrayList<Integer>();
	private ArrayList<String> mesproduits = new ArrayList<String>();
	private ArrayList<String> prod=new ArrayList<String>();
	private ArrayList<Double> prixUnitaire=new ArrayList<Double>();
	
	

	public ArrayList<String> getMesproduits() {
		return mesproduits;
	}

	public void setMesproduits(ArrayList<String> mesproduits) {
		this.mesproduits = mesproduits;
	}

	public void maliste(String prod) {

		ArrayList<String> mesprod = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(prod, "*");
		String str = st.nextToken();
		mesprod.add(str);

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double d) {
		this.montant = d;
	}

	public String getMoyen() {
		return moyen;
	}

	public void setMoyen(String moyen) {
		this.moyen = moyen;
	}

	public ArrayList<Integer> getQuantite() {
		return quantite;
	}

	public void setQuantite(ArrayList<Integer> quantite) {
		this.quantite = quantite;
	}

	public ArrayList<String> getProd() {
		return prod;
	}

	public void setProd(ArrayList<String> prod) {
		this.prod = prod;
	}

	public double getTva() {
		return tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

	public double getPrixht() {
		return prixht;
	}

	public void setPrixht(double prixht) {
		this.prixht = prixht;
	}

	public ArrayList<Double> getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(ArrayList<Double> prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	
	
	

	

//	public String getPanier() {
//		return panier;
//	}
//
//	public void setPanier(String panier) {
//		this.panier = panier;
//	}

}
