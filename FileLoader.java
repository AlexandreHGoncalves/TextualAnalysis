import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileLoader{
    public static String text = "";

    public static String loadSingleFile(int fileIndex){
        Path filePath = Path.of("./Documents/arq_" + fileIndex + ".txt");
        try {
            text = Files.readString(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    public static String loadMultipleFiles(int firstIndex, int lastIndex){
        for (int i = firstIndex; i <= lastIndex; i++) {
            Path filePath = Path.of("./Documents/arq_" + i + ".txt");
            try {
                text = text.concat(Files.readString(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }

        return text;
    }

}