package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.handler.PeriodicalHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class PeriodicalSAXBuilder extends AbstractPeriodicalBuilder {
    private static Logger logger = LogManager.getLogger();
    private PeriodicalHandler ph;
    private XMLReader reader;

    public PeriodicalSAXBuilder() {
        ph = new PeriodicalHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(ph);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Parse error: " + e);
        }
    }

    public void buildSetPapers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            logger.log(Level.ERROR, "Parse error: " + e);
        } catch (IOException e) {
            logger.log(Level.ERROR, "File I/O error: " + e);
        }
        papers = ph.getPapers();
    }
}
