package com.fpak.classification.rally.cpr;
import java.net.URI;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import com.fpak.classification.rally.cpr.dtos.CPRDriversDTO;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CPRService {
    public List<CPRDriversDTO> getDriversClassification(String urlString) throws Exception {
        List<CPRDriversDTO> result = new ArrayList<>();

        try (InputStream input = URI.create(urlString).toURL().openStream();
             PDDocument document = PDDocument.load(input)) {

            String text = new PDFTextStripper().getText(document);

            Pattern pattern = Pattern.compile("^(\\d{1,2})\\s+(\\d{1,2})\\s+([A-ZÁÉÍÓÚÂÊÔÃÕÇ ]+)\\s+(\\d{1,3})", Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(text);

            while (matcher.find()) {
                int position = Integer.parseInt(matcher.group(1));
                int number = Integer.parseInt(matcher.group(2));
                String name = matcher.group(3).trim();
                int total = Integer.parseInt(matcher.group(4));

                result.add(new CPRDriversDTO(position, number, name, total));
            }
            return result;
        }

        
    }
}
