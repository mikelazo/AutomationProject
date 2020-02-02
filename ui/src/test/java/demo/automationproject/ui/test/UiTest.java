package demo.automationproject.ui.test;

import demo.automationproject.ui.core.Driver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.xml.XmlSuite;

public class UiTest {
    private static XmlSuite.ParallelMode PARALLEL;

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {
        PARALLEL = XmlSuite.ParallelMode.valueOf(context.getSuite().getParallel().toUpperCase());

        if (PARALLEL.equals(XmlSuite.ParallelMode.NONE)) {
            Driver.createDriver();
        }
    }

    @BeforeTest
    protected void beforeTest(ITestContext context) {

        if (PARALLEL.equals(XmlSuite.ParallelMode.TESTS)) {
            Driver.createDriver();
        }
    }

    @BeforeClass
    protected void beforeClass() {
        if (PARALLEL.equals(XmlSuite.ParallelMode.CLASSES)) {
            Driver.createDriver();
        }
    }

    @BeforeMethod
    protected void beforeMethod() {
        if (PARALLEL.equals(XmlSuite.ParallelMode.METHODS)) {
            Driver.createDriver();
        }
    }

    @AfterMethod
    protected void afterMethod() {
        if (PARALLEL.equals(XmlSuite.ParallelMode.METHODS)) {
            Driver.quit();
        }
    }

    @AfterClass
    protected void afterClass() {
        if (PARALLEL.equals(XmlSuite.ParallelMode.CLASSES)) {
            Driver.quit();
        }
    }

    @AfterTest
    protected void afterTest() {
        if (PARALLEL.equals(XmlSuite.ParallelMode.TESTS)) {
            Driver.quit();
        }
    }

    @AfterSuite
    protected void afterSuite() {
        if (PARALLEL.equals(XmlSuite.ParallelMode.NONE)) {
            Driver.quit();
        }
    }
}
