package upp.demo.elastic.handler;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class PdfIndexHandler {

    public String getPdfText(File file) throws IOException {
        PDFParser parser = new PDFParser(new RandomAccessFile(file,"r"));
        parser.parse();
        PDFTextStripper stripper = new PDFTextStripper();
        PDDocument document = parser.getPDDocument();
        String text = stripper.getText(document);
        document.close();
        return text;
    }
}
