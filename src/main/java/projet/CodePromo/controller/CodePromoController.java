package projet.CodePromo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import projet.CodePromo.model.CodePromo;
import projet.CodePromo.repository.CodePromoRepository;


@Controller
public class CodePromoController extends WebMvcConfigurerAdapter {
		
	@Autowired
	private CodePromoRepository CPRepository;
		
		
		@RequestMapping(value = "/create/codePromo", method = RequestMethod.GET)
		public String createCPromoForm(Model model) {
			model.addAttribute("codePromo", new CodePromo());
			return "createCPromo";
		}
		
		@RequestMapping(value = "/create/codePromo", method = RequestMethod.POST)
		public String submitForm( @Valid @ModelAttribute CodePromo codePromo, BindingResult bindingresult) {
			if (bindingresult.hasErrors()) {
	            return "createCPromo";
	        }
			CPRepository.save(codePromo);
			return "redirect:/codePromo/";
		}
		

		@RequestMapping(value = "/codePromo/", method = RequestMethod.GET)
		public String listcodes(Model model) {
			
			model.addAttribute("codePromos", CPRepository.findAll());
			return "listCodePromo";
		}
				
		
		@RequestMapping(value = "/delete/codePromo", method = RequestMethod.GET)
		public String deletecodePromo(@RequestParam("id") Long id, Model model) {
			CPRepository.delete(id);
			return "redirect:/codePromo/";
		}
		
		@RequestMapping(value = "/edit/codePromo", method = RequestMethod.GET)
		public String editForm(@RequestParam("id") Long id, Model model) {
			model.addAttribute("codePromo", CPRepository.findOne(id));
			return "createCPromo";
		}
		
		@RequestMapping(value = "/edit/codePromo", method = RequestMethod.POST)
		public String editPost(@ModelAttribute CodePromo codePromo, Model model) {
			CPRepository.save(codePromo);
			return "redirect:/codePromo/";
		}
	    

	}


