package com.github.yerasis.repository;

import com.github.yerasis.model.dto.RoleToSave;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public interface RoleRepository {

  Map<Integer,String> getRolesId();

  Collection<RoleToSave> getAllRole();

  Integer addRole(String name) throws ClassNotFoundException, SQLException;

  void addPrivilege(Integer id, Integer privId) throws ClassNotFoundException, SQLException;

  void setRole(Integer id, Integer role) throws ClassNotFoundException, SQLException;

  void ClearOldRecord(Integer id) throws ClassNotFoundException, SQLException;

  void getRoles() throws ClassNotFoundException, SQLException;

  void getAllRoles() throws ClassNotFoundException, SQLException;

  void getRole() throws ClassNotFoundException, SQLException;

}
