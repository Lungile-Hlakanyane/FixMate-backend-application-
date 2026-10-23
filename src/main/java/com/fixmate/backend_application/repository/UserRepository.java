package com.fixmate.backend_application.repository;
import com.fixmate.backend_application.entity.User;
import com.fixmate.backend_application.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByRole(Role role); //will call this method in the service class;
    List<User> findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
    long countByRole(Role role);
    //custom method to get available pros
    List<User> findByAvailable(Boolean available);
    List<User> findByOccupation(String occupation);
}
