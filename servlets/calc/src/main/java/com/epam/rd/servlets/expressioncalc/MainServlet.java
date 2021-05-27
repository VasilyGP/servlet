package com.epam.rd.servlets.expressioncalc;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

//@WebServlet(name = "MainServlet", urlPatterns = "/calc")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String expression = req.getParameter("expression");

        Map<String, String> par = getMapOfArguments(req);
        for(Map.Entry en1 : par.entrySet()){
            for(Map.Entry en2 : par.entrySet()){
                if(en1.getKey().equals(en2.getValue())){
                    par.put(en2.getKey().toString(),en1.getValue().toString());
                }
            }
        }

        CalcAnswer calcAnswer = new CalcAnswer(expression, par);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(calcAnswer.getAnswer());
        printWriter.close();
    }
    private Map<String, String> getMapOfArguments(HttpServletRequest req) {
        return req.getParameterMap().entrySet().stream()
                .filter(x -> !x.getKey().equals("expression"))
                .collect(Collectors.toMap(Map.Entry::getKey, es -> {
                    return es.getValue()[0];
                }));
    }
}