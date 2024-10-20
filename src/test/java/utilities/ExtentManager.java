package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;

public class ExtentManager {

  public static ExtentHtmlReporter htmlReporter;
  public static ExtentReports extent;
  public static ExtentTest test;

  public static void setExtent() {
    htmlReporter = new ExtentHtmlReporter(
            System.getProperty("user.dir") + "/Reports/ExtentReport/"
                    + "TestExecutionReport"
                    + ".html");
   /* htmlReporter.loadXMLConfig(System.getProperty("user.dir")
            + "/extent-extent-extent-config.xml");
*/
    extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
  }

  public static void endReport() {
    extent.flush();
  }
}
