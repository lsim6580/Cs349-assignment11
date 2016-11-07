package main.java.src;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 11/6/2016.
 */

    /**
     * Created by luke on 11/5/2016.
     */
    public class SQLLayer {
        private Connection con;
        private Statement stmt;

        public SQLLayer() throws Exception {
            String url = "jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/lsg72";
            String userID = "lsg72";
            String password = "bMNLwflRlmmHhi58CaVD";
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (java.lang.ClassNotFoundException e) {
                System.out.println(e);
                System.exit(0);
            }
            con = DriverManager.getConnection(url,userID,password);
            stmt = con.createStatement();
        }
        public List<Question> getQuestions(){
            String query = "Select * from questions";

            List<Question> result = new ArrayList<Question>();
            try {

                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    result.add(new Question(rs.getString("question"), rs.getInt("question_id")));
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }
        public void addQuestion(Question q){
            String query = "Insert into Questions(question) values(?)";
            PreparedStatement pstmt = null;

            try {
                pstmt = con.prepareStatement(query);
                pstmt.setString(1,q.getQuestion());

                pstmt.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public void addAnswer(Answer a){
            String query = "Insert into Answers(answer,question_id_fk) values(?,?)";
            PreparedStatement pstmt = null;

            try {
                pstmt = con.prepareStatement(query);
                pstmt.setString(1,a.getAnswer());
                pstmt.setInt(2,a.getQuestionId());

                pstmt.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        public List<Answer> getAnswer(int id){
            String query = "Select * from Answers Where question_id_fk = "+ id;
            List<Answer> answerList = new ArrayList<>();
            Answer answer;
            try{
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()){
                    answer = new Answer();
                    answer.setId(rs.getInt("answer_id"));
                    answer.setAnswer(rs.getString("answer"));
                    answer.setQuestionId(rs.getInt("question_id_fk"));
                    answerList.add(answer);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return answerList;
        }
    }


