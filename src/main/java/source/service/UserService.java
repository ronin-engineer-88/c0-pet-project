package source.service;

import source.dto.request.UserCreateDto;
import source.dto.request.UserUpdateDto;
import source.dto.response.PagingResponseDto;
import source.dto.response.UserResponse;

public interface UserService {

    UserResponse createUser(UserCreateDto userCreateDto);

    UserResponse updateUser(Long id, UserUpdateDto userUpdateDto);

    UserResponse getUserById(Long id);

    void deleteUserById(Long id);

    PagingResponseDto<UserResponse> getUsers(String searchKey, int pageNo, int pageSize, String sortBy, String sortDir);
}
