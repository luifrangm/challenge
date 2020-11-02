package co.com.challenge.challenge.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersEntity {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private BigDecimal iduser;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;
}
