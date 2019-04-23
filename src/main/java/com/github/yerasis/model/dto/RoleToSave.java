package com.github.yerasis.model.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class RoleToSave {
  private Integer id;
  private String name;
  private HashMap<Integer,String> privelege;

  public void setPrivelege(Integer id, String name) {
    this.privelege.put(id, name);
  }
}
