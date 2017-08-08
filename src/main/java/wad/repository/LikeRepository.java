package wad.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Like;
import wad.domain.Person;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
    Like findByCommentIdAndPerson(String commentId, Person person);
    
    Collection<Like> findByCommentIdIn(Collection<String> commentIds);
}
