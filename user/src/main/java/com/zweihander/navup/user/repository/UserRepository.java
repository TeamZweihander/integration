package com.zweihander.navup.user.repository;

import com.zweihander.navup.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Spring Data JPA provides implementation of CRUD operations at runtime

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //@Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

}