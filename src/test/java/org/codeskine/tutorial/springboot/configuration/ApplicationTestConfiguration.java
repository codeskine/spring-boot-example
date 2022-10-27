package org.codeskine.tutorial.springboot.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.codeskine.tutorial.springboot.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

@TestConfiguration
public class ApplicationTestConfiguration {
  private static final MongoDBContainer mongoDBContainer;
  static {
    mongoDBContainer = new MongoDBContainer("mongo:latest");
    mongoDBContainer.start();
  }
  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
  }
  @Bean
  public MongoTemplate mongoTemplate() {
    ConnectionString connectionString = new ConnectionString(mongoDBContainer.getReplicaSetUrl());
    MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build();

    MongoClient mongoClient = MongoClients.create(mongoClientSettings);

    return new MongoTemplate(mongoClient, "test");
  }

  @Bean
  @DependsOn("mongoTemplate")
  CommandLineRunner init(MongoOperations operations) {
    return args -> {
      operations.dropCollection(User.class);
      operations.insert(User.of("Stefano", "Veloccia", "s.veloccia@innen.it"));
      operations.insert(User.of("Alexander", "De Carlo", "a.decarlo@innen.it"));
    };
  }
}
