package com.github.yerasis.controller;

import com.github.yerasis.model.dto.UserToSave;
import com.github.yerasis.repository.RoleRepository;
import com.github.yerasis.repository.UserRepository;
import com.github.yerasis.service.UserManipulate;
import com.github.yerasis.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RequestMapping(value = "/user")
@Controller
public class UserController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  UserManipulate userManipulate;
  @Autowired
  Utils utils;
  @Autowired
  RoleRepository roleRepository;

  @GetMapping(value = "/regpage")
  public String getPage(HttpServletRequest request) throws SQLException, ClassNotFoundException {
    roleRepository.getRoles();
    request.setAttribute("roles",roleRepository.getAllRole());
    return "register";
  }

  @PostMapping(value = "/register")
  public String addUser(@ModelAttribute UserToSave user, HttpServletRequest request) throws SQLException, ClassNotFoundException {
    userManipulate.Register(user);

    userRepository.selectAllUser();
    request.setAttribute("users", userRepository.getAllUsers());
    return "index";
  }

  @GetMapping(value = "/update")
  public String updateUser(@ModelAttribute UserToSave userToSave, HttpServletRequest request) throws SQLException, ClassNotFoundException {
    userManipulate.Update(userToSave);

    userRepository.selectAllUser();
    request.setAttribute("users", userRepository.getAllUsers());
    return "index";
  }


  @PostMapping(value = "/edit")
  public String updateUser(@RequestParam Integer id, HttpServletRequest request) throws SQLException, ClassNotFoundException {
    request.setAttribute("user", utils.getUser(id));
    roleRepository.getRoles();
    request.setAttribute("roles",roleRepository.getAllRole());
    return "edit";
  }

  @PostMapping(value = "/delete")
  public String deleteUser(Integer id, HttpServletRequest request) throws SQLException, ClassNotFoundException {
    userManipulate.Delete(id);
    userRepository.selectAllUser();
    request.setAttribute("users", userRepository.getAllUsers());
    return "index";
  }

  @GetMapping(value = "/search")
  public String searchUser(@RequestParam String name, String role, Integer age, HttpServletRequest request) throws SQLException, ClassNotFoundException {
    if (name.equals(""))
      name=null;
    if (role.equals(""))
      role=null;
    if (age==null)
      age=0;

    userManipulate.Search(name, role, age);
    request.setAttribute("users", userRepository.getAllUsers());
    return "search";
  }
}
