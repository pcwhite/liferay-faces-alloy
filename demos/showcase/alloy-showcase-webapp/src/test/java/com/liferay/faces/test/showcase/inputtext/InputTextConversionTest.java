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
