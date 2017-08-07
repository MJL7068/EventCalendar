package wad.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import wad.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Collection<Comment> findByResourceIdIn(Collection<String> eventIds);
}
