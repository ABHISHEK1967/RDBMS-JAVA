package controller;

import java.io.*;

public class MetaGenerator {

    public String generateMetaDataFile(final String databaseName,
                                       final String tableName){
        final StringBuilder stringBuilder = new StringBuilder();
        final String metaDataPath = "./src/main/java/metadata/"+databaseName +"/" ;
        final File metadataPath = new File(metaDataPath);
        if(!metadataPath.isDirectory() && !metadataPath.exists()){
            metadataPath.mkdirs();
        }
        try(final FileWriter fileWriter = new FileWriter(metadataPath+ "/"+tableName + ".txt"
                , true)){
            stringBuilder.append("Database Name").append(": ").append(databaseName).append("\n");
            stringBuilder.append("Table Name").append(": ").append(tableName).append("\n");
            stringBuilder.append("Columns").append(":").append("\n");
            final String tablePath = "./src/main/java/Model/database/"
                    + databaseName + "/" + tableName + ".txt";
            try (final FileReader fileReader = new FileReader(tablePath);
                 final BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                final String tableHeading = bufferedReader.readLine();
                final String[] headerColumns = tableHeading.split("\\$\\|\\|\\$");
                for (int i = 0; i < headerColumns.length; i++) {
                    final String TableHeadingColumn = headerColumns[i];
                    final String[] TableHeading = TableHeadingColumn.split("\\(");
                    final String columnName = TableHeading[0];
                    stringBuilder
                            .append("\t")
                            .append("- ")
                            .append(columnName)
                            .append(" (");
                    final String Attributes =
                            TableHeading[1]
                                    .substring(0, TableHeading[1].length() - 1);
                    final String[] attributes = Attributes.split("\\|");
                    stringBuilder.append("type").append(": ").append(attributes[0]);
                    if (attributes.length == 2 && attributes[1]
                            .equals("PK")) {
                        stringBuilder
                                .append(" | ")
                                .append("Key")
                                .append(": ")
                                .append("PRIMARY KEY");
                    }
                    if (attributes.length == 4 && attributes[1]
                            .equals("FK")) {
                        stringBuilder
                                .append(" | ")
                                .append("Key")
                                .append(": ")
                                .append("FOREIGN KEY");
                        stringBuilder
                                .append(" | ")
                                .append("Reference Table")
                                .append(": ")
                                .append(attributes[2]);
                        stringBuilder
                                .append(" | ")
                                .append("Reference Column")
                                .append(": ")
                                .append(attributes[3]);
                    }
                    stringBuilder.append(")").append("\n");
                }
            }
            fileWriter.append(stringBuilder);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}
