package com.github.yerasis.service.impl;

import com.github.yerasis.model.db.User;
import com.github.yerasis.model.dto.UserToSave;
import com.github.yerasis.repository.UserRepository;
import com.github.yerasis.service.UserManipulate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserManipulateImpl implements UserManipulate {

  @Autowired
  UserRepository userRepository;

  @Override
  public void Register(UserToSave userToSave) throws SQLException, ClassNotFoundException {
    User user = new User(userToSave);
    userRepository.addUser(user);

  }

  @Override
  public void Update(UserToSave userToSave) throws SQLException, ClassNotFoundException {
    User user = new User(userToSave);
    userRepository.updateUser(user);
  }

  @Override
  public void Delete(Integer id) throws SQLException, ClassNotFoundException {
    userRepository.deleteUser(id);
  }

  @Override
  public void Search(String name, String role, Integer age) throws SQLException, ClassNotFoundException {
    userRepository.searchUser(name, role, age);
  }
}
