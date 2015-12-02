/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferay.faces.test.showcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

/**
 * @author  Kyle Stiemann
 */
public class Browser {

	private static WebDriver browser = null;

	public static WebDriver getInstance() {

		if (browser == null) {
			browser = new FirefoxDriver();
			browser.manage();
		}

		return browser;
	}
}
