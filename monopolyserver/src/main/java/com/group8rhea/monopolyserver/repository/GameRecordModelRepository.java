package com.group8rhea.monopolyserver.repository;

import com.group8rhea.monopolyserver.model.GameRecordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface handles storing games into game_record table.
 */
@Repository
public interface GameRecordModelRepository extends CrudRepository<GameRecordEntity, Integer> {

}


