package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.constant.CustomConstant;
import com.liukhtenko.xmlxsdparsing.entity.Paper;
import configuration.ConfigurationForTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PeriodicalSAXBuilderTest extends ConfigurationForTest {
    private PeriodicalSAXBuilder saxBuilder;

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        saxBuilder = new PeriodicalSAXBuilder();
    }

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();
        saxBuilder = null;
    }

    @Test
    public void testBuildSetPapers() {
        saxBuilder.buildSetPapers(CustomConstant.FILE_NAME);
        for (Paper paper : saxBuilder.getPapers()) {
            actual.add(paper.getTitle());
        }
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testBuildSetPapersFalse() {
        saxBuilder.buildSetPapers("Wrong/path.xml");
        Assert.assertEquals(saxBuilder.getPapers().size(), 0);
    }
}