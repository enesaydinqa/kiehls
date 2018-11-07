package Interfaces;

public interface Constants {

    interface Regex
    {
        String LOCATOR_TYPE = "([^^]*> )";
        String LOCATOR_VALUE = "{LOCATORTYPE}: ";
    }

    interface LocatorType
    {
        String CLASS_NAME = "class name";
        String CSS = "css selector";
        String ID = "id";
        String LINK_TEXT = "linkText";
        String NAME = "name";
        String PARTIAL_LINK_TEXT = "partialLinkText";
        String TAG_NAME = "tagName";
        String XPATH = "xpath";
    }


}
