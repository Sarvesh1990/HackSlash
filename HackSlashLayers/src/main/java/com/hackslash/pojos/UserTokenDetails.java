package com.hackslash.pojos;

/**
 * Created by apple on 25/09/16.
 */
public class UserTokenDetails {
    private String flockToken;
    private String calendarApiAuthToken;
    private String calendarApiRefreshToken;
    private Long calendarTokenExpiry;

    public UserTokenDetails(String flockToken, String calendarApiAuthToken, String calendarApiRefreshToken, Long calendarTokenExpiry) {
        this.flockToken = flockToken;
        this.calendarApiAuthToken = calendarApiAuthToken;
        this.calendarApiRefreshToken = calendarApiRefreshToken;
        this.calendarTokenExpiry = calendarTokenExpiry;
    }

    public Long getCalendarTokenExpiry() {
        return calendarTokenExpiry;
    }

    public void setCalendarTokenExpiry(Long calendarTokenExpiry) {
        this.calendarTokenExpiry = calendarTokenExpiry;
    }

    public String getFlockToken() {
        return flockToken;
    }

    public void setFlockToken(String flockToken) {
        this.flockToken = flockToken;
    }

    public String getCalendarApiAuthToken() {
        return calendarApiAuthToken;
    }

    public void setCalendarApiAuthToken(String calendarApiAuthToken) {
        this.calendarApiAuthToken = calendarApiAuthToken;
    }

    public String getCalendarApiRefreshToken() {
        return calendarApiRefreshToken;
    }

    public void setCalendarApiRefreshToken(String calendarApiRefreshToken) {
        this.calendarApiRefreshToken = calendarApiRefreshToken;
    }
}
