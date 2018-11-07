package Interfaces;

public interface JavaScripts {

    interface TextContent
    {
        String TAG_NAME = "return document.getElementsByTagName('{LOCATORVALUE}').textContent";
        String NAME = "return document.querySelector('[name= '{LOCATORVALUE}']').textContent";
        String ID = "return document.getElementById('{LOCATORVALUE}').textContent";
        String CSS = "return document.querySelector('{LOCATORVALUE}').textContent";
        String CLASS_NAME = "return document.getElementsByClassName('{LOCATORVALUE}').textContent";
        String XPATH = "return document.evaluate('{LOCATORVALUE}', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.textContent";
    }

    interface SelectedIndex
    {
        String TAG_NAME = "document.getElementsByTagName('{LOCATORVALUE}').selectedIndex='{INDEX}'";
        String NAME = "document.querySelector('[name= '{LOCATORVALUE}']').selectedIndex='{INDEX}'";
        String ID = "document.getElementById('{LOCATORVALUE}').selectedIndex='{INDEX}'";
        String CSS = "document.querySelector('{LOCATORVALUE}').selectedIndex='{INDEX}'";
        String CLASS_NAME = "document.getElementsByClassName('{LOCATORVALUE}').selectedIndex='{INDEX}'";
        String XPATH = "document.evaluate('{LOCATORVALUE}', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.selectedIndex='{INDEX}'";
    }


}

