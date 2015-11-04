package com.liferay.faces.test.showcase;
//J-

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlloyInputText {

	private static final String inputXpath = "//input[contains(@id,':text')]";
	private static final String submitButtonXpath = "//button[contains(text(),'Submit')]";
	private static final String modelValueXpath = "//span[contains(@id,':modelValue')]";

//	private String inputTextUrl = "http://localhost:8080/alloy-showcase-webapp-3.0.0-SNAPSHOT/web/guest/showcase/-/component/alloy/inputtext"
	private String inputTextUrl = "http://localhost:8080/web/guest/showcase1/-/component/alloy/inputtext";
	// http://localhost:8080/web/guest/showcase1/-/component/alloy/inputtext/general
	
	private String url;

	private WebElement input;
	private WebElement submitButton;
	private WebElement modelValue;
	
	private List<WebElement> inputList;
	private List<WebElement> submitButtonList;
	private List<WebElement> modelValueList;

	WebDriver browser = new FirefoxDriver();
//	WebDriver browser = new PhantomJSDriver();
//	WebDriver browser = new ChromeDriver();
//	WebDriver browser = new HtmlUnitDriver();
	
	WebDriverWait wait = new WebDriverWait(browser, 10);

	@Before
	public void setUp() {
		Options options = browser.manage();
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

		input = browser.findElement(By.xpath(inputXpath));
		input.sendKeys(magic);
//		waitForElement(browser, inputXpath);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));


		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();

		waitForElement(browser, modelValueXpath);

		modelValue = browser.findElement(By.xpath(modelValueXpath));
		System.out.println("modelValue.getText() = " + modelValue.getText());

		assertTrue(modelValue.getText().contains(magic));

	}

	
	@Test
	public void alloyInputTextConversion() throws Exception {
		url = inputTextUrl + "/conversion";
		browser.navigate().to(url);

		String magicIn = "apr 3, 33";
		String magicOut = "Apr 3, 0033";
		
		inputList = (List<WebElement>) browser.findElements(By.xpath(inputXpath));
		input = inputList.get(0);
		input.clear();
		input.sendKeys(magicIn);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));
		
		submitButtonList = (List<WebElement>) browser.findElements(By.xpath(submitButtonXpath));
		submitButtonList.get(0).click();
		
		waitForElement(browser, modelValueXpath);
//		Thread.sleep(150);
		String text = "";
		
		modelValueList = (List<WebElement>) browser.findElements(By.xpath(modelValueXpath));
		text = modelValueList.get(0).getText();
		System.out.println("text = " + text);

		assertTrue("modelValueList should contain " + magicOut + ", but it contains '" + text + "'", 
			text.contains(magicOut)
		);
		
		magicIn = "4/17/33";
		magicOut = "04/17/0033";
		
		inputList = (List<WebElement>) browser.findElements(By.xpath(inputXpath));
		input = inputList.get(1);
		input.clear();
		input.sendKeys(magicIn);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));

		submitButtonList = (List<WebElement>) browser.findElements(By.xpath(submitButtonXpath));
		submitButtonList.get(1).click();
	
		waitForElement(browser, modelValueXpath);
//		Thread.sleep(1000);

		modelValueList = (List<WebElement>) browser.findElements(By.xpath(modelValueXpath));
		System.out.println("modelValueList.get(1).getText() = " + modelValueList.get(1).getText());

		assertTrue("modelValueList should contain " + magicOut + ", but it contains '" + modelValueList.get(1).getText() + "'",
			modelValueList.get(1).getText().contains(magicOut)
		);

	}


	
	@Test
	public void alloyInputTextImmediate() throws Exception {
		url = inputTextUrl + "/immediate";
		browser.navigate().to(url);

		String magic = "Hello World!";

		input = browser.findElement(By.xpath(inputXpath));
		input.sendKeys(magic);
		waitForElement(browser, inputXpath);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));

		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();

		waitForElement(browser, modelValueXpath);

		modelValue = browser.findElement(By.xpath(modelValueXpath));
		System.out.println("modelValue.getText() = " + modelValue.getText());

		assertTrue(modelValue.getText().contains(magic));

	}

	
	@Test
	public void alloyInputTextValidation() throws Exception {
		url = inputTextUrl + "/validation";

		browser.navigate().to(url);

		String magic = "Hello World!";

		input = browser.findElement(By.xpath(inputXpath));
		input.sendKeys(magic);
		waitForElement(browser, inputXpath);
		System.out.println("input.getAttribute('value') = " + input.getAttribute("value"));


		submitButton = browser.findElement(By.xpath(submitButtonXpath));
		submitButton.click();

		waitForElement(browser, modelValueXpath);

		modelValue = browser.findElement(By.xpath(modelValueXpath));
		System.out.println("modelValue.getText() = " + modelValue.getText());

		assertTrue(modelValue.getText().contains(magic));

	}
	
	public void waitForElement(WebDriver browser, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(browser, 10);
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.ByXPath.xpath(xpath)));
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.ByXPath.xpath(xpath)));
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
