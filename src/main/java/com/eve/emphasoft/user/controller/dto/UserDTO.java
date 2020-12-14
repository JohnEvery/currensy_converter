package com.eve.emphasoft.user.controller.dto;

import com.eve.emphasoft.exchange.controller.dto.ExchangeDTO;
import com.eve.emphasoft.user.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {

    private Long userId;

    private List<ExchangeDTO> exchanges;

    public UserDTO(UserEntity userEntity) {
        userId = userEntity.getId();
    }
}
