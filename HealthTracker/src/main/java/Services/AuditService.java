package Services;

import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {

    private static final String AUDIT_FILE = "audit_log.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logAction(String action, String tableName, String details){
        String timestamp = LocalDateTime.now().format(formatter);

        String logEntry = timestamp + ": The action " + action + " was done to the table " + tableName + ". Details " + details + "\n";

        try (FileWriter writer = new FileWriter(AUDIT_FILE, true)){
            writer.write(logEntry);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
