// http://docs.casperjs.org/en/latest/testing.html#browser-tests
// http://docs.casperjs.org/en/latest/modules/

var url = "http://localhost:8181/alloy-showcase-webapp-3.0.0-SNAPSHOT/views/component.faces?componentPrefix=alloy&componentName=inputtext&componentUseCase=";
var useCase = 'general';
var x = require('casper').selectXPath;

casper.test.begin('Test alloy:inputText ' + useCase, function suite(test) {

	casper.start(url + useCase, function () {

		var inputXpath = '//input[contains(@id,":text")]';
		var buttonXpath = '//button[text()="Submit"]';
		var modelValueXpath = '//span[contains(@id,":modelValue")]';

		this.test.assertExists({
			type: 'xpath',
			path: inputXpath
		}, 'input element exists');

		this.test.assertExists({
			type: 'xpath',
			path: buttonXpath
		}, 'submit button element exists');

		this.test.assertExists({
			type: 'xpath',
			path: modelValueXpath
		}, 'modelValue element exists');

		var magic = "Hello World!";
		this.sendKeys(x(inputXpath), magic);
		this.click(x(buttonXpath));
		var modelValueSelector = x(modelValueXpath);
		this.waitForSelectorTextChange(modelValueSelector, function () {
			var modelValueText = this.fetchText(modelValueSelector);
			this.test.assertEquals(modelValueText, magic, "modelValueText equals \"" + magic + "\"");
		});
	}).run(function () {
		test.done();
	});
});

useCase = 'conversion';

casper.test.begin('Test alloy:inputText ' + useCase, function suite(test) {

	casper.start(url + useCase, function () {

		var inputXpath = '//input[contains(@id,":text")]';
		var buttonXpath = '//button[text()="Submit"]';
		var modelValueXpath = '//span[contains(@id,":modelValue")]';

		this.test.assertExists({
			type: 'xpath',
			path: inputXpath
		}, 'input element exists');

		this.test.assertExists({
			type: 'xpath',
			path: buttonXpath
		}, 'submit button element exists');

		this.test.assertExists({
			type: 'xpath',
			path: modelValueXpath
		}, 'modelValue element exists');

		var magic = "Hello World!";
		this.sendKeys(x(inputXpath), magic);
		this.click(x(buttonXpath));
		var modelValueSelector = x(modelValueXpath);
		this.waitForSelectorTextChange(modelValueSelector, function () {
			var modelValueText = this.fetchText(modelValueSelector);
			this.test.assertEquals(modelValueText, magic, "modelValueText equals \"" + magic + "\"");
		});
	}).run(function () {
		test.done();
	});
});
