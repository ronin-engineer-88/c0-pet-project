package source.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import source.constant.ResponseCode;
import source.constant.UserStatusConstant;
import source.dto.request.UserCreateDto;
import source.dto.request.UserUpdateDto;
import source.dto.response.PagingResponseDto;
import source.dto.response.UserResponse;
import source.entity.User;
import source.exception.BusinessException;
import source.mapper.UserMapper;
import source.repository.UserRepository;
import source.service.UserService;
import source.utils.PageableUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public PagingResponseDto<UserResponse> getUsers(String searchKey, int pageNo, int pageSize, String sortBy, String sortDir) {
        Pageable pageable = PageableUtils.getPageable(pageNo, pageSize, sortBy, sortDir);
        Page<User> products = userRepository.searchUsers(searchKey, pageable);
        List<User> listOfItems = products.getContent();
        List<UserResponse> content = listOfItems.stream().map(userMapper::toResponse).collect(Collectors.toList());
        return new PagingResponseDto<>(products, content);
    }

    @Override
    public UserResponse createUser(UserCreateDto userCreateDto) {
        if (userRepository.existsByUsername(userCreateDto.getUsername())) {
            throw new BusinessException(ResponseCode.BAD_REQUEST, "Username already exists");
        }
        User user = userMapper.toUser(userCreateDto);
        user.setStatus(UserStatusConstant.ACTIVE.getStatus());
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }


    @Override
    public UserResponse updateUser(Long id, UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new BusinessException(ResponseCode.BAD_REQUEST, "UserId does not exist in the database"));
        existingUser.setAge(userUpdateDto.getAge());
        existingUser.setStatus(userUpdateDto.getStatus());
        User savedUser = userRepository.save(existingUser);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new BusinessException(ResponseCode.BAD_REQUEST, "UserId does not exist in the database"));
        return userMapper.toResponse(existingUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new BusinessException(ResponseCode.BAD_REQUEST, "UserId does not exist in the database"));
        existingUser.setStatus(UserStatusConstant.INACTIVE.getStatus());
        userRepository.save(existingUser);
    }

}
