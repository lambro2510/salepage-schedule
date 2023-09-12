package com.salespage.salepageschedule.domains.storages;

import com.salespage.salepageschedule.domains.entities.User;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserStorage extends BaseStorage {
  public void save(User user) {
    userRepository.save(user);
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public User findUserById(String userId) {
    return userRepository.findUserById(new ObjectId(userId));
  }

  public boolean isExistByUsernameAndRole(String refId) {
    return userRepository.existsById(new ObjectId(refId));
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }
}
