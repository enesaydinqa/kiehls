package context.annotations;

import java.util.Arrays;

public class DescriptionManager
{

    public DescriptionManager(Object o)
    {
        getDescription(o);
    }

    private void getDescription(Object o)
    {
        Arrays.stream(o.getClass().getDeclaredFields()).parallel().filter(DescriptionField
                -> DescriptionField.isAnnotationPresent(TestDescription.class)).forEach(DescriptionField
                -> {

            TestDescription description = DescriptionField.getAnnotation(TestDescription.class);
            DescriptionField.setAccessible(true);
            try
            {
                if (!description.value().isEmpty())
                    DescriptionField.set(o, description.value());
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        });
    }
}
