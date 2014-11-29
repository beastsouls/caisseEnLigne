package projet.authentification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projet.CodePromo.repository.CodePromoRepository;
import projet.client.repository.ClientRepository;
import projet.facture.repository.FactureRepository;
import projet.produit.model.Produit;
import projet.produit.model.ProduitQuantity;
import projet.produit.repository.produitRepository;

@Controller

public class AppController {

	@Autowired
	private produitRepository produitRepository;
	
	
	
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private CodePromoRepository CPrepository;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String createForm(Model model, Authentication authentication) {
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
		
		if(authentication.getName().equalsIgnoreCase("admin"))
		{
		return "form";
		}
		else
		{return "user";
		}
		
		
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String productSubmit(@ModelAttribute Produit product,
			HttpSession session, Model model) {
		List<Produit> panier = (List<Produit>) session.getAttribute("panier");
		if (panier == null)
			panier = new ArrayList<Produit>();
		panier.add(product);
		session.setAttribute("panier", panier);
		return "redirect:/user";
	}

//	
//	@RequestMapping(value="/index",method=RequestMethod.GET)
//	public String postLogin(Authentication authentication)
//	{
//	if(authentication.getName().equalsIgnoreCase("admin"))
//	{
//	return "form";
//	}
//	else
//	{return "user";
//	}
//	}
	
}
