package com.liukhtenko.xmlxsdparsing.parser;

import com.liukhtenko.xmlxsdparsing.constant.CustomConstant;
import com.liukhtenko.xmlxsdparsing.entity.Paper;
import configuration.ConfigurationForTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class DirectorOfBuilderTest extends ConfigurationForTest {

    @Test
    public void testCreatePeriodicalWithDOM() {
        Set<String> actual = new HashSet<>();
        Set<Paper> papers = DirectorOfBuilder.createPeriodical(new PeriodicalDOMBuilder(), CustomConstant.FILE_NAME);
        for (Paper paper : papers) {
            actual.add(paper.getTitle());
        }
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCreatePeriodicalWithSAX() {
        Set<String> actual = new HashSet<>();
        Set<Paper> papers = DirectorOfBuilder.createPeriodical(new PeriodicalSAXBuilder(), CustomConstant.FILE_NAME);
        for (Paper paper : papers) {
            actual.add(paper.getTitle());
        }
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCreatePeriodicalWithStAX() {
        Set<String> actual = new HashSet<>();
        Set<Paper> papers = DirectorOfBuilder.createPeriodical(new PeriodicalStAXBuilder(), CustomConstant.FILE_NAME);
        for (Paper paper : papers) {
            actual.add(paper.getTitle());
        }
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCreatePeriodicalFalse() {
        Set<String> actual = new HashSet<>();
        Set<Paper> papers = DirectorOfBuilder.createPeriodical(new PeriodicalDOMBuilder(), "Wrong/path.xml");
        for (Paper paper : papers) {
            actual.add(paper.getTitle());
        }
        Assert.assertNotEquals(actual, expected);
    }
}