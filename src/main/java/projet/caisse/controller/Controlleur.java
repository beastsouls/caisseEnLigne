package projet.caisse.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	
	
	private Map<Long,ProduitQuantity> panierListe;
	private double total=0;
	private int message = 2;
	private Map<Long , ProduitQuantity> facture;
	private double prixht=0;
	private double totale = 0;
	double tva;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private CodePromoRepository CPrepository;

	@RequestMapping(value = "/caisse", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("products", produitRepository.findAll());
		model.addAttribute("clients",  clientRepository.findAll());
		model.addAttribute("codePromos",  CPrepository.findAll());
		model.addAttribute("product", new Produit());
		return "caisse";
	}
	
	
	@RequestMapping(value = "/caisse", method = RequestMethod.POST)
	public String productSubmit(@ModelAttribute Produit product,	HttpSession session, Model model) 
	{
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
	public String produitSubmit(@ModelAttribute("id") Produit produit,	HttpSession session, Model model) {

		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		if (panierListe == null)
			{panierListe = new LinkedHashMap<Long,ProduitQuantity>(); }
		
		if(!panierListe.containsKey(produit.getId())){
			ProduitQuantity  Pquantity = new ProduitQuantity();
		Pquantity.setElementPanier(produit);
		Pquantity.setQuantity(1);
		panierListe.put(Pquantity.getElementPanier().getId(),Pquantity);
		}

		session.setAttribute("panierListe", panierListe);
		return "redirect:/caisse";
	}

	
	
	@RequestMapping(value = "/calcul", method = RequestMethod.POST)
	public String calculpanierSubmit(@RequestParam("qt") int qt,@RequestParam("id") Long id, Model model, HttpSession session , Produit produit) {
		//System.out.println(id);
		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		ProduitQuantity prodQuantity = panierListe.get(id);
		int stock = prodQuantity.getElementPanier().getStock();
		int messtock =0;
		//System.out.println(" stock de " + prodQuantity.getElementPanier().getName() + "  =" + prodQuantity.getElementPanier().getStock());
		prodQuantity.getElementPanier().setStock(prodQuantity.getElementPanier().getStock() - qt);
		//System.out.println("nouveau stock de " + prodQuantity.getElementPanier().getName() + "  =" + prodQuantity.getElementPanier().getStock());
		if(prodQuantity.getElementPanier().getStock()<0)
		{
			//System.out.println("le stock est negatif ");
			prodQuantity.setQuantity(prodQuantity.getElementPanier().getStock());
			messtock = 1; //"vous avez rentré un nombre de produits trop grand !!!";
//			prodQuantity.setSomme(prodQuantity.getQuantity() * prodQuantity.getElementPanier().getPrix());
//			produitRepository.save(prodQuantity.getElementPanier());
//			
//			
//			total =total  + panierListe.get(prodQuantity.getElementPanier().getId()).getSomme();
//			prixht=total;
//			 tva=prixht*0.20;
//			//System.out.println("prix ht "+prixht);
//			//System.out.println("tva "+tva);
//			//prodQuantity.setSommeTotalFacture(total);
//			session.setAttribute("total", total);
//			session.setAttribute("stock", messtock);
//			
//			
//			session.setAttribute("panierListe", panierListe);
//			return "redirect:/caisse";
		
			//System.out.println("quantite donnee dans le panier " + prodQuantity.getQuantity());
			
		}
		else
		{
			if(prodQuantity.getElementPanier().getStock() == 0){
			prodQuantity.setQuantity(0);
			messtock = 2;}
			else
				{prodQuantity.setQuantity(qt); messtock=3;}
		}
		//prodQuantity.setQuantity(qt);
		prodQuantity.setQuantity(stock);
		messtock = 1; //"vous avez rentré un nombre de produits trop grand !!!";
		prodQuantity.setSomme(prodQuantity.getQuantity() * prodQuantity.getElementPanier().getPrix());
		produitRepository.save(prodQuantity.getElementPanier());
		
		
		total =total  + panierListe.get(prodQuantity.getElementPanier().getId()).getSomme();
		prixht=total;
		 tva=prixht*0.20;
		//System.out.println("prix ht "+prixht);
		//System.out.println("tva "+tva);
		//prodQuantity.setSommeTotalFacture(total);
		session.setAttribute("total", total);
		session.setAttribute("stock", messtock);
		
		
		session.setAttribute("panierListe", panierListe);
		return "redirect:/caisse";
	}
	
	@RequestMapping(value = "/resetPanier", method = RequestMethod.POST)
	public String resetpanierSubmit(Model model, HttpSession session) {
		//System.out.println(id);
		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		panierListe = new LinkedHashMap<Long,ProduitQuantity>();
		total=0;
		totale=0;
		session.setAttribute("panierListe", panierListe);
		session.setAttribute("total", total);
		return "redirect:/caisse";
	}

	
	
	@RequestMapping(value = "/validCode", method = RequestMethod.GET)
	public String testCodeSubmit(@RequestParam("codepromos") String code, Model model, HttpSession session) {
//		List<CodePromo> c = (List<CodePromo>) CPrepository.findAll();
//		double totale=0;
//		for(int i=0; i< c.size(); i++)
//		{
//			if(c.get(i).getCode().equals(code))
//			{
//				totale = (total*1.20) - c.get(i).getMontant();
//			}
//		}
//		
	
		totale=total;
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.format(date);
        
		CodePromo c = CPrepository.findByCode(code);
				
		if(c != null && c.getId() > 0 )
		{
			if(date.compareTo(c.getDebut())>=0 && date.compareTo(c.getFin())<=0)
			{
				//totale = (total*1.20) - c.getMontant();
				message=1;
				//total = totale;
			}
			else 
			{ 
				//totale = total ; 
				message = 0; 
			}
		}
		
		if(message == 1)
		{
			totale = (total*1.20) - c.getMontant();
			//total = totale;
		}
		else if(message == 0)
		{
			totale = total*1.20 ;
		}
		else {totale = total*1.20 ;}
		
		session.setAttribute("message", message);
		session.setAttribute("totale", totale);
		return "redirect:/caisse";
	}
	
	
	@RequestMapping(value = "/payespece", method = RequestMethod.POST)
	public String payerenespece(Model model, HttpSession session) {
		//System.out.println(id);
		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		String liste="";
		facture  = new LinkedHashMap<Long,ProduitQuantity>();
		
		for(long i =0 ; i< panierListe.size() ; i++)
		{
			facture.put(i, panierListe.get(i));
			
		}
		
		return "redirect:/caisse";
	}
	@RequestMapping(value = "/paiement", method = RequestMethod.POST)
	public String payerenespece(Model model, HttpSession session, @RequestParam("nom") String nom, @RequestParam("paye") String paye) {
		panierListe = (Map<Long, ProduitQuantity>) session.getAttribute("panierListe");
		double montant = 0;

		ArrayList<String> fact = new ArrayList<String>();
		String liste = "";
		FactureModel lafacture = new FactureModel();
		for (long key : panierListe.keySet()) {
			
            lafacture.setListeproduis(panierListe.get(key).getElementPanier().getName().toString());
		    lafacture.setListeqtt(toString().valueOf(panierListe.get(key).getQuantity()));
		    lafacture.setListemontant(toString().valueOf(panierListe.get(key).getSomme()));
		    lafacture.setListeprix(toString().valueOf(panierListe.get(key).getElementPanier().getPrix()));
			lafacture.setListeid(panierListe.get(key).getElementPanier().getId());
		    montant = montant + panierListe.get(key).getSomme();

		}

		lafacture.setMoyen(paye);
		lafacture.setNomclient(nom);
		lafacture.setDatecommande(new Date());
		lafacture.setMontantTTC(montant * 1.20);
		lafacture.setMontant(montant);
		lafacture.setTva(montant*0.20);
		
		
	//System.out.println("prix unitaire "+pu.toString());
		lafacture.setMontant(montant * 1.20);
		factureRepository.save(lafacture);
		model.addAttribute("factur", factureRepository.findAll());
		

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
		//System.out.println(id);
		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		panierListe = new LinkedHashMap<Long,ProduitQuantity>();
		total=0;
		session.setAttribute("panierListe", panierListe);
		session.setAttribute("total", total);
		return "redirect:/caisse";
	}
	
	@RequestMapping(value = "/facture/", method = RequestMethod.GET)
	public String fact() {
		return "facture";
	}
	
		//System.out.println(id);
	
}
