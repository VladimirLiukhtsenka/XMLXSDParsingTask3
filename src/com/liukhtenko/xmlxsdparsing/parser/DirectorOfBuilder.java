package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.entity.Paper;
import com.liukhtenko.xmlxsdparsing.validator.ValidatorSAXXSD;

import java.util.Set;

public class DirectorOfBuilder {
    public static Set<Paper> createPeriodical(AbstractPeriodicalBuilder builder, String fileName) {
        ValidatorSAXXSD.runValidation();
        builder.buildSetPapers(fileName);
        return builder.getPapers();
    }
}
