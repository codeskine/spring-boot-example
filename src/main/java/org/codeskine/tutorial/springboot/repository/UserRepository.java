package org.codeskine.tutorial.springboot.repository;


import org.codeskine.tutorial.springboot.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRepository extends MongoRepository<User, String> {
  User findByEmail(String email);

}
