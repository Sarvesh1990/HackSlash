package com.hackslash.pojos;

/**
 * Created by apple on 25/09/16.
 */
public class UserTokenDetails {
    private String flockToken;
    private String calendarApiAuthToken;
    private String calendarApiRefreshToken;
    private Long calendarTokenExpiry;
    private String calendarId;
    private boolean isActive;

    public UserTokenDetails(String flockToken, String calendarApiAuthToken, String calendarApiRefreshToken, Long calendarTokenExpiry, String calendarId, boolean isActive) {
        this.flockToken = flockToken;
        this.calendarApiAuthToken = calendarApiAuthToken;
        this.calendarApiRefreshToken = calendarApiRefreshToken;
        this.calendarTokenExpiry = calendarTokenExpiry;
        this.calendarId = calendarId;
        this.isActive = isActive;
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

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
