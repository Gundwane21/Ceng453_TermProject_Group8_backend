package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.repository.GameRecordStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class LeaderboardService {

    @Autowired
    private GameRecordStoreRepository gameRecordStoreRepository;

    public List<Map<String, Integer>> getLeaderboardOfAllTimes() {
        return gameRecordStoreRepository.getLeaderboardAllTimes();
    }

    public List<Map<String, Integer>> getLeaderboardOfLastWeek() {
        LocalDate today =  LocalDate.now();
        return gameRecordStoreRepository.getLeaderboardBetweenDates(Date.valueOf(today.minusDays(7)), Date.valueOf(today));
    }

    public List<Map<String, Integer>> getLeaderboardOfLastMonth() {
        LocalDate today =  LocalDate.now();
        return gameRecordStoreRepository.getLeaderboardBetweenDates(Date.valueOf(today.minusMonths(1)), Date.valueOf(today));
    }

}
