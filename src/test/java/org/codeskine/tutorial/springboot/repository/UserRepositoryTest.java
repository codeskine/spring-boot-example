package org.codeskine.tutorial.springboot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.codeskine.tutorial.springboot.configuration.ApplicationTestConfiguration;
import org.codeskine.tutorial.springboot.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Import(ApplicationTestConfiguration.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void shouldReturnListOfCustomerWithMatchingRate() {
    List<User> customers = userRepository.findAll();

    assertEquals(2, customers.size());
  }
}