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
public class InputTextImmediateTest extends InputTextTester {

	@Test
	public void runInputTextImmediateTest() throws Exception {

		navigateToURL(inputTextURL + "immediate");
		String text = "Hello World!";

		{
			waitForElement(submitButtonXpath);
			WebElement input = getElement(inputXpath);
			input.clear();
			input.sendKeys(text);
			click(submitButtonXpath);
			String immediateMessage = "(//li[@class='text-info'])[1]";
			waitForElement(immediateMessage);
			assertElementTextExists(modelValueXpath, text);
			assertElementExists(immediateMessage);
		}

		{
			WebElement input = getElement(inputXpathRight);
			input.clear();
			input.sendKeys(text);
			click(submitButtonXpathRight);
			String immediateMessageRight = "(//li[@class='text-info'])[2]";
			waitForElement(immediateMessageRight);
			assertElementTextExists(modelValueXpathRight, text);
			assertElementExists(immediateMessageRight);
		}
	}
}
