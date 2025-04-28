package Directory_Walker;

import java.util.HashMap;
import java.util.Map;


public class FilePropertiesFactory {
    static Map<String, FileProperties> FilePropertiesTypes = new HashMap<>();

    public static FileProperties getTreeType(String extension, boolean isReadOnly, String owner, String group) {
        FileProperties result = FilePropertiesTypes.get(extension);
        if (result == null) {
            result = new FileProperties(extension, isReadOnly, owner, group);
            FilePropertiesTypes.put(extension, result);
        }
        return result;
    }
}
