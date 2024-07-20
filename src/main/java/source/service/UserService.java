package source.service;

import source.dto.request.UserRequest;
import source.dto.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    UserResponse getUserById(Long id);

    void deleteUserById(Long id);
}
