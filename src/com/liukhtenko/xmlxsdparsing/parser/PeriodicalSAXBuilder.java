package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.handler.PeriodicalHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class PeriodicalSAXBuilder extends AbstractPeriodicalBuilder {
 //  private Set<Paper> papers; // FIXME: 28.12.2019
    private PeriodicalHandler ph;
    private XMLReader reader;

    public PeriodicalSAXBuilder() {
// создание SAX-анализатора
        ph = new PeriodicalHandler();
        try {
// создание объекта-обработчика
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(ph);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        }
    }

//    public Set<Paper> getPapers() { // FIXME: 28.12.2019 
//        return papers;
//    }

    public void buildSetPapers(String fileName) {
        try {
// разбор XML-документа
            reader.parse(fileName);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        papers = ph.getPapers();

    }
}
