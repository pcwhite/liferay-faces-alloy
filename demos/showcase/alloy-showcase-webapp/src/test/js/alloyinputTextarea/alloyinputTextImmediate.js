// http://docs.casperjs.org/en/latest/testing.html#browser-tests
// http://docs.casperjs.org/en/latest/modules/

// var url = "http://localhost:8080/alloy-showcase-webapp-2.0.0-SNAPSHOT/views/component.faces?componentPrefix=alloy&componentName=inputtext&componentUseCase=";
var url = "http://localhost:8080/web/guest/showcase/-/component/alloy/inputtextarea/";
var x = require('casper').selectXPath;
var useCase = 'immediate';

casper.test.begin('Test alloy:inputText ' + useCase, function suite(test) {

        casper.start(url + useCase, function () {

		var buttonXpath = "//button[text()='Submit']";
		var buttonSelector = x(buttonXpath);
		var inputXpath = "(//textarea[contains(@id,':textarea')])[1]";
		var inputSelector = x(inputXpath);
		var specificTextXpath = "(//li[@class='text-info'])[1]";
                var specificTextSelector = x(specificTextXpath);

                this.test.assertExists(buttonSelector);
                this.test.assertExists(inputSelector);
                var magic = "Hello World 1";
                this.sendKeys(inputSelector, magic, {reset: true});
                this.click(buttonSelector);

                var modelValueXpath = "(//span[contains(@id,':modelValue')])[1]";
                var modelValueSelector = x(modelValueXpath);
                this.test.assertExists(modelValueSelector);

                this.waitForSelectorTextChange(modelValueSelector, function () {
                        var modelValueText = this.fetchText(modelValueSelector);
                        this.test.assertEquals(modelValueText, magic, "modelValueText equals \"" + magic + "\"");
                	this.test.assertExists(specificTextSelector, "'APPLY_REQUEST_VALUES' text present");
                });
        });

        casper.then(function () {

		var buttonXpath2 = "(//button[text()='Submit'])[2]";
		var buttonSelector2 = x(buttonXpath2);
		var inputXpath2 = "(//textarea[contains(@id,':textarea')])[2]";
		var inputSelector2 = x(inputXpath2);
		var specificTextXpath = "(//li[@class='text-info'])[2]";
                var specificTextSelector = x(specificTextXpath);

                this.test.assertExists(buttonSelector2);
                this.test.assertExists(inputSelector2);
                var magic = "Hello World 2";
                this.sendKeys(inputSelector2, magic, {reset: true});
                this.click(buttonSelector2);

		var modelValueXpath2 = "(//span[contains(@id,':modelValue')])[2]";
                var modelValueSelector = x(modelValueXpath2);
                this.test.assertExists(modelValueSelector);

                this.waitForSelectorTextChange(modelValueSelector, function () {
                        var modelValueText = this.fetchText(modelValueSelector);
                        this.test.assertEquals(modelValueText, magic, "modelValueTextRight equals \"" + magic + "\"");
                	this.test.assertExists(specificTextSelector, "'PROCESS_VALIDATIONS' text present");
                });

        });

        casper.run(function () {
                test.done();
        });
});
