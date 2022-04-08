package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class EventLoggingController {

    public static final String eventLogLocation = "./src/main/java/Logs/EventLogs.txt";

    public void writeLog(String message, Long time) {
        try (FileWriter fileWriter = createFile(eventLogLocation)) {
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
                fileWriter = new FileWriter(eventLogLocation, true);
            } else {
                file.createNewFile();
                fileWriter = new FileWriter(eventLogLocation);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return fileWriter;
    }
}
