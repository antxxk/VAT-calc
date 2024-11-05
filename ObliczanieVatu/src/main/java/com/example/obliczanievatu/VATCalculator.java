package com.example.obliczanievatu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VATCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("VAT Calculator");

        // Tworzenie przycisków wyboru dla metody obliczeń
        ToggleGroup methodGroup = new ToggleGroup();

        Label methodLabel = new Label("Metody Obliczeń");  
        RadioButton rbNettoToBrutto = new RadioButton("Od netto do brutto");
        rbNettoToBrutto.setToggleGroup(methodGroup);
        rbNettoToBrutto.setSelected(true); // domyślnie zaznaczony
        RadioButton rbBruttoToNetto = new RadioButton("Od brutto do netto");
        rbBruttoToNetto.setToggleGroup(methodGroup);
        RadioButton rbAdjustToVat = new RadioButton("Dopasuj do kwoty VAT");
        rbAdjustToVat.setToggleGroup(methodGroup);

        VBox methodBox = new VBox(10);
        methodBox.getChildren().addAll(rbNettoToBrutto, rbBruttoToNetto, rbAdjustToVat);
        methodBox.setPadding(new Insets(10));
        methodBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

        // Pole do wprowadzania wartości bazowej
        Label baseValueLabel = new Label("Wartość bazowa:");
        TextField baseValueField = new TextField();
        baseValueField.setText("2000.00"); // domyślna wartość

        // Stawka VAT
        Label vatRateLabel = new Label("Stawka VAT:");
        ComboBox<String> vatRateCombo = new ComboBox<>();
        vatRateCombo.getItems().addAll("23%", "8%", "5%");
        vatRateCombo.setValue("23%"); // domyślna wartość

        // Sekcja "Dane" (dodajemy border)
        Label dataLabel = new Label("Dane");  // Etykieta sekcji "Dane"
        GridPane dataGrid = new GridPane();
        dataGrid.setHgap(10);
        dataGrid.setVgap(10);
        dataGrid.add(baseValueLabel, 0, 0);
        dataGrid.add(baseValueField, 1, 0);
        dataGrid.add(vatRateLabel, 0, 1);
        dataGrid.add(vatRateCombo, 1, 1);
        dataGrid.setPadding(new Insets(10));
        dataGrid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

        // Przyciski
        Button calculateButton = new Button("OBLICZ");
        Button closeButton = new Button("Zamknij");

        // Sekcja: przyciski (dodajemy border)
        HBox buttonBox = new HBox(10, calculateButton, closeButton);
        buttonBox.setPadding(new Insets(10));

        // Wyniki
        Label resultLabel = new Label("Wyniki");  // Etykieta sekcji "Wyniki"
        Label nettoLabel = new Label("Netto:");
        Label nettoValue = new Label();
        Label vatLabel = new Label("VAT:");
        Label vatValue = new Label();
        Label bruttoLabel = new Label("Brutto:");
        Label bruttoValue = new Label();

        GridPane resultGrid = new GridPane();
        resultGrid.setHgap(10);
        resultGrid.setVgap(10);
        resultGrid.add(nettoLabel, 0, 0);
        resultGrid.add(nettoValue, 1, 0);
        resultGrid.add(vatLabel, 0, 1);
        resultGrid.add(vatValue, 1, 1);
        resultGrid.add(bruttoLabel, 0, 2);
        resultGrid.add(bruttoValue, 1, 2);
        resultGrid.setPadding(new Insets(10));
        resultGrid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));

        // Obsługa przycisku "Oblicz"
        calculateButton.setOnAction(e -> {
            try {
                double baseValue = Double.parseDouble(baseValueField.getText());
                String vatRateText = vatRateCombo.getValue().replace("%", "");
                double vatRate = Double.parseDouble(vatRateText) / 100.0;

                if (rbNettoToBrutto.isSelected()) {
                    double vatAmount = baseValue * vatRate;
                    double bruttoValueAmount = baseValue + vatAmount;
                    nettoValue.setText(String.format("%.2f", baseValue));
                    vatValue.setText(String.format("%.2f @ %s", vatAmount, vatRateCombo.getValue()));
                    bruttoValue.setText(String.format("%.2f", bruttoValueAmount));
                } else if (rbBruttoToNetto.isSelected()) {
                    double nettoValueAmount = baseValue / (1 + vatRate);
                    double vatAmount = baseValue - nettoValueAmount;
                    nettoValue.setText(String.format("%.2f", nettoValueAmount));
                    vatValue.setText(String.format("%.2f @ %s", vatAmount, vatRateCombo.getValue()));
                    bruttoValue.setText(String.format("%.2f", baseValue));
                } else if (rbAdjustToVat.isSelected()) {
                    double vatAmount = baseValue;
                    double nettoValueAmount = vatAmount / vatRate;
                    double bruttoValueAmount = nettoValueAmount + vatAmount;
                    nettoValue.setText(String.format("%.2f", nettoValueAmount));
                    vatValue.setText(String.format("%.2f @ %s", vatAmount, vatRateCombo.getValue()));
                    bruttoValue.setText(String.format("%.2f", bruttoValueAmount));
                }
            } catch (NumberFormatException ex) {
                nettoValue.setText("Błąd");
                vatValue.setText("Błąd");
                bruttoValue.setText("Błąd");
            }
        });

        // Obsługa przycisku "Zamknij"
        closeButton.setOnAction(e -> primaryStage.close());

        // Główny układ
        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(methodLabel, methodBox, dataLabel, dataGrid, buttonBox, resultLabel, resultGrid);
        mainLayout.setPadding(new Insets(10));

        mainLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN,CornerRadii.EMPTY,Insets.EMPTY)));

        // Ustawienie sceny
        Scene scene = new Scene(mainLayout, 900, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
