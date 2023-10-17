package com.salespage.salepageschedule.domains.entities;

import com.salespage.salepageschedule.domains.entities.types.UserRole;
import com.salespage.salepageschedule.domains.entities.types.UserState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Document("account")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

  @Id
  private ObjectId id;

  @Field("username")
  private String username;

  @Field("password")
  private String password;

  @Field("salt")
  private String salt;

  @Field("role")
  private UserRole role;

  @Field("user_state")
  private UserState state;

  @Field("last_login")
  private String lastLogin;


}
