package Directory_Walker;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating and reusing FileProperties instances using the Flyweight pattern.
 * Caches FileProperties to reduce memory usage by sharing identical instances.
 */
public class FilePropertiesFactory {
    // Cache for storing unique FileProperties instances
    private static final Map<String, FileProperties> FilePropertiesTypes = new HashMap<>();

    /**
     * Retrieves or creates a FileProperties instance for the given attributes.
     * Uses a composite key to cache and reuse identical instances.
     * @param extension file extension
     * @param isReadOnly read-only status
     * @param owner file owner
     * @param group file group
     * @return cached or new FileProperties instance
     */
    public static FileProperties getFileProperties(String extension, boolean isReadOnly, String owner, String group) {
        // Generate unique key for the combination of attributes
        String keyString = extension + "-" + isReadOnly + "-" + owner + "-" + group;
        // Retrieve from cache or create new instance
        FileProperties result = FilePropertiesTypes.get(keyString);
        if (result == null) {
            result = new FileProperties(extension, isReadOnly, owner, group);
            FilePropertiesTypes.put(keyString, result);
        }
        return result;
    }
}