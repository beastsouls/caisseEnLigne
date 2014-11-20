package projet.employe.controller;

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

import projet.employe.model.Employe;
import projet.employe.repository.EmployeRepository;

@Controller
public class EmployeController extends WebMvcConfigurerAdapter {

	@Autowired
	private EmployeRepository employeRepository;

	@RequestMapping(value = "/createEmploye/employe", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("employe", new Employe());
		return "createEmploye";
	}

	@RequestMapping(value = "/createEmploye/employe", method = RequestMethod.POST)
	public String submitForm(@Valid @ModelAttribute Employe employe,
			BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			return "createEmploye";
		}
		employeRepository.save(employe);
		return "redirect:/employe/";
	}

	@RequestMapping(value = "/employe/recherche", method = RequestMethod.GET)
	public String searchForm(@RequestParam("id") Long id, Model model) {
		model.addAttribute("employes", employeRepository.findOne(id));
		System.out.println(employeRepository.findOne(id).getName());
		return "listEmploye";
	}

	@RequestMapping(value = "/employe/", method = RequestMethod.GET)
	public String listemployes(Model model) {
		// Pour avoir des employes par défault
		if (employeRepository.count() == 0) {
			Employe e = new Employe();
			e.setName("name1");
			e.setAdresse("adresse1");
			e.setPrenom("prenom1");
			e.setEmail("email1@gmail.com");
			e.setPassword("password1");
			e.setLogin("login1");
			employeRepository.save(e);

			e.setName("name");
			e.setAdresse("adresse");
			e.setPrenom("prenom");
			e.setEmail("email@gmail.com");
			e.setPassword("password");
			e.setLogin("login");
			employeRepository.save(e);
		}
		model.addAttribute("employes", employeRepository.findAll());
		return "listEmploye";
	}

	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String accueil(Model model) {
		return "form";
	}*/

	@RequestMapping(value = "/delete/employe", method = RequestMethod.GET)
	public String deleteemploye(@RequestParam("id") Long id, Model model) {
		employeRepository.delete(id);
		return "redirect:/employe/";
	}

	@RequestMapping(value = "/edit/employe", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		model.addAttribute("employe", employeRepository.findOne(id));
		return "createEmploye";
	}

	@RequestMapping(value = "/edit/employe", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Employe employe, Model model) {
		employeRepository.save(employe);
		return "redirect:/employe/";
	}

	/*
	 * public void addViewControllers(ViewControllerRegistry registry) {
	 * registry.addViewController("/list").setViewName("list"); }
	 */

}
