package SuppliersTestCases;


import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sa.testbase.BaseClass;
import com.sa.testbase.CommonMethods;
import com.sa.utility.ExcelReader;

public class GetRequirementList extends BaseClass{
	
	@DataProvider(name="SuppliersLogins")
	public Object[][] SuppliersDataLogins(){
		Object[][] supplierLoginData = ExcelReader.getData("Logins.xlsx", "SaLogins");
				return supplierLoginData;
	}
	
	
	@Test(dataProvider="SuppliersLogins", priority=0)
	public static void LoginSupplier(String SupplierUser,String Password, String Runmode) throws Exception{
		Reporter.log("This test is running through DataProvider....", true);
		if(Runmode.equalsIgnoreCase("n")){
			throw new SkipException("Skipping Suppliers Login test for user -> "+ SupplierUser );
		}
		else {
			CommonMethods.ClickById(Homepage.getProperty("Suppliers_link"), "");
			CommonMethods.SendKeysByXpath(Suppliers.getProperty("username_txtbx"), SupplierUser);
			CommonMethods.SendKeysByXpath(Suppliers.getProperty("password_txtbx"), Password);
			CommonMethods.ClickByXpath(Suppliers.getProperty("submit_btn"), "");
			
			List<WebElement> ReqID = driver.findElements(By.xpath("//a[@class='reqS fleft']"));
			// Enhanced for loop....
			/*for(WebElement EachReq : ReqID){
				System.out.println("Requirement ID is -> "+EachReq.getText());*/
			
			{
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("RequirementList");
												
				int rowCount = 1;
				Row RowHeader= sheet.createRow(0);
				
				for (WebElement EachReq : ReqID){
					Row row = sheet.createRow(rowCount++);
					int columnCount = 0;
					String Jobs = EachReq.getText();
					System.out.println("Req->"+Jobs);
					
						Cell cell = row.createCell(columnCount++);
						cell.setCellValue(Jobs);
						
						Cell cellheader = RowHeader.createCell(0);
						cellheader.setCellValue("JobsID");
						
						FileOutputStream fileOutput;
						try {
							
							Calendar calendar = Calendar.getInstance();
							String formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(calendar.getTime());
							
							String User = driver.findElement(By.xpath("//*[contains(text(),'Welcome to')]")).getText();
							
							fileOutput = new FileOutputStream("C:\\HSR\\ExcelData\\SourceAbled\\ReqList-"+User+"-"+formater+".xlsx");
						
							workbook.write(fileOutput);
							//workbook.close();
						} catch (IOException e) {
							System.out.println(e.getMessage());
						
						}
						/*Thread.sleep(5000);
						CommonMethods.ClickByXpath(Suppliers.getProperty("logout_link"), "");
						driver.get("http://demo.sourceabled.com/Suppliers");
						try {
							workbook.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
					}
				Thread.sleep(5000);
				workbook.close();
				}
			
			//Thread.sleep(5000);
			CommonMethods.ClickByXpath(Suppliers.getProperty("logout_link"), "");
			driver.get("http://demo.sourceabled.com/");
						
			}
	}
	}
			
