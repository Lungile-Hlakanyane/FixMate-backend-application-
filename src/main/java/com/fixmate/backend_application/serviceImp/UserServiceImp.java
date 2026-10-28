package com.fixmate.backend_application.serviceImp;
import com.fixmate.backend_application.dto.UserRegistrationRequest;
import com.fixmate.backend_application.dto.UserResponse;
import com.fixmate.backend_application.entity.User;
import com.fixmate.backend_application.enums.Role;
import com.fixmate.backend_application.enums.UserStatus;
import com.fixmate.backend_application.exceptions.EmailAlreadyRegisteredException;
import com.fixmate.backend_application.exceptions.InvalidCredentialsException;
import com.fixmate.backend_application.exceptions.InvalidEnumException;
import com.fixmate.backend_application.exceptions.UserNotFoundException;
import com.fixmate.backend_application.mapper.UserMapper;
import com.fixmate.backend_application.repository.UserRepository;
import com.fixmate.backend_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserRegistrationRequest req) {
        if(userRepository.existsByEmail(req.getEmail())){
            throw new EmailAlreadyRegisteredException(req.getEmail());
        }
        User user = new User();
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail().toLowerCase());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole());
        user.setPhone(req.getPhone());
        user.setLocation(req.location);
        user.setIdNumber(req.getIdNumber());
        user.setVerified(true);//TODO: change this logic later after email/otp implemented
        user.setActive(true); //TODO: change this logic later after email/otp implemented
        user = userRepository.save(user);
        return mapper.toDTO(user);
    }

    @Override
    public UserResponse toggleActive(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        user.markAsActive();
        User updated = userRepository.save(user);
        return mapper.toDTO(updated);
    }

    @Override
    public UserResponse verifyPro(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        user.markAsVerified();
        User updated = userRepository.save(user);
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse login(String email, String password) {
        User user = userRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new InvalidCredentialsException(email));
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException(email);
        }
        if (!user.getActive()) {
            throw new RuntimeException("Account deactivated");
        }
        return mapper.toDTO(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return mapper.toDTOList(userRepository.findAll());
    }

    @Override
    public long countByRole(Role role) {
       List<User> users = userRepository.findByRole(role);
       if(users.equals(Role.CUSTOMER) || users.equals(Role.PRO) || users.equals(Role.ADMIN)){
           throw new InvalidEnumException(role);
       }
       return userRepository.countByRole(role);
    }

    @Override
    public long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public UserResponse banUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        user.setStatus(UserStatus.BANNED);
        User bannedUser = userRepository.save(user);
        return mapper.toDTO(bannedUser);
    }

    @Override
    public UserResponse unbanUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        user.setStatus(UserStatus.ACTIVE);
        User bannedUser = userRepository.save(user);
        return mapper.toDTO(bannedUser);
    }

    @Override
    public UserResponse getUserDetailsById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
        return mapper.toDTO(user);
    }

    @Override
    public UserResponse updateUserDetailsById(Long userId, UserRegistrationRequest dto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
        user.setFullName(dto.getFullName());
        user.setLocation(dto.getLocation());
        user.setPhone(dto.getPhone());
        user.setOccupation(dto.getOccupation());
        user.setAvailable(dto.getAvailable());
        user.setBiography(dto.getBiography());
        User updated = userRepository.save(user);
        return mapper.toDTO(updated);
    }

    @Override
    public List<UserResponse> getUsersByRole(Role role) {
        List<User> users = userRepository.findByRole(role);
        if(users.equals(Role.CUSTOMER) || users.equals(Role.ADMIN) || users.equals(Role.PRO)){
            throw new InvalidEnumException(role);
        }
        return mapper.toDTOList(userRepository.findByRole(role));
    }
}
