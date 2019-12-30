package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.constant.CustomConstant;
import com.liukhtenko.xmlxsdparsing.entity.Paper;
import com.liukhtenko.xmlxsdparsing.entity.PeriodicalEnum;
import com.liukhtenko.xmlxsdparsing.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PeriodicalStAXBuilder extends AbstractPeriodicalBuilder {
    private static Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;

    public PeriodicalStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public void buildSetPapers(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (PeriodicalEnum.valueOf(name.toUpperCase()) == PeriodicalEnum.PAPER) {
                        Paper paper = buildPaper(reader);
                        papers.add(paper);
                    }
                }
            }
        } catch (XMLStreamException | CustomException e) {
            logger.log(Level.ERROR, "Parsing error: " + e);
        } catch (FileNotFoundException ex) {
            logger.log(Level.ERROR, "File " + fileName + " not found: " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.log(Level.ERROR, "Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Paper buildPaper(XMLStreamReader reader) throws XMLStreamException, CustomException {
        String name;
        Paper paper = new Paper();
        paper.setTitle(reader.getAttributeValue(null, PeriodicalEnum.TITLE.getValue()));
        String attribute = reader.getAttributeValue(null, PeriodicalEnum.TYPE.getValue());
        if (attribute.length() > 0) {
            paper.setType(attribute);
        } else {
            paper.setType(CustomConstant.PERIODICAL);
        }
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = PeriodicalEnum.getNameByValue(reader.getLocalName());
                    switch (PeriodicalEnum.valueOf(name)) {
                        case SUBSCRIPTION_INDEX:
                            paper.setSubscriptionIndex(getXMLText(reader));
                            break;
                        case MONTHLY:
                            boolean monthly = Boolean.parseBoolean(getXMLText(reader));
                            paper.setMonthly(monthly);
                            break;
                        case YEAR_OF_FOUNDATION:
                            int year = Integer.parseInt(getXMLText(reader));
                            paper.setYearOfFoundation(year);
                            break;
                        case VISUAL_CHARACTERISTICS:
                            paper.setVisualCharacteristics(getXMLVisualCarat(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = PeriodicalEnum.getNameByValue(reader.getLocalName());
                    if (PeriodicalEnum.valueOf(name) == PeriodicalEnum.PAPER) {
                        return paper;
                    }
                    break;
            }
        }
        throw new CustomException("Unknown element in tag Paper");
    }

    private Paper.VisualCharacteristics getXMLVisualCarat(XMLStreamReader reader) throws XMLStreamException, CustomException {
        Paper.VisualCharacteristics visualCharacteristics = new Paper.VisualCharacteristics();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = PeriodicalEnum.getNameByValue(reader.getLocalName());
                    switch (PeriodicalEnum.valueOf(name)) {
                        case COLORED:
                            boolean colored = Boolean.parseBoolean(getXMLText(reader));
                            visualCharacteristics.setColored(colored);
                            break;
                        case PAGE_SIZE:
                            int pageSize = Integer.parseInt(getXMLText(reader));
                            visualCharacteristics.setPageSize(pageSize);
                            break;
                        case GLOSSY:
                            boolean glossy = Boolean.parseBoolean(getXMLText(reader));
                            visualCharacteristics.setGlossy(glossy);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = PeriodicalEnum.getNameByValue(reader.getLocalName());
                    if (PeriodicalEnum.valueOf(name) == PeriodicalEnum.VISUAL_CHARACTERISTICS) {
                        return visualCharacteristics;
                    }
                    break;
            }
        }
        throw new CustomException("Unknown element in tag VisualCharacteristics");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
