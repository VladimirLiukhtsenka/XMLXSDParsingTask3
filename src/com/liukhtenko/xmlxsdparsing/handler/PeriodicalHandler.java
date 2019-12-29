package com.liukhtenko.xmlxsdparsing.handler;

import com.liukhtenko.xmlxsdparsing.entity.Paper;
import com.liukhtenko.xmlxsdparsing.entity.PeriodicalEnum;
import com.liukhtenko.xmlxsdparsing.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class PeriodicalHandler extends DefaultHandler {
    private static final String NAME = "paper";
    static Logger logger = LogManager.getLogger();
    private Set<Paper> papers;
    private Paper current = null;
    private PeriodicalEnum currentEnum = null;
    private EnumSet<PeriodicalEnum> withText;

    public PeriodicalHandler() {
        papers = new HashSet<>();
        withText = EnumSet.range(PeriodicalEnum.COLORED, PeriodicalEnum.YEAR_OF_FOUNDATION);
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (NAME.equals(localName)) {
            current = new Paper();
            current.setTitle(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                current.setType(attrs.getValue(1));
            }
        } else {
            String name = null;
            try {
                name = PeriodicalEnum.getNameByValue(localName);
            } catch (CustomException e) {
                logger.log(Level.ERROR, "Impossible to create: " + e);
            }
            PeriodicalEnum temp = PeriodicalEnum.valueOf(name);
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (NAME.equals(localName)) {
            papers.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case COLORED:
                    current.getVisualCharacteristics().setColored(Boolean.parseBoolean(s));
                    break;
                case PAGE_SIZE:
                    current.getVisualCharacteristics().setPageSize(Integer.parseInt(s));
                    break;
                case GLOSSY:
                    current.getVisualCharacteristics().setGlossy(Boolean.parseBoolean(s));
                    break;
                case SUBSCRIPTION_INDEX:
                    current.setSubscriptionIndex(s);
                    break;
                case MONTHLY:
                    current.setMonthly(Boolean.parseBoolean(s));
                    break;
                case YEAR_OF_FOUNDATION:
                    current.setYearOfFoundation(Integer.parseInt(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(  // FIXME: 28.12.2019 
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
