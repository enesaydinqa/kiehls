package context.helper;

public interface JavaScripts
{
    String GET_TEXT_BY_XPATH = "return document.evaluate(\"{XPATH_SELECTOR}\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.textContent;";
}
