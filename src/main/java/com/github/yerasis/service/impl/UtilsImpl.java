package com.github.yerasis.service.impl;

import com.github.yerasis.model.dto.UserToSave;
import com.github.yerasis.repository.UserRepository;
import com.github.yerasis.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilsImpl implements Utils {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserToSave getUser(Integer id) {
    return (userRepository.getUsersMap().get(id));
  }


}
