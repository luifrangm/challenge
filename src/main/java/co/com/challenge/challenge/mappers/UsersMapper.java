package co.com.challenge.challenge.mappers;

import co.com.challenge.challenge.entities.UsersEntity;
import co.com.challenge.challenge.models.UsersModel;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {

  private final MapperFactory factory = new DefaultMapperFactory.Builder().build();
  private final MapperFacade facade;

  public UsersMapper() {
    factory.classMap(UsersModel.class, UsersEntity.class);
    factory.classMap(UsersEntity.class, UsersModel.class);
    factory.classMap(UsersModel.class, UserDetails.class);
    facade = factory.getMapperFacade();
  }

  public UsersModel UsersEntity_To_Model(final UsersEntity usersEntity) {
    return
        facade.map(usersEntity, UsersModel.class);
  }


}
