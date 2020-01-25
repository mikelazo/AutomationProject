package demo.automationproject.utils.common;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringsTest {
    /**
     * [0] = String to test
     * [1] = Expected result (true or false)
     * [2] = Description. Used to help understand what failed (cannot input literal null to a failure message).
     */
    @DataProvider(name = "nullEmptyString")
    public Object[][] nullEmptyString() {
        return new Object[][]{
                {"Hello World", false, "Valid String"},
                {"", true, "Empty String"},
                {null, true, "Null value"},
                {" ", false, "Single space character in String"},
                {"\r", false, "Carriage return"},
                {"\n", false, "New line"},
                {"\r\n", false, "Carriage return and new line"},
                {"\t", false, "Tab"}
        };
    }

    /**
     * Tests the given strings against {@link Strings#IsNullOrEmpty(String)} and determines if the result is valid. The dataprovider controls the expected result of true or false.
     */
    @Test(dataProvider = "nullEmptyString")
    public void isNullOrEmptyTest(String s, boolean expectedResult, String description) {
        Assert.assertEquals(Strings.IsNullOrEmpty(s), expectedResult, "String is null or empty test failed on permutation '" + description + "'.");
    }
}
