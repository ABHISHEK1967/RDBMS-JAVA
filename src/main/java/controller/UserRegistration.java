package controller;

import java.io.FileWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserRegistration {
    static User user;
    final MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

    public UserRegistration() throws NoSuchAlgorithmException {
    }


    private String getNewUser(final long userId,
                              final User user)
            throws NoSuchAlgorithmException {
        return userId + " " +
                user.getUsername() + " " +
                String.format("%064x", new BigInteger(1,
                        messageDigest.digest(user.getPassword().getBytes(StandardCharsets.UTF_8)))) + " " +
                user.getQ1ans() + " " +
                user.getQ2ans() + " " +
                user.getQ3ans() + " " +
                user.getQ4ans() + "\n";
    }
    public boolean register(final User user)
            throws Exception {
        try (final FileWriter fileWriter =
                     new FileWriter("./src/main/java/Model/users/users.txt"
                , true)) {
            final long userId = Files.lines(
                    Paths.get("./src/main/java/Model/users/users.txt")).count() + 1;
            final String newUser = getNewUser(userId, user);
            fileWriter.append(newUser);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Something went wrong !!!");
        }
    }
}
