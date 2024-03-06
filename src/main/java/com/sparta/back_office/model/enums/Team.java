package com.sparta.back_office.model.enums;

public enum Team {
    CURRICULUM("커리큘럼"),
    MARKETING("마케팅"),
    DEV("개발");

    private final String teamName;

    Team(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }
}
