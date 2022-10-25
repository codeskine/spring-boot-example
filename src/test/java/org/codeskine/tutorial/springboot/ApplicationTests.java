package org.codeskine.tutorial.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest({
    "my.first-name=mario",
    "my.last-name=bros"})
class ApplicationTests {

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private DemoMetadata metadata;

  @Test
  void contextLoads() {
    assertThat(applicationContext).isNotNull();

    assertThat(metadata).isNotNull();
    assertThat(metadata.getFirstName()).isEqualTo("mario");
    assertThat(metadata.getLastName()).isEqualTo("bros");
  }

}
