package utils;

import org.testng.ITestContext;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestNgUtils {
    private static Logger logger = Logger.getLogger(TestDataUtil.class.getName());

    public static String getAttribute(ITestContext context, String attribute) {
        try {
            return context.getAttribute(attribute).toString();
        } catch (NullPointerException e) {
            logger.log(Level.WARNING, "Key with '%s' name doesn't exist in context.");
        }
        return null;
    }
}
