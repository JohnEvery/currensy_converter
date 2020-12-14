package com.eve.emphasoft.user.controller;

import com.eve.emphasoft.user.controller.dto.UserDTO;
import com.eve.emphasoft.user.entity.UserEntity;
import com.eve.emphasoft.user.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserEntityController {

    private final UserEntityService userEntityService;

    @Autowired
    public UserEntityController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/user")
    public Long createUser() {
        return new UserDTO(userEntityService.saveUser()).getUserId();
    }

    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        UserEntity userById = userEntityService.getUserById(id);
        UserDTO userDTO = new UserDTO(userById);
        userDTO.setExchanges(userEntityService.mapExchange(userById));
        return userDTO;
    }
}
