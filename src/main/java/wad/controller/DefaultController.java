package wad.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wad.domain.Authority;
import wad.domain.Person;
import wad.repository.PersonRepository;

@Controller
public class DefaultController {
//    private List<String> events;

    public DefaultController() {
//        this.events = new ArrayList<>();
//        events.add("TEST");
//        events.add("SECOND TEST");
    }
    
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index(Model model) {
//        
//        model.addAttribute("list", events);
//        
//        return "index";
//    }
    
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public String addEvent(@RequestParam String name, @RequestParam String description) {
//        events.add(name);
//        
//        return "redirect:/";
//    }
    
//    @RequestMapping("*")
//    @ResponseBody
//    public String index() {
//        return "redirect/events";
//    }
    
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    @Autowired
    private PersonRepository accountRepository;
 
    @PostConstruct
    public void init() {
        Person larry = new Person();
        larry.setUsername("larry");
        larry.setPassword(passwordEncoder.encode("larry"));
        larry.getRights().add(new Authority(new SimpleGrantedAuthority("USER")));
        accountRepository.save(larry);
         
        Person moe = new Person();
        moe.setUsername("moe");
        moe.setPassword(passwordEncoder.encode("moe"));
        moe.getRights().add(new Authority(new SimpleGrantedAuthority("ADMIN")));
        accountRepository.save(moe);
         
        Person curly = new Person();
        curly.setUsername("curly");
        curly.setPassword(passwordEncoder.encode("curly"));
        curly.getRights().add(new Authority(new SimpleGrantedAuthority("ADMIN")));
        accountRepository.save(curly);
    }
}
