package com.group8rhea.monopolyserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group8rhea.monopolyserver.service.LeaderboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leaderboard")
@Tag(name="Leaderboard API", description = "Retrieves Leaderboard")
public class LeaderboardController {
    private LeaderboardService leaderboardService;

    LeaderboardController(LeaderboardService leaderboardService){
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/alltimes")
    @Operation(summary = "", description = "Retrieves all of the scoreboard.")
    @ApiResponse(responseCode = "200", description = "Returns the username score mapping in JSON format.", content = @Content(mediaType = "application/json"))
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

    @GetMapping("/lastmonth")
    @Operation(summary = "", description = "Retrieves the scoreboard of the last month.")
    @ApiResponse(responseCode = "200", description = "Returns the username score mapping in JSON format.", content = @Content(mediaType = "application/json"))
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

    @GetMapping("/lastweek")
    @Operation(summary = "", description = "Retrieves the scoreboard of the last week.")
    @ApiResponse(responseCode = "200", description = "Returns the username score mapping in JSON format.", content = @Content(mediaType = "application/json"))
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

}
