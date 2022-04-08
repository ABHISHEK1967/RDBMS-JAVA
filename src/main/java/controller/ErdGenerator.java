package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ErdGenerator {
    public void generateERD(String databaseName){
        final String databasePath = "./src/main/java/Model/database/" + databaseName+"/";
        final File allTables = new File(databasePath);
        for(File table: allTables.listFiles()){
            try (final FileWriter fileWriter = new FileWriter(
                    "./src/main/java/erd/"+databaseName+".txt", true);
                 final FileReader fileReader = new FileReader(table);
                 final BufferedReader bufferReader = new BufferedReader(fileReader)){
                String tableName = table.getName().split("\\.")[0];
                fileWriter.append(tableName);
                fileWriter.append(System.getProperty("line.separator"));
                final List<String> list = new ArrayList<>();
                final String columnHeaders = bufferReader.readLine();
                final String[] ColumnHeaders = columnHeaders.split("\\$\\|\\|\\$");
                for(String columnNames : ColumnHeaders){
                    buildErdLogic(fileWriter, tableName, list, columnNames);
                }
                fileWriter.append(System.getProperty("line.separator"));
                for (final String value : list) {
                    fileWriter.append(value);
                    fileWriter.append("\n");
                }
                fileWriter.append(System.getProperty("line.separator"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void buildErdLogic(FileWriter fileWriter, String tableName, List<String> list, String columnNames) throws IOException {
        final String[] columnName = columnNames.split("\\(");
        final String colName = columnName[0];
        final String colAttributes = columnName[1].substring(0, columnName[1].length() - 1);
        final String[] Attributes = colAttributes.split("\\|");
        if (Attributes.length == 2 && Attributes[1].equals("PK")) {
            fileWriter.append("PK ")
                    .append("| ")
                    .append(colName).append(" ")
                    .append(Attributes[0]);
        } else if (Attributes.length == 4 && Attributes[1].equals("FK")) {
            fileWriter.append("FK ")
                    .append("| ")
                    .append(colName).append(" ")
                    .append(Attributes[0]);
            list.add(tableName +" "+colName+" => "+Attributes[2]+" "+ Attributes[3]);
           } else {
            fileWriter.append(colName).append(" ")
                    .append(Attributes[0]);
        }
        fileWriter.append(System.getProperty("line.separator"));
    }


}
