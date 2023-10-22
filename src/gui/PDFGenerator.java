package gui;

import entities.Competition;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFGenerator {

    public static void printCompetitionToPDF(Competition competition, String outputDirectory, String fileName) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();

        try {
            document.addPage(page);

            String outputPath = outputDirectory + System.getProperty("file.separator") + fileName + ".pdf";
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Competition Name: " + competition.getName());
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("Start Date: " + competition.getStartDate());
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("End Date: " + competition.getEndDate());
            String loc = competition.getLocation().replace("\n", "").replace("\r", "");
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("Location : " + loc);
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("Sport Type: " + competition.getSportType());
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("Competition Category: " + competition.getCompetitionCategory());
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("Entry Fee: " + competition.getEntryFee() + "DT");
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("Max Participants: " + competition.getMaxParticipants());
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("Status: " + competition.getStatus());
            contentStream.newLineAtOffset(0, -50);
            contentStream.showText("Prizes: " + competition.getPrizes());
            contentStream.newLineAtOffset(0, -50);
            String text = competition.getDescription().replace("\n", "").replace("\r", "");
            contentStream.showText("Description: " + text);
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
