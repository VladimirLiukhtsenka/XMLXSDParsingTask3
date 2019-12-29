package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.entity.Paper;
import com.liukhtenko.xmlxsdparsing.entity.PeriodicalEnum;
import com.liukhtenko.xmlxsdparsing.exception.CustomException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PeriodicalStAXBuilder extends AbstractPeriodicalBuilder{
    
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
// StAX parsing
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
        } catch (XMLStreamException | CustomException ex) {  // FIXME: 28.12.2019
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Paper buildPaper(XMLStreamReader reader) throws XMLStreamException, CustomException {
        Paper paper = new Paper();
        paper.setTitle(reader.getAttributeValue(null, PeriodicalEnum.TITLE.getValue()));
        paper.setType(reader.getAttributeValue(null, PeriodicalEnum.TYPE.getValue())); // проверить на null fixme
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    //       name = reader.getLocalName();
                    name = PeriodicalEnum.getNameByValue(reader.getLocalName());

                    switch (PeriodicalEnum.valueOf(name)) {
                        case SUBSCRIPTION_INDEX:
                            paper.setSubscriptionIndex(getXMLText(reader));
                            break;
                        case MONTHLY:
                            name = getXMLText(reader);
                            paper.setMonthly(Boolean.parseBoolean(name));
                            break;
                        case YEAR_OF_FOUNDATION:
                            name = getXMLText(reader);
                            paper.setYearOfFoundation(Integer.parseInt(name));
                            break;
                        case VISUAL_CHARACTERISTICS:
                            paper.setVisualCharacteristics(getXMLVisualCharact(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    //        name = reader.getLocalName();
                    name = PeriodicalEnum.getNameByValue(reader.getLocalName());
                    if (PeriodicalEnum.valueOf(name) == PeriodicalEnum.PAPER) {
                        return paper;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Student");
    }

    private Paper.VisualCharacteristics getXMLVisualCharact(XMLStreamReader reader) throws XMLStreamException, CustomException {
        Paper.VisualCharacteristics visualCharacteristics = new Paper.VisualCharacteristics();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    //  name = reader.getLocalName();  // FIXME: 27.12.2019 возможно тут ошибка
                    name = PeriodicalEnum.getNameByValue(reader.getLocalName());
                    switch (PeriodicalEnum.valueOf(name)) {
                        case COLORED:
                            visualCharacteristics.setColored(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case PAGE_SIZE:
                            visualCharacteristics.setPageSize(Integer.parseInt(getXMLText(reader)));
                            break;
                        case GLOSSY:
                            visualCharacteristics.setGlossy(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    //          name = reader.getLocalName();
                    name = PeriodicalEnum.getNameByValue(reader.getLocalName());
                    if (PeriodicalEnum.valueOf(name) == PeriodicalEnum.VISUAL_CHARACTERISTICS) {
                        return visualCharacteristics;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Address");
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
