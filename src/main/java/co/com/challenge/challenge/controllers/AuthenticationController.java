package co.com.challenge.challenge.controllers;

import co.com.challenge.challenge.models.UsersModel;
import co.com.challenge.challenge.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("${satellite.location.base.url}")
@CrossOrigin
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @Autowired
  public AuthenticationController(AuthenticationService service) {
    this.authenticationService = service;
  }

  @PostMapping("/login")
  @ResponseBody
  public ResponseEntity<String> login(@RequestBody UsersModel usersModel) {
    return
        ResponseEntity.ok(
            authenticationService.login(usersModel));
  }

}
