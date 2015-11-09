package com.liferay.faces.test.showcase; 

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class AlloyInputText {

    private String inputTextUrl = "http://localhost:8080/web/guest/showcase1/-/component/alloy/inputtext";
    private String url;

    private WebClient webClient;

    @Before
    public void setUp() {
        webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true);
    }

    @After
    public void tearDown() {
        webClient.close();
    }

    @Test
    public void alloyInputTextGeneral() throws Exception {

    	url = inputTextUrl + "/general";
    	HtmlPage initialPage = webClient.getPage(url);

    	String magic = "Hello World!";
    	String inputXpath = "//input[contains(@id,':text')]";
    	String buttonXpath = "//button[@type='submit']";
    	String modelValueXpath = "//span[contains(@id,':modelValue')]";

    	HtmlTextInput input = initialPage.getFirstByXPath(inputXpath);
    	HtmlButton submit = initialPage.getFirstByXPath(buttonXpath);
    	HtmlSpan modelValueSpan = initialPage.getFirstByXPath(modelValueXpath);

    	if (input != null) {
    		input.type(magic);
    		System.out.println("input.getValueAttribute() = " + input.getValueAttribute());
    	}

        if (submit != null) {
        	HtmlPage pageAfterSubmit = (HtmlPage) submit.click();
        	webClient.waitForBackgroundJavaScriptStartingBefore(100);
        	
        	System.out.println("pageAfterSubmit.isHtmlPage() = " + pageAfterSubmit.isHtmlPage());
        	modelValueSpan = pageAfterSubmit.getFirstByXPath(modelValueXpath);
        	if (modelValueSpan != null) {
        		System.out.println("modelValueList.getTextContent() = " + modelValueSpan.getTextContent());
        	}
        }

        assertTrue(modelValueSpan.getTextContent().contains(magic));

    }
    
    @Test
    public void alloyInputTextConversion() throws Exception {

    	url = inputTextUrl + "/conversion";
    	HtmlPage initialPage = webClient.getPage(url);

    	String magic = "Apr 3, 0033";
    	String inputXpath = "//input[contains(@id,':text')]";
    	String buttonXpath = "//button[@type='submit']";
    	String modelValueXpath = "//span[contains(@id,':modelValue')]";

    	List<HtmlTextInput> inputList = (List<HtmlTextInput>) initialPage.getByXPath(inputXpath);
    	List<HtmlButton> submitList = (List<HtmlButton>) initialPage.getByXPath(buttonXpath);

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
        	HtmlPage pageAfterSubmit = (HtmlPage) submit.click();
        	webClient.waitForBackgroundJavaScriptStartingBefore(100);
        	
        	List<HtmlSpan> modelValueSpanList = (List<HtmlSpan>) pageAfterSubmit.getByXPath(modelValueXpath);
        	HtmlSpan modelValueSpan = (HtmlSpan) modelValueSpanList.get(0);
        	if (modelValueSpan != null) {
        		System.out.println("0 modelValueSpan.getTextContent() = " + modelValueSpan.getTextContent());
        	}
        	assertTrue("modelValueSpan should contain " + magic + ", but it contains '" + modelValueSpan.getTextContent() + "'", 
        		modelValueSpan.getTextContent().contains(magic)
        	);
    	}
    	
    	// test for the right column
    	magic = "04/03/0033";
    	
    	if (inputList != null) {
    		HtmlTextInput inputText = (HtmlTextInput) inputList.get(1);
    		System.out.println("1 before inputText.getValueAttribute() = " + inputText.getValueAttribute());
    		inputText.setValueAttribute("");
    		inputText.type(magic);
    		System.out.println("1 after inputText.getValueAttribute() = " + inputText.getValueAttribute());
    	}
    	
    	if (submitList != null) {
    		HtmlButton submit = (HtmlButton) submitList.get(1);
        	HtmlPage pageAfterSubmit = (HtmlPage) submit.click();
        	webClient.waitForBackgroundJavaScriptStartingBefore(100);
        	
        	List<HtmlSpan> modelValueSpanList = (List<HtmlSpan>) pageAfterSubmit.getByXPath(modelValueXpath);
        	HtmlSpan modelValueSpan = (HtmlSpan) modelValueSpanList.get(1);
        	if (modelValueSpan != null) {
        		System.out.println("1 modelValueSpan.getTextContent() = " + modelValueSpan.getTextContent());
        	}
        	assertTrue("modelValueSpan should contain " + magic + ", but it contains '" + modelValueSpan.getTextContent() + "'", 
        		modelValueSpan.getTextContent().contains(magic)
        	);
    	}
    	
    }
    
    @Test
    public void alloyInputTextImmediate() throws Exception {
    	
      url = inputTextUrl + "/immediate";
    	HtmlPage initialPage = webClient.getPage(url);
    	
    }
    
    @Test
    public void alloyInputTextValidation() throws Exception {
    	
      url = inputTextUrl + "/validation";
    	HtmlPage initialPage = webClient.getPage(url);
    	
    }

}
