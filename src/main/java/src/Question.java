package main.java.src;

/**
 * Created by luke on 11/6/2016.
 */
public class Question {
    private String question;
    private int id;
    public Question(String q, int id){
        this.question = q;
        this.id = id;
    }
    public Question(String q){
        this.question = q;

    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
