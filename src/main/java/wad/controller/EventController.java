package wad.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Person;
import wad.domain.Event;
import wad.repository.CommentRepository;
import wad.repository.EventRepository;
import wad.repository.PersonRepository;

@Controller
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("list", eventRepository.findAll());

        return "index";
    }

//    @Secured("ADMIN")
    @RequestMapping(method = RequestMethod.POST)
    public String postEvent(@ModelAttribute Event event) {
        eventRepository.save(event);

        return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEvent(@PathVariable /*Long*/String id, Model model) {
        model.addAttribute("event", eventRepository.getOne(id));
        
        model.addAttribute("comments", commentRepository.findByEventIdIn(Arrays.asList(id)));
        
        return "event";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteEvent(@PathVariable /*Long*/String id) {
        eventRepository.delete(id);

        return "redirect:/";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addParticipant(@PathVariable /*Long*/String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Event event = eventRepository.findOne(id);
        Person account = personRepository.findByUsername(name);

        event.getParticipants().add(account);
        eventRepository.save(event);

        return "redirect:/" + id;
    }

    @RequestMapping(value = "/removeParticipant/{eventId}/{username}", method = RequestMethod.DELETE)
    public String removeParticipant(@PathVariable /*Long*/String eventId, @PathVariable String username) {
        Event event = eventRepository.findOne(eventId);

        List<Person> accounts = event.getParticipants();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(username)) {
                event.getParticipants().remove(i);
            }
        }

        eventRepository.save(event);

        return "redirect:/" + eventId;
    }
}
