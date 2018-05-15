package com.bezama.portalserver.repositories;

import com.bezama.portalserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findByUserName(String username);
}
