package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.constant.CustomConstant;
import com.liukhtenko.xmlxsdparsing.entity.Paper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class PeriodicalDOMBuilder extends AbstractPeriodicalBuilder {
    private static Logger logger = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    public PeriodicalDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Parser configuration error: " + e);
        }
    }

    public void buildSetPapers(String fileName) {
        Document doc;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList papersList = root.getElementsByTagName(CustomConstant.PAPER);
            for (int i = 0; i < papersList.getLength(); i++) {
                Element paperElement = (Element) papersList.item(i);
                Paper paper = buildPaper(paperElement);
                papers.add(paper);
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "File I/O error: " + e);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Parsing failure: " + e);
        }
    }

    private Paper buildPaper(Element paperElement) {
        Paper paper = new Paper();
        paper.setTitle(paperElement.getAttribute(CustomConstant.TITLE));
        String attribute = paperElement.getAttribute(CustomConstant.TYPE);
        if (attribute.length() > 0) {
            paper.setType(attribute);
        } else {
            paper.setType(CustomConstant.PERIODICAL);
        }
        paper.setSubscriptionIndex(getElementTextContent(paperElement, CustomConstant.SUBSCRIPTION_INDEX));
        boolean monthly = Boolean.parseBoolean(getElementTextContent(paperElement, CustomConstant.MONTHLY));
        paper.setMonthly(monthly);
        int yearOfFound = Integer.parseInt(getElementTextContent(paperElement, CustomConstant.YEAR_OF_FOUNDATION));
        paper.setYearOfFoundation(yearOfFound);
        Paper.VisualCharacteristics visualCharacteristics = new Paper.VisualCharacteristics();
        Element visualCharacterElement = (Element) paperElement.getElementsByTagName(
                CustomConstant.VISUAL_CHARACTERISTICS).item(0);
        boolean colored = Boolean.parseBoolean(getElementTextContent(visualCharacterElement, CustomConstant.COLORED));
        visualCharacteristics.setColored(colored);
        int pageSize = Integer.parseInt(getElementTextContent(visualCharacterElement, CustomConstant.PAGE_SIZE));
        visualCharacteristics.setPageSize(pageSize);
        boolean glossy = Boolean.parseBoolean(getElementTextContent(visualCharacterElement, CustomConstant.GLOSSY));
        visualCharacteristics.setGlossy(glossy);
        paper.setVisualCharacteristics(visualCharacteristics);
        return paper;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
