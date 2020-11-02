package co.com.challenge.challenge.repositories;

import co.com.challenge.challenge.entities.UsersEntity;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRespository extends JpaRepository<UsersEntity, BigDecimal> {
  UsersEntity findByusername(final String username);
}
