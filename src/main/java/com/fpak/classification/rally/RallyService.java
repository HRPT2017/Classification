package com.fpak.classification.rally;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RallyService {
    @Value("${rally.url}")
    private String rallyUrl;
        public String getCompetition(String urlName) throws IOException {

        Document doc = Jsoup.connect(rallyUrl).get();

        // Find all PDF links inside the correct HTML scope
        Elements links = doc.select("div.node__content a[href$=.pdf]");
        String pdfUrl = null;
    
        for (Element link : links) {
            String text = link.text().trim();
            if (text.equalsIgnoreCase(urlName)) {
               pdfUrl = link.absUrl("href");
            }
        }
        return pdfUrl;}
}
