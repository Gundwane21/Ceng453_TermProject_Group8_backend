package com.group8rhea.monopolyserver.repository;

import com.group8rhea.monopolyserver.model.UserModelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
* This repository is used to reach the User table using JPA and hibernate
* UserModelEntity is used.
* */

@Repository
public interface UserModelRepository extends CrudRepository<UserModelEntity, Integer> {
    /**
     *
     * @param username - username of the user
     * @return Optional - -Optional of UserModelEntity
     */
    Optional<UserModelEntity> findByUsername(String username);
    /**
     *
     * @param email - email of the user
     * @return Optional - Optional of UserModelEntity
     */
    Optional<UserModelEntity> findByEmail(String email);
    /**
     *
     * @param resettoken - resettoken of the user
     * @return Optional - Optional of UserModelEntity
     */
    Optional<UserModelEntity> findByResettoken(Integer resettoken);
}
