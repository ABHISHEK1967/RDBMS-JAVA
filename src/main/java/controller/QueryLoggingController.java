package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class QueryLoggingController {

    public static final String queryLogLocation = "./src/main/java/Logs/QueryLogs.txt";

    public void writeLog(String message, Long time) {
        try (FileWriter fileWriter = createFile(queryLogLocation)) {
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
                fileWriter = new FileWriter(queryLogLocation, true);
            } else {
                file.createNewFile();
                fileWriter = new FileWriter(queryLogLocation);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return fileWriter;
    }

}
