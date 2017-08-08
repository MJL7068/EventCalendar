package wad.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Like extends AbstractStringId {
    
    private String commentId;
    
    @ManyToOne
    private Person person;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
}
