package com.group8rhea.monopolyserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group8rhea.monopolyserver.service.LeaderboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
    private LeaderboardService leaderboardService;

    LeaderboardController(LeaderboardService leaderboardService){
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/alltimes")
    public String getLeaderboardOfAllTimes() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Map<String, Integer>> leaderboard = leaderboardService.getLeaderboardOfAllTimes();
        String json = null;
        try {
            json = mapper.writeValueAsString(leaderboard);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @GetMapping("/lastweek")
    public String getLeaderboardOfLastWeek() {
        getLeaderboardOfAllTimes();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Map<String, Integer>> leaderboard = leaderboardService.getLeaderboardOfLastWeek();
        String json = null;
        try {
            json = mapper.writeValueAsString(leaderboard);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @GetMapping("/lastmonth")
    public String getLeaderboardofLastMonth() {
        getLeaderboardOfAllTimes();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Map<String, Integer>> leaderboard = leaderboardService.getLeaderboardOfLastMonth();
        String json = null;
        try {
            json = mapper.writeValueAsString(leaderboard);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
