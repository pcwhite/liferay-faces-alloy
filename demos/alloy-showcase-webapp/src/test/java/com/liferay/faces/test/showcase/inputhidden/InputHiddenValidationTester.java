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
package com.liferay.faces.test.showcase.inputhidden;

import org.junit.Test;

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.showcase.Browser;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputHiddenValidationTester extends InputHiddenTester {

	@Test
	public void runInputHiddenValidationTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.navigateToURL(inputHiddenURL + "validation");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButtonXpath);

		// Test that a hidden valid value submits successfully.
		String text = "test@liferay.com";
		String hiddenButtonValid = "(//button[contains(text(),'a valid')])[1]";
		browser.click(hiddenButtonValid);
		browser.click(submitButtonXpath);
		browser.waitForElementTextPresent(modelValueXpath, text);
		browser.assertElementTextPresent(modelValueXpath, text);
		
		// Test that the hidden value clears successfully.
		browser.click(clearButtonXpath);
		browser.click(submitButtonXpath);
		browser.waitForElementPresent(modelValueEmptyXpath);
		browser.assertElementPresent(modelValueEmptyXpath);
		
		// Test that the web page shows an error message when an invalid value is submitted.
		String hiddenButtonInvalid = "(//button[contains(text(),'an invalid')])[1]";
		browser.click(hiddenButtonInvalid);
		browser.click(submitButtonXpath);
		browser.waitForElementVisible(errorXpath);
		browser.assertElementPresent(errorXpath);
		
		
		// Test that a hidden valid value submits successfully.
		hiddenButtonValid = "(//button[contains(text(),'a valid')])[2]";
		browser.click(hiddenButtonValid);
		browser.click(submitButtonXpathRight);
		browser.waitForElementTextPresent(modelValueXpathRight, text);
		browser.assertElementTextPresent(modelValueXpathRight, text);
		

		// Test that the hidden value clears successfully.
		browser.click(clearButtonXpathRight);
		browser.click(submitButtonXpathRight);
		browser.waitForElementPresent(modelValueEmptyXpathRight);
		browser.assertElementPresent(modelValueEmptyXpathRight);

		// Test that the web page shows an error message when an invalid value is submitted.
		hiddenButtonInvalid = "(//button[contains(text(),'an invalid')])[2]";
		browser.click(hiddenButtonInvalid);
		browser.click(submitButtonXpathRight);
		browser.waitForElementVisible(errorXpathRight);
		browser.assertElementPresent(errorXpathRight);
	}
}
