package com.fpak.classification.rally.cpr;
import java.net.URI;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import com.fpak.classification.rally.RallyService;
import com.fpak.classification.rally.cpr.dtos.CPRStagePointsDTO;
import com.fpak.classification.rally.cpr.dtos.CPRDriversDTO;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import technology.tabula.*;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;


@Service
public class CPRService {
    private final RallyService rallyService;

    public CPRService(RallyService rallyService) {
        this.rallyService = rallyService;
    }

    @SuppressWarnings("rawtypes")
    public String readPdf(String competitionName) throws Exception {
        String pdfUrl = rallyService.getCompetition(competitionName);

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
            // Save HTML
            System.out.println(content.toString());
            oe.close();
            return content.toString();
            
        }
    }

    public List<CPRDriversDTO> getClassification(String competitionName) throws Exception {
        String content = this.readPdf(competitionName);
        List<CPRDriversDTO> result = new ArrayList<>();

        Document doc = Jsoup.parse(content);
        Elements rows = doc.select("table tr");
        
        List<Element> driverRows = rows.subList(2, rows.size());

        for (Element row : driverRows) {
            Elements cols = row.select("td");

            if (cols.size() < 4) continue;

            int position = Integer.parseInt(cols.get(0).text().trim());
            int number = Integer.parseInt(cols.get(1).text().trim());
            String name = cols.get(2).text().trim();
            int total = Integer.parseInt(cols.get(3).text());

     

            List<CPRStagePointsDTO> stage = new ArrayList<>();

            // Starting from index 4 (column 5), collect stage data in pairs
            for (int i = 4, stageNumber = 1; i  < cols.size(); i += 4, stageNumber++) {
                String powerStage = cols.get(i+1).text().trim();
                String generalFinal = cols.get(i +3).text().trim();

                stage.add(new CPRStagePointsDTO(String.valueOf(stageNumber), powerStage, generalFinal));
            }

            result.add(new CPRDriversDTO(position, number, name, total, stage));
        }
        return result;
    }
    

    public List<CPRDriversDTO> getDriversClassification() throws Exception {
        return this.getClassification("C P Ralis 2025 Condutores - Absoluto");
    }

    public List<CPRDriversDTO> getCoDriversClassification() throws Exception {
        return this.getClassification("C P Ralis 2025 Navegadores - Absoluto");
    }

}
