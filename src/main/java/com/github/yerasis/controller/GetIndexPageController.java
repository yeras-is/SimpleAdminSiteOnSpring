package com.github.yerasis.controller;


import com.github.yerasis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class GetIndexPageController {


  @Autowired
  UserRepository userRepository;

  @GetMapping(value = "/index")
  public String getIndex(HttpServletRequest request) throws SQLException, ClassNotFoundException {

    userRepository.selectAllUser();

    request.setAttribute("users", userRepository.getAllUsers());
    return "index";
  }

}
