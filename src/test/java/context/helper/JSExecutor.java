package context.helper;

public interface JSExecutor
{
    <T> T executeScript(Class<T> clazz,String script, Object... args);

}
