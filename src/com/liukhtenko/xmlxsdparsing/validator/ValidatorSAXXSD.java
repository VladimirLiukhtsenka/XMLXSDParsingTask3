package com.liukhtenko.xmlxsdparsing.validator;

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
    static Logger logger = LogManager.getLogger();

    public static void runValidation() throws SAXException {    // FIXME: 26.12.2019  all
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = "D:\\Java\\Projects\\XMLXSDParsing\\data\\periodical.xml";  // FIXME: 27.12.2019 
        String schemaName = "D:\\Java\\Projects\\XMLXSDParsing\\data\\periodical.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
// создание схемы
            Schema schema = factory.newSchema(schemaLocation);
// создание валидатора на основе схемы
            Validator validator = schema.newValidator();
// проверка документа
            Source source = new StreamSource(fileName);
            PeriodicalErrorHandler sh = new PeriodicalErrorHandler(); // FIXME: 27.12.2019
            validator.setErrorHandler(sh);
            validator.validate(source);
            logger.log(Level.INFO, fileName + " validating is ended.");
        } catch (IOException e) {
            System.err.print(fileName + " is not valid because "
                    + e.getMessage());
        }
    }
}