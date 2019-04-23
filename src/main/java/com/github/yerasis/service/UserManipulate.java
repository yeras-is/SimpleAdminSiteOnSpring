package com.github.yerasis.service;

import com.github.yerasis.model.dto.UserToSave;

import java.sql.SQLException;

public interface UserManipulate {

  void Register(UserToSave user) throws SQLException, ClassNotFoundException;

  void Update(UserToSave user) throws SQLException, ClassNotFoundException;

  void Delete(Integer id) throws SQLException, ClassNotFoundException;

  void Search(String name, String role, Integer age) throws SQLException, ClassNotFoundException;

}
