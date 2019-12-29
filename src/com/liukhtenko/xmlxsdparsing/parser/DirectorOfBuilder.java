package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.entity.Paper;
import com.liukhtenko.xmlxsdparsing.validator.ValidatorSAXXSD;
import org.xml.sax.SAXException;

import java.util.Set;

public class DirectorOfBuilder {
    public static Set<Paper> createPeriodical(AbstractPeriodicalBuilder builder, String fileName) {
        try {
            ValidatorSAXXSD.runValidation();
            builder.buildSetPapers(fileName);

        } catch (SAXException e) {
            e.printStackTrace();  // FIXME: 28.12.2019 
        }
    //    builder.buildSetPapers(fileName);
        return builder.getPapers();
    }
}
