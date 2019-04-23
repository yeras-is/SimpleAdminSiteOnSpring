package com.github.yerasis.repository.impl;

import com.github.yerasis.model.dto.RoleToSave;
import com.github.yerasis.repository.RoleRepository;
import com.github.yerasis.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

  @Autowired
  Utils utils;

  private Map<Integer, RoleToSave> roles = new ConcurrentHashMap<>();

  private Map<Integer, String> rolesId = new ConcurrentHashMap<>();

  @Override
  public Map<Integer, String> getRolesId() {
    return rolesId;
  }

  @Override
  public Collection<RoleToSave> getAllRole() {
    return roles.values();
  }

  @Override
  public Integer addRole(String name) throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement prepareStatement = connection.prepareStatement(SQLRole.InsertRole.QUERY)) {
        prepareStatement.setString(1, name);
        try (ResultSet resultSet = prepareStatement.executeQuery()) {
          resultSet.next();
          return resultSet.getInt(1);
        }
      }
    }
  }

  @Override
  public void addPrivilege(Integer id, Integer privId) throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement prepareStatement = connection.prepareStatement(SQLRole.InsertPrivilege.QUERY)) {
        prepareStatement.setInt(1, id);
        prepareStatement.setInt(2, privId);
        prepareStatement.execute();
      }
    }
  }

  @Override
  public void setRole(Integer id, Integer roleId) throws ClassNotFoundException, SQLException {
    ClearOldRecord(id);
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement prepareStatement = connection.prepareStatement(SQLRole.SetRole.QUERY)) {
        prepareStatement.setInt(1, id);
        prepareStatement.setInt(2, roleId);
        prepareStatement.execute();
      }
    }
  }

  @Override
  public void ClearOldRecord(Integer id) throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement prepareStatement = connection.prepareStatement(SQLRole.ClearOldRecord.QUERY)) {
        prepareStatement.setInt(1, id);
        prepareStatement.execute();
      }
    }
  }

  @Override
  public void getRoles() throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement prepareStatement = connection.prepareStatement(SQLRole.GetAllRoles.QUERY)) {
        roles.clear();
        try (ResultSet resultSet = prepareStatement.executeQuery()) {
          while (resultSet.next()) {
            RoleToSave role = new RoleToSave();
            role.setId(resultSet.getInt(1));
            role.setName(resultSet.getString(2));
            roles.put(role.getId(), role);
          }
        }
      }
    }
  }


  @Override
  public void getAllRoles() throws ClassNotFoundException, SQLException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement prepareStatement = connection.prepareStatement(SQLRole.GetAllRoles.QUERY)) {
        rolesId.clear();
        try (ResultSet resultSet = prepareStatement.executeQuery()) {
          while (resultSet.next()) {
            rolesId.put(resultSet.getInt(1), resultSet.getString(2));
          }
        }
      }
    }
  }


  @Override
  public void getRole() throws SQLException, ClassNotFoundException {
    Class.forName("org.postgresql.Driver");
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/springls", "maikhanov_springls", "qwerty")) {
      try (PreparedStatement prepareStatement = connection.prepareStatement(SQLRole.GetRole.QUERY)) {
        roles.clear();
        try (ResultSet resultSet = prepareStatement.executeQuery()) {
          while (resultSet.next()) {
            RoleToSave role = new RoleToSave();
            role.setId(resultSet.getInt(1));
            role.setName(resultSet.getString(2));
            while (resultSet.getInt(1) == role.getId()) {
              resultSet.next();
              role.setPrivelege(resultSet.getInt(4), resultSet.getString(5));
            }
            roles.put(role.getId(), role);
          }
        }
      }
    }
  }


  enum SQLRole {
    InsertPrivilege("INSERT into role_priv(role_id, priv_id) values(?,?);"),
    InsertRole("insert into role(role_name) VALUES (?) RETURNING id;"),
    ClearOldRecord("delete from person_role where person_id=?"),
    GetAllRoles("select * from role"),
    SetRole("INSERT INTO person_role VALUES (?,?)"),
    GetRole("select rp.id,role_name,role_id,priv_id,name from role r join role_priv rp on r.id = role_id join privilege prv on priv_id = prv.id;"),
    ;
    String QUERY;

    SQLRole(String QUERY) {
      this.QUERY = QUERY;
    }
  }

}
