package selenium.tests.galen.path;

public enum SpecFilePath
{

    WEB_DIRECTORY(System.getProperty("user.dir") + "/src/test/resources/specs/web/"),
    RESPONSIVE_DIRECTORY(System.getProperty("user.dir") + "/src/test/resources/specs/mobile/"),

    HOME_PAGE_WEB(WEB_DIRECTORY, "NYXCosmeticsMainPage.spec"),


    HOME_PAGE_RESPONSIVE(RESPONSIVE_DIRECTORY, "ResponsiveNYXCosmeticsMainPage.spec");


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
