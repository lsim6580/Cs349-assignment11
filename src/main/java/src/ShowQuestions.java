package main.java.src;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by luke on 11/6/2016.
 */
@WebServlet(name = "ShowQuestions")
public class ShowQuestions extends HttpServlet {
    SQLLayer sqlLayer;
    public ShowQuestions(){
        super();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String theQuestion = request.getParameter("theQuestion");
        System.out.println(theQuestion);
        sqlLayer.addQuestion(new Question(theQuestion));

        response.sendRedirect("ShowQuestions");



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            sqlLayer = new SQLLayer();
        }catch (Exception e){
            e.printStackTrace();
        }
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Questions</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Questions</h1>");

        List<Question> questions = sqlLayer.getQuestions();
        int count = 0;
        for (Question q : questions) {
            count++;
            out.print("<p>" + count + ". <a href=\"ShowAnswers?id="+q.getId()+"\">" + q.getQuestion() + "</a></p>");
        }
        out.println("<form method=\"POST\" action=\"ShowQuestions\">");
        out.println("<p><input type=\"text\" name=\"theQuestion\" size=\"50\"> <input type=\"submit\" value=\"Add Question\"></p>");
        out.println("</form>");
        out.println("<p><a href =\"GetQuestionsAPI\">JSON</a><p>");
        out.println("</body></html>");
    }
}
