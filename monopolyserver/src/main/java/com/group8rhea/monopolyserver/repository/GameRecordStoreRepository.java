package com.group8rhea.monopolyserver.repository;

import com.group8rhea.monopolyserver.model.GameRecordScoreEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface GameRecordStoreRepository extends CrudRepository<GameRecordScoreEntity, UUID> {

    @Query(value =
            "SELECT um.username, ids.score " +
            "FROM ( " +
                    "SELECT gs.user_id id, SUM(gs.score) score " +
                    "FROM game_score gs, game_record gr " +
                    "WHERE gs.game_id = gr.game_id " +
                    "GROUP BY gs.user_id ) ids, user_model um " +
            "WHERE um.id = ids.id;", nativeQuery = true)
    List<Map<String, Integer>> getLeaderboardAllTimes();

    @Query(value =
            "SELECT um.username, ids.score " +
                    "FROM ( " +
                    "SELECT gs.user_id id, SUM(gs.score) score " +
                    "FROM game_score gs, game_record gr " +
                    "WHERE gs.game_id = gr.game_id and gr.created_at >= :startDate and gr.created_at <= :endDate " +
                    "GROUP BY gs.user_id ) ids, user_model um " +
                    "WHERE um.id = ids.id;", nativeQuery = true)
    List<Map<String, Integer>> getLeaderboardBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}