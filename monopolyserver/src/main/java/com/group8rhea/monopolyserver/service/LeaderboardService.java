package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.model.TimeInterval;
import com.group8rhea.monopolyserver.repository.GameRecordStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Map;


/**
 * This class retrieves the leaderboard for a specific time choice. It utilizes the GameRecordStoreRepository interface for this purpose.
 */
@Service
public class LeaderboardService {


    @Autowired
    private GameRecordStoreRepository gameRecordStoreRepository;

    /**
     * @return Returns the leaderboard considering all the games that are played.
     */
    private List<Map<String, Long>> getLeaderboardOfAllTimes() {
        return gameRecordStoreRepository.getLeaderboardAllTimes();
    }

    /**
     * @return Returns the leaderboard considering the games that are played between today and one week before today.
     */
    private List<Map<String, Long>> getLeaderboardOfLastWeek() {
        LocalDate today =  LocalDate.now();
        return gameRecordStoreRepository.getLeaderboardBetweenDates(Date.valueOf(today.minusDays(7)), Date.valueOf(today));
    }

    /**
     * @return Returns the leaderboard considering the games that are played between today and one month before today.
     */
    private List<Map<String, Long>> getLeaderboardOfLastMonth() {
        LocalDate today =  LocalDate.now();
        return gameRecordStoreRepository.getLeaderboardBetweenDates(Date.valueOf(today.minusMonths(1)), Date.valueOf(today));
    }

    /**
     * @param timeInterval determines the interval for the dates of games to be included in leaderboard.
     * @return Returns the leaderboard according to given timeInterval.
     */
    public List<Map<String, Long>> getLeaderboard(TimeInterval timeInterval) {
        switch (timeInterval) {
            case ALL_TIMES:
                return getLeaderboardOfAllTimes();
            case LAST_MONTH:
                return getLeaderboardOfLastMonth();
            case LAST_WEEK:
                return getLeaderboardOfLastWeek();
            default:
                return null;
        }
    }
}
