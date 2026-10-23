package com.fixmate.backend_application.mapper;
import com.fixmate.backend_application.dto.UserRegistrationRequest;
import com.fixmate.backend_application.dto.UserResponse;
import com.fixmate.backend_application.entity.User;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    //1. toEntity, 2.DTO, 3. toDTOList
    public User toEntity(UserRegistrationRequest dto){
        if(dto==null){return null;}
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setPhone(dto.getPhone());
        user.setCreatedAt(dto.getCreatedAt());
        user.setActive(dto.getActive());
        user.setVerified(dto.getVerified());
        user.setLocation(dto.getLocation());
        user.setIdNumber(dto.getIdNumber());
        user.setOccupation(dto.getOccupation());
        user.setAvailable(dto.getAvailable());
        user.setBiography(dto.getBiography());
        return user;
    }

    public UserResponse toDTO(User user){
        if(user==null){return null;}
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFullName(user.getFullName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());
        userResponse.setPhone(user.getPhone());
        userResponse.setLocation(user.getLocation());
        userResponse.setIdNumber(user.getIdNumber());
        userResponse.setStatus(user.getStatus());
        userResponse.setActive(user.getActive());
        userResponse.setVerified(user.getVerified());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setOccupation(user.getOccupation());
        userResponse.setAvailable(user.getAvailable());
        userResponse.setBiography(user.getBiography());
        return userResponse;
    }

    public List<UserResponse> toDTOList(List<User> users){
        if(users.isEmpty()){
            return Collections.emptyList();
        }
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
