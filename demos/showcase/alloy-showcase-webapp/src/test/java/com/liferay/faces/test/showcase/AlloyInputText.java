package com.liferay.faces.test.showcase;
//J-

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AlloyInputText {

	private static final String inputXpath = "//input[contains(@id,':text')]";
	private static final String submitButtonXpath = "//button[contains(text(),'Submit')]";
	private static final String modelValueXpath = "//span[contains(@id,':modelValue')]";
	private static final String inputXpathRight = "(//input[contains(@id,':text')])[2]";
	private static final String submitButtonXpathRight = "(//button[contains(text(),'Submit')])[2]";
	private static final String modelValueXpathRight = "(//span[contains(@id,':modelValue')])[2]";
//	private static final String checkboXpath = "//input[@class='alloy-select-boolean-checkbox checkbox']";
	private static final String checkboxXpath_02 = "(//input[@class='alloy-select-boolean-checkbox checkbox'])[2]";
	private static final String successXpath = "(//div[@class='alloy-field control-group success'])[1]";
	private static final String ModelValueContainsErrorTextXpath_01 = "(//span[@class='alloy-message help-inline'])";
	private static final String ModelValueContainsSpecifiedTextXpath_01 = "(//li[@class='text-info'])[1]";
	private static final String ModelValueContainsSpecifiedTextXpath_02 = "(//li[@class='text-info'])[2]";
//	private static final String checkbox = "(//input[@class='alloy-select-boolean-checkbox checkbox'])[2]";
//	private static final String checkbox = "(//input[@class='alloy-select-boolean-checkbox checkbox'])[2]";
//	private static final String checkbox = "(//input[@class='alloy-select-boolean-checkbox checkbox'])[2]";
//	private static final String checkbox = "(//input[@class='alloy-select-boolean-checkbox checkbox'])[2]";
//	
	
	

//	private String inputTextUrl = "http://localhost:8080/alloy-showcase-webapp-3.0.0-SNAPSHOT/web/guest/showcase/-/component/alloy/inputtext"
	private String inputTextUrl = "http://localhost:8080/web/guest/showcase/-/component/alloy/inputtext";
	// http://localhost:8080/web/guest/showcase1/-/component/alloy/inputtext/general
	
	private String url;

	private WebElement input;
	private WebElement submitButton;
	private WebElement modelValue;
	
//	private List<WebElement> inputList;
//	private List<WebElement> submitButtonList;
//	private List<WebElement> modelValueList;

	WebDriver browser = new FirefoxDriver();
//	WebDriver browser = new PhantomJSDriver();
//	WebDriver browser = new ChromeDriver();
//	WebDriver browser = new HtmlUnitDriver();
	
	WebDriverWait wait = new WebDriverWait(browser, 10);

	@Before
	public void setUp() {
		browser.manage();
	}

	@After
	public void tearDown() {
		browser.close();
	}

	@Test
	public void alloyInputTextGeneral() throws Exception {
		url = inputTextUrl + "/general";
		browser.navigate().to(url);

		String magic = "Hello World!";
		
		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();

		Thread.sleep(150);
		
		if (browser.findElement(By.xpath(successXpath)) != null) {
			System.out.println("SuccessText is Present");
			}else{
			System.out.println("SuccessText is Absent");
			}
		
		input = browser.findElement(By.xpath(checkboxXpath_02));
		input.click();
		
		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();

		Thread.sleep(150);

		if (browser.findElement(By.xpath(ModelValueContainsErrorTextXpath_01)) != null) {
			System.out.println("ErrorText is Present");
			}else{
			System.out.println("ErrorText is Absent");
			}

		input = browser.findElement(By.xpath(inputXpath));
		input.sendKeys(magic);
//		waitForElement(browser, inputXpath);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));


		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();

//		waitForElement(browser, modelValueXpath);
		Thread.sleep(150);

		modelValue = browser.findElement(By.xpath(modelValueXpath));
		System.out.println("modelValue.getText() = " + modelValue.getText());

		assertTrue(modelValue.getText().contains(magic));

		url = inputTextUrl + "/conversion";
		browser.navigate().to(url);

		String magicIn = "apr 3, 33";
		String magicInErr = "apr 3 33";
		String magicOut = "Apr 3, 0033";
		
		
//		inputList = (List<WebElement>) browser.findElements(By.xpath(inputXpath)); "('xpath of the link')[2]")
//		input = inputList.get(0);
		input = browser.findElement(By.xpath(inputXpath));

		input.clear();
		input.sendKeys(magicInErr);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();
		
//		waitForElement(browser, modelValueXpath);
		Thread.sleep(150);
		
		if (browser.findElement(By.xpath(ModelValueContainsErrorTextXpath_01)) != null) {
			System.out.println("ConversionErrorText is Present");
			}else{
			System.out.println("ConversionErrorText is Absent");
			}
		
		input = browser.findElement(By.xpath(inputXpath));
		input.clear();
		input.sendKeys(magicIn);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
//		submitButtonList = (List<WebElement>) browser.findElements(By.xpath(submitButtonXpath));
//		submitButtonList.get(0).click();
		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();
		
//		waitForElement(browser, modelValueXpath);
		Thread.sleep(150);
		
		String text = "";
		
		modelValue = browser.findElement(By.xpath(modelValueXpath));
		text = modelValue.getText();
		System.out.println("text = " + text);

		assertTrue("modelValue should contain " + magicOut + ", but it contains '" + text + "'", 
			text.contains(magicOut)
		);
		
		magicIn = "4/3/33";
		magicInErr = "4/333";
		magicOut = "04/03/0033";
		
//		inputList = (List<WebElement>) browser.findElements(By.xpath(inputXpath)); "('xpath of the link')[2]")
//		input = inputList.get(0);
		input = browser.findElement(By.xpath(inputXpathRight));
		
		input.clear();
		input.sendKeys(magicInErr);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();
		
//		waitForElement(browser, modelValueXpath);
		Thread.sleep(150);

		input = browser.findElement(By.xpath(inputXpathRight));		
		input.clear();
		input.sendKeys(magicIn);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
//		submitButtonList = (List<WebElement>) browser.findElements(By.xpath(submitButtonXpath));
//		submitButtonList.get(0).click();
		submitButton = browser.findElement(By.xpath(submitButtonXpathRight));
		submitButton.click();


		
		waitForElement(browser, modelValueXpathRight);
		Thread.sleep(150);
		text = "";
		
		modelValue = browser.findElement(By.xpath(modelValueXpathRight));
		text = modelValue.getText();
		System.out.println("text = " + text);

		assertTrue("modelValue should contain " + magicOut + ", but it contains '" + text + "'", 
			text.contains(magicOut)
		);

		url = inputTextUrl + "/immediate";
		browser.navigate().to(url);

		magicIn = "Hello World!";
		magicOut = "Hello World!";
		
//		inputList = (List<WebElement>) browser.findElements(By.xpath(inputXpath)); "('xpath of the link')[2]")
//		input = inputList.get(0);
		input = browser.findElement(By.xpath(inputXpath));
		input.clear();
		input.sendKeys(magicIn);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
//		submitButtonList = (List<WebElement>) browser.findElements(By.xpath(submitButtonXpath));
//		submitButtonList.get(0).click();
		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();


		
		waitForElement(browser, modelValueXpath);
		Thread.sleep(150);
		text = "";
		
		modelValue = browser.findElement(By.xpath(modelValueXpath));
		text = modelValue.getText();
		System.out.println("text = " + text);

		assertTrue("modelValue should contain " + magicOut + ", but it contains '" + text + "'", 
			text.contains(magicOut)
		);
		
		magicIn = "Hello World!";
		magicOut = "Hello World!";
		
//		inputList = (List<WebElement>) browser.findElements(By.xpath(inputXpath)); "('xpath of the link')[2]")
//		input = inputList.get(0);
		input = browser.findElement(By.xpath(inputXpathRight));
		input.clear();
		input.sendKeys(magicIn);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
//		submitButtonList = (List<WebElement>) browser.findElements(By.xpath(submitButtonXpath));
//		submitButtonList.get(0).click();
		submitButton = browser.findElement(By.xpath(submitButtonXpathRight));
		submitButton.click();


		
		waitForElement(browser, modelValueXpathRight);
		Thread.sleep(150);
		text = "";
		
		modelValue = browser.findElement(By.xpath(modelValueXpathRight));
		text = modelValue.getText();
		System.out.println("text = " + text);

		assertTrue("modelValue should contain " + magicOut + ", but it contains '" + text + "'", 
			text.contains(magicOut)
		);
		
		if (browser.findElement(By.xpath(ModelValueContainsSpecifiedTextXpath_01)) != null) {
			System.out.println("ImmediateShowcaseModelText is Present");
			}else{
			System.out.println("ImmediateShowcaseModelText is Absent");
			}
		if (browser.findElement(By.xpath(ModelValueContainsSpecifiedTextXpath_02)) != null) {
			System.out.println("ImmediateShowcaseModelText02 is Present");
			}else{
			System.out.println("ImmediateShowcaseModelText02 is Absent");
			}

		url = inputTextUrl + "/validation";

		browser.navigate().to(url);

		magicIn = "Hello@World.com";
		magicInErr = "HelloWorldcom";
		magicOut = "Hello@World.com";
		
//		inputList = (List<WebElement>) browser.findElements(By.xpath(inputXpath)); "('xpath of the link')[2]")
//		input = inputList.get(0);
		input = browser.findElement(By.xpath(inputXpath));

		input.clear();
		input.sendKeys(magicInErr);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();
		
//		waitForElement(browser, modelValueXpath);
		Thread.sleep(150);
		
		if (browser.findElement(By.xpath(ModelValueContainsErrorTextXpath_01)) != null) {
			System.out.println("ValidationErrorText is Present");
			}else{
			System.out.println("ValidationErrorText is Absent");
			}
		
		input = browser.findElement(By.xpath(inputXpath));
		input.clear();
		input.sendKeys(magicIn);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
//		submitButtonList = (List<WebElement>) browser.findElements(By.xpath(submitButtonXpath));
//		submitButtonList.get(0).click();
		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();


		
		waitForElement(browser, modelValueXpath);
		Thread.sleep(150);
		text = "";
		
		modelValue = browser.findElement(By.xpath(modelValueXpath));
		text = modelValue.getText();
		System.out.println("text = " + text);

		assertTrue("modelValue should contain " + magicOut + ", but it contains '" + text + "'", 
			text.contains(magicOut)
		);
		
		magicIn = "World@Hello.com";
		magicInErr = "WorldHellocom";
		magicOut = "World@Hello.com";
		
//		inputList = (List<WebElement>) browser.findElements(By.xpath(inputXpath)); "('xpath of the link')[2]")
//		input = inputList.get(0);
		input = browser.findElement(By.xpath(inputXpathRight));

		input.clear();
		input.sendKeys(magicInErr);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
		submitButton = browser.findElement(By.xpath(submitButtonXpathRight));
		submitButton.click();
		
//		waitForElement(browser, modelValueXpath);
		Thread.sleep(150);
		
		if (browser.findElement(By.xpath(ModelValueContainsErrorTextXpath_01)) != null) {
			System.out.println("ValidationErrorText is Present");
			}else{
			System.out.println("ValidationErrorText is Absent");
			}
		
		input = browser.findElement(By.xpath(inputXpathRight));
		input.clear();
		input.sendKeys(magicIn);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
//		submitButtonList = (List<WebElement>) browser.findElements(By.xpath(submitButtonXpath));
//		submitButtonList.get(0).click();
		submitButton = browser.findElement(By.xpath(submitButtonXpathRight));
		submitButton.click();


		
		waitForElement(browser, modelValueXpath);
		Thread.sleep(150);
		text = "";
		
		modelValue = browser.findElement(By.xpath(modelValueXpathRight));
		text = modelValue.getText();
		System.out.println("text = " + text);

		assertTrue("modelValue should contain " + magicOut + ", but it contains '" + text + "'", 
			text.contains(magicOut)
		);

	}
	
	public void waitForElement(WebDriver browser, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(browser, 10);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.ByXPath.xpath(xpath)));
//			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.ByXPath.xpath(xpath)));
		} catch(Exception e) {
			System.err.println("waitForElement: e.getMessage() = " + e.getMessage());
//			e.printStackTrace();
		}
	}
	
//	public boolean isThere(WebDriver browser, String xpath) {
//		boolean isThere = false;
//		int count = 0;
//		count = browser.findElements(By.xpath(xpath)).size();
//
//		if (count == 0) {
//			isThere = false;
//		}
//
//		if (count > 0) {
//			isThere = true;
//		}
//
//		if (count > 1) {
//			System.err.println(
//				"The method 'isThere(xpath)' found " + count + 
//				" matches using xpath = " + xpath + 
//				" ... the word 'is' implies singluar, or 1, not " + count
//			);
//		}
//
//		return isThere;
//	}

}
// J+
