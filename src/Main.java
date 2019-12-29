import com.liukhtenko.xmlxsdparsing.entity.Paper;
import com.liukhtenko.xmlxsdparsing.exception.CustomException;
import com.liukhtenko.xmlxsdparsing.parser.DirectorOfBuilder;
import com.liukhtenko.xmlxsdparsing.parser.PeriodicalStAXBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class Main {
    static Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws CustomException {
        Paper.VisualCharacteristics visualCharacteristics = new Paper.VisualCharacteristics();
//
//        PeriodicalSAXBuilder saxBuilder = new PeriodicalSAXBuilder();
//        saxBuilder.buildSetPapers("data/periodical.xml");
//        for (Paper paper : saxBuilder.getPapers()) {
//            System.out.println(paper);
//        }
//
//        PeriodicalDOMBuilder domBuilder = new PeriodicalDOMBuilder();
//        domBuilder.buildSetPapers("data/periodical.xml");
//        for (Paper paper : domBuilder.getPapers()) {
//            System.out.println(paper);
//        }
//
//        PeriodicalStAXBuilder staxBuilder = new PeriodicalStAXBuilder();
//        staxBuilder.buildSetPapers("data/periodical.xml");
//        for (Paper paper : staxBuilder.getPapers()) {
//            System.out.println(paper);
//        }
//
//
        Set<Paper> papers = null;
        papers = DirectorOfBuilder.createPeriodical(new PeriodicalStAXBuilder(),"data/perAiodical.xml");
        for (Paper paper : papers) {
            System.out.println(paper);
        }
//        PeriodicalEnum periodicalEnum = PeriodicalEnum.valueOf("TYPE");
//        System.out.println(periodicalEnum.getValue());
//        System.out.println(periodicalEnum.getDeclaringClass());
//        System.out.println(periodicalEnum.name());
    }
}
