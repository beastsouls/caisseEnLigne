package projet.facture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projet.facture.model.FactureModel;
import projet.facture.repository.FactureRepository;



@Controller
public class ControllerFacture {
	
//	@Autowired
//	private FactureRepository facureRepository;
//
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public String createForm(Model model) {
//		
//		//model.addAttribute("factures", facureRepository.findAll());
//		model.addAttribute("facture", new FactureModel());
//		
//		return "caisse";
//	}

}
