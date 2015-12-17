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
public class InputHiddenImmediateTester extends InputHiddenTester {

	@Test
	public void runInputHiddenImmediateTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.navigateToURL(inputHiddenURL + "immediate");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElementVisible(submitButtonXpath);

		// Test that the hidden value submits successfully and the valueChangeListener method is called during the
		// APPLY_REQUEST_VALUES phase.
		browser.click(hiddenButtonXpath);
		browser.click(submitButtonXpath);

		String immediateMessage = "//li[@class='text-info'][contains(text(),'APPLY_REQUEST_VALUES')]";
		String hiddenValue = "(//input[contains(@value,'1234')])[1]";
		browser.waitForElementVisible(immediateMessage);
		browser.assertElementPresent(hiddenValue);
		browser.assertElementPresent(immediateMessage);

		browser.click(clearButtonXpath);
		browser.click(submitButtonXpath);
		browser.waitForElementPresent(hiddenValueEmptyXpath);
		browser.assertElementPresent(hiddenValueEmptyXpath);

		// Test that the value submits successfully and the valueChangeListener method is called during the
		// PROCESS_VALIDATIONS phase.
		browser.click(hiddenButtonXpathRight);
		browser.click(submitButtonXpathRight);

		String immediateMessageRight = "//li[@class='text-info'][contains(text(),'PROCESS_VALIDATIONS')]";
		String hiddenValueRight = "(//input[contains(@value,'1234')])[2]";
		browser.waitForElementVisible(immediateMessageRight);
		browser.assertElementPresent(hiddenValueRight);
		browser.assertElementPresent(immediateMessageRight);

		browser.click(clearButtonXpath);
		browser.click(submitButtonXpath);
		browser.waitForElementPresent(hiddenValueEmptyXpathRight);
		browser.assertElementPresent(hiddenValueEmptyXpathRight);
	}
}
