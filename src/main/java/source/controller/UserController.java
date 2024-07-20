package source.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import source.dto.request.UserRequest;
import source.dto.response.BaseResponse;
import source.dto.response.UserResponse;
import source.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public BaseResponse<?> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return BaseResponse.ofSucceeded(userResponse);
    }

    @PutMapping("/{id}")
    public BaseResponse<?> createUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateUser(id, userRequest);
        return BaseResponse.ofSucceeded(userResponse);
    }

    @GetMapping("/{id}")
    public BaseResponse<?> getUserById(@PathVariable Long id) {
        return BaseResponse.ofSucceeded(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return BaseResponse.ofSucceeded();
    }



}
