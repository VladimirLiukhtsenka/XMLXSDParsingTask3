package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.constant.CustomConstant;
import com.liukhtenko.xmlxsdparsing.entity.Paper;
import configuration.ConfigurationForTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PeriodicalStAXBuilderTest extends ConfigurationForTest {
    private PeriodicalStAXBuilder builder;

    @BeforeMethod
    public void beforeMethod() {
        super.beforeMethod();
        builder = new PeriodicalStAXBuilder();
    }

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();
        builder = null;
    }

    @Test
    public void testBuildSetPapers() {
        builder.buildSetPapers(CustomConstant.FILE_NAME);
        for (Paper paper : builder.getPapers()) {
            actual.add(paper.getTitle());
        }
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testBuildSetPapersFalse() {
        builder.buildSetPapers("Wrong/path.xml");
        Assert.assertEquals(builder.getPapers().size(), 0);
    }
}