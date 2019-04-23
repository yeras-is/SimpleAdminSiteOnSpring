package com.github.yerasis.model.dto;


import lombok.Data;


@Data
public class UserToSave {
  private Integer id;
  private Integer roleId;
  private String name;
  private String surname;
  private String role;
  private String phone;
  private Integer age;
  private String login;
  private String password;
  private Boolean actual;

  //Save To DB
  public UserToSave(String name, String surname, Integer roleId, String phone, Integer age, String login, String password) {
    this.name = name;
    this.surname = surname;
    this.roleId = roleId;
    this.phone = phone;
    this.age = age;
    this.login = login;
    this.password = password;
  }

  //for present from DB
  public UserToSave(Integer id, Integer roleId, String name, String surname, String role, String phone, Integer age, String login, String password) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.role = role;
    this.phone = phone;
    this.age = age;
    this.login = login;
    this.password = password;
    this.roleId=roleId;
  }
}
