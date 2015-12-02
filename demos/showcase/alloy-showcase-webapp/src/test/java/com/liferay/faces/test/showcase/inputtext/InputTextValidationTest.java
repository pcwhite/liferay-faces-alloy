/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferay.faces.test.showcase.inputtext;

import static com.liferay.faces.test.showcase.AlloyShowcaseTestSuiteUtil.*;
import org.junit.Test;
import org.openqa.selenium.WebElement;

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
