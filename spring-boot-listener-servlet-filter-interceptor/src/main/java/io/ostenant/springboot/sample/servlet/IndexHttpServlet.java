package io.ostenant.springboot.sample.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;

@WebServlet(name = "IndexHttpServlet",
        displayName = "indexHttpServlet",
        urlPatterns = {"/index/IndexHttpServlet"},
        initParams = {
                @WebInitParam(name = "createdBy", value = "Icarus"),
                @WebInitParam(name = "createdOn", value = "2018-06-20")
        }
)
public class IndexHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.getWriter().println(format("Created by %s", getInitParameter("createdBy")));
        resp.getWriter().println(format("Created on %s", getInitParameter("createdOn")));
        resp.getWriter().println(format("Servlet context param: %s",
                req.getServletContext().getAttribute("content")));
    }
}
