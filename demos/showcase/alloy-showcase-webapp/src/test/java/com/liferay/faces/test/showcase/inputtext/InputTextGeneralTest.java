/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferay.faces.test.showcase.inputtext;

import static com.liferay.faces.test.showcase.AlloyShowcaseTestSuiteUtil.*;
import org.junit.Test;

/**
 * @author  Kyle Stiemann
 */
public class InputTextGeneralTest extends InputTextTester {

	@Test
	public void runInputTextGeneralTest() throws Exception {

		navigateToURL(inputTextURL + "general");
		String successXpath = "(//div[@class='alloy-field control-group success'])[1]";

		{
			waitForElement(submitButtonXpath);
			click(submitButtonXpath);
			waitForElement(successXpath);
			assertElementExists(successXpath);
		}

		{
			String requiredCheckboxXpath = "(//input[@class='alloy-select-boolean-checkbox checkbox'])[2]";
			click(requiredCheckboxXpath);
			waitForInvisiblilityOfElement(successXpath);
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
