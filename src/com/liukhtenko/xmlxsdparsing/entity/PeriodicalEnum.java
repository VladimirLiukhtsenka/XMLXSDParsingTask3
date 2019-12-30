package com.liukhtenko.xmlxsdparsing.entity;

import com.liukhtenko.xmlxsdparsing.constant.CustomConstant;
import com.liukhtenko.xmlxsdparsing.exception.CustomException;

public enum PeriodicalEnum {
    PAPERS(CustomConstant.PAPERS),
    TITLE(CustomConstant.TITLE),
    TYPE(CustomConstant.TYPE),
    PAPER(CustomConstant.PAPER),
    COLORED(CustomConstant.COLORED),
    PAGE_SIZE(CustomConstant.PAGE_SIZE),
    GLOSSY(CustomConstant.GLOSSY),
    SUBSCRIPTION_INDEX(CustomConstant.SUBSCRIPTION_INDEX),
    MONTHLY(CustomConstant.MONTHLY),
    YEAR_OF_FOUNDATION(CustomConstant.YEAR_OF_FOUNDATION),
    VISUAL_CHARACTERISTICS(CustomConstant.VISUAL_CHARACTERISTICS);

    private String value;

    PeriodicalEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getNameByValue(String value) throws CustomException {
        for (PeriodicalEnum e : PeriodicalEnum.values()) {
            if (e.value.equals(value)) {
                return e.name();
            }
        }
        throw new CustomException("PeriodicalEnum does not have such value: " + value);
    }
}
