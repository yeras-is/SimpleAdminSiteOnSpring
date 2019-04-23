package com.github.yerasis.controller;

import com.github.yerasis.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@RequestMapping(value = "/role")
@Controller
public class RoleController {

  @Autowired
  RoleRepository roleRepository;

  @GetMapping(value = "/roles")
  public String getRolePages(HttpServletRequest request) throws SQLException, ClassNotFoundException {
    roleRepository.getRoles();
    request.setAttribute("roles",roleRepository.getAllRole());
    return "role";
  }

  @PostMapping(value = "/add")
  public String addRole(@RequestParam String name , Integer read, Integer write, Integer delete, Integer edit) throws SQLException, ClassNotFoundException {
    Integer id = roleRepository.addRole(name);
    if (read!=null){
      roleRepository.addPrivilege(id,read);
    }
    if (write!=null){
      roleRepository.addPrivilege(id,write);
    }
    if(delete!=null){
      roleRepository.addPrivilege(id,delete);
    }
    if (edit!=null){
      roleRepository.addPrivilege(id,edit);
    }
    return "role";
  }

}
