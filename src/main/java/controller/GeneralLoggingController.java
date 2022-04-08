package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class GeneralLoggingController {

    public static final String GeneralLogLocation = "./src/main/java/Logs/GeneralLogs.txt";

    public void writeLog(String message, Long time) {
        try (FileWriter fileWriter = createFile(GeneralLogLocation)) {
            fileWriter.write(message + " " + new Timestamp(time)+"\n");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public FileWriter createFile(String fileLocation) {
        File file = new File(fileLocation);
        FileWriter fileWriter = null;
        try {
            if (file.exists()) {
                fileWriter = new FileWriter(GeneralLogLocation, true);
            } else {
                file.createNewFile();
                fileWriter = new FileWriter(GeneralLogLocation);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return fileWriter;
    }
}
