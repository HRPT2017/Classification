package com.fpak.classification.rally.cpr;
import java.net.URI;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import com.fpak.classification.rally.RallyService;
import com.fpak.classification.rally.cpr.dtos.CPRDriversDTO;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Service
public class CPRService {
    private final RallyService rallyService;

    public CPRService(RallyService rallyService) {
        this.rallyService = rallyService;
    }

    public List<CPRDriversDTO> getClassification(String competitionName) throws Exception {
        String pdfUrl = rallyService.getCompetition(competitionName);
        List<CPRDriversDTO> result = new ArrayList<>();
        List<String> test = new ArrayList<>();

        try (InputStream input = URI.create(pdfUrl).toURL().openStream();
             PDDocument document = PDDocument.load(input)) {

            String text = new PDFTextStripper().getText(document);

            Pattern pattern = Pattern.compile("^(\\d{1,2})\\s+(\\d{1,2})\\s+([A-ZÁÉÍÓÚÂÊÔÃÕÇ ]+)\\s+(\\d{1,3})\\s+(\\d{1,3})\\s+(\\d{1,3})", Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(text);
            System.out.println(pattern);

            while (matcher.find()) {
                int position = Integer.parseInt(matcher.group(1));
                int number = Integer.parseInt(matcher.group(2));
                String name = matcher.group(3).trim();
                int total = Integer.parseInt(matcher.group(4));
                int ps1 = Integer.parseInt(matcher.group(5));
                int ps2 = Integer.parseInt(matcher.group(6));

                result.add(new CPRDriversDTO(position, number, name, total));
                test.add( position + ", " + number + "," + name + ", "+ total + ", "+ ps1 + ", " + ps2 + "\n");
            }
            
            System.out.println(text);
            return result;
        }

        
    }
   
    public List<CPRDriversDTO> getDriversClassification() throws Exception {
        return this.getClassification("C P Ralis 2025 Condutores - Absoluto");
    }

    public List<CPRDriversDTO> getCoDriversClassification() throws Exception {
        return this.getClassification("C P Ralis 2025 Navegadores - Absoluto");
    }
}
