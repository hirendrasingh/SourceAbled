package com.sa.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	public static WebDriver driver = null;
	
	public static File configProp = null;
	public static FileInputStream configinput = null;
	public static Properties config = null;
	
	public static File srcexcel = null;
	public static FileInputStream excelfis = null;
	public static XSSFWorkbook srcbook = null;
	public static XSSFSheet srcsheet = null;
	public static XSSFRow srcrow = null;
	public static XSSFCell srccell = null;
	// public static String browserval = allsitedata.getProperty("Browser");
	
	public static File JSloginProp = null;
	public static FileInputStream JSinput = null;
	public static Properties JSlogin = null;
	
	public static File SuppliersLocators = null;
	public static FileInputStream Suppliersinput = null;
	public static Properties Suppliers = null;
	
	public static File HomepageLocators = null;
	public static FileInputStream Homepageinput = null;
	public static Properties Homepage = null;
	
	public static String cellcont=null;
	public static String cellint=null;
	public static Boolean cellbol=null;
	
	public static WebDriverWait wait;
	public static Logger log = Logger.getLogger(BaseClass.class.getName());
	
	/*public static Sheet firstSheet = null;
	public static Workbook workBook=null;*/

	/*
	 * public static String coluser=null; public static String colpass=null;
	 */

	/*@BeforeSuite
	public void LoadConfigFile() {

		propfile = new File(System.getProperty("user.dir") + "\\ConfigFile\\Config.properties");
		try {
			fispath = new FileInputStream(propfile);
			config = new Properties();
			config.load(fispath);
			System.out.println("Config File Loaded Successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to Load Config File --> " + e.getMessage());
		}
	}*/

	/*
	 * @BeforeTest public static void LoadProperties() {
	 * 
	 * JSloginProp = new File(System.getProperty("user.dir") +
	 * "\\ElementLocators\\JSLoginLocators.properties"); try { JSpath = new
	 * FileInputStream(JSloginProp); JSelements = new Properties();
	 * JSelements.load(JSpath); System.out.println(
	 * "JSLoginLocators File Loaded Successfully"); } catch (Exception e) { //
	 * TODO Auto-generated catch block System.out.println(
	 * "Unable to Load JSLoginLocators File --> " + e.getMessage()); } }
	 */
	
	@BeforeSuite
	public static void LoadProperties() {
		//To read properties files we need to add new file path in this method along with script....		
		configProp = new File(System.getProperty("user.dir") + "\\ConfigFile\\Config.properties");
		JSloginProp = new File(System.getProperty("user.dir") + "\\ElementLocators\\JSLoginLocators.properties");
		SuppliersLocators = new File(System.getProperty("user.dir") + "\\ElementLocators\\SuppliersLocators.properties");
		HomepageLocators = new File(System.getProperty("user.dir") + "\\ElementLocators\\SAHomepage.properties");
		try {
			configinput = new FileInputStream(configProp);
			config = new Properties();
			config.load(configinput);
			Reporter.log("Config.properties file Loaded Successfully", true);
			//System.out.println("Config File Loaded Successfully");
			
			// From this piece of code we can load properties file....
			JSinput = new FileInputStream(JSloginProp);
			JSlogin = new Properties();
			JSlogin.load(JSinput);
			System.out.println("JSLoginLocators.properties file Loaded Successfully");
			
			// To load SuppliersLocators properties file....
			Suppliersinput = new FileInputStream(SuppliersLocators);
			Suppliers = new Properties();
			Suppliers.load(Suppliersinput);
			System.out.println("SuppliersLocators.properties file Loaded Successfully");
			
			// To load Homepage properties file....
			Homepageinput = new FileInputStream(HomepageLocators);
			Homepage = new Properties();
			Homepage.load(Homepageinput);
			System.out.println("HomepageLocators.properties file Loaded Successfully");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to Load Properties File --> " + e.getMessage());
		}

	}
	
	/*@BeforeSuite
	public void ReadExcelFile() {

		srcexcel = new File(System.getProperty("user.dir") + "\\ExcelFiles\\Logins.xlsx");
		try {
			excelfis = new FileInputStream(srcexcel);
			srcbook = new XSSFWorkbook(excelfis);
			System.out.println(srcexcel.getName() + ": Excel File loaded");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to load Excel file --> " + e.getMessage());
		}
			
		srcsheet = srcbook.getSheetAt(0);
		System.out.println(srcsheet.getSheetName());
		int totalRow = srcsheet.getLastRowNum();
		//System.out.println(totalRow);
		int lastCell = srcsheet.getRow(totalRow).getLastCellNum();
		//System.out.println(lastCell);
		
		for(int i=1; i<=totalRow; i++){
			//String Cell1 = firstSheet.getRow(i).getCell(0).getStringCellValue();
			//System.out.println(Cell1);
			for(int j=0; j<lastCell; j++){
				Cell cell = srcsheet.getRow(i).getCell(j);
								
				if(cell.getCellType()==cell.CELL_TYPE_STRING){
					cellcont = srcsheet.getRow(i).getCell(j).getStringCellValue();
					System.out.println("String-> "+cellcont);
				}
		// ********Use setCellType to convert numeric cell to string to get correct data**********
				else if (cell.getCellType()==cell.CELL_TYPE_NUMERIC) {
					cell.setCellType(cell.CELL_TYPE_STRING);
					cellint = srcsheet.getRow(i).getCell(j).getStringCellValue();
					//int cellint = (int) firstSheet.getRow(i).getCell(j).getNumericCellValue();
					System.out.println("Numeric-> "+cellint);
				}
				else if (cell.getCellType()==cell.CELL_TYPE_BOOLEAN) {
					cellbol = srcsheet.getRow(i).getCell(j).getBooleanCellValue();
					System.out.println("Boolean-> "+cellbol);
				}
				
				//System.out.println(cell);
			}
		}
		
	}*/

	@BeforeClass
	// @BeforeTest
	public void LoadDriver() throws InterruptedException {
		// if (driver==null)

		// In this method we have read browser path and type from config properties file.....
		String browserval = config.getProperty("Browser");

		if (browserval.equalsIgnoreCase("Chrome")) {
			System.out.println("Launching Chrome Browser");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+config.getProperty("Chromepath"));
			driver = new ChromeDriver();
		} else if (browserval.equalsIgnoreCase("Firefox")) {
			System.out.println("Launching Firefox Browser");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+config.getProperty("FFpath"));
			driver = new FirefoxDriver();
		} else if (browserval.equalsIgnoreCase("IE")) {
			System.out.println("Launching IE Browser");
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+config.getProperty("IEpath"));
			driver = new InternetExplorerDriver();
		}

		/*
		 * System.out.println("Initializing Browser");
		 * System.setProperty("webdriver.chrome.driver",
		 * allsitedata.getProperty("Chromepath")); driver = new ChromeDriver();
		 * System.out.println("Driver Loaded Successfully");
		 */
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(config.getProperty("SiteUrl"));
		Thread.sleep(3000);

	}

	
	 @AfterClass public void CloseBrowser() 
	 { 
	 //driver.close(); 
	 driver.quit();
	 //System.out.println("Browser Closed Test Complete"); 
	 Reporter.log( "Test Complete Browser Quit", true);
	 }
	 
	 //Methods element loader......
	 
			
		public static void waitForVisitibilty(WebElement element) {
			/*System.setProperty("webdriver.chrome.driver", "Y:\\MySeleniumProjectBackup\\New folder\\chromedriver.exe");
			driver = new ChromeDriver();*/
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 60);
			// wait.until(ExpectedConditions.visibilityOf(element));
			if (!element.isDisplayed()) {
				wait.until(ExpectedConditions.visibilityOf(element));
				wait.ignoring(NoSuchElementException.class);
			} else {
				System.out.println("Element not present");
			}
		}

		public static void click(WebElement element) {
			waitForVisitibilty(element);
			element.click();
		}

		public static void clear(WebElement element) {
			waitForVisitibilty(element);
			element.clear();
		}

		public static void sendKeys(WebElement element, String keyword) {
			waitForVisitibilty(element);
			element.clear();
			element.sendKeys(keyword);
		}

		public static boolean isDisplayed(WebElement element) {
			boolean present = false;
			waitForVisitibilty(element);
			try {
				present = element.isDisplayed();
				log.info(element.getText() + " is dispalyed");
				// return element.isDisplayed();
			} catch (Exception e) {
				log.error("Element not found " + e);
				// log.info(e.getMessage());
			}
			return present;
		}

		public static boolean isElementPresent(WebElement element) {

			waitForVisitibilty(element);
			log.info("element is present:" + element.toString());
			return element.isDisplayed();

		}

		public static String getText(WebElement element) {
			waitForVisitibilty(element);
			return element.getText();
		}

		public static WebElement linkText(WebElement element, String Keyword) {
			waitForVisitibilty(element);
			return element = driver.findElement(By.partialLinkText(Keyword));
		}

		public static String getAttributeValue(WebElement element, String attribute) {
			waitForVisitibilty(element);
			return element.getAttribute(attribute);
		}

		public static void SelectByText(String tagname, String text) {
			log.info("Text=" + text + "\t" + "tagname=" + tagname);
			if (tagname != null) {
				driver.findElement(By.xpath(".//'" + tagname + "'[contains(text(),'" + text + "')]"));
			} else {
				driver.findElement(By.xpath(".//*[contains(text(),'" + text + "')]"));
			}
		}
	
}
