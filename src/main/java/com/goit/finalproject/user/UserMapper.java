package com.goit.finalproject.user;

import com.goit.finalproject.note.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto mapEntityToDto(User source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        UserDto target = new UserDto();
        target.setId(source.getId());
        target.setUsername(source.getUsername());
        target.setPassword(source.getPassword());
        return target;
    }

    @Override
    public User mapDtoToEntity(UserDto source) {
        if (isNull(source)) {
            return null;
        }
        User target = new User();
        target.setId(source.getId());
        target.setUsername(source.getUsername());
        target.setPassword(source.getPassword());
        return target;
    }
}
