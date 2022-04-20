package com.group8rhea.monopolyserver.service;

import com.group8rhea.monopolyserver.repository.GameRecordStoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;


public class LeaderboardServiceTest {

    @Autowired
    LeaderboardService leaderboardService;

    @MockBean
    GameRecordStoreRepository gameRecordStoreRepository;

    @Test
    public void getLeaderboardAllTimesTest() {
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        List<Map<String, Long>> result = new ArrayList<>();
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("Kerem", (long) 100);
        map.put("Gorkem", (long) 130);
        map.put("Jonathan", (long) 80);
        map.put("Melis", (long) 1463);
        result.add(map);
        Map<String, Long> map2 = new HashMap<String, Long>();
        map2.put("Player1", (long) 18);
        map2.put("Player2", (long) 17);
        map2.put("Player3", (long) 9);
        result.add(map2);
//        when(gameRecordStoreRepository.getLeaderboardAllTimes()).thenReturn(result);
//        ResponseDTO returnedValue = (ResponseDTO) scoreBoardService.getScoreBoard(hashMap).getSecond();
//        Assert.assertEquals(result, returnedValue.getData());
    }
}
