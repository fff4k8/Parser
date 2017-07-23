package com.devcolibri.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@WebServlet("/")
public class MainServlet extends HttpServlet {

  public MainServlet() throws IOException {
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {

    LinkedList <String> list =  Parser.getWebmList();
    int random = new Random().nextInt(list.size());

    // TODO: instead of using random, make a list.next() method using iterator or pull/poll for hashset.

    String webmUrl = "https://2ch.hk" + list.get(random);

    req.setAttribute("name", webmUrl);
    req.getRequestDispatcher("mypage.jsp").forward(req, resp);

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    // TODO: should make a post method, that generates incrementing variable for list.get() method, so we also need redirect back to get then
    super.doPost(req, resp);
  }


}