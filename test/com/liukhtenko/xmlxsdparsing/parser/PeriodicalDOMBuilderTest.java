package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.constant.CustomConstant;
import com.liukhtenko.xmlxsdparsing.entity.Paper;
import configuration.ConfigurationForTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PeriodicalDOMBuilderTest extends ConfigurationForTest {
    private PeriodicalDOMBuilder domBuilder;

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        domBuilder = new PeriodicalDOMBuilder();
    }

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();
        domBuilder = null;
    }

    @Test
    public void testBuildSetPapers() {
        domBuilder.buildSetPapers(CustomConstant.FILE_NAME);
        for (Paper paper : domBuilder.getPapers()) {
            actual.add(paper.getTitle());
        }
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testBuildSetPapersFalse() {
        domBuilder.buildSetPapers("Wrong/path.xml");
        Assert.assertEquals(domBuilder.getPapers().size(), 0);
    }
}