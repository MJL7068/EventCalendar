package wad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
public class AbstractStringId implements Persistable<String> {
    
    @Id
    private String id;
    
    public AbstractStringId() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @JsonIgnore
    @Override
    public boolean isNew() {
        return false;
    }
    
}
