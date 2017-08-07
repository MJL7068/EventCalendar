package wad.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Comment extends AbstractPersistable<Long> {
    
    @ManyToOne
    private Person author;
    
    @ManyToOne
    private Event event;
    
    @Column(name = "COMMENT_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    
    private String content;

    public Comment() {
        this.date = new Date();
    }

    public Person getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
    
}
