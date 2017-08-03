package wad.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
public class Authority extends AbstractPersistable<Long> {
    
    private SimpleGrantedAuthority authority;

    public Authority() {
    }

    public Authority(SimpleGrantedAuthority authority) {
        this.authority = authority;
    }

    public SimpleGrantedAuthority getAuthority() {
        return authority;
    }

    public void setAuthority(SimpleGrantedAuthority authority) {
        this.authority = authority;
    }
        
}
