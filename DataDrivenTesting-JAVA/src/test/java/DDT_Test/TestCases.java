package DDT_Test;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import DDT_Helpers.Xls_Reader;

public class TestCases extends TestBase{

	Xls_Reader Excelreader = new Xls_Reader("D:\\Automation\\Selenium\\Java\\GitHub\\DataDrivenTesting-JAVA\\src\\main\\java\\TestData\\TestData.xlsx");
	@Test
	public void  TC01_DDTTestFillValues() throws InterruptedException
	{
		String FirstNameData = Excelreader.getCellData("Data", "FirstName", 2);
		System.out.println(FirstNameData);
		driver.findElement(By.name("first_name")).sendKeys(FirstNameData);
		String LastNameData = Excelreader.getCellData("Data", "LastName", 2);
		System.out.println(LastNameData);
		driver.findElement(By.name("last_name")).sendKeys(LastNameData);

		String eMailData = Excelreader.getCellData("Data", "Email", 2);
		System.out.println(eMailData);
		driver.findElement(By.name("email")).sendKeys(eMailData);
		String phoneData = Excelreader.getCellData("Data", "Phone", 2);
		System.out.println(phoneData);
		driver.findElement(By.name("phone")).sendKeys(phoneData);

		String AddressData = Excelreader.getCellData("Data", "Address", 2);
		System.out.println(AddressData);
		driver.findElement(By.name("address")).sendKeys(AddressData);
		String CityData = Excelreader.getCellData("Data", "City", 2);
		System.out.println(CityData);
		driver.findElement(By.name("city")).sendKeys(CityData);

		Select stateSelect = new Select(driver.findElement(By.name("state")));
		stateSelect.selectByVisibleText("California");

		String ZipCodeData = Excelreader.getCellData("Data", "ZIPCode", 2);
		System.out.println(ZipCodeData);
		driver.findElement(By.name("zip")).sendKeys(ZipCodeData);
		String WebsiteData = Excelreader.getCellData("Data", "Website", 2);
		System.out.println(WebsiteData);
		driver.findElement(By.name("website")).sendKeys(WebsiteData);

		driver.findElement(By.xpath("//input[@name='hosting'][@value='no']")).click();

		String DescriptionData = Excelreader.getCellData("Data", "Description", 2);
		System.out.println(DescriptionData);
		driver.findElement(By.name("comment")).sendKeys(DescriptionData);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(5000);

	}
	@Test
	public void TC02_FindNumberofColumns()
	{

		//get test data from excel:
		Xls_Reader reader = new Xls_Reader("D:\\Automation\\Selenium\\Java\\GitHub\\DataDrivenTesting-JAVA\\src\\main\\java\\TestData\\HalfEbayTestData.xlsx");			
		if(!reader.isSheetExist("HomePage")){
			reader.addSheet("HomePage");
		}		
		int colCount = reader.getColumnCount("RegTestData");
		System.out.println("Total cols present in RegTestData sheet:====" + colCount);
		System.out.println(reader.getCellRowNum("RegTestData", "firstname", "Reshma"));	
	}

	//Data Provider
	@DataProvider
	public Iterator<Object[]> getTestData(){
		ArrayList<Object[]> testData = DDT_Helpers.TestUtil.getDataFromExcel();
		return testData.iterator();
	}

