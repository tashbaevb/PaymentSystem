package com.example.PaymentSystem.mapper;

import com.example.PaymentSystem.dto.UserDto;
import com.example.PaymentSystem.dto.UserResponseDto;
import com.example.PaymentSystem.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public UserResponseDto responsetoDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    public User toEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public List<UserDto> toDtoList(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<UserResponseDto> responsetoDtoList(List<User> users) {
        return users.stream().map(this::responsetoDto).collect(Collectors.toList());
    }

}
