package projet.caisse.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projet.CodePromo.model.CodePromo;
import projet.CodePromo.repository.CodePromoRepository;
import projet.client.model.Client;
import projet.client.repository.ClientRepository;
import projet.facture.model.FactureModel;
import projet.facture.repository.FactureRepository;
import projet.produit.model.Produit;
import projet.produit.model.ProduitQuantity;
import projet.produit.repository.produitRepository;

@Controller
public class Controlleur {
	@Autowired
	private produitRepository produitRepository;

	private Map<Long, ProduitQuantity> panierListe;
	private double total = 0;
	private int message = 2;
	private Map<Long, ProduitQuantity> facture;
	private double prixht = 0;
	private double totale = 0;
	double tva;
	private boolean valpourstock = false;
	private int tmp = 0;
	private Map<String, Double>  file = new LinkedHashMap<String, Double>();
	//contient le prix par produit
	private Map<Long, Integer>  prodPrix = new LinkedHashMap<Long, Integer>();
	
	//valuer contenant le stock total
	private int stock = 0; 

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private FactureRepository factureRepository;

	@Autowired
	private CodePromoRepository CPrepository;

	@RequestMapping(value = "/caisse", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("products", produitRepository.findAll());
		model.addAttribute("clients", clientRepository.findAll());
		model.addAttribute("codePromos", CPrepository.findAll());
		model.addAttribute("product", new Produit());
		
		List<Produit> produitsBoisson = new ArrayList<Produit>();
		for(Produit p : produitRepository.findAll()){
			if(p.getTypeproduit().compareToIgnoreCase("Boisson") == 0)
				produitsBoisson.add(p);
		}
		model.addAttribute("produitsBoisson", produitsBoisson);
		
		List<Produit> produitsNourriture = new ArrayList<Produit>();
		for(Produit p : produitRepository.findAll()){
			if(p.getTypeproduit().compareToIgnoreCase("Nourriture") == 0)
				produitsNourriture.add(p);
		}
		model.addAttribute("produitsNourriture", produitsNourriture);
		
		List<Produit> produitsElectro = new ArrayList<Produit>();
		for(Produit p : produitRepository.findAll()){
			if(p.getTypeproduit().compareToIgnoreCase("Electroménager") == 0)
				produitsElectro.add(p);
		}
		model.addAttribute("produitsElectro", produitsElectro);
		
		List<Produit> produitsVetement = new ArrayList<Produit>();
		for(Produit p : produitRepository.findAll()){
			if(p.getTypeproduit().compareToIgnoreCase("Vetements") == 0)
				produitsVetement.add(p);
		}
		model.addAttribute("produitsVetement", produitsVetement);
		
		List<Produit> produitsAutre = new ArrayList<Produit>();
		for(Produit p : produitRepository.findAll()){
			if(p.getTypeproduit().compareToIgnoreCase("Autre") == 0)
				produitsAutre.add(p);
		}
		model.addAttribute("produitsAutre", produitsAutre);
		
		
		
		return "caisse";
	}

	@RequestMapping(value = "/caisse", method = RequestMethod.POST)
	public String productSubmit(@ModelAttribute Produit product,
			HttpSession session, Model model) {
		List<Produit> panier = (List<Produit>) session.getAttribute("panier");
		if (panier == null)
			panier = new ArrayList<Produit>();
		panier.add(product);
		session.setAttribute("panier", panier);
		return "redirect:/caisse";
	}

	// Réponse au clic pour ajouter un produit au panier
	@RequestMapping(value = "/ajoutPanier", method = RequestMethod.GET)
	public String boutonProduit(@RequestParam("id") Long id, Model model) {
		model.addAttribute("produit", produitRepository.findOne(id));
		return "caisse";
	}

	@RequestMapping(value = "/ajoutPanier", method = RequestMethod.POST)
	public String produitSubmit(@ModelAttribute("id") Produit produit,
			HttpSession session, Model model) {

		panierListe = (Map<Long, ProduitQuantity>) session.getAttribute("panierListe");
		if (panierListe == null) {
			panierListe = new LinkedHashMap<Long, ProduitQuantity>();
		}

		if (!panierListe.containsKey(produit.getId())) {
			ProduitQuantity Pquantity = new ProduitQuantity();
			Pquantity.setElementPanier(produit);
			Pquantity.setQuantity(0);
			panierListe.put(Pquantity.getElementPanier().getId(), Pquantity);
		}

		session.setAttribute("panierListe", panierListe);
		return "redirect:/caisse";
	}
	
	
	

