/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferay.faces.test.showcase;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author  Kyle Stiemann
 */
public class AlloyShowcaseTestSuiteUtil {

	private static final WebDriverWait WAIT = new WebDriverWait(Browser.getInstance(), 5);
	public static final String url = "http://localhost:8080/web/guest/showcase/-/component/alloy/";

	@BeforeClass
	public void setUp() {
		Browser.getInstance().manage();
	}

	public static void click(String xpath) {
		getElement(xpath).click();
	}

	public static void sendKeys(String xpath, String keys) {
		getElement(xpath).sendKeys(keys);
	}

	public static void navigateToURL(String url) {
		Browser.getInstance().navigate().to(url);
	}

	public static WebElement getElement(String xpath) {
		return Browser.getInstance().findElement(By.xpath(xpath));
	}

	public static void waitForElement(String xpath) {
		WAIT.until(ExpectedConditions.visibilityOfElementLocated(By.ByXPath.xpath(xpath)));
	}

	public static void waitForElementText(String xpath, String text) {
		WAIT.until(ExpectedConditions.textToBePresentInElementLocated(By.ByXPath.xpath(xpath), text));
	}

	public static void waitForInvisiblilityOfElement(String xpath) {
		WAIT.until(ExpectedConditions.invisibilityOfElementLocated(By.ByXPath.xpath(xpath)));
	}

	public static void assertElementExists(String message, String xpath) {
		WebElement element = getElement(xpath);
		Assert.assertNotNull(message, element);
	}

	public static void assertElementExists(String xpath) {
		assertElementExists("Element " + xpath + " exists.", xpath);
	}

	public static void assertElementTextExists(String xpath, String text) {
		assertElementTextExists("Element " + xpath + " contains text '" + text + "'", xpath, text);
	}

	public static void assertElementTextExists(String message, String xpath, String text) {
		WebElement modelValue = getElement(xpath);
		String modelValueText = modelValue.getText();
		Assert.assertEquals(message, text, modelValueText);
	}
}
