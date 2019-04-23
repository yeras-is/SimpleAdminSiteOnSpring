package com.github.yerasis.model.db;

import com.github.yerasis.model.dto.UserToSave;
import lombok.Data;

@Data //Part of Lombok used to replace getters, setters
public class User {
  private  Integer id;
  private  String name;
  private  String surname;
  private  String phone;
  private  Integer age;
  private  Boolean actual;
  private  String password;
  private  String login;

  //add to DB
  public User(String name, String surname, String phone, Integer age, Boolean actual, String password, String login) {
    this.name = name;
    this.surname = surname;
    this.phone = phone;
    this.age = age;
    this.actual = actual;
    this.password = password;
    this.login = login;
  }

  //get,edit,delete from DB
  public User(Integer id, String name, String surname, String phone, Integer age, Boolean actual, String password, String login) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.phone = phone;
    this.age = age;
    this.actual = actual;
    this.password = password;
    this.login = login;
  }

  //transform from DTO to DB model
  public User(UserToSave userToSave) {
    this.name=userToSave.getName();
    this.surname=userToSave.getSurname();
    this.phone=userToSave.getPhone();
    this.id=userToSave.getId();
    this.age=userToSave.getAge();
    this.actual=userToSave.getActual();
    this.password=userToSave.getPassword();
    this.login=userToSave.getLogin();
  }
}
