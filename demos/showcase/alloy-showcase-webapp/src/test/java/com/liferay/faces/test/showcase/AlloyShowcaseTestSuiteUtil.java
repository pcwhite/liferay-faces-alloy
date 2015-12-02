/**
 * Copyright (c) 2000-2015 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
package com.liferay.faces.test.showcase;

import java.util.List;

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

	public static void assertElementExists(String xpath) {
		assertElementExists("Element " + xpath + " exists.", xpath);
	}

	public static void assertElementExists(String message, String xpath) {
		WebElement element = getElement(xpath);
		Assert.assertNotNull(message, element);
	}

	public static void assertElementTextExists(String xpath, String text) {
		assertElementTextExists("Element " + xpath + " contains text '" + text + "'", xpath, text);
	}

	public static void assertElementTextExists(String message, String xpath, String text) {
		WebElement modelValue = getElement(xpath);
		String modelValueText = modelValue.getText();
		Assert.assertEquals(message, text, modelValueText);
	}

	public static void click(String xpath) {
		getElement(xpath).click();
	}

	public static void navigateToURL(String url) {
		Browser.getInstance().navigate().to(url);
	}

	public static void sendKeys(String xpath, String keys) {
		getElement(xpath).sendKeys(keys);
	}

	public static void waitForElement(String xpath) {

		// Perhaps this is a good place for logging, since we should be concerned about race conditions here.
		WAIT.until(ExpectedConditions.visibilityOfElementLocated(By.ByXPath.xpath(xpath)));
	}

	public static void waitForElementText(String xpath, String text) {

		// Perhaps this is a good place for logging, since we should be concerned about race conditions here.
		WAIT.until(ExpectedConditions.textToBePresentInElementLocated(By.ByXPath.xpath(xpath), text));
	}

	public static void waitWhileElementExists(WebElement element) {

		// Perhaps this is a good place for logging, since we should be concerned about race conditions here.

		// Note: I used stalenessOf instead of invisibilityOf because invisibiltyOf causes PhantomJS to log a warning.
		// Either one works though.
		WAIT.until(ExpectedConditions.stalenessOf(element));
	}

	public static WebElement getElement(String xpath) {
		return Browser.getInstance().findElement(By.xpath(xpath));
	}

	@BeforeClass
	public void setUp() {
		Browser.getInstance().manage();
	}
}
