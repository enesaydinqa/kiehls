package selenium.tests.galen.path;

public enum SpecFilePath
{

    WEB_DIRECTORY(System.getProperty("user.dir") + "/src/test/resources/specs/web/"),
    RESPONSIVE_DIRECTORY(System.getProperty("user.dir") + "/src/test/resources/specs/mobile/"),

    MAIN_PAGE_WEB(WEB_DIRECTORY, "NyxCostemicMainPage.spec"),



    MAIN_PAGE_RESPONSIVE(RESPONSIVE_DIRECTORY, "ResponsiveNyxCostemicsMainPage.spec");


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
