package com.example.sirh_backend.models.utils;

import com.example.sirh_backend.models.entities.Evaluation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EvaluationReportGenerator {

    private static EvaluationReportGenerator instance;

    private EvaluationReportGenerator() {}

    public static synchronized EvaluationReportGenerator getInstance() {
        if (instance == null) {
            instance = new EvaluationReportGenerator();
        }
        return instance;
    }

    public byte[] generatePdfReport(Evaluation evaluation) throws IOException {
        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.OVERWRITE, true, true)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 16);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(50, 750);

                contentStream.showText("Evaluation Report");
                contentStream.newLine();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.showText("Id: " + evaluation.getId());
                contentStream.newLine();
                contentStream.showText("Year: " + evaluation.getYear());
                contentStream.newLine();
                contentStream.showText("Description: " + evaluation.getDescription());
                contentStream.newLine();
                contentStream.showText("Employee: " + evaluation.getEmployee().getFirstName() + " " + evaluation.getEmployee().getLastName() + " (id: " + evaluation.getEmployee().getId() + ", position: " + evaluation.getEmployee().getPosition().getName() + ")");
                contentStream.newLine();
                contentStream.showText("Manager: " + evaluation.getEmployee().getTeam().getManager().getFirstName() + " " + evaluation.getEmployee().getTeam().getManager().getLastName() + " (id: " + evaluation.getEmployee().getTeam().getManager().getId() + ", position: " + evaluation.getEmployee().getTeam().getManager().getPosition().getName() + ")");
                contentStream.newLine();
                contentStream.showText("Status: " + evaluation.getStatus());
                contentStream.newLine();

                contentStream.newLine();
                contentStream.showText("Objectives:");
                contentStream.newLine();
                evaluation.getObjectives().forEach(objective -> {
                    try {
                        contentStream.showText("- " + objective.getDescription() + " (" + (objective.isAchieved() ? "achieved" : "not achieved") + ")");
                        contentStream.newLine();
                    } catch (IOException e) {
                        System.err.println("Error while writing objective text to the PDF content stream: " + e.getMessage());
                    }
                });

                contentStream.newLine();
                contentStream.showText("Feedbacks:");
                contentStream.newLine();
                evaluation.getFeedbacks().forEach(feedback -> {
                    try {
                        contentStream.showText("- " + feedback.getDescription() + " (reviewer: " + feedback.getReviewer().getFirstName() + " " + feedback.getReviewer().getLastName() + " (id: " + feedback.getReviewer().getId() + ", position: " + feedback.getReviewer().getPosition().getName() + "))");
                        contentStream.newLine();
                    } catch (IOException e) {
                        System.err.println("Error while writing feedback text to the PDF content stream: " + e.getMessage());
                    }
                });

                contentStream.endText();
            }

            document.save(outputStream);
            return outputStream.toByteArray();
        }
    }
}
