package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.entity.Paper;
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

    private DocumentBuilder docBuilder;

    public PeriodicalDOMBuilder() {
// создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e); // FIXME: 27.12.2019
        }
    }

//    public Set<Paper> getPapers() {
//        return papers;
//    }

    public void buildSetPapers(String fileName) {
        Document doc;
        try {
// parsing XML-документа и создание древовидной структуры
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
// получение списка дочерних элементов <student>
            NodeList papersList = root.getElementsByTagName("paper"); // FIXME: 27.12.2019
            for (int i = 0; i < papersList.getLength(); i++) {
                Element paperElement = (Element) papersList.item(i);
                Paper paper = buildPaper(paperElement);
                papers.add(paper);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private Paper buildPaper(Element paperElement) {
        Paper paper = new Paper();
        paper.setTitle(paperElement.getAttribute("title")); //fixme в константы

        if (paperElement.getAttribute("type").length() > 0) {
            paper.setType(paperElement.getAttribute("type"));
        } else {
            paper.setType("periodical");
        }
        paper.setSubscriptionIndex(getElementTextContent(paperElement, "subscriptionIndex"));
        boolean monthly = Boolean.parseBoolean(getElementTextContent(paperElement, "monthly"));
        paper.setMonthly(monthly);
        Integer yearOfFound = Integer.parseInt(getElementTextContent(
                paperElement, "yearOfFoundation"));
        paper.setYearOfFoundation(yearOfFound);
        Paper.VisualCharacteristics visualCharacteristics = new Paper.VisualCharacteristics();
        Element visualCharactElement = (Element) paperElement.getElementsByTagName(
                "visualCharacteristics").item(0);
        boolean colored = Boolean.parseBoolean(getElementTextContent(visualCharactElement, "colored"));
        visualCharacteristics.setColored(colored);
        Integer pageSize = Integer.parseInt(getElementTextContent(visualCharactElement, "pageSize"));
        visualCharacteristics.setPageSize(pageSize);
        boolean glossy = Boolean.parseBoolean(getElementTextContent(visualCharactElement, "glossy"));
        visualCharacteristics.setGlossy(glossy);
        paper.setVisualCharacteristics(visualCharacteristics);
        return paper;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
