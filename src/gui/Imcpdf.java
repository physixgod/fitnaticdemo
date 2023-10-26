package gui;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class Imcpdf {
    public static void printInformationToPDF(String poidsActuelle, String imc, String category, String poidsIdeale, String outputDirectory, String fileName) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        try {
            document.addPage(page);

            String outputPath = outputDirectory + System.getProperty("file.separator") + fileName + ".pdf";
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Poids Actuelle: " + poidsActuelle);
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("IMC: " + imc);
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("Category: " + category);
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("Poids Ideale: " + poidsIdeale);
            contentStream.endText();
            contentStream.close();

            document.save(outputPath);
            document.close();

            System.out.println("PDF generated successfully at: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}