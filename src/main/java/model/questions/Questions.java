package model.questions;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class Questions {

    private final List<String> securityQuestions = new ArrayList<>();
    private static Questions instance;

    private Questions(){
        securityQuestions.clear();
        securityQuestions.add("What is your special day?");
        securityQuestions.add("What is your birth place?");
        securityQuestions.add("What is your first company name?");
        securityQuestions.add("What is your favorite sport?");
    }
    public static Questions getInstance() {
        if (instance == null) {
            instance = new Questions();
        }
        return instance;
    }
    public List<String> getSecurityQuestions() {
        return securityQuestions;
    }

    public String getRandomSecurityQuestion(){
        final int randomQuestionIndex = new Random().nextInt(4);
        return securityQuestions.get(randomQuestionIndex);
    }

    public int getRandomSecurityQuestionIndex(String question){
        return securityQuestions.indexOf(question);
    }
}
