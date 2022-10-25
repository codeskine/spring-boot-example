package org.codeskine.tutorial.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.codeskine.tutorial.springboot.configuration.ApplicationTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(ApplicationTestConfiguration.class)
class ApplicationTests {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void contextLoads() {
    assertThat(applicationContext).isNotNull();
  }

}
