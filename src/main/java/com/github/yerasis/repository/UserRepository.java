package com.github.yerasis.repository;

import com.github.yerasis.model.db.User;
import com.github.yerasis.model.dto.UserToSave;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public interface UserRepository {

   int addUser(User user) throws ClassNotFoundException, SQLException;

   void updateUser(User user) throws ClassNotFoundException, SQLException;

   void deleteUser(Integer id) throws ClassNotFoundException, SQLException;

   void selectAllUser() throws ClassNotFoundException, SQLException;

   Collection<UserToSave> getAllUsers();

   Map<Integer, UserToSave> getUsersMap();

   void searchUser(String name, String role, Integer age) throws ClassNotFoundException, SQLException;

}
