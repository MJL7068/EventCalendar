package wad.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DefaultController {
    private List<String> events;

    public DefaultController() {
        this.events = new ArrayList<>();
        events.add("TEST");
        events.add("SECOND TEST");
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        
        model.addAttribute("list", events);
        
        return "index";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String addEvent(@RequestParam String name, @RequestParam String description) {
        events.add(name);
        
        return "redirect:/";
    }
}
