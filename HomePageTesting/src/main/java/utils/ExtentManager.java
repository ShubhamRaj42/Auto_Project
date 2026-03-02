package utils;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	private static Map<String, ExtentReports> reportMap = new HashMap<>();

	public static ExtentReports getExtentReport(String reportName) {

		if (!reportMap.containsKey(reportName)) {

			ExtentSparkReporter spark = new ExtentSparkReporter("test-output/" + reportName + ".html");

			spark.config().setReportName(reportName + " Automation Report");
			spark.config().setDocumentTitle(reportName + " Test Execution");

			ExtentReports extent = new ExtentReports();
			extent.attachReporter(spark);

			extent.setSystemInfo("Project", "Myntra UI Automation");
			extent.setSystemInfo("Tester", "Sarthak Rathore");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Browser", "Chrome");

			reportMap.put(reportName, extent);
		}

		return reportMap.get(reportName);
	}
}