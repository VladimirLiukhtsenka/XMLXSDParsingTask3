package com.liukhtenko.xmlxsdparsing.entity;

import com.liukhtenko.xmlxsdparsing.exception.CustomException;

public enum PeriodicalEnum {
    PAPERS("papers"),
    TITLE("title"),
    TYPE("type"),
    PAPER("paper"),
    COLORED("colored"),
    PAGE_SIZE("pageSize"),
    GLOSSY("glossy"),
    SUBSCRIPTION_INDEX("subscriptionIndex"),
    MONTHLY("monthly"),
    YEAR_OF_FOUNDATION("yearOfFoundation"),
    VISUAL_CHARACTERISTICS("visualCharacteristics");

    private String value;

    PeriodicalEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getNameByValue(String value) throws CustomException {
        for (PeriodicalEnum e : PeriodicalEnum.values()) {
            if (e.value == value) {
                return e.name();
            }
        }
        throw new CustomException("PeriodicalEnum does not have such a value: "+value);
    }
}
