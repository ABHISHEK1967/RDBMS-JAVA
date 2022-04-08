package controller;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



public class ProcessQueryTest {
    private static ProcessQuery processQuery;
    @BeforeAll
    public static void setup(){
        processQuery  = new ProcessQuery();
    }

    @Test
    public void CreateDatabaseQueryTest() throws Exception{
        String Query = "CREATE DATABASE students;";
        assertEquals("CREATED DB SUCCESSFULLY !!!",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidCreateDatabaseQueryTest() {
       String Query = "CREATE DATABASE students";
       assertThrows(Exception.class,()->{
           processQuery.processorQuery(Query);
       });
    }
    @Test
    public void dropDatabaseQueryTest() throws Exception{
        String Query = "drop DATABASE TEST;";
        assertEquals("TEST HAS BEEN DROPPED SUCCESSFULLY !!!",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidDropDatabaseQueryTest() {
        String Query = "drop DATABASE TEST";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }
    @Test
    public void useDatabaseQueryTest() throws Exception{
        String Query = "use DATABASE TEST;";
        assertEquals("TEST HAS BEEN SELECTED SUCCESSFULLY !!!",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidUseDatabaseQueryTest() {
        String Query = "use DATABASE test";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }

    @Test
    public void CreateTableQueryTest() throws Exception {
        String Query = "CREATE TABLE students (id INT PRIMARY KEY, name TEXT, email TEXT);";
        assertEquals("TABLE HAS BEEN CREATED SUCCESSFULLY !!!",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidCreateTableQueryTest() {
        String Query = "CREATE TABLE students (id INT PRIMARY KEY, name TEXT, email TEXT)";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }

    @Test
    public void insertIntoTableQueryTest() throws Exception {
        String Query = "INSERT INTO students (id, name, email) VALUES (1, \"abhi\", \"abc@gmail.com\");";
        assertEquals("RECORD HAS BEEN INSERTED SUCCESSFULLY !!!",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidInsertIntoTableQueryTest() {
        String Query = "INSERT INTO courses (courseId, courseName, id) VALUES (1, \"physics\", 1)";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }

    @Test
    public void selectQueryTest() throws Exception {
        String Query = "SELECT id, name FROM students WHERE id = 1;";
        assertEquals("QUERY HAS BEEN SELECTED SUCCESSFULLY!!!",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidSelectQueryTest() throws Exception {
        String Query = "SELECT id, name FROM students WHERE id = 1";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }

    @Test
    public void updateQueryTest() throws Exception {
        String Query = "UPDATE students SET name = arjun WHERE id = 1;";
        assertEquals("UPDATE QUERY HAS BEEN EXECUTED SUCCESSFULLY!!!",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidUpdateQueryTest() throws Exception {
        String Query = "UPDATE students SET name = arjun WHERE id = 1";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }

    @Test
    public void deleteQueryTest() throws Exception {
        String Query = "DELETE FROM students WHERE name = arjun;";
        assertEquals("DELETE QUERY HAS BEEN EXECUTED SUCCESSFULLY!!!",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidDeleteQueryTest() throws Exception {
        String Query = "DELETE FROM student WHERE name = arjun";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }


    @Test
    public void dropTableQueryTest() throws Exception {
        String Query = "TRUNCATE TABLE students;";
        assertEquals("TABLE HAS BEEN DROPPED SUCCESSFULLY!!!",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidDropTableQueryTest() throws Exception {
        String Query = "TRUNCATE TABLE student";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }
    @Test
    public void startTransactionQueryTest() throws Exception {
        String Query = "start transaction;";
        assertEquals("Started transaction",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidStartTransactionQueryTest() throws Exception {
        String Query = "start transaction";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }

    @Test
    public void commitQueryTest() throws Exception {
        String Query = "Commit;";
        assertEquals("Committed transaction",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidCommitQueryTest() throws Exception {
        String Query = "Commit";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }

    @Test
    public void rollbackQueryTest() throws Exception {
        String Query = "Rollback;";
        assertEquals("Rollback successful",processQuery.processorQuery(Query));
    }

    @Test
    public void InvalidRollbackQueryTest() throws Exception {
        String Query = "Rollback";
        assertThrows(Exception.class,()->{
            processQuery.processorQuery(Query);
        });
    }

    @Test
    public void concurrentTransactionTest() throws Exception {
//        try(FileWriter fw = new FileWriter("logger_afterLock.txt",true)){
//            Thread thread1 = new Thread();
//            Thread thread2 = new Thread();
//            thread1.setPriority(Thread.MAX_PRIORITY);
//            thread2.setPriority(Thread.MAX_PRIORITY);
//
//            thread1.start();
//            thread2.start();
//
//            thread1.join();
//            thread2.join();
//        } catch(Exception e){
//            System.out.println(e.getMessage());
//        }

        
    }

//    @Override
//    public void run() throws RuntimeException {
//        String Query = "INSERT INTO students (id, name, email) VALUES (1, \"abhi\", \"abc@gmail.com\");";
//        try {
//            processQuery.processorQuery(Query);
//
//        } catch (GenericException e) {
//            Thread t = Thread.currentThread();
//            t.getUncaughtExceptionHandler().uncaughtException(t, e);
//        }
//    }

}
