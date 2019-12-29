package configuration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.HashSet;
import java.util.Set;

public class ConfigurationForTest {
    protected Set<String> expected;
    protected Set<String> actual;
    @BeforeClass
    public void setup() {
        expected = new HashSet<>();
        expected.add("КУЛЬТУРА");
        expected.add("ВРАЧ");
        expected.add("СПИД-ИНФО!");
        expected.add("ОХОТА И РЫБАЛКА!");
        expected.add("ПОГРАНИЧНИК");
        expected.add("100 ВКУСНЫХ БЛЮД");
        expected.add("АНОМАЛЬНЫЕ НОВОСТИ");
        expected.add("ВЕСЁЛЫЙ ЗАТЕЙНИК");
        expected.add("STATUS PRESSES");
        expected.add("ЮРИДИЧЕСКИЙ ОТДЕЛ");
        expected.add("ИСКАТЕЛЬ");
        expected.add("ОДНАКО, ЖИЗНЬ!");
        expected.add("AUTOMATION OF CONTROL PROCESSES");
        expected.add("ЭНЕРГЕТИК");
        expected.add("CONTROL SCIENCES");
        expected.add("МИР СМЕХА");
    }

    @AfterClass
    public void tearDown() {
        expected = null;
    }

    @BeforeMethod
    public void beforeMethod() {
        actual = new HashSet<>();
    }

    @AfterMethod
    public void afterMethod() {
        actual = null;
    }
}
