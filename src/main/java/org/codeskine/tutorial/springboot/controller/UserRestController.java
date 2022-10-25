package org.codeskine.tutorial.springboot.controller;


import org.codeskine.tutorial.springboot.DemoMetadata;
import org.codeskine.tutorial.springboot.model.User;
import org.codeskine.tutorial.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserRestController {

  private final UserRepository repository;
  private final DemoMetadata metadata;

  @Autowired
  public UserRestController(UserRepository repository, DemoMetadata demoMetadata) {
    this.repository = repository;
    this.metadata = demoMetadata;
  }

  @GetMapping
  public Page<User> findAll(@PageableDefault(size = 20)
  @SortDefault.SortDefaults({
      @SortDefault(sort = "name", direction = Sort.Direction.DESC),
      @SortDefault(sort = "id", direction = Sort.Direction.ASC)
  }) Pageable pageable) {
    return repository.findAll(pageable);
  }

  @GetMapping(path = "/{email}")
  public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
    var user = repository.findByEmail(email);
    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(user);
  }

  @GetMapping(path = "/greetings")
  public ResponseEntity<String> greetings() {
    return ResponseEntity.ok(
        String.format("HI!!!! %s %s, welcome to %s!!!!", metadata.getFirstName(),
            metadata.getLastName(), metadata.getCity()));
  }
}
