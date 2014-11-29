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
        
        prepository.save(new Produit("Orange", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("Pomme", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("Poire", "Nourriture", 1.0 , "orange" , 1000)); 
        prepository.save(new Produit("Avocat", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("Prune", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("Fraise", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("Figue", "Nourriture", 1.0 , "orange" , 1000));  
        prepository.save(new Produit("Citron", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("Raisin", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("Framboise", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("kiwi", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("Banane", "Nourriture", 1.0 , "banane" , 1000));
        prepository.save(new Produit("abricot", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("peche", "Nourriture", 1.0 , "orange" , 1000));
        prepository.save(new Produit("pamplemousse", "Nourriture", 1.0 , "orange" , 1000));
        
        
        prepository.save(new Produit("armani pulls homme", "Vetements", 25.0 , "armani pulls homme" , 1000));
        prepository.save(new Produit("jeans femme bleu", "Vetements", 25.0 , "jeans femme bleu" , 1000));
        prepository.save(new Produit("jeans homme bleu", "Vetements", 25.0 , "jeans homme bleu" , 1000)); 
        prepository.save(new Produit("pull en angora rouge sud", "Vetements", 25.0 , "pull en angora rouge sud" , 1000));
        prepository.save(new Produit("pull en maille", "Vetements", 25.0 , "pull en maille" , 1000));
        prepository.save(new Produit("pull capuche maille", "Vetements", 25.0 , "pull capuche maille" , 1000));  
        prepository.save(new Produit("ralph lauren pulls vert", "Vetements", 25.0 , "ralph lauren pulls vert" , 1000));
        
        
        prepository.save(new Produit("coca 0.5l", "Boisson", 1.0 , "coca 0.5l" , 1000));
        prepository.save(new Produit("coca 33cl", "Boisson", 1.0 , "coca 33cl" , 1000));
        prepository.save(new Produit("fanta 33cl", "Boisson", 1.0 , "fanta 33cl" , 1000));
        prepository.save(new Produit("orangina", "Boisson",1.0 , "orangina" , 1000));
        
        
        prepository.save(new Produit("tele sony", "Autre", 700.0 , "tele sony" , 1000));
        prepository.save(new Produit("tele samsung 3d", "Autre", 1000.0 , "tele samsung 3d" , 1000));
        
        
        prepository.save(new Produit("machine a laver bosch", "Electroménager", 450.0 , "machine a laver bosch" , 1000));
        prepository.save(new Produit("machine a laver samsung", "Electroménager", 450.0 , "machine a laver samsung" , 1000));
        prepository.save(new Produit("micro onde whirlpool", "Electroménager", 100.0 , "micro onde whirlpool" , 1000)); 
        prepository.save(new Produit("mixeur", "Electroménager", 70.0 , "mixeur" , 1000));
        prepository.save(new Produit("frigo americain", "Electroménager", 300.0 , "frigo americain" , 1000));
        

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
        //cprepository.save(new CodePromo(78,new Date("26/11/2014"), new Date("10/12/2014") , "AZERTYN"));//invalide
        cprepository.save(new CodePromo(98,new Date("10/12/2014"), new Date("11/12/2014") , "AZERTYB"));// invalide
        
        
    }
}
