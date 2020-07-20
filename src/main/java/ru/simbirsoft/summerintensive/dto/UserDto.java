package ru.simbirsoft.summerintensive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.simbirsoft.summerintensive.models.Pixel;
import ru.simbirsoft.summerintensive.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private String country;
    private String city;

    public static UserDto from(User user) {
        return UserDto.builder().name(user.getName()).city(user.getCity()).country(user.getCountry())
                .email(user.getEmail()).build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}