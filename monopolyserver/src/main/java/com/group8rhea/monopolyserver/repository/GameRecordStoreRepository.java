package com.group8rhea.monopolyserver.repository;

import com.group8rhea.monopolyserver.model.GameRecordScoreEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface GameRecordStoreRepository extends CrudRepository<GameRecordScoreEntity, UUID>  {
    @Query()
    List<GameRecordScoreEntity> findGameRecordScoreEntitiesByCreatedAtBetween(Date startDate, Date endDate);
    List<GameRecordScoreEntity> getAllByGameIdNotNull();
}