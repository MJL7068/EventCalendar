package wad.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wad.domain.Account;
import wad.repository.AccountRepository;

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
    private AccountRepository accountRepository;
 
    @PostConstruct
    public void init() {
        Account larry = new Account();
        larry.setUsername("larry");
        larry.setPassword(passwordEncoder.encode("larry"));
        larry.setAuthorities(Arrays.asList("USER"));
        accountRepository.save(larry);
         
        Account moe = new Account();
        moe.setUsername("moe");
        moe.setPassword(passwordEncoder.encode("moe"));
        moe.setAuthorities(Arrays.asList("USER", "ADMIN"));
        accountRepository.save(moe);
         
        Account curly = new Account();
        curly.setUsername("curly");
        curly.setPassword(passwordEncoder.encode("curly"));
        curly.setAuthorities(Arrays.asList("ADMIN"));
        accountRepository.save(curly);
    }
}
