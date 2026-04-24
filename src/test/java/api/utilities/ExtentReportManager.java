package api.utilities;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    public static ExtentReports extent;

    // Singleton method
    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = createInstance("reports/ExtentReport.html");
        }
        return extent;
    }

    // Create and configure report
    public static ExtentReports createInstance(String fileName) {

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);

        // Report configurations
        sparkReporter.config().setReportName("Automation Test Report");
        sparkReporter.config().setDocumentTitle("Extent Report");
        sparkReporter.config().setEncoding("utf-8");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Optional system info
        extent.setSystemInfo("Tester", "QA Team");
        extent.setSystemInfo("Environment", "QA");

        return extent;
    }
}
