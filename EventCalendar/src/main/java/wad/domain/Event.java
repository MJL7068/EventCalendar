package wad.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Event extends AbstractPersistable<Long> {
    
    private String name;
    private String description;
    
    @OneToMany
    private List<Account> participitants;

    public Event() {
    }

    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Account> getParticipitants() {
        return participitants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParticipitants(List<Account> participitants) {
        this.participitants = participitants;
    }
    
}
