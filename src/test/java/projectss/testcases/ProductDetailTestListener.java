package projectss.testcases;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import projectss.base.SheetsQuickstart;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import static projectss.base.BaseSetup.paramsInputExistingSheetId;
import static projectss.testcases.ProductDetailTest.*;

public class ProductDetailTestListener implements ITestListener {
    private static String resultSheetName = "";
    private static String existingSpreadSheetID= "";
    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ITestContext arg0)  {
        try {
            testSetupWriteResultIntoSheets(arg0);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        // TODO Auto-generated method stub
        try {
            SheetsQuickstart.writeDataGoogleSheets(resultSheetName, new ArrayList<Object>(Arrays.asList(
                    testCaseID1,testCaseName1, qaNo1,qaURL1,xpath1,expectedResult1,
                    actualElementText,"Fail")),existingSpreadSheetID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestStart(ITestResult arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        // TODO Auto-generated method stub
        try {
            SheetsQuickstart.writeDataGoogleSheets(resultSheetName, new ArrayList<Object>(Arrays.asList(
                    testCaseID1,testCaseName1, qaNo1,qaURL1,xpath1,expectedResult1,
                    actualElementText,"Pass")), existingSpreadSheetID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void testSetupWriteResultIntoSheets(ITestContext context) throws GeneralSecurityException, IOException {
        LocalDateTime myDateTime = LocalDateTime.now();
        existingSpreadSheetID = paramsInputExistingSheetId(context);

        SheetsQuickstart.getCredentials();
        SheetsQuickstart.getSpreadsheetInstance();

        DateTimeFormatter myDateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
        String formatDate = myDateTime.format(myDateTimeFormat);

        resultSheetName = "TestResult_" + formatDate;
        System.out.println("SheetName-" + formatDate);
        SheetsQuickstart.createNewSheet(existingSpreadSheetID, resultSheetName);
        SheetsQuickstart.writeDataGoogleSheets(resultSheetName,
                new ArrayList<Object>(Arrays.asList("Test Case ID","Test Case Name", "QA No", "QA URL","Xpath", "Expected","Actual","Result")),
                existingSpreadSheetID);
    }
}
