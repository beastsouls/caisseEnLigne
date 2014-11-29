package projet.facture.model;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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

	
	private double montant;
	private double montantTTC;
	private double tva;
	
	

	private String moyen;
	private String Nomclient;
	private Date datecommande;
	private double reduction;
	private double totalavantreduction;
	

	private ArrayList<String> mesproduits = new ArrayList<String>();
	private ArrayList<String> listeproduis = new ArrayList<String>();
	private ArrayList<String> listeqtt = new ArrayList<String>();
	private ArrayList<String> listemontant = new ArrayList<String>();
	private ArrayList<String> listeprix = new ArrayList<String>();
	private ArrayList<Long> listeid = new ArrayList<Long>();
	
	
	public double getTotalavantreduction() {
		return totalavantreduction;
	}

	public void setTotalavantreduction(double totalavantreduction) {
		this.totalavantreduction = totalavantreduction;
	}

	public double getReduction() {
		return reduction;
	}

	public void setReduction(double reduction) {
		this.reduction = reduction;
	}
	
	public ArrayList<Long> getListeid() {
		return listeid;
	}

	public void setListeid(Long listeid) {
		this.listeid.add(listeid);
	}

	public double getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(double d) {
		//DecimalFormat df = new DecimalFormat("0.00");
		this.montantTTC =d;
	}

	public Date getDatecommande() {
		return datecommande;
	}

	public void setDatecommande(Date date) {
		this.datecommande = date;
	}

	public ArrayList<String> getListeproduis() {
		return listeproduis;
	}

	public void setListeproduis(String listeproduis) {
		this.listeproduis.add(listeproduis);
	}

	public ArrayList<String> getListeqtt() {
		return listeqtt;
	}

	public void setListeqtt(String listeqtt) {
		this.listeqtt.add(listeqtt);
	}

	public ArrayList<String> getListemontant() {
		return listemontant;
	}

	public void setListemontant(String listemontant) {
		this.listemontant.add(listemontant);
	}

	public ArrayList<String> getListeprix() {
		return listeprix;
	}

	public void setListeprix(String listeprix) {
		this.listeprix.add(listeprix) ;
	}


	public String getNomclient() {
		return Nomclient;
	}

	public void setNomclient(String nomclient) {
		Nomclient = nomclient;
	}

	// moyen de paiement

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

	

	public double getTva() {
		return tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

}
