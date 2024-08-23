package me.nepali.springboot.repository;

import me.nepali.springboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repository extends JpaRepository<Post,Long> {


    Optional<Post> findByUrl(String url);


}
