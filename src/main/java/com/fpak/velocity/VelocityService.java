package com.fpak.velocity;

import com.fpak.rally.RallyService;
import com.fpak.rally.dtos.RallyClassificationDTO;
import com.fpak.rally.dtos.StagePointsDTO;
import com.fpak.velocity.dtos.VelocityClassificationDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class VelocityService {

    @Value("${velocity.url}")
    private String rallyUrl;

    private static final Logger LOGGER = Logger.getLogger(VelocityService.class.getName());

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
                LOGGER.log(Level.SEVERE, "An error occurred", e);
            }
            return content.toString();

        }
    }

    public List<VelocityClassificationDTO> getClassification(String competitionName, String type) throws Exception {
        String content = this.readPdf(competitionName);
        List<VelocityClassificationDTO> result = new ArrayList<>();

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



            List<StagePointsDTO> stage = new ArrayList<>();
            if(type.equals("abs")){
                for (int i = 4, stageNumber = 1; i  < cols.size(); i += 4, stageNumber++) {
                    String powerStage = cols.get(i+1).text().trim();
                    String generalFinal = cols.get(i +3).text().trim();

                    stage.add(new StagePointsDTO( powerStage, generalFinal));
                }
            }else {
                for (int i = 4, stageNumber = 1; i  < cols.size(); i += 2 , stageNumber++) {
                    String generalFinal = cols.get(i +1).text().trim();

                    stage.add(new StagePointsDTO("", generalFinal));
                }
            }


            result.add(new VelocityClassificationDTO(position, number, name, total, null,null));
        }
        return result;
    }

}
