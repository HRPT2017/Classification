package com.fpak.rally;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fpak.rally.dtos.CPRAbsDriversDTO;
import com.fpak.rally.dtos.CPRAbsStagePointsDTO;
import com.fpak.rally.dtos.CPRDriversDTO;
import com.fpak.rally.dtos.CPRStagePointsDTO;

import technology.tabula.ObjectExtractor;
import technology.tabula.RectangularTextContainer;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import technology.tabula.*;

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

    @SuppressWarnings("rawtypes")
    public String readPdf(String competitionName) throws Exception {
        String pdfUrl = this.getCompetition(competitionName);

        try (InputStream input = URI.create(pdfUrl).toURL().openStream();
                PDDocument document = PDDocument.load(input)) {
            ObjectExtractor oe = new ObjectExtractor(document);
            Page page = oe.extract(1);
            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
            List<Table> tables = sea.extract(page);

            StringBuilder html = new StringBuilder();
            html.append("<html><body><table border='1'>");

            for (Table table : tables) {
                for (List<RectangularTextContainer> row : table.getRows()) {
                    html.append("<tr>");
                    for (RectangularTextContainer cell : row) {
                        html.append("<td>").append(cell.getText()).append("</td>");
                    }
                    html.append("</tr>");
                }
            }

            html.append("</table></body></html>");
            StringWriter content = new StringWriter();
            content.write(html.toString());
            oe.close();
            try(BufferedWriter writer = new BufferedWriter(new FileWriter("output2.html"))) {
                writer.write(content.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toString();
            
        }
    }

    public List<CPRAbsDriversDTO> getAbsClassification(String competitionName) throws Exception {
        String content = this.readPdf(competitionName);
        List<CPRAbsDriversDTO> result = new ArrayList<>();

        Document doc = Jsoup.parse(content);
        Elements rows = doc.select("table tr");
        
        List<Element> driverRows = rows.subList(2, rows.size());

        for (Element row : driverRows) {
            Elements cols = row.select("td");

            if (cols.size() < 4) continue;

            int position = Integer.parseInt(cols.get(0).text().trim());
            int number = Integer.parseInt(cols.get(1).text().trim());
            String name = cols.get(2).text().trim();
            int total = Integer.parseInt(cols.get(3).text().replaceAll("\\D","").trim());

     

            List<CPRAbsStagePointsDTO> stage = new ArrayList<>();

            // Starting from index 4 (column 5), collect stage data in pairs
            for (int i = 4, stageNumber = 1; i  < cols.size(); i += 4, stageNumber++) {
                String powerStage = cols.get(i+1).text().trim();
                String generalFinal = cols.get(i +3).text().trim();

                stage.add(new CPRAbsStagePointsDTO(String.valueOf(stageNumber), powerStage, generalFinal));
            }

            result.add(new CPRAbsDriversDTO(position, number, name, total, stage));
        }
        return result;
    }
    
    public List<CPRDriversDTO>  getClassification(String competitionName) throws Exception{
        List<CPRDriversDTO> result = new ArrayList<>();
        String content = this.readPdf(competitionName);
        Document doc = Jsoup.parse(content);
        Elements rows = doc.select("table tr");
        
        List<Element> driverRows = rows.subList(2, rows.size());

        for (Element row : driverRows) {
            Elements cols = row.select("td");

            if (cols.size() < 4) continue;

            int position = Integer.parseInt(cols.get(0).text().trim());
            int number = Integer.parseInt(cols.get(1).text().trim());
            String name = cols.get(2).text().trim();
            int total = Integer.parseInt(cols.get(3).text().replaceAll("\\D","").trim());

            List<CPRStagePointsDTO> stage = new ArrayList<>();
            for (int i = 4, stageNumber = 1; i  < cols.size(); i += 2 , stageNumber++) {
                String generalFinal = cols.get(i +1).text().trim();

                stage.add(new CPRStagePointsDTO(String.valueOf(stageNumber), generalFinal));
            }

            result.add(new CPRDriversDTO(position, number, name, total, stage));
        }
        
        return result;
    }
    
    }
