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
@WebServlet(name = "ShowAnswers")
public class ShowAnswers extends HttpServlet {
    SQLLayer sqlLayer;
    private int questionID;
    public ShowAnswers() throws Exception{
        super();
        sqlLayer = new SQLLayer();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String theAnswer = request.getParameter("theAnswer");
        System.out.println(theAnswer);
        Answer answer = new Answer();
        answer.setQuestionId(questionID);
        answer.setAnswer(theAnswer);
        sqlLayer.addAnswer(answer);

        response.sendRedirect("ShowAnswers?id="+ questionID);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            questionID = Integer.parseInt(request.getParameter("id"));
            List<Answer> answerList = sqlLayer.getAnswer(questionID);
            out.println("<html>");
            out.println("<body>");

            out.println("<p>Answers for question " + questionID + "</p>");
            for(Answer a: answerList){
                out.print("<p>" + a.getAnswer()+"<p>");
            }
        out.println("<form method=\"POST\" action=\"ShowAnswers\">");
        out.println("<p><input type=\"text\" name=\"theAnswer\" size=\"50\"> <input type=\"submit\" value=\"Add Answer\"></p>");
        out.println("</form>");
        out.println("</body></html>");
        }
    }
