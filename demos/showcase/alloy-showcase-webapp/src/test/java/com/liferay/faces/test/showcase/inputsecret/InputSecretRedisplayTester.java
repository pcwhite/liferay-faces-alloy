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
package com.liferay.faces.test.showcase.inputsecret;

import org.junit.Test;

import org.openqa.selenium.WebElement;

import com.liferay.faces.test.showcase.Browser;


/**
 * @author  Kyle Stiemann
 * @author  Philip White
 */
public class InputSecretRedisplayTester extends InputSecretTester {

	@Test
	public void runInputSecretRedisplayTest() throws Exception {

		Browser browser = Browser.getInstance();
		browser.navigateToURL(inputSecretURL + "redisplay");

		// Wait to begin the test until the submit button is rendered.
		browser.waitForElement(submitButtonXpath);

		// Test that the value submits successfully and the alloy:inputSecret component is intentionally
		// not re-rendered in the DOM.
		WebElement input = browser.getElement(inputSecretXpath);
		String text = "Hello World!";
		input.sendKeys(text);
		browser.click(submitButtonXpath);

		String immediateMessage = "//td[contains(text(),'was intentionally not re-rendered')]";
		browser.waitForElement(immediateMessage);
		browser.assertElementTextExists(modelValueXpath, text);
		browser.assertElementExists(immediateMessage);

		// Test that the value submits successfully and the entire form (including the alloy:inputSecret component)
		// is re-rendered in the DOM.
		input = browser.getElement(inputSecretXpathRight);
		input.sendKeys(text);
		browser.click(submitButtonXpathRight);

		String immediateMessageRight = "//td[contains(text(),'entire form')]";
		browser.waitForElement(immediateMessageRight);
		browser.assertElementTextExists(modelValueXpathRight, text);
		browser.assertElementExists(immediateMessageRight);
	}
}
