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
	public void runInputHiddenConversionTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.navigateToURL(inputHiddenURL + "conversion");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButtonXpath);

		// Test that a hidden valid value submits successfully.
		String text = "Apr 5, 0033";
		browser.click(hiddenButtonXpath);
		browser.click(submitButtonXpath);
		browser.waitForElementTextPresent(modelValueXpath, text);
		browser.assertElementTextPresent(modelValueXpath, text);
		
		// Test that the hidden value clears successfully.
		browser.click(clearButtonXpath);
		browser.click(submitButtonXpath);
		browser.waitForElementPresent(modelValueEmptyXpath);
		browser.assertElementPresent(modelValueEmptyXpath);

		// Test that a hidden valid value submits successfully.
		text = "04/05/0033";
		browser.click(hiddenButtonXpathRight);
		browser.click(submitButtonXpathRight);
		browser.waitForElementTextPresent(modelValueXpathRight, text);
		browser.assertElementTextPresent(modelValueXpathRight, text);
		
		// Test that the hidden value clears successfully.
		browser.click(clearButtonXpathRight);
		browser.click(submitButtonXpathRight);
		browser.waitForElementPresent(modelValueEmptyXpathRight);
		browser.assertElementPresent(modelValueEmptyXpathRight);
	}
}
