package selenium.tests.galen.path;


public enum SpecFilePath
{

    MAIN_DIRECTORY(System.getProperty("user.dir") + "/src/test/resources/specs/"),

    MAIN_PAGE(MAIN_DIRECTORY, "NyxCostemicMainPage.spec");


    //-----

    private final String filePath;

    SpecFilePath(String specFileName)
    {
        this.filePath = specFileName;
    }

    SpecFilePath(SpecFilePath baseDirectory, String specFileName)
    {
        this.filePath = baseDirectory.filePath + specFileName;
    }

    public String getFilePath()
    {
        return filePath;
    }
}
