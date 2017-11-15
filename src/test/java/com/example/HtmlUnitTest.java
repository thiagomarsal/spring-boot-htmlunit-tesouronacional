package com.example;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class HtmlUnitTest {

    @Test
    public void homePage() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
            Assert.assertEquals("HtmlUnit – Welcome to HtmlUnit", page.getTitleText());

            final String pageAsXml = page.asXml();
            Assert.assertTrue(pageAsXml.contains("<body class=\"topBarDisabled\">"));

            final String pageAsText = page.asText();
            Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
        }
    }

    @Test
    public void getElements() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
            final HtmlDivision div = page.getHtmlElementById("banner");
            Assert.assertNotNull(div);
        }
    }

    @Test
    public void xpath() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

            //get list of all divs
            final List<?> divs = page.getByXPath("//div");

            //get div which has a 'name' attribute of 'John'
//            final HtmlDivision div = (HtmlDivision) page.getByXPath("//div[@name='John']").get(0);
//            Assert.assertNotNull(div);

            String xpath = "(//ul[@class='breadcrumb']/li)[1]/span";
            final List<Object> anchor = page.getByXPath(xpath);
            Assert.assertNotNull(anchor);
        }
    }

    @Test
    public void cssSelector() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

            //get list of all divs
            final DomNodeList<DomNode> divs = page.querySelectorAll("div");
            for (DomNode div : divs) {
            }

            //get div which has the id 'breadcrumbs'
            final DomNode div = page.querySelector("div#breadcrumbs");
            Assert.assertNotNull(div);
        }
    }

    /*@Test
    public void homePage_proxy() throws Exception {
        try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52, "myproxyserver", myProxyPort)) {

            //set proxy username and password
            final DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient.getCredentialsProvider();
            credentialsProvider.addCredentials("username", "password");

            final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
            Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
        }
    }

    @Test
    public void submittingForm() throws Exception {
        try (final WebClient webClient = new WebClient()) {

            // Get the first page
            final HtmlPage page1 = webClient.getPage("http://some_url");

            // Get the form that we are dealing with and within that form,
            // find the submit button and the field that we want to change.
            final HtmlForm form = page1.getFormByName("myform");

            final HtmlSubmitInput button = form.getInputByName("submitbutton");
            final HtmlTextInput textField = form.getInputByName("userid");

            // Change the value of the text field
            textField.setValueAttribute("root");

            // Now submit the form by clicking the button and get back the second page.
            final HtmlPage page2 = button.click();
        }
    }*/
}
