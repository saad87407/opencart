package utilities;

import java.awt.Desktop;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import java.io.File;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	
	
	String repName;
	
	public  void onStart(ITestContext testcontext) {
		
		//SimpledateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		//Date df=new Date();
		//String currentdatetimestamp=df.format();
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-" +timeStamp +".html";
	    
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle(" opencart Automation report");
		sparkReporter.config().setReportName("opencart functional testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("appplication", " opencart");
		extent.setSystemInfo("computer name", "local host");
		extent.setSystemInfo("envirnment", " QA");
		extent.setSystemInfo("tester name", "war");
		extent.setSystemInfo("browser", "chrome");
		
		String os=testcontext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating system", os);
		
		String browser=testcontext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);
		
		List<String> includedGroups=  testcontext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}		
		
	  }
	 public void onTestSuccess(ITestResult result)
	 {
		 test=extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.PASS, "test case passed is "+result.getName());
		 
		 
		  }
	 
	 public void onTestFailure(ITestResult result) {
		 test=extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 
		 test.log(Status.FAIL, "test case failed is "+result.getName());
		 test.log(Status.FAIL, "test case failed cause is "+result.getThrowable());
		 try {
			 
			 String imPath=new BaseClass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imPath);
			 
		 }
		 catch (Exception e1) {
			 
			e1.printStackTrace();
		}
	 	 
	 }
	 
	 public void onTestSkipped(ITestResult result) {
		 test=extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, "test case skipped is "+result.getName());
		 
		  }
	 public  void onFinish(ITestContext context)
	 {
		 extent.flush();
		 
		 String pathofExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		 File extentReport=new File(pathofExtentReport);
		 
		 try {
			 Desktop.getDesktop().browse(extentReport.toURI());
			 
		 }
		 catch (Exception e) {
			 e.printStackTrace();
			// TODO: handle exception
		}
	 }
	 
	
	
	
	

}
