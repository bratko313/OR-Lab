package fer.or.api.util;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {

    public static void overwriteFileWithString(Resource resource, String text) throws IOException {
        Path path = resource.getFile().toPath();
        Files.write(path, text.getBytes());
    }
}
