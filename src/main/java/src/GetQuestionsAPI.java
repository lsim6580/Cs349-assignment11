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
@WebServlet(name = "GetQuestionsAPI")
public class GetQuestionsAPI extends HttpServlet {
    SQLLayer sqlLayer;
    public GetQuestionsAPI()throws Exception{
        super();
        sqlLayer = new SQLLayer();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String json = "{\"questions\": [,";
        for(Question q: sqlLayer.getQuestions()){
            json += "{\"question\":"+"'"+ q.getQuestion()+ "'"+"},";
        }
        json += "]}";
        out.println(json);
    }
}
