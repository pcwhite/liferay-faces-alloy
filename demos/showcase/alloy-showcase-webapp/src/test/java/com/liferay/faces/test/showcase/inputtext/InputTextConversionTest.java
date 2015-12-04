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
public class InputTextConversionTest extends InputTextTester {

	@Test
	public void runInputTextConversionTest() throws Exception {

		navigateToURL(inputTextURL + "conversion");

		{
			waitForElement(submitButtonXpath);

			WebElement input = getElement(inputXpath);
			input.clear();

			String invalidText = "apr 3 33";
			input.sendKeys(invalidText);
			click(submitButtonXpath);
			waitForElement(errorXpath);
			assertElementExists(errorXpath);
		}

		{
			WebElement input = getElement(inputXpath);
			input.clear();

			String text = "apr 3, 33";
			input.sendKeys(text);
			click(submitButtonXpath);

			String textOutput = "Apr 3, 0033";
			waitForElementText(modelValueXpath, textOutput);
			assertElementTextExists(modelValueXpath, textOutput);
		}

		{
			WebElement input = getElement(inputXpathRight);
			input.clear();

			String invalidText = "4/333";
			input.sendKeys(invalidText);
			click(submitButtonXpathRight);
			waitForElement(errorXpath);
			assertElementExists(errorXpath);
		}

		{
			WebElement input = getElement(inputXpathRight);
			input.clear();

			String text = "4/3/33";
			input.sendKeys(text);
			click(submitButtonXpathRight);

			String textOutput = "04/03/0033";
			waitForElementText(modelValueXpathRight, textOutput);
			assertElementTextExists(modelValueXpathRight, textOutput);
		}
	}
}
