package com.eve.emphasoft.user.service;

import com.eve.emphasoft.exceptions.UserNotFoundException;
import com.eve.emphasoft.exchange.controller.dto.ExchangeDTO;
import com.eve.emphasoft.exchange.entity.Exchange;
import com.eve.emphasoft.user.entity.UserEntity;
import com.eve.emphasoft.user.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;

    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    public UserEntity saveUser() {
        return userEntityRepository.save(new UserEntity());
    }

    public UserEntity getUserById(Long id) {
        return userEntityRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException("Unable to find user with id: " + id));
    }

    public UserEntity updateUser(Long userId, List<Exchange> exchanges) {
        UserEntity userById = getUserById(userId);
        UserEntity userEntity = new UserEntity(exchanges);
        userEntity.setId(userId);
        userEntity.setExchanges(userById.getExchanges());
        return userEntityRepository.save(userEntity);
    }

    public List<ExchangeDTO> mapExchange(UserEntity userEntity) {
        return userEntity.getExchanges()
                .stream()
                .map(ExchangeDTO::new)
                .collect(Collectors.toList());
    }
}
