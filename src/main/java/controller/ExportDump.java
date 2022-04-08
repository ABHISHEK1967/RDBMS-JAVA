package controller;

import view.printer.Printer;

import java.io.*;

public class ExportDump {
    private File databaseToExport;
    private String databaseDumpFolderName;
    private File databaseDumpFolder;
    private File parentFolder;
    private Printer printer;
    private String[] columnTypes;

    public ExportDump(File database, String destination, Printer print){
        this.databaseToExport = database;
        this.databaseDumpFolderName = destination + database.getName() + "Dump";
        this.databaseDumpFolder = new File(databaseDumpFolderName);
        this.parentFolder = new File(destination);
        this.printer = print;
    }

    public boolean downloadSchema() throws IOException {
        if(parentFolder.exists()){
            if(!databaseDumpFolder.exists()){
                databaseDumpFolder.mkdir();
            }
            String[] schemas = databaseToExport.list();
            for (String schema : schemas){
                String schemaName = schema.replaceAll("\\.txt", "");
                File sourceSchemaFile = new File(databaseToExport, schema);
                File destinationSchemaFile = new File(databaseDumpFolder, schemaName+".sql");
                InputStream inputStream = new FileInputStream(sourceSchemaFile);
                BufferedReader inputBufferReader = new BufferedReader(new InputStreamReader(inputStream));
                OutputStream outputStream = new FileOutputStream(destinationSchemaFile);
                BufferedWriter outputBufferWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

                String line;
                String primaryKey = "PRIMARY KEY (`";
                String insertQuery = "--\n-- Dumping data for table `" + schemaName + "`\n" + "--\n\nINSERT INTO `" + schemaName +"` VALUES (";
                String createTableQuery = "CREATE TABLE `" + schemaName + "` ";
                String dropTableQuery = "--\n-- Table structure for table `" + schemaName + "`\n--\n\n";
                dropTableQuery += "DROP TABLE IF EXISTS `" + schemaName + "`;\n";
                String foreignKey = "";
                boolean readHeader = true;
                boolean isDataPresent = false;

                outputBufferWriter.write(dropTableQuery);
                outputBufferWriter.newLine();

                while ((line = inputBufferReader.readLine()) != null){
                    if(line.indexOf('$') != -1){
                        line = line.replaceAll("\\$", "");
                        String[] lineValues = line.split("\\|\\|");
                        if(readHeader){
                            createTableQuery = getCreateQuery(lineValues, createTableQuery, primaryKey, foreignKey);
                            readHeader = false;
                        } else {
                            isDataPresent = true;
                            insertQuery = getInsertQuery(lineValues, insertQuery);
                        }
                    }
                }

                outputBufferWriter.write(createTableQuery);
                outputBufferWriter.newLine();

                if(isDataPresent){
                    insertQuery = insertQuery.substring(0, insertQuery.length()-3) + ";";
                    outputBufferWriter.write(insertQuery);
                }

                inputBufferReader.close();
                outputBufferWriter.close();
            }
            return true;
        } else{
            printer.printString("Please enter correct path of the folder.");
            return false;
        }
    }

    public String getInsertQuery(String[] values, String insertQuery){
        for(int i = 0; i < values.length; i++){
            if(columnTypes[i].equals("TEXT")){
                insertQuery += "'" + values[i] + "'";
            } else{
                insertQuery += values[i];
            }
            if(i < values.length-1){
                insertQuery += ", ";
            }
        }
        insertQuery += "), (";
        return insertQuery;
    }

    public String getCreateQuery(String[] columns, String createTableQuery, String primaryKey, String foreignKey){
        createTableQuery += "(";
        boolean isPrimaryKeyPresent = false;
        boolean isForeignKeyPresent = false;
        columnTypes = new String[columns.length];

        for(int i = 0; i < columns.length; i++){
            int columnTypeStartIndex = columns[i].indexOf('(');
            int columnTypeEndIndex = columns[i].indexOf(')');
            String columnName = columns[i].substring(0, columnTypeStartIndex);
            String columnType = columns[i].substring(columnTypeStartIndex + 1, columnTypeEndIndex);

            if(columns[i].indexOf("PK") != -1){
                primaryKey += columnName + "`)";
                columnType = columnType.replaceAll("\\|PK", "");
                isPrimaryKeyPresent = true;
            } else if(columns[i].indexOf("FK") != -1){
                isForeignKeyPresent = true;
                String[] fKeyConstraints = columnType.split("\\|");
                columnType = fKeyConstraints[0];
                String referencedTable = fKeyConstraints[2];
                String referencedColumn = fKeyConstraints[3];
                String constraintName = referencedColumn+"foreignKey";
                foreignKey = "CONSTRAINT `" + constraintName + "` FOREIGN KEY (`" + columnName + "`) REFERENCES `" +
                        referencedTable + "` (`" + referencedColumn +"`)";
            }
            columnTypes[i] = columnType;
            createTableQuery += "`" +  columnName + "` " +  columnType;
            if(i < columns.length-1){
                createTableQuery+=", \n";
            }
        }
        if(isPrimaryKeyPresent && isForeignKeyPresent){
            createTableQuery+= ",\n" + primaryKey + ",\n" + foreignKey + ");\n";
        } else if(isPrimaryKeyPresent){
            createTableQuery+= ",\n" + primaryKey + ");\n";
        } else{
            createTableQuery+= ");\n";
        }
        return createTableQuery;
    }
}
