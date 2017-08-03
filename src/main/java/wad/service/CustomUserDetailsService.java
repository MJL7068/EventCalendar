package wad.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wad.domain.Authority;
import wad.domain.Person;
import wad.repository.PersonRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
 
         
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Authority s : account.getRights()) {
            authorities.add(s.getAuthority());
        }
 
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                authorities);
    }
}
