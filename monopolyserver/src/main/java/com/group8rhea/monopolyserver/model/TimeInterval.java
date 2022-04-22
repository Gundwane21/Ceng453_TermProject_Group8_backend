package com.group8rhea.monopolyserver.model;

/**
 * Represents time intervals for the game dates.
 * ALL_TIMES: Games played on any date
 * LAST_MONTH: Games played between today and one month before today
 * LAST_WEEK: Games played between today and one week before today
 */
public enum TimeInterval {
    ALL_TIMES, LAST_MONTH, LAST_WEEK
}
