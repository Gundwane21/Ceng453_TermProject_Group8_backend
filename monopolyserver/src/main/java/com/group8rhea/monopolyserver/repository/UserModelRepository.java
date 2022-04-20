package com.group8rhea.monopolyserver.repository;

import com.group8rhea.monopolyserver.model.UserModelEntity;
import org.springframework.data.repository.CrudRepository;
import com.group8rhea.monopolyserver.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
//@Repository
//public interface UserModelRepository extends CrudRepository<UserModel, Integer> {
//    Optional<UserModel> findByUsername(String username);
//    Optional<UserModel> findByEmail(String email);
//}

@Repository
public interface UserModelRepository extends CrudRepository<UserModelEntity, Integer> {
    Optional<UserModelEntity> findByUsername(String username);
    Optional<UserModelEntity> findByEmail(String email);
    Optional<UserModelEntity> findByResettoken(Integer resettoken);
}
