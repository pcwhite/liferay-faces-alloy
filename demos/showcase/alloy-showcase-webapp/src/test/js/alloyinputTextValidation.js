// http://docs.casperjs.org/en/latest/testing.html#browser-tests
// http://docs.casperjs.org/en/latest/modules/

// var url = "http://localhost:8080/alloy-showcase-webapp-2.0.0-SNAPSHOT/views/component.faces?componentPrefix=alloy&componentName=inputtext&componentUseCase=";
var url = "http://localhost:8080/web/guest/showcase/-/component/alloy/inputtext/";
var x = require('casper').selectXPath;
var useCase = 'validation';

casper.test.begin('Test alloy:inputText ' + useCase, function suite(test) {

	var buttonXpath = "//button[text()='Submit']";
	var buttonSelector = x(buttonXpath);
	var inputXpath = "//input[contains(@id,':text')]";
	var inputSelector = x(inputXpath);

        casper.start(url + useCase, function () {

                this.test.assertExists(buttonSelector);
                this.test.assertExists(inputSelector);
                var magic = "helloworldcom";
                this.sendKeys(inputSelector, magic, {reset: true});
                this.click(buttonSelector);

                var errorTextXpath = "//span[@class='alloy-message help-inline']";
                var errorTextSelector = x(errorTextXpath);
                this.waitUntilVisible(errorTextSelector, function () {
                        this.test.assertExists(errorTextSelector);
                });
        });

        casper.then(function () {

                var magic = "hello@world.com";
                this.sendKeys(inputSelector, magic, {reset: true});
                this.click(buttonSelector);

                var modelValueXpath = "(//span[contains(@id,':modelValue')])[1]";
                var modelValueSelector = x(modelValueXpath);
                this.test.assertExists(modelValueSelector);

                this.waitForSelectorTextChange(modelValueSelector, function () {
                        var modelValueText = this.fetchText(modelValueSelector);
                        this.test.assertEquals(modelValueText, magic, "modelValueText equals \"" + magic + "\"");
                });
        });

	var buttonXpath2 = "(//button[text()='Submit'])[2]";
	var buttonSelector2 = x(buttonXpath2);
	var inputXpath2 = "(//input[contains(@id,':text')])[2]";
	var inputSelector2 = x(inputXpath2);

        casper.then(function () {

                this.test.assertExists(buttonSelector2);
                this.test.assertExists(inputSelector2);
                var magic = "worldhellocom";
                this.sendKeys(inputSelector2, magic, {reset: true});
                this.click(buttonSelector2);

                var errorTextXpath = "//span[@class='alloy-message help-inline']";
                var errorTextSelector = x(errorTextXpath);
                this.waitUntilVisible(errorTextSelector, function () {
                        this.test.assertExists(errorTextSelector);
                });
        });

        casper.then(function () {

                var magic = "world@hello.com";
                this.sendKeys(inputSelector2, magic, {reset: true});
                this.click(buttonSelector2);

                var modelValueXpath2 = "(//span[contains(@id,':modelValue')])[2]";
                var modelValueSelector = x(modelValueXpath2);
                this.test.assertExists(modelValueSelector);

                this.waitForSelectorTextChange(modelValueSelector, function () {
                        var modelValueText = this.fetchText(modelValueSelector);
                        this.test.assertEquals(modelValueText, magic, "modelValueTextRight equals \"" + magic + "\"");
                });
        });
        casper.run(function () {
                test.done();
        });
});
