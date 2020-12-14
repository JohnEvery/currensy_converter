package com.eve.emphasoft.user.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    private Long userId;

    private List<Long> exchangeId;

}
