package wad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Like;
import wad.domain.Person;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
    Like findByCommentIdAndPerson(String commentId, Person person);
}
