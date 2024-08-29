package me.nepali.springboot.repository;

import me.nepali.springboot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
