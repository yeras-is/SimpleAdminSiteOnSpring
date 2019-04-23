package com.github.yerasis.repository.impl;

import com.github.yerasis.model.db.User;
import com.github.yerasis.model.dto.UserToSave;
import com.github.yerasis.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepositoryImpl implements UserRepository {

  public static Map<Integer, UserToSave> users = new ConcurrentHashMap<>();

  @Override
  public Collection<UserToSave> getAllUsers() {
    return users.values();
  }

  @Override
  public Map<Integer, UserToSave> getUsersMap() {
    return users;
  }

  @Override
  public int addUser(User user) throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement preparedStatement = connection.prepareStatement(SQLUser.addUser.QUERY)) {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getPhone());
        preparedStatement.setInt(4, user.getAge());
        preparedStatement.setString(5, user.getLogin());
        preparedStatement.setString(6, user.getPassword());
        preparedStatement.setBoolean(7, true);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
          resultSet.next();
          return resultSet.getInt(1);
        }
      }
    }
  }

  @Override
  public void updateUser(User user) throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement preparedStatement = connection.prepareStatement(SQLUser.updateUser.QUERY)) {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getPhone());
        preparedStatement.setInt(4, user.getAge());
        preparedStatement.setString(5, user.getLogin());
        preparedStatement.setString(6, user.getPassword());
        preparedStatement.setInt(7, user.getId());
        preparedStatement.executeQuery();
      }
    }
  }

  @Override
  public void deleteUser(Integer id) throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement preparedStatement = connection.prepareStatement(SQLUser.deleteUser.QUERY)) {
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
      }
    }
  }


  @Override
  public void selectAllUser() throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement prepareStatement = connection.prepareStatement(SQLUser.SelectAllUser.QUERY)) {
        try (ResultSet resultSet = prepareStatement.executeQuery()) {
          users.clear();//clear before putting new queries
          while (resultSet.next()) {
            UserToSave user = new UserToSave(resultSet.getInt(1), resultSet.getInt(2),
              resultSet.getString(3), resultSet.getString(4),
              resultSet.getString(5), resultSet.getString(6),
              resultSet.getInt(7), resultSet.getString(8), resultSet.getString(9));
            users.put(user.getId(), user);
          }
        }
      }
    }
  }

  @Override
  public void searchUser(String name, String role, Integer age) throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement prepareStatement = connection.prepareStatement(SQLUser.searchUser.QUERY)) {
        prepareStatement.setString(1, name);
        prepareStatement.setInt(2, age);
        prepareStatement.setString(3, role);
        try (ResultSet rs = prepareStatement.executeQuery()) {
          users.clear();//clear before putting new queries
          while (rs.next()) {
            UserToSave user = new UserToSave(rs.getInt(1), rs.getInt(2),
              rs.getString(3), rs.getString(4), rs.getString(5),
              rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9));
            users.put(user.getId(), user);
          }
        }
      }
    }
  }


  enum SQLUser {
    SelectAllUser("select person.id, role_id,\n" +
      "       person.name,\n" +
      "       surname,\n" +
      "       role_name,\n" +
      "       phone,\n" +
      "       age,\n" +
      "       login,\n" +
      "       password\n" +
      "from person\n" +
      "       join person_role pr on person.id = pr.person_id\n" +
      "       join role r on pr.role_id = r.id\n" +
      "where actual=true"),
    searchUser("select person.id, role_id,\n" +
      "       person.name,\n" +
      "       surname,\n" +
      "       role_name,\n" +
      "       phone,\n" +
      "       age,\n" +
      "       login,\n" +
      "       password\n" +
      "from person\n" +
      "       join person_role pr on person.id = pr.person_id\n" +
      "       join role r on pr.role_id = r.id\n" +
      "where actual=true and (to_tsvector(person.name || ' ' || surname) @@ to_tsquery(?) or age=? or role_name=?)"),
    addUser("insert into person(name, surname, phone, age, login, password,actual) values(?,?,?,?,?,?,?) returning id;"),
    deleteUser("update person set actual= false where id=?;"),
    updateUser("update person set name=?, surname=?, phone=?, age=?, login=?, password=? where id=?;"),
    ;

    String QUERY;

    SQLUser(String QUERY) {
      this.QUERY = QUERY;
    }
  }

}
