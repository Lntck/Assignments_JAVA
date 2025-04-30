package Directory_Walker;

import java.util.HashMap;
import java.util.Map;


public class FilePropertiesFactory {
    static Map<String, FileProperties> FilePropertiesTypes = new HashMap<>();

    public static FileProperties getFileProperties(String extension, boolean isReadOnly, String owner, String group) {
        String keyString = extension + "-" + isReadOnly + "-" + owner + "-" + group;
        FileProperties result = FilePropertiesTypes.get(keyString);
        if (result == null) {
            result = new FileProperties(extension, isReadOnly, owner, group);
            FilePropertiesTypes.put(keyString, result);
        }
        return result;
    }
}
