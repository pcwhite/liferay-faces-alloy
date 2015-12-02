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
