package com.group8rhea.monopolyserver.repository;

import com.group8rhea.monopolyserver.model.UserModelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
/*
* This repository is used to reach the User table using JPA and hibernate
* UserModelEntity is used.
* Has the following additional APIS
*   Optional<UserModelEntity> findByUsername(String username);
*   Optional<UserModelEntity> findByEmail(String email);
*   Optional<UserModelEntity> findByResettoken(Integer resettoken);
* */

@Repository
public interface UserModelRepository extends CrudRepository<UserModelEntity, Integer> {
    Optional<UserModelEntity> findByUsername(String username);
    Optional<UserModelEntity> findByEmail(String email);
    Optional<UserModelEntity> findByResettoken(Integer resettoken);
}
