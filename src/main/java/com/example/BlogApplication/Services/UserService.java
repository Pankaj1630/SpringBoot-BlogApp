package com.example.BlogApplication.Services;

import com.example.BlogApplication.Payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO, Integer id);
    UserDTO getUserById(Integer id);
    List<UserDTO> getAllUser();
    void deleteUser(Integer id);
}

