package source.mapper;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import source.constant.UserStatusConstant;
import source.dto.request.UserRequest;
import source.dto.response.UserResponse;
import source.entity.User;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;

    public User toUser(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    public UserResponse toResponse(User user) {
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setStatus(UserStatusConstant.fromValue(user.getStatus()).toString());
        return userResponse;
    }


}

