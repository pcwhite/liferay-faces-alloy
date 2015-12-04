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
package com.liferay.faces.test.showcase.inputtext;

import org.junit.Test;

import org.openqa.selenium.WebElement;

import static com.liferay.faces.test.showcase.AlloyShowcaseTestSuiteUtil.*;


/**
 * @author  Kyle Stiemann
 */
public class InputTextValidationTest extends InputTextTester {

	@Test
	public void runInputTextValidationTest() throws Exception {

		navigateToURL(inputTextURL + "validation");

		String invalidText = "HelloWorldcom";
		String text = "Hello@World.com";

		{
			waitForElement(submitButtonXpath);

			WebElement input = getElement(inputXpath);
			input.clear();
			input.sendKeys(invalidText);
			click(submitButtonXpath);
			waitForElement(errorXpath);
			assertElementExists(errorXpath);
		}

		{
			WebElement input = getElement(inputXpath);
			input.clear();
			input.sendKeys(text);
			click(submitButtonXpath);
			waitForElementText(modelValueXpath, text);
			assertElementTextExists(modelValueXpath, text);
		}

		{
			WebElement input = getElement(inputXpathRight);
			input.clear();
			input.sendKeys(invalidText);
			click(submitButtonXpathRight);
			waitForElement(errorXpath);
			assertElementExists(errorXpath);
		}

		{
			WebElement input = getElement(inputXpathRight);
			input.clear();
			input.sendKeys(text);
			click(submitButtonXpathRight);
			waitForElementText(modelValueXpathRight, text);
			assertElementTextExists(modelValueXpathRight, text);
		}
	}
}
