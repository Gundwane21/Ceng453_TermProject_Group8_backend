package com.group8rhea.monopolyserver.repository;

import com.group8rhea.monopolyserver.model.GameRecordScoreEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * This interface handles the queries on the joined table from game_record and game_score. It handles retrieval of the leaderboard.
 */
@Repository
public interface GameRecordStoreRepository extends CrudRepository<GameRecordScoreEntity, UUID> {


    /**
     * @return Returns the leaderboard considering all the games.
     */
    @Query(value =
            "SELECT um.username, ids.score " +
            "FROM ( " +
                    "SELECT gs.user_id id, SUM(gs.score) score " +
                    "FROM game_score gs, game_record gr " +
                    "WHERE gs.game_id = gr.game_id " +
                    "GROUP BY gs.user_id ) ids, user_model um " +
            "WHERE um.id = ids.id;", nativeQuery = true)
    List<Map<String, Long>> getLeaderboardAllTimes();

    /**
     * @param startDate Determines the start date of the game to be considered.
     * @param endDate Determines the end date of the game to be considered.
     * @return Returns the leaderboard considering games whose dates are between startDate and endDate.
     */
    @Query(value =
            "SELECT um.username, ids.score " +
                    "FROM ( " +
                    "SELECT gs.user_id id, SUM(gs.score) score " +
                    "FROM game_score gs, game_record gr " +
                    "WHERE gs.game_id = gr.game_id and gr.created_at >= :startDate and gr.created_at <= :endDate " +
                    "GROUP BY gs.user_id ) ids, user_model um " +
                    "WHERE um.id = ids.id;", nativeQuery = true)
    List<Map<String, Long>> getLeaderboardBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}