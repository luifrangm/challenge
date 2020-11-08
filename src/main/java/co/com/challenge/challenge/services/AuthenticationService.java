package co.com.challenge.challenge.services;

import co.com.challenge.challenge.daos.UsersDao;
import co.com.challenge.challenge.models.UsersModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

  public static final String USERNAME_OR_PASSWORD_INVALID = "Username or password invalid";
  public static final String USERNAME_NOT_EXIST = "Username does not exist";
  public static final String SECRET = "luifrangm2020**";
  private static final String PREFIX = "Bearer ";

  private final UsersDao dao;

  @Autowired
  public AuthenticationService(UsersDao dao) {
    this.dao = dao;
  }

  public String login(UsersModel usersModel) {

    UserDetails userAuth =
        loadUserByUsername(usersModel.getUsername());

    if(userAuth == null) {
      return USERNAME_NOT_EXIST;
    }

    if(!validatePassword(
        userAuth.getPassword(),
        usersModel.getPassword())) {
            return USERNAME_OR_PASSWORD_INVALID;
    }

    return getJWTToken(userAuth);
  }

  private String getJWTToken(
      UserDetails userAuth) {
    String token = Jwts
        .builder()
        .setId("Quasar Fire Challenge")
        .setSubject(userAuth.getUsername())
        .claim("authorities",
            userAuth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 600000))
        .signWith(SignatureAlgorithm.HS512,
            SECRET.getBytes()).compact();
    return PREFIX + token;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    UsersModel usersModel =
        dao.findByUsernameUser(username);
    return
        Optional.ofNullable(usersModel)
            .map(this::buildUser)
            .orElse(null);
  }

  private UserDetails buildUser(UsersModel usersModel) {
    List<GrantedAuthority> listGrantedAutority = new ArrayList<>();
    listGrantedAutority.add(new SimpleGrantedAuthority("USER"));
    return
        new User(
            usersModel.getUsername(),
            usersModel.getPassword(),
            listGrantedAutority);
  }

  private boolean validatePassword(final String cryptedPassword, final String clearPassword) {
    BCryptPasswordEncoder passwordEncoder =
        new BCryptPasswordEncoder(10);
    return  passwordEncoder.matches(clearPassword,cryptedPassword);
  }



}