	@RequestMapping(value = "/calcul", method = RequestMethod.POST)
	public String calculpanierSubmit(@RequestParam("qt") int qt,@RequestParam("id") Long id, Model model, HttpSession session,Produit produit) {
		////System.out.println("QT " + qt);
		panierListe = (Map<Long, ProduitQuantity>) session.getAttribute("panierListe");
		ProduitQuantity prodQuantity = panierListe.get(id);
		
		//valuer contenant le stock total
		stock = prodQuantity.getElementPanier().getStock();
		String name  = prodQuantity.getElementPanier().getName();
		double prix = prodQuantity.getElementPanier().getPrix();
		
		if(qt<1 || qt > stock)
		{return "redirect:/caisse";}
		
		if (prodPrix.containsKey(id)) {
			prodPrix.remove(id);
		}
		
		prodPrix.put(id,qt);
		
		total = 0;
		for(Long l : prodPrix.keySet()){
			Integer quantite = prodPrix.get(l);
			Double price = panierListe.get(l).getElementPanier().getPrix();
			Double somme = price*quantite;
				
			prodQuantity.getElementPanier().setTmpstock(stock-quantite);
			prodQuantity.setTmpquantity(quantite);			
			prodQuantity.setTmpsomme(somme);
			
			total = total + somme;
		}
	
		prixht = total;
		tva = prixht * 0.20;
		prodQuantity.setSommeTotalFacture(tva); // attention
		session.setAttribute("total", total);
		//session.setAttribute("stock", messtock);

		session.setAttribute("panierListe", panierListe);
		return "redirect:/caisse";
	}

	
	
	
	@RequestMapping(value = "/resetPanier", method = RequestMethod.POST)
	public String resetpanierSubmit(Model model, HttpSession session) {
		// System.out.println(id);
		panierListe = (Map<Long, ProduitQuantity>) session
				.getAttribute("panierListe");
		panierListe = new LinkedHashMap<Long, ProduitQuantity>();
		prodPrix = new LinkedHashMap<Long, Integer>();
		
		total = 0;
		totale = 0;
		
		session.setAttribute("panierListe", panierListe);
		session.setAttribute("total", total);
		return "redirect:/caisse";
	}

	@RequestMapping(value = "/validCode", method = RequestMethod.GET)
	public String testCodeSubmit(@RequestParam("codepromos") String code,
			Model model, HttpSession session) {
		// List<CodePromo> c = (List<CodePromo>) CPrepository.findAll();
		// double totale=0;
		// for(int i=0; i< c.size(); i++)
		// {
		// if(c.get(i).getCode().equals(code))
		// {
		// totale = (total*1.20) - c.get(i).getMontant();
		// }
		// }
		//

		totale = total;
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.format(date);

		CodePromo c = CPrepository.findByCode(code);

		if (c != null && c.getId() > 0) {
			if (date.compareTo(c.getDebut()) >= 0
					&& date.compareTo(c.getFin()) <= 0) {
				// totale = (total*1.20) - c.getMontant();
				message = 1;
				// total = totale;
			} else {
				// totale = total ;
				message = 0;
			}
		}

		if (message == 1) {
			totale = (total * 1.20) - c.getMontant();
			// total = totale;
		} else if (message == 0) {
			totale = total * 1.20;
		} else {
			totale = total * 1.20;
		}

		session.setAttribute("message", message);
		session.setAttribute("totale", totale);

		message = 3;
		return "redirect:/caisse";
	}

	@RequestMapping(value = "/payespece", method = RequestMethod.POST)
	public String payerenespece(Model model, HttpSession session) {
		// System.out.println(id);
		panierListe = (Map<Long, ProduitQuantity>) session
				.getAttribute("panierListe");
		String liste = "";
		facture = new LinkedHashMap<Long, ProduitQuantity>();

		for (long i = 0; i < panierListe.size(); i++) {
			facture.put(i, panierListe.get(i));

		}

		return "redirect:/caisse";
	}

	//Permet d'attribuer les valeurs correctes après avoir payer
	void setvaleur(){
		for(Long l : prodPrix.keySet()){
			ProduitQuantity prodQuantity = panierListe.get(l);
			
			prodQuantity.getElementPanier().setStock(prodQuantity.getElementPanier().getTmpstock());
			prodQuantity.setSomme(prodQuantity.getTmpsomme());
			prodQuantity.setQuantity(prodQuantity.getTmpquantity());
			prodQuantity.getElementPanier().setQtiteVendu(prodQuantity.getElementPanier().getPrix()*(stock-prodQuantity.getElementPanier().getStock()));
			
			produitRepository.save(prodQuantity.getElementPanier());
			
		}
	}
	
