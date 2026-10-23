package com.fixmate.backend_application.service;
import com.fixmate.backend_application.dto.UserRegistrationRequest;
import com.fixmate.backend_application.dto.UserResponse;
import com.fixmate.backend_application.enums.Role;
import java.util.List;

public interface UserService {

    UserResponse register(UserRegistrationRequest req);
    UserResponse toggleActive(Long id);
    UserResponse verifyPro(Long id);
    void delete(Long id);
    UserResponse login(String email, String password);
    List<UserResponse> getAllUsers();
    long countByRole(Role role);
    long getTotalUsers();
    UserResponse banUser(Long id);
    UserResponse unbanUser(Long id);
    UserResponse getUserDetailsById(Long userId);
    UserResponse updateUserDetailsById(Long userId, UserRegistrationRequest userRegistrationRequest);
    List<UserResponse> getUsersByRole(Role role);
}
