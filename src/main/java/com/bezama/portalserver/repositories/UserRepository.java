package com.bezama.portalserver.repositories;

import com.bezama.portalserver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
