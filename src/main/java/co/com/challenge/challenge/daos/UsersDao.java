package co.com.challenge.challenge.daos;

import co.com.challenge.challenge.mappers.UsersMapper;
import co.com.challenge.challenge.models.UsersModel;
import co.com.challenge.challenge.repositories.UsersRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersDao {

  private final UsersRespository repository;
  private final UsersMapper mapper;

  @Autowired
  public UsersDao(UsersRespository repository,
      UsersMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public UsersModel findByUsernameUser(final String username) {
    return
        mapper.usersEntity_To_Model(
            repository.findByusername(username));

  }

}
