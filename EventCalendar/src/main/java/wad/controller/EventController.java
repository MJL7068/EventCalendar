package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.domain.Event;
import wad.repository.EventRepository;

@Controller
public class EventController {
    
    @Autowired
    private EventRepository eventRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("list", eventRepository.findAll());
        
        return "index";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String postEvent(@ModelAttribute Event event) {
        eventRepository.save(event);
        
        return "redirect:/";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEvent(@PathVariable Long id, Model model) {
        model.addAttribute("event", eventRepository.getOne(id));
        
        return "event";
    }
}
