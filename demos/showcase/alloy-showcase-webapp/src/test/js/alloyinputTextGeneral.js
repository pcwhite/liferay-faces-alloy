// http://docs.casperjs.org/en/latest/testing.html#browser-tests
// http://docs.casperjs.org/en/latest/modules/

// var url = "http://localhost:8080/alloy-showcase-webapp-2.0.0-SNAPSHOT/views/component.faces?componentPrefix=alloy&componentName=inputtext&componentUseCase=";
var url = "http://localhost:8080/web/guest/showcase/-/component/alloy/inputtext/";
var useCase = 'general';
var x = require('casper').selectXPath;

casper.test.begin('Test alloy:inputText ' + useCase, function suite(test) {

	var buttonXpath = "//button[text()='Submit']";
	var buttonSelector = x(buttonXpath); 
	var successXpath = "//div[@class='alloy-field control-group success']";
	var successSelector = x(successXpath);

	casper.start(url + useCase, function () {

		this.test.assertExists(buttonSelector);

		this.click(buttonSelector);
                this.waitUntilVisible(successSelector, function () {
                        this.test.assertExists(successSelector);
                });
	});
	
	casper.then(function () {

		var checkboxXpath = "(//input[@class='alloy-select-boolean-checkbox checkbox'])[2]";
		var checkboxSelector = x(checkboxXpath); 
                this.test.assertExists(checkboxSelector);
		this.click(checkboxSelector);
		this.waitWhileVisible(successSelector, function () {
			this.click(buttonSelector);
		});
        });

        casper.then(function () {

		var errorTextXpath = "//span[@class='alloy-message help-inline']";
		var errorTextSelector = x(errorTextXpath);
		this.waitUntilVisible(errorTextSelector, function () {
			this.test.assertExists(errorTextSelector);
		});
        });

        casper.then(function () {
		
		var inputXpath = "//input[contains(@id,':text')]";
		var inputSelector = x(inputXpath);
		this.test.assertExists(inputSelector);
		
		var magic = "Hello World!";
		this.sendKeys(inputSelector, magic);
		this.click(buttonSelector);

                var modelValueXpath = "//span[contains(@id,':modelValue')]";
                var modelValueSelector = x(modelValueXpath);
                this.test.assertExists(modelValueSelector);
		
		this.waitForSelectorTextChange(modelValueSelector, function () {
			var modelValueText = this.fetchText(modelValueSelector);
			this.test.assertEquals(modelValueText, magic, "modelValueText equals \"" + magic + "\"");
		});
	});
		
	casper.run(function () {
		test.done();
	});
});
