package org.codeskine.tutorial.springboot.repository;


import java.util.Optional;
import org.codeskine.tutorial.springboot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  Optional<User> findByEmail(String email);

}
