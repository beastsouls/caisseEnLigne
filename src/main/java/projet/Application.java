package projet;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import projet.CodePromo.model.CodePromo;
import projet.CodePromo.repository.CodePromoRepository;
import projet.client.model.Client;
import projet.client.repository.ClientRepository;
import projet.produit.model.Produit;
import projet.produit.repository.produitRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("12800KB");
        factory.setMaxRequestSize("12800KB");
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) throws ParseException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        produitRepository prepository = context.getBean(produitRepository.class);
        ClientRepository crepository = context.getBean(ClientRepository.class);
        CodePromoRepository cprepository = context.getBean(CodePromoRepository.class);
        
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("Pomme", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("Poire", "Nourriture", 10.0 , "orange" , 1000)); 
        prepository.save(new Produit("Avocat", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("Prune", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("Fraise", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("Figue", "Nourriture", 10.0 , "orange" , 1000));  
        prepository.save(new Produit("Citron", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("Raisin", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("Framboise", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("kiwi", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("Banane", "Nourriture", 95.0 , "banane" , 1000));
        prepository.save(new Produit("abricot", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("peche", "Nourriture", 10.0 , "orange" , 1000));
        prepository.save(new Produit("pamplemousse", "Nourriture", 10.0 , "orange" , 1000));
       

        crepository.save(new Client("jean","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("pierre","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("sophie","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("lucie","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("boris","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("lol","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("test","pdg", "test" , "n@gmail.com", "test"));
        
        DateFormat date = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
        
        cprepository.save(new CodePromo(50, new Date("10/10/2014"), new Date("30/12/2014") , "AZERTYU")); //valide
        cprepository.save(new CodePromo(150,new Date("10/10/2014"), new Date("10/11/2014") , "AZERTYP"));// invalid
        cprepository.save(new CodePromo(40,new Date("10/01/2013"), new Date("10/12/2013") , "AZERTYM"));//invalide	
        cprepository.save(new CodePromo(78,new Date("26/11/2014"), new Date("10/12/2014") , "AZERTYN"));//invalide
        cprepository.save(new CodePromo(98,new Date("10/12/2014"), new Date("11/12/2014") , "AZERTYB"));// invalide
        
        
    }
}