	@RequestMapping(value = "/paiement", method = RequestMethod.POST)
	public String payerenespece(Model model, HttpSession session, @RequestParam("nom") String nom, @RequestParam("paye") String paye) {
		panierListe = (Map<Long, ProduitQuantity>) session.getAttribute("panierListe");
		if (panierListe.size() == 0){
			
			return "redirect:/caisse";
			}
		setvaleur();
		double montant = 0;

		ArrayList<String> fact = new ArrayList<String>();
		String liste = "";
		FactureModel lafacture = new FactureModel();
		
		for (long key : panierListe.keySet()) {
			
			if ( panierListe.get(key).getTmpsomme() ==0 ){
				
				return "redirect:/caisse";
			}
			lafacture.setListeproduis(panierListe.get(key).getElementPanier().getName().toString());
		    lafacture.setListeqtt(toString().valueOf(panierListe.get(key).getTmpquantity()));
		    lafacture.setListemontant(toString().valueOf(panierListe.get(key).getTmpsomme()));
		    lafacture.setListeprix(toString().valueOf(panierListe.get(key).getElementPanier().getPrix()));
			lafacture.setListeid(panierListe.get(key).getElementPanier().getId());
		    montant = montant + panierListe.get(key).getTmpsomme();

		}

		lafacture.setMoyen(paye);
		lafacture.setNomclient(nom);
		lafacture.setDatecommande(new Date());
		lafacture.setMontantTTC(montant * 1.20);
		lafacture.setMontant(montant);
		lafacture.setTva(montant*0.20);
		factureRepository.save(lafacture);
		model.addAttribute("factur", factureRepository.findAll());
		
        panierListe.clear();
        System.out.println(panierListe.size());
		return "facture";
	}

	@RequestMapping(value = "/imprime", method = RequestMethod.GET)
	public String payere(@RequestParam("id") Long id, Model model) {
		model.addAttribute("facture", factureRepository.findOne(id));
		model.addAttribute("produi", produitRepository.findOne(id));
		return "imprimeFacture";
	}
	
	public String editFacture(@RequestParam("id") Long id, Model model) {
//		System.out.println(factureRepository.findOne(id).getId());
//		System.out.println(factureRepository.findOne(id).getNomclient().toString());
//		System.out.println(factureRepository.findOne(id).getMontant());
//		System.out.println(factureRepository.findOne(id).getMoyen().toString());
//		System.out.println(factureRepository.findOne(id).getMesproduits().toString());
//		
		model.addAttribute("identifiant",factureRepository.findOne(id).getId());
		model.addAttribute("description",factureRepository.findOne(id).getMesproduits().contains(id));
		model.addAttribute("factures", factureRepository.findOne(id));

		return "imprimeFacture";
	}
	
	@RequestMapping(value = "/imprime", method = RequestMethod.POST)
	public String editPost(@ModelAttribute FactureModel facture, Model model) {
		factureRepository.save(facture);
		return "redirect:/";
	}
	@RequestMapping(value = "/RetourFacture", method = RequestMethod.POST)
	public String resetPanierApresFacture(Model model, HttpSession session) {
		// System.out.println(id);
		panierListe = (Map<Long, ProduitQuantity>) session.getAttribute("panierListe");
		panierListe = new LinkedHashMap<Long, ProduitQuantity>();
		prodPrix = new LinkedHashMap<Long, Integer>();
		total = 0;
		session.setAttribute("panierListe", panierListe);
		session.setAttribute("total", total);
		return "redirect:/caisse";
	}
	
	
	@RequestMapping(value = "/supprime", method = RequestMethod.POST)
	public String supprimeElement(@RequestParam("id") Long id, Model model, HttpSession session , Produit produit) {
		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		//ProduitQuantity prodQuantity = panierListe.get(id);
		System.out.println("id du produit selectionné:"+id);
		System.out.println("element selectionné :"+panierListe.get(id).getElementPanier().getName());
		System.out.println("TOTAL : " +total + " somme ! " + panierListe.get(id).getTmpsomme());
		
		total =total  - panierListe.get(id).getTmpsomme();
		System.out.println("TOTAL : " +total);
			panierListe.remove(id);
			session.setAttribute("total",total);
		 session.setAttribute("panierListe",panierListe);
		
		
		return "redirect:/caisse";
	}
}