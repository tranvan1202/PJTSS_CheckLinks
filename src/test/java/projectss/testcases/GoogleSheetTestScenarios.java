package projectss.testcases;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import projectss.base.SheetsQuickstart;
import projectss.base.SheetsUpdate;

import java.io.IOException;

import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GoogleSheetTestScenarios {
    private static final String existingSpreadSheetID = "";

    private static String sheetName = "";

    private static String testDataSheetName = "Test Data";
    static int totalRows = 0;
    static int index = 0;
    static int totalColumnsTestDataSheet = 3;

    /**
     * @throws GeneralSecurityException
     * @throws IOException
     */

    @BeforeClass
    public static void testSetup() throws GeneralSecurityException, IOException {
        LocalDateTime myDateTime = LocalDateTime.now();

        SheetsQuickstart.getCredentials();

        SheetsQuickstart.getSpreadsheetInstance();

        totalRows = SheetsQuickstart.getRows(testDataSheetName, existingSpreadSheetID);
        DateTimeFormatter myDateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");
        String formatDate = myDateTime.format(myDateTimeFormat);

        sheetName = "TestResult_" + formatDate;

        System.out.println("SheetName-" + formatDate);

        SheetsQuickstart.createNewSheet(existingSpreadSheetID, sheetName);
        SheetsQuickstart.writeDataGoogleSheets(sheetName,
                new ArrayList<Object>(Arrays.asList("URL", "Status Code", "Result", "Region", "Domain")),
                existingSpreadSheetID);
    }

    /**
     * @param testData
     * @throws IOException
     */
    @Test(dataProvider = "TestData")
    public void GoogleSheetExample(Map<Object, Object> testData) throws IOException {
        ValidatableResponse response = null;
        int statusCode = 0;
        try {
            System.out.println("Test Data:- " + testData);
            response = RestAssured.given().when().get(testData.get("URL").toString()).then();
            statusCode = response.extract().response().statusCode();

            System.out.println("statusCode-" + statusCode);
            if (statusCode == 200)
                SheetsQuickstart.writeDataGoogleSheets(sheetName, new ArrayList<Object>(Arrays.asList(testData.get("URL"), statusCode, "Pass", testData.get("Region"), testData.get("Domain"))),
                         existingSpreadSheetID);
            else
                SheetsQuickstart.writeDataGoogleSheets(sheetName, new ArrayList<Object>(Arrays.asList(testData.get("URL"), statusCode, "Fail", testData.get("Region"), testData.get("Domain"))),
                        existingSpreadSheetID);
        } catch (Exception e) {
            SheetsQuickstart.writeDataGoogleSheets(
                    sheetName, new ArrayList<Object>(Arrays.asList(testData.get("URL"), statusCode,"Fail",
                            testData.get("Region"), testData.get("Domain"), response.extract().response())),
                    existingSpreadSheetID);
        }
    }
    /**
     * @return
     */
    @DataProvider(name = "TestData")
    public Object[][] readAllSheetData(){
        List<String> headerValues = new ArrayList<String>();
        List<String> rowValues = new ArrayList<>();
        String headerData = null;
        Map<String,String> rowMap = new HashMap<String, String>();

        Object[][] finalData = new HashMap[totalRows -1][1];
        try {
            for(int row = 0; row < totalRows; row++) {
                rowValues = new ArrayList<String>();
                rowMap = new HashMap<String, String>();

                for (int i = 0; i < totalColumnsTestDataSheet; i++) {
                    if(row ==0) {
                        headerData = SheetsQuickstart.getCellContent(0,i,testDataSheetName);
                        headerValues.add(headerData);
                    } else if (row !=0) {
                        String rowData;
                        rowData = SheetsQuickstart.getCellContent(row, i, testDataSheetName);
                        rowValues.add(rowData);
                    }
                }
                if (row ==0) {
                    continue;
                }
                for (int j = 0; j< totalColumnsTestDataSheet; j++) {
                    rowMap.put(headerValues.get(j), rowValues.get(j));
                }
                finalData[index][0] = rowMap;
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalData;
    }

    //@DataProvider(name = "TestData")
//    public Object[][] readFirst2ColumnData() throws IOException {
//        Object[][] data = new Object[totalRows-1][2];
//        for (int i =1 ; i< totalRows; i++) {
//            for (int j = 0; j <= 1; j++) {
//                data[i-1][j] = SheetsQuickstart.getCellContent(i,j,"Test Data");
//            }
//        }
//        return data;
//    }

}