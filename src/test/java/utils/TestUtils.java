package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    public static String convertDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    public static File getSchemaByName(String name) {
        return new File("src/test/resources/schema/" + name + ".json");
    }

}