	@Test(dataProvider="getTestData",priority=1)
	public void TC03_ParameterTest(String firstName, String lastName, String address1, String address2, 
			String city, String state, String zipCode, String emailAddress) throws InterruptedException
	{
		driver.get("https://scgi.half.ebay.com/ws/eBayISAPI.dll?RegisterEnterInfo&usage=2943&ru=");
		//enter data:
		Thread.sleep(10000);

		driver.findElement(By.xpath("//*[@id='firstname']")).clear();
		driver.findElement(By.xpath("//*[@id='firstname']")).sendKeys(firstName);

		driver.findElement(By.xpath("//*[@id='lastname']")).clear();
		driver.findElement(By.xpath("//*[@id='lastname']")).sendKeys(lastName);

		driver.findElement(By.xpath("//*[@id='address1']")).clear();
		driver.findElement(By.xpath("//*[@id='address1']")).sendKeys(address1);

		driver.findElement(By.xpath("//*[@id='address1']")).clear();
		driver.findElement(By.xpath("//*[@id='address1']")).sendKeys(address2);

		driver.findElement(By.xpath("//*[@id='city']")).clear();
		driver.findElement(By.xpath("//*[@id='city']")).sendKeys(city);

		Select select = new Select(driver.findElement(By.xpath("//*[@id='state']")));
		select.selectByVisibleText(state);

		driver.findElement(By.xpath("//*[@id='zip']")).clear();
		driver.findElement(By.xpath("//*[@id='zip']")).sendKeys(zipCode);

		driver.findElement(By.xpath("//*[@id='email']")).clear();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(emailAddress);

		driver.findElement(By.xpath("//*[@id='retype_email']")).clear();
		driver.findElement(By.xpath("//*[@id='retype_email']")).sendKeys(emailAddress);
	}

	@Test
	public static void TC04_ParameterisedTest()
	{

		driver.get("https://scgi.half.ebay.com/ws/eBayISAPI.dll?RegisterEnterInfo&usage=2943&ru="); // enter url

		//Data Driven Approach (Parameterization) -- used to create data driven framework: driving the test data from excel files
		//get test data from excel:
		Xls_Reader reader = new Xls_Reader("D:\\Automation\\Selenium\\Java\\GitHub\\DataDrivenTesting-JAVA\\src\\main\\java\\TestData\\HalfEbayTestData.xlsx");
		int rowCount = reader.getRowCount("RegTestData");

		reader.addColumn("RegTestData", "Status");

		//Parameterization:
		for(int rowNum = 2; rowNum<=rowCount; rowNum++)
		{
			System.out.println("=====");
			String firstName = reader.getCellData("RegTestData", "firstname", rowNum);
			System.out.println(firstName);

			String lastName = reader.getCellData("RegTestData", "lastname", rowNum);
			System.out.println(lastName);

			String address1 = reader.getCellData("RegTestData", "address1", rowNum);
			System.out.println(address1);

			String address2 = reader.getCellData("RegTestData", "address2", rowNum);
			System.out.println(address2);

			String city = reader.getCellData("RegTestData", "city", rowNum);
			System.out.println(city);

			String state = reader.getCellData("RegTestData", "state", rowNum);
			System.out.println(state);

			String zipCode = reader.getCellData("RegTestData", "zipcode", rowNum);
			System.out.println(zipCode);

			String emailAddress = reader.getCellData("RegTestData", "emailaddress", rowNum);
			System.out.println(emailAddress);

			//enter data:
			driver.findElement(By.xpath("//*[@id='firstname']")).clear();
			driver.findElement(By.xpath("//*[@id='firstname']")).sendKeys(firstName);

			driver.findElement(By.xpath("//*[@id='lastname']")).clear();
			driver.findElement(By.xpath("//*[@id='lastname']")).sendKeys(lastName);

			driver.findElement(By.xpath("//*[@id='address1']")).clear();
			driver.findElement(By.xpath("//*[@id='address1']")).sendKeys(address1);

			driver.findElement(By.xpath("//*[@id='address1']")).clear();
			driver.findElement(By.xpath("//*[@id='address1']")).sendKeys(address2);

			driver.findElement(By.xpath("//*[@id='city']")).clear();
			driver.findElement(By.xpath("//*[@id='city']")).sendKeys(city);

			Select select = new Select(driver.findElement(By.xpath("//*[@id='state']")));
			select.selectByVisibleText(state);

			driver.findElement(By.xpath("//*[@id='zip']")).clear();
			driver.findElement(By.xpath("//*[@id='zip']")).sendKeys(zipCode);

			driver.findElement(By.xpath("//*[@id='email']")).clear();
			driver.findElement(By.xpath("//*[@id='email']")).sendKeys(emailAddress);

			driver.findElement(By.xpath("//*[@id='retype_email']")).clear();
			driver.findElement(By.xpath("//*[@id='retype_email']")).sendKeys(emailAddress);

			reader.setCellData("RegTestData", "Status", rowNum, "Pass"); //write the data into a cell

		}

	}
}
