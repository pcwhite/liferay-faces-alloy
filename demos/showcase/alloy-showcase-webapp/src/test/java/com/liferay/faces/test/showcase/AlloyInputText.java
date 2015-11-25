package com.liferay.faces.test.showcase; 

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.validator.internal.constraintvalidators.AssertTrueValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class AlloyInputText {

    private String inputTextUrl = "http://localhost:8080/web/guest/showcase/-/component/alloy/inputtext";
    private String url;

    private WebClient webClient;

    @Before
    public void setUp() {
        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
    }

    @After
    public void tearDown() {
        webClient.close();
    }

    @Test
    public void alloyInputTextGeneral() throws Exception {

    	url = inputTextUrl + "/general";
    	HtmlPage page = webClient.getPage(url);

    	String magic = "Hello World!";
    	String inputXpath = "//input[contains(@id,':text')]";
    	String buttonXpath = "//button[@type='submit']";
    	String modelValueXpath = "//span[contains(@id,':modelValue')]";
    	String checkboxXpath = "//input[@class='alloy-select-boolean-checkbox checkbox']";
    	String errorXpath = "//span[@class='alloy-message help-inline']";

    	HtmlTextInput input = page.getFirstByXPath(inputXpath);
    	HtmlButton submit = page.getFirstByXPath(buttonXpath);
    	HtmlSpan modelValueSpan = page.getFirstByXPath(modelValueXpath);
    	HtmlSpan errorSpan = page.getFirstByXPath(errorXpath);
    	
    	List<HtmlCheckBoxInput> checkboxList = (List<HtmlCheckBoxInput>) page.getByXPath(checkboxXpath);

    	if (checkboxList != null) {
    		HtmlCheckBoxInput checkbox = (HtmlCheckBoxInput) checkboxList.get(1);
    		page = checkbox.click();
//    		checkboxList = (List<HtmlCheckBoxInput>) page.getByXPath(checkboxXpath);
//    		checkbox = (HtmlCheckBoxInput) checkboxList.get(1);
    		System.out.println("checkbox.isChecked() = " + checkbox.isChecked());
    	}
    	
    	if (submit != null) {
        	page = (HtmlPage) submit.click();
        	
        	System.out.println("page.isHtmlPage() = " + page.isHtmlPage());
        	errorSpan = page.getFirstByXPath(errorXpath);
        	if (errorSpan != null) {
        		System.out.println("errorSpan.getTextContent() = " + errorSpan.getTextContent());
        	}
        }
    	
    	if (input != null) {
    		input.type(magic);
    		System.out.println("input.getValueAttribute() = " + input.getValueAttribute());
    	}

        if (submit != null) {
        	page = (HtmlPage) submit.click();
        	
        	System.out.println("pageAfterSubmit.isHtmlPage() = " + page.isHtmlPage());
        	modelValueSpan = page.getFirstByXPath(modelValueXpath);
        	if (modelValueSpan != null) {
        		System.out.println("modelValueSpan.getTextContent() = " + modelValueSpan.getTextContent());
        	}
        }

        assertTrue(modelValueSpan.getTextContent().contains(magic));

    }
    
    @Test
    public void alloyInputTextConversion() throws Exception {

    	url = inputTextUrl + "/conversion";
    	HtmlPage page = webClient.getPage(url);

    	String magicErr = "Apr 3 0033";
    	String magicIn = "April 03, 0033";
    	String magicOut = "Apr 3, 0033";
    	String inputXpath = "//input[contains(@id,':text')]";
    	String buttonXpath = "//button[@type='submit']";
    	String modelValueXpath = "//span[contains(@id,':modelValue')]";
    	String errorXpath = "//span[@class='alloy-message help-inline']";
    	
    	HtmlSpan errorSpan = page.getFirstByXPath(errorXpath);

    	List<HtmlTextInput> inputList = (List<HtmlTextInput>) page.getByXPath(inputXpath);
    	List<HtmlButton> submitList = (List<HtmlButton>) page.getByXPath(buttonXpath);

    	// test for the left column
    	if (inputList != null) {
    		HtmlTextInput inputText = (HtmlTextInput) inputList.get(0);
    		System.out.println("0 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
    		inputText.setValueAttribute("");
    		inputText.type(magicErr);
    		System.out.println("0 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
    	}
    	
    	if (submitList != null) {
    		HtmlButton submit = (HtmlButton) submitList.get(0);
        	page = (HtmlPage) submit.click();
        	
        	System.out.println("page.isHtmlPage() = " + page.isHtmlPage());
        	errorSpan = page.getFirstByXPath(errorXpath);
        	if (errorSpan != null) {
        		System.out.println("errorSpan.getTextContent() = " + errorSpan.getTextContent());
        	}
    	}
    	
    	if (inputList != null) {
    		HtmlTextInput inputText = (HtmlTextInput) inputList.get(0);
    		System.out.println("0 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
    		inputText.setValueAttribute("");
    		inputText.type(magicIn);
    		System.out.println("0 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
    	}
    	
    	if (submitList != null) {
    		HtmlButton submit = (HtmlButton) submitList.get(0);
        	page = (HtmlPage) submit.click();

        	List<HtmlSpan> modelValueSpanList = (List<HtmlSpan>) page.getByXPath(modelValueXpath);
        	HtmlSpan modelValueSpan = (HtmlSpan) modelValueSpanList.get(0);
        	if (modelValueSpan != null) {
        		System.out.println("0 modelValueSpan.getTextContent() = " + modelValueSpan.getTextContent());
        	}
        	assertTrue("modelValueSpan should contain " + magicOut + ", but it contains '" + modelValueSpan.getTextContent() + "'", 
        		modelValueSpan.getTextContent().contains(magicOut)
        	);
    	}
    	
    	// test for the right column
    	magicErr = "04/030033";
    	magicIn = "4/3/33";
    	magicOut = "04/03/0033";
    	
    	if (inputList != null) {
    		HtmlTextInput inputText = (HtmlTextInput) inputList.get(1);
    		System.out.println("1 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
    		inputText.setValueAttribute("");
    		inputText.type(magicErr);
    		System.out.println("1 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
    	}
    	
    	if (submitList != null) {
    		HtmlButton submit = (HtmlButton) submitList.get(1);
        	HtmlPage pageAfterSubmit = (HtmlPage) submit.click();
        	System.out.println("page.isHtmlPage() = " + page.isHtmlPage());
        	errorSpan = page.getFirstByXPath(errorXpath);
        	if (errorSpan != null) {
        		System.out.println("errorSpan.getTextContent() = " + errorSpan.getTextContent());
        	}
    	}
    	
    	if (inputList != null) {
    		HtmlTextInput inputText = (HtmlTextInput) inputList.get(1);
    		System.out.println("1 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
    		inputText.setValueAttribute("");
    		inputText.type(magicIn);
    		System.out.println("1 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
    	}
    	
    	if (submitList != null) {
    		HtmlButton submit = (HtmlButton) submitList.get(1);
        	page = (HtmlPage) submit.click();
        	
        	List<HtmlSpan> modelValueSpanList = (List<HtmlSpan>) page.getByXPath(modelValueXpath);
        	HtmlSpan modelValueSpan = (HtmlSpan) modelValueSpanList.get(1);
        	if (modelValueSpan != null) {
        		System.out.println("1 modelValueSpan.getTextContent() = " + modelValueSpan.getTextContent());
        	}
        	assertTrue("modelValueSpan should contain " + magicOut + ", but it contains '" + modelValueSpan.getTextContent() + "'", 
        		modelValueSpan.getTextContent().contains(magicOut)
        	);
    	}
    	
    }
    
    @Test
    public void alloyInputTextImmediate() throws Exception {
    	
      url = inputTextUrl + "/immediate";
    	HtmlPage page = webClient.getPage(url);
    	
    	String magic = "Hello World 1!";
    	String inputXpath = "//input[contains(@id,':text')]";
    	String buttonXpath = "//button[@type='submit']";
    	String modelValueXpath = "//span[contains(@id,':modelValue')]";
    	String SpecifiedTextXpath = "//li[@class='text-info']";

    	List<HtmlTextInput> inputList = (List<HtmlTextInput>) page.getByXPath(inputXpath);
    	List<HtmlButton> submitList = (List<HtmlButton>) page.getByXPath(buttonXpath);

    	// test for the left column
    	if (inputList != null) {
    		HtmlTextInput inputText = (HtmlTextInput) inputList.get(0);
    		System.out.println("0 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
    		inputText.setValueAttribute("");
    		inputText.type(magic);
    		System.out.println("0 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
    	}
    	
    	if (submitList != null) {
    		HtmlButton submit = (HtmlButton) submitList.get(0);
        	page = (HtmlPage) submit.click();
//        	webClient.waitForBackgroundJavaScriptStartingBefore(100);
        	
        	List<HtmlSpan> modelValueSpanList = (List<HtmlSpan>) page.getByXPath(modelValueXpath);
        	HtmlSpan modelValueSpan = (HtmlSpan) modelValueSpanList.get(0);
        	if (modelValueSpan != null) {
        		System.out.println("0 modelValueSpan.getTextContent() = " + modelValueSpan.getTextContent());
        	}
        	
        	List<HtmlListItem> SpecifiedTextList = (List<HtmlListItem>) page.getByXPath(SpecifiedTextXpath);
        	HtmlListItem SpecifiedText = (HtmlListItem) SpecifiedTextList.get(0);
        	if (SpecifiedText != null) {
        		System.out.println("0 SpecifiedText.getTextContent() = " + SpecifiedText.getTextContent());
        	}
        	
        	assertTrue("modelValueSpan should contain " + magic + ", but it contains '" + modelValueSpan.getTextContent() + "'", 
        		modelValueSpan.getTextContent().contains(magic)
        	);
    	}
    	
    	// test for the right column
    	magic = "Hello World 2!";
    	
    	if (inputList != null) {
    		HtmlTextInput inputText = (HtmlTextInput) inputList.get(1);
    		System.out.println("1 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
    		inputText.setValueAttribute("");
    		inputText.type(magic);
    		System.out.println("1 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
    	}
    	
    	if (submitList != null) {
    		HtmlButton submit = (HtmlButton) submitList.get(1);
        	page = (HtmlPage) submit.click();
//        	webClient.waitForBackgroundJavaScriptStartingBefore(100);
        	
        	List<HtmlSpan> modelValueSpanList = (List<HtmlSpan>) page.getByXPath(modelValueXpath);
        	HtmlSpan modelValueSpan = (HtmlSpan) modelValueSpanList.get(1);
        	if (modelValueSpan != null) {
        		System.out.println("1 modelValueSpan.getTextContent() = " + modelValueSpan.getTextContent());
        	}
        	
        	List<HtmlListItem> SpecifiedTextList = (List<HtmlListItem>) page.getByXPath(SpecifiedTextXpath);
        	HtmlListItem SpecifiedText = (HtmlListItem) SpecifiedTextList.get(1);
        	if (SpecifiedText != null) {
        		System.out.println("0 SpecifiedText.getTextContent() = " + SpecifiedText.getTextContent());
        	}
        	
        	assertTrue("modelValueSpan should contain " + magic + ", but it contains '" + modelValueSpan.getTextContent() + "'", 
        		modelValueSpan.getTextContent().contains(magic)
        	);
    	}
    	
    }
    
    @Test
    public void alloyInputTextValidation() throws Exception {
    	
      url = inputTextUrl + "/validation";
	  	HtmlPage page = webClient.getPage(url);
	
	  	String magicErr = "helloworldcom";
	  	String magicIn = "hello@world.com";
	  	String magicOut = "hello@world.com";
	  	String inputXpath = "//input[contains(@id,':text')]";
	  	String buttonXpath = "//button[@type='submit']";
	  	String modelValueXpath = "//span[contains(@id,':modelValue')]";
	  	String errorXpath = "//span[@class='alloy-message help-inline']";
	  	
	  	HtmlSpan errorSpan = page.getFirstByXPath(errorXpath);
	
	  	List<HtmlTextInput> inputList = (List<HtmlTextInput>) page.getByXPath(inputXpath);
	  	List<HtmlButton> submitList = (List<HtmlButton>) page.getByXPath(buttonXpath);
	
	  	// test for the left column
	  	if (inputList != null) {
	  		HtmlTextInput inputText = (HtmlTextInput) inputList.get(0);
	  		System.out.println("0 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
	  		inputText.setValueAttribute("");
	  		inputText.type(magicErr);
	  		System.out.println("0 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
	  	}
	  	
	  	if (submitList != null) {
	  		HtmlButton submit = (HtmlButton) submitList.get(0);
	      	page = (HtmlPage) submit.click();
	      	
	      	System.out.println("page.isHtmlPage() = " + page.isHtmlPage());
	      	errorSpan = page.getFirstByXPath(errorXpath);
	      	if (errorSpan != null) {
	      		System.out.println("errorSpan.getTextContent() = " + errorSpan.getTextContent());
	      	}
	  	}
	  	
	  	if (inputList != null) {
	  		HtmlTextInput inputText = (HtmlTextInput) inputList.get(0);
	  		System.out.println("0 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
	  		inputText.setValueAttribute("");
	  		inputText.type(magicIn);
	  		System.out.println("0 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
	  	}
	  	
	  	if (submitList != null) {
	  		HtmlButton submit = (HtmlButton) submitList.get(0);
	      	page = (HtmlPage) submit.click();
	
	      	List<HtmlSpan> modelValueSpanList = (List<HtmlSpan>) page.getByXPath(modelValueXpath);
	      	HtmlSpan modelValueSpan = (HtmlSpan) modelValueSpanList.get(0);
	      	if (modelValueSpan != null) {
	      		System.out.println("0 modelValueSpan.getTextContent() = " + modelValueSpan.getTextContent());
	      	}
	      	assertTrue("modelValueSpan should contain " + magicOut + ", but it contains '" + modelValueSpan.getTextContent() + "'", 
	      		modelValueSpan.getTextContent().contains(magicOut)
	      	);
	  	}
	  	
	  	// test for the right column
	  	magicErr = "worldhellocom";
	  	magicIn = "world@hello.com";
	  	magicOut = "world@hello.com";
	  	
	  	if (inputList != null) {
	  		HtmlTextInput inputText = (HtmlTextInput) inputList.get(1);
	  		System.out.println("1 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
	  		inputText.setValueAttribute("");
	  		inputText.type(magicErr);
	  		System.out.println("1 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
	  	}
	  	
	  	if (submitList != null) {
	  		HtmlButton submit = (HtmlButton) submitList.get(1);
	      	HtmlPage pageAfterSubmit = (HtmlPage) submit.click();
	      	System.out.println("page.isHtmlPage() = " + page.isHtmlPage());
	      	errorSpan = page.getFirstByXPath(errorXpath);
	      	if (errorSpan != null) {
	      		System.out.println("errorSpan.getTextContent() = " + errorSpan.getTextContent());
	      	}
	  	}
	  	
	  	if (inputList != null) {
	  		HtmlTextInput inputText = (HtmlTextInput) inputList.get(1);
	  		System.out.println("1 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
	  		inputText.setValueAttribute("");
	  		inputText.type(magicIn);
	  		System.out.println("1 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
	  	}
	  	
	  	if (submitList != null) {
	  		HtmlButton submit = (HtmlButton) submitList.get(1);
	      	page = (HtmlPage) submit.click();
	      	
	      	List<HtmlSpan> modelValueSpanList = (List<HtmlSpan>) page.getByXPath(modelValueXpath);
	      	HtmlSpan modelValueSpan = (HtmlSpan) modelValueSpanList.get(1);
	      	if (modelValueSpan != null) {
	      		System.out.println("1 modelValueSpan.getTextContent() = " + modelValueSpan.getTextContent());
	      	}
	      	assertTrue("modelValueSpan should contain " + magicOut + ", but it contains '" + modelValueSpan.getTextContent() + "'", 
	      		modelValueSpan.getTextContent().contains(magicOut)
	      	);
	  	}
  	
  }

}
