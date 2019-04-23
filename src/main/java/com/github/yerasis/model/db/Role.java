package com.github.yerasis.model.db;

import lombok.Data;

@Data
public class Role {
  private Integer id;
  private Integer privelegeId;
  private String privelege;
  private String name;
}
