package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Comment;
import wad.repository.CommentRepository;
import wad.repository.EventRepository;
import wad.service.PersonService;

@Controller
@RequestMapping("/comments")
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private PersonService personService;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String postComment(@PathVariable String id, /*@RequestParam String content*/@ModelAttribute Comment comment) {
        comment.setAuthor(personService.getAuthPerson());
        comment.setEventId(id);
        commentRepository.save(comment);
        
        return "redirect:/" + id;
    }
}
