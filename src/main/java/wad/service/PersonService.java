package wad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wad.domain.Person;
import wad.repository.PersonRepository;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;
    
    public Person getAuthPerson() {
        return personRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
