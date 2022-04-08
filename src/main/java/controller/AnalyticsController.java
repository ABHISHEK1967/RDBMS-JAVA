package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;

public class AnalyticsController {

    private static final String queryLogsPath = "./src/main/java/Logs/QueryLogs.txt";
    private static final String COUNT_QUERIES =
            "count queries";
    private static final String COUNT_QUERY_STRING =
            "^(?i)(COUNT\\sQUERIES\\s[a-zA-Z\\d]+;)$";
    private static final String COUNT_UPDATE =
            "count update";
    private static final String COUNT_UPDATE_STRING =
            "^(?i)(COUNT\\sUPDATE\\s[a-zA-Z\\d]+;)$";
    private static final String COUNT_SELECT =
            "count select";
    private static final String COUNT_SELECT_STRING =
            "^(?i)(COUNT\\sSELECT\\s[a-zA-Z\\d]+;)$";
    private static final String COUNT_INSERT =
            "count insert";
    private static final String COUNT_INSERT_STRING =
            "^(?i)(COUNT\\sINSERT\\s[a-zA-Z\\d]+;)$";
    private static final String COUNT_DELETE =
            "count delete";
    private static final String COUNT_DELETE_STRING =
            "^(?i)(COUNT\\sDELETE\\s[a-zA-Z\\d]+;)$";
    private static int queryCount;
    private static int updateCount;
    private static int selectCount;
    private static int insertCount;
    private static int deleteCount;

    public String analytics(String query) throws Exception {
        String message = null;
        if (isQueryValid(query)) {
            if (Pattern.matches(COUNT_QUERY_STRING, query)) {
                message = executeCountQuery(query);
            } else if (Pattern.matches(COUNT_UPDATE_STRING, query)) {
                message = executeUpdateQuery(query);
            }else if (Pattern.matches(COUNT_SELECT_STRING, query)) {
                message = executeSelectQuery(query);
            }else if (Pattern.matches(COUNT_INSERT_STRING, query)) {
                message = executeInsertQuery(query);
            }else if (Pattern.matches(COUNT_DELETE_STRING, query)) {
                message = executeDeleteQuery(query);
            }
        } else {
            message = "Invalid Analytics query";
        }
        return message;
    }

    private boolean isQueryValid(String query) throws Exception {
        final String Query = query.trim().toLowerCase();
        if (Query.contains(COUNT_QUERIES)) {
            if (!Pattern.matches(COUNT_QUERY_STRING, Query)) {
                return false;
            }
        } else if (Query.contains(COUNT_UPDATE)) {
            if (!Pattern.matches(COUNT_UPDATE_STRING, Query)) {
                return false;
            }
        }else if (Query.contains(COUNT_SELECT)) {
            if (!Pattern.matches(COUNT_SELECT_STRING, Query)) {
                return false;
            }
        }else if (Query.contains(COUNT_INSERT)) {
            if (!Pattern.matches(COUNT_INSERT_STRING, Query)) {
                return false;
            }
        }else if (Query.contains(COUNT_DELETE)) {
            if (!Pattern.matches(COUNT_DELETE_STRING, Query)) {
                return false;
            }
        }else {
            return false;
        }
        return true;
    }


    private String executeCountQuery(String query) throws Exception {
        String dbName = query.substring(0, query.length() - 1).split(" ")[2];
        String line, message;
        try (final FileReader fileReader = new FileReader(queryLogsPath);
             final BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("|")) {
                    String dbNameFromLog = line.split("\\|")[2].trim().split(" ")[0];
                    if (dbNameFromLog.equalsIgnoreCase(dbName)) {
                        ++queryCount;
                    }
                }
            }
            message = "Submitted " + queryCount + " queries on " + dbName;
        }
        System.out.println(message);
        return "Analytics executed";
    }

    private String executeUpdateQuery(String query) throws Exception {
        String dbName = query.substring(0, query.length() - 1).split(" ")[2];
        String line, message;
        try (final FileReader fileReader = new FileReader(queryLogsPath);
             final BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("|") && line.contains("UPDATE")) {
                    String dbNameFromLog = line.split("\\$")[1].trim().split(" ")[0];
                    if (dbNameFromLog.equalsIgnoreCase(dbName)) {
                        ++updateCount;
                    }
                }
            }
            message = "Total " + updateCount + " Update operations are performed on " + dbName;
        }
        System.out.println(message);
        return "Analytics executed";
    }

    private String executeInsertQuery(String query) throws Exception {
        String dbName = query.substring(0, query.length() - 1).split(" ")[2];
        String line, message;
        try (final FileReader fileReader = new FileReader(queryLogsPath);
             final BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("|") && line.contains("INSERTING")) {
                    String dbNameFromLog = line.split("\\$")[1].trim().split(" ")[0];
                    if (dbNameFromLog.equalsIgnoreCase(dbName)) {
                        ++insertCount;
                    }
                }
            }
            message = "Total " + insertCount + " Insert operations are performed on " + dbName;
        }
        System.out.println(message);
        return "Analytics executed";
    }

    private String executeSelectQuery(String query) throws Exception {
        String dbName = query.substring(0, query.length() - 1).split(" ")[2];
        String line, message;
        try (final FileReader fileReader = new FileReader(queryLogsPath);
             final BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("|") && line.contains("SELECT")) {
                    String dbNameFromLog = line.split("\\$")[1].trim().split(" ")[0];
                    if (dbNameFromLog.equalsIgnoreCase(dbName)) {
                        ++selectCount;
                    }
                }
            }
            message = "Total " + selectCount + " Select operations are performed on " + dbName;
        }
        System.out.println(message);
        return "Analytics executed";
    }

    private String executeDeleteQuery(String query) throws Exception {
        String dbName = query.substring(0, query.length() - 1).split(" ")[2];
        String line, message;
        try (final FileReader fileReader = new FileReader(queryLogsPath);
             final BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("|") && line.contains("DELETE")) {
                    String dbNameFromLog = line.split("\\$")[1].trim().split(" ")[0];
                    if (dbNameFromLog.equalsIgnoreCase(dbName)) {
                        ++deleteCount;
                    }
                }
            }
            message = "Total " + deleteCount + " DELETE operations are performed on " + dbName;
        }
        System.out.println(message);
        return "Analytics executed";
    }

}
