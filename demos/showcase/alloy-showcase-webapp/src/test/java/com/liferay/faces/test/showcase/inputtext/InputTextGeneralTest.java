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

import static com.liferay.faces.test.showcase.AlloyShowcaseTestSuiteUtil.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * @author  Kyle Stiemann
 */
public class InputTextGeneralTest extends InputTextTester {

	@Test
	public void runInputTextGeneralTest() throws Exception {

		navigateToURL(inputTextURL + "general");

		String successXpath = "//div[@class='alloy-field control-group success']";

		{
			waitForElement(submitButtonXpath);
			click(submitButtonXpath);
			waitForElement(successXpath);
			assertElementExists(successXpath);
		}

		{
			WebElement successElement = getElement(successXpath);
			String requiredCheckboxXpath = "(//input[@class='alloy-select-boolean-checkbox checkbox'])[2]";
			click(requiredCheckboxXpath);
			waitWhileElementExists(successElement);
		}

		{
			click(submitButtonXpath);
			waitForElement(errorXpath);
			assertElementExists(errorXpath);
		}

		String text = "Hello World!";

		{
			sendKeys(inputXpath, text);
			click(submitButtonXpath);
			waitForElementText(modelValueXpath, text);
			assertElementTextExists(modelValueXpath, text);
		}
	}
}
