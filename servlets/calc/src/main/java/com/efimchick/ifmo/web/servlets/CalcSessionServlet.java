package com.efimchick.ifmo.web.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CalcSessionServlet", value = "/calc")
public class CalcSessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet:");
        System.err.println(request.getParameterMap());
        System.err.println("==============================");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("1");
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost:");
        System.err.println(request.getParameterMap());
        System.err.println("==============================");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("1");
        printWriter.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPut:");
        System.err.println(req.getParameterMap());
        System.err.println("==============================");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("1");
        printWriter.close();
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doDelete:");
        System.err.println(req.getParameterMap());
        System.err.println("==============================");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("1");
        printWriter.close();
        super.doDelete(req, resp);
    }
}
