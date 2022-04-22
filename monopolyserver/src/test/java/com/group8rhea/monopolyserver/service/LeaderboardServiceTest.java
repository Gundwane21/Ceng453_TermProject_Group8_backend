package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.model.TimeInterval;
import com.group8rhea.monopolyserver.repository.GameRecordStoreRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@SpringBootTest
public class LeaderboardServiceTest {

    @Autowired
    LeaderboardService leaderboardService;

    @MockBean
    GameRecordStoreRepository gameRecordStoreRepository;

    @Test
    @DisplayName("Test retrieving leaderboard for All Times")
    public void getLeaderboardAllTimesTest() {
        List<Map<String, Long>> result = new ArrayList<>();
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("KeremTheMonopolyKing", (long) 100);
        Map<String, Long> map2 = new HashMap<String, Long>();
        map2.put("GorkemOfRivia", (long) 180);
        Map<String, Long> map3 = new HashMap<String, Long>();
        map2.put("John", (long) 360);
        result.add(map);
        result.add(map2);
        result.add(map3);
        when(gameRecordStoreRepository.getLeaderboardAllTimes()).thenReturn(result);
        List<Map<String, Long>> leaderboardAllTimes = leaderboardService.getLeaderboard(TimeInterval.ALL_TIMES);
        Assert.assertEquals(result, leaderboardAllTimes);
    }
}
