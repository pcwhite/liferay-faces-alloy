/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferay.faces.test.showcase.inputtext;

import com.liferay.faces.test.showcase.AlloyShowcaseTestSuiteUtil;

/**
 * @author  Kyle Stiemann
 */
public class InputTextTester {

	protected static final String inputTextURL = AlloyShowcaseTestSuiteUtil.url + "inputtext/";
	protected static final String modelValueXpath = "//span[contains(@id,':modelValue')]";
	protected static final String inputXpath = "//input[contains(@id,':text')]";
	protected static final String submitButtonXpath = "//button[contains(text(),'Submit')]";
	protected static final String errorXpath = "//span[@class='alloy-message help-inline']";
	protected static final String inputXpathRight = "(//input[contains(@id,':text')])[2]";
	protected static final String submitButtonXpathRight = "(//button[contains(text(),'Submit')])[2]";
	protected static final String modelValueXpathRight = "(//span[contains(@id,':modelValue')])[2]";
}
