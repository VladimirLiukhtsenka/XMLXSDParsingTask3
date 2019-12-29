package com.liukhtenko.xmlxsdparsing.validator;

import com.liukhtenko.xmlxsdparsing.constant.CustomConstant;
import com.liukhtenko.xmlxsdparsing.handler.PeriodicalErrorHandler;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorSAXXSD {
    private static Logger logger = LogManager.getLogger();
    public static void runValidation() {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = CustomConstant.FILE_NAME;
        String schemaName = CustomConstant.SCHEMA_NAME;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            PeriodicalErrorHandler sh = new PeriodicalErrorHandler();
            validator.setErrorHandler(sh);
            validator.validate(source);
            logger.log(Level.INFO, fileName + " validating is ended.");
        } catch (IOException | SAXException e) {
            logger.log(Level.ERROR, fileName + " is not valid because "
                    + e.getMessage());
        }
    }
}