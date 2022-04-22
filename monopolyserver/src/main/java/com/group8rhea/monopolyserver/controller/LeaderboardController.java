package com.group8rhea.monopolyserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.group8rhea.monopolyserver.model.TimeInterval;
import com.group8rhea.monopolyserver.service.LeaderboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Tag(name="Leaderboard API", description = "Retrieves Leaderboard")
@RestController
public class LeaderboardController {
    private LeaderboardService leaderboardService;

    LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/api/leaderboard")
    @Operation(summary = "", description = "Retrieves the leaderboard given a time choice.")
    @ApiResponse(responseCode = "200", description = "Returns the username score mapping in JSON format.", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Timechoice is invalid. It should be one of {lastMonth, lastWeek, allTimes}.", content = @Content(mediaType = "application/json"))
    @Parameter(name = "timeChoice", description = "Determines the time interval of the leaderboard resource. Possible values are: {lastMonth, lastWeek, allTimes}.lastMonth returns the leaderboard considering only the games that are played between today and one month before today. lastWeek returns the leaderboard considering only the games that are played between today and one week before today. allTimes returns the leaderboard considering all of the games played. ")
    public String getLeaderboard(@RequestParam("timeChoice") String timeChoice) {
        TimeInterval timeInterval = null;
        switch (timeChoice) {
            case "lastMonth":
                timeInterval = TimeInterval.LAST_MONTH;
                break;
            case "lastWeek":
                timeInterval = TimeInterval.LAST_WEEK;
                break;
            case "allTimes":
                timeInterval = TimeInterval.ALL_TIMES;
                break;
            default:
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "TimeChoice is not valid!"
                );
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        List<Map<String, Long>> leaderboard = leaderboardService.getLeaderboard(timeInterval);
        String json = null;
        try {
            json = mapper.writeValueAsString(leaderboard);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
