package wad.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.domain.Like;
import wad.domain.Person;
import wad.repository.LikeRepository;
import wad.service.PersonService;

@Controller
@RequestMapping("/likes")
public class LikeController {
    
    @Autowired
    private LikeRepository likeRepository;
    
    @Autowired
    private PersonService personService;
    
    @RequestMapping(method = RequestMethod.POST)
    public Like addLike(@RequestParam String commentId) {
        Person person = personService.getAuthPerson();
        if (likeRepository.findByCommentIdAndPerson(commentId, person) != null) {
            return null;
        }
        
        Like newLike = new Like();
        newLike.setCommentId(commentId);
        newLike.setPerson(person);
        
        return likeRepository.save(newLike);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Integer> getLikeCounts(@RequestParam String[] ids) {
        Map<String, Integer> likeCounts = new HashMap<>();
        
        for (Like like : likeRepository.findByCommentIdIn(Arrays.asList(ids))) {
            if (!likeCounts.containsKey(like.getCommentId())) {
                likeCounts.put(like.getCommentId(), 0);
            }
            
            likeCounts.put(like.getCommentId(), likeCounts.get(like.getCommentId()) + 1);
        }
        
        return likeCounts;
    }
    
}
