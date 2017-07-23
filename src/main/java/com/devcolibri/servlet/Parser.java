package com.devcolibri.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

  public static void main(String[] args) throws IOException {

  }
  public static HashSet<String> getTopicLinksList() throws IOException {
    HashSet<String> topicsLinkList = new HashSet<>();

    String thread = "https://2ch.hk/b/";
    Document document = Jsoup.connect(thread).get();
    Elements links = document.select("a[href]");

    for (Element link : links) {
      String linkAttr = link.attr("href");
      if (linkAttr.contains("b/res") && linkAttr.endsWith("html")) {
        topicsLinkList.add("https://2ch.hk" + linkAttr);
      }
    }
    return topicsLinkList;
  }

  public static LinkedList<String> getWebmList() throws IOException {
    LinkedList<String> webmList = new LinkedList<>();
    Iterator<String> topicLinksIterator = getTopicLinksList().iterator();
    // i've insert <50 cuz wanted to generate list once, and then iterate it on servlet-side, but it works different,
    // it runs every time when doGet method execute
    while (webmList.size() < 50 && topicLinksIterator.hasNext()) {
      String thread = topicLinksIterator.next();
      // thread generates wrong link sometimes like "https://2ch.hkhttps://2ch.hk/b/res/157073305.html"
      // TODO fix it in right manner
      if (thread.contains("hkht") && topicLinksIterator.hasNext()) {
        thread = topicLinksIterator.next();
      }
      // What if iterator don't have nextElement? Still no errors here, mb it works.

      Document document = Jsoup.connect(thread).get();
      Elements figCaption = document.getElementsByAttributeValue("class", "file-attr");

      for (Element caption : figCaption) {
        Element element = caption.child(0);
        String url = element.attr("href");
        String name = element.text();
        if (url.endsWith("webm")) {
          webmList.add(url);
        }
      }
    }
    return webmList;
  }

}