package source.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import source.constant.UserStatusConstant;
import source.dto.request.UserRequest;
import source.dto.response.UserResponse;
import source.entity.User;
import source.exception.BusinessException;
import source.mapper.UserMapper;
import source.repository.UserRepository;
import source.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        user.setStatus(UserStatusConstant.ACTIVE.getStatus());
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }


    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new BusinessException(400, "UserId does not exist in the database", HttpStatus.BAD_REQUEST));
        existingUser.setAge(userRequest.getAge());
        existingUser.setStatus(userRequest.getStatus());
        User savedUser = userRepository.save(existingUser);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new BusinessException(400, "UserId does not exist in the database", HttpStatus.BAD_REQUEST));
        return userMapper.toResponse(existingUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new BusinessException(400, "UserId does not exist in the database", HttpStatus.BAD_REQUEST));
        existingUser.setStatus(UserStatusConstant.INACTIVE.getStatus());
        userRepository.save(existingUser);
    }

}
