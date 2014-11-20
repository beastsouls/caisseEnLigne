package projet.client.controller;

import java.awt.Window;
import java.awt.event.WindowStateListener;
import java.lang.ProcessBuilder.Redirect;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
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

import projet.client.model.Client;
import projet.client.repository.ClientRepository;
import projet.produit.model.ProduitQuantity;


@Controller
public class ClientController extends WebMvcConfigurerAdapter {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	@RequestMapping(value = "/create/client", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("client", new Client());
		return "create";
	}
	
	@RequestMapping(value = "/create/client", method = RequestMethod.POST)
	public String submitForm( @Valid @ModelAttribute Client client, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
            return "create";
        }
		clientRepository.save(client);
		return "redirect:/client/";
	}
	
	@RequestMapping(value = "/client/recherche", method = RequestMethod.GET)
	public String searchForm(@RequestParam("id") Long id, Model model) {
		model.addAttribute("clients", clientRepository.findOne(id));
		System.out.println(clientRepository.findOne(id).getName());
		return "list";
	}
	
	
	
	@RequestMapping(value = "/create/clientPanier", method = RequestMethod.GET)
	public String createCPForm(Model model) {
		model.addAttribute("client", new Client());
		return "createCPanier";
	}
	
	@RequestMapping(value = "/create/clientPanier", method = RequestMethod.POST)
	public String submitCPForm( @Valid @ModelAttribute Client client, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
            return "createCPanier";
        }
		clientRepository.save(client);
		return "redirect:/caisse/";
	}
	
	
//	@RequestMapping(value="/create/clientPanier" , method = RequestMethod.POST)
//	public String retourPanier()
//	{
//		
//		return "createCPanier";
//	}
	
	@RequestMapping(value = "/client/", method = RequestMethod.GET)
	public String listclients(Model model) {
		//Pour avoir des clients par défault
		if(clientRepository.count()==0){
			Client c = new Client();
			c.setName("name1");
			c.setAdresse("adresse1");
			c.setCompagnie("compagnie1");
			c.setEmail("email1@gmail.com");
			c.setRole("role1");
			clientRepository.save(c);
			
			c = new Client();
			c.setName("name");
			c.setAdresse("adresse");
			c.setCompagnie("compagnie");
			c.setEmail("email@gmail.com");
			c.setRole("role");
			clientRepository.save(c);
		}
		model.addAttribute("clients", clientRepository.findAll());return "list";
	}
	
	@RequestMapping(value = "/delete/client", method = RequestMethod.GET)
	public String deleteclient(@RequestParam("id") Long id, Model model) {
		clientRepository.delete(id);
		return "redirect:/client/";
	}
	
	@RequestMapping(value = "/edit/client", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		model.addAttribute("client", clientRepository.findOne(id));
		return "create";
	}
	
	@RequestMapping(value = "/edit/client", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Client client, Model model) {
		clientRepository.save(client);
		return "redirect:/client/";
	}
	
	
	/*public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/list").setViewName("list");
    }*/

//	@RequestMapping(value = "/AjouterClientFacture", method = RequestMethod.POST)
//	public String AjouterClientSubmit( Model model, HttpSession session) {
//		
//		session.setAttribute("clients",  clientRepository.findAll());
//		return "redirect:/caisse";
//		//}
//	}
    

}
