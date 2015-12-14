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
public class InputHiddenConversionTester extends InputHiddenTester {

	@Test
	public void runInputTextareaConversionTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.navigateToURL(inputHiddenURL + "conversion");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElement(submitButtonXpath);

		// Test that the web page shows an error message when an invalid value is submitted.
		String text = "Apr 5, 0033";
		browser.click(hiddenButtonXpath);
		browser.click(submitButtonXpath);
		browser.waitForElementText(modelValueXpath, text);
		browser.assertElementTextExists(modelValueXpath, text);
		browser.click(clearButtonXpath);
		browser.click(submitButtonXpath);

		// Test that a valid value submits successfully.
//		input = browser.getElement(inputXpath);
//		input.clear();
//
//		String text = "apr 3, 33";
//		input.sendKeys(text);
//		browser.click(submitButtonXpath);
//
//		String textOutput = "Apr 3, 0033";
//		browser.waitForElementText(modelValueXpath, textOutput);
//		browser.assertElementTextExists(modelValueXpath, textOutput);

		// Test that the web page shows an error message when an invalid value is submitted.
		text = "04/05/0033";
		browser.click(hiddenButtonXpathRight);
		browser.click(submitButtonXpathRight);
		browser.waitForElementText(modelValueXpathRight, text);
		browser.assertElementTextExists(modelValueXpathRight, text);
		browser.click(clearButtonXpathRight);
		browser.click(submitButtonXpathRight);

		// Test that a valid value submits successfully.
//		input = browser.getElement(inputXpathRight);
//		input.clear();
//		text = "4/3/33";
//		input.sendKeys(text);
//		browser.click(submitButtonXpathRight);
//		textOutput = "04/03/0033";
//		browser.waitForElementText(modelValueXpathRight, textOutput);
//		browser.assertElementTextExists(modelValueXpathRight, textOutput);
	}
}
