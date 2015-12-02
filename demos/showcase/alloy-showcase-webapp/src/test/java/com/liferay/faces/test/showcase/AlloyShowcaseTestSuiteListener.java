/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.liferay.faces.test.showcase;

import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/**
 * @author  Kyle Stiemann
 */
public class AlloyShowcaseTestSuiteListener extends RunListener {

	@Override
	public void testRunFinished(Result result) throws Exception {

		Browser.getInstance().close();
		super.testRunFinished(result);
	}
}
