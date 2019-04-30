package com.GUI;

import com.jfoenix.controls.*;
import com.logic.*;
import com.logic.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {

    private ObservableList<Double> middleSquaresResult, congruentialLinealResult, congruentialMultiResult, uniformDistriResult, uniformDistriInput;
    private Thread threadMeanSquaresExec;
    private GUIUtils gui;

    @FXML
    private TabPane mainTabbedPanel;
    @FXML
    private JFXSpinner loadingSpinner;
    @FXML
    private JFXTextField seedTextField, infiniteGenerationInput, xoInput, xoInputM, aInput, aInputM, bInput, mInput, mInputM, iterationsInput, iterationsInputM, maxInput, minInput, iterationsUniformDistributionInput;
    @FXML
    private JFXToggleButton infiniteGenerationToggle;
    @FXML
    private JFXListView<Double> numbersList;
    @FXML
    private ListView<Double> listNumbersConLineal, listNumbersConMulti, listMeanTest, listVarianceTest, listNiUniformDistribution, listRiUniformDistribution, listKSTest;
    @FXML
    private Button saveMiddleSquaresTxtButton, generateMiddleSquearesButton, stopMeanSquearesButton, saveMiddleSquaresXlsButton, saveConLinealTxtButton, saveConLinealXlsButton, saveConMultiTxtButton, saveConMultiXlsButton, testMeansButton, testVarianceButton, testKSButton, doTestMeansButton, generateUniformDistribution, saveUniformDistriXlsButton, loadNumbersUniformDistribution, saveUniformDistriTxtButton;
    @FXML
    private JFXSlider meanAcceptGradesSlider, varianceAcceptGradesSlider, KSAcceptGradesSlider;
    @FXML
    private Label meanAcceptGradeLabel, meanAlphaLabel, meanMeanLabel, meanNLabel, meanOneAlphaLabel, meanZLabel, meanLILabel, meanLSLabel, meanValidLabel,
            varianceAccepGradesLabel, varianceAlphaLabel, varianceMeanLabel, varianceNLabel, varianceVarianceLabel, varianceAlphaMediosLabel, varianceUnoAlphaMediosLabell, varianceChiAlphaLabel, varianceChiUnoAplhaLabel, varianceLILabel, varianceLSLabel, varianceValidLabel,
            ksAcceptationGradesLabel, ksAlphaLabel, ksMeanLabel, ksNLabel, ksMinLabel, ksMaxLabel, ksDmaxLabel, ksDmaxPLabel, KsValidLabel;
    @FXML
    private BarChart<String, Number> histograma;

    /**
     * Guarda la lista de números generados con congruencia multiplicativa como texto plano
     */
    @FXML
    void saveConMultiTxt() {
        FileUtils.savePlainText(Utils.convertDoubleToString(congruentialMultiResult), mainTabbedPanel.getScene().getWindow());
    }

    /**
     * Guarda la lista de números generados con congruencia multiplicativa como una hoja de excel
     */
    @FXML
    void saveConMultiXls() {
        FileUtils.saveExcel(Utils.convertDoubleToString(congruentialMultiResult), mainTabbedPanel.getScene().getWindow(), "Congruencial Multiplicativo");
    }

    /**
     * Guarda la lista de números generados con congruencia lineal como texto plano
     */
    @FXML
    void saveConLinealTxt() {
        FileUtils.savePlainText(Utils.convertDoubleToString(congruentialLinealResult), mainTabbedPanel.getScene().getWindow());
    }

    /**
     * Guarda la lista de números generados con congruencia lineal como hoja de excel
     */
    @FXML
    void saveConLinealXls() {
        FileUtils.saveExcel(Utils.convertDoubleToString(congruentialLinealResult), mainTabbedPanel.getScene().getWindow(), "Congruencial Lineal");
    }

    /**
     * Guarda la lista de números generados con cuadrados medios como texto plano
     */
    @FXML
    void saveMiddleSquaresTxt() {
        FileUtils.savePlainText(Utils.convertDoubleToString(new ArrayList<>(middleSquaresResult)), mainTabbedPanel.getScene().getWindow());
    }

    /**
     * Guarda la lista de números generados con cuadrados medios como una hoja de excel
     */
    @FXML
    void saveMiddleSquaresXls() {
        FileUtils.saveExcel(Utils.convertDoubleToString(new ArrayList<>(middleSquaresResult)), mainTabbedPanel.getScene().getWindow(), "Cuadrados Medios");
    }

    /**
     * Guarda la lista de números generados con Distribución uniforme como texto plano
     */
    @FXML
    void saveUniformDistriTxt() {
        FileUtils.savePlainText(Utils.convertDoubleToString(new ArrayList<>(uniformDistriResult)), mainTabbedPanel.getScene().getWindow());
    }

    /**
     * Guarda la lista de números generados con Distribución uniforme como una hoja de excel
     */
    @FXML
    void saveUniformDistriXls() {
        FileUtils.saveExcel(Utils.convertDoubleToString(new ArrayList<>(uniformDistriResult)), mainTabbedPanel.getScene().getWindow(), "Distribución Uniforme");
    }

    /**
     * Inicia la generación de números con el método de congruencia lineal
     */
    @FXML
    void generateCongruenLineal() {
        congruentialLinealResult.clear();
        OperationCongruenceLinear linear = new OperationCongruenceLinear(new CongruenceLinear(
                Integer.parseInt(aInput.getText()),
                Integer.parseInt(bInput.getText()),
                Integer.parseInt(mInput.getText()),
                Integer.parseInt(xoInput.getText()),
                Integer.parseInt(iterationsInput.getText())));
        linear.iteration(congruentialLinealResult);
        congruentialLinealResult.forEach(System.out::println);
    }

    /**
     * Inicia la generación de números con el método de congruencia multiplicativa
     */
    @FXML
    void generateCongruenMulti() {
        congruentialMultiResult.clear();
        OperationCongruenceMultiply multiply = new OperationCongruenceMultiply(new CongruenceMultiply(
                Integer.parseInt(aInputM.getText()),
                Integer.parseInt(mInputM.getText()),
                Integer.parseInt(xoInputM.getText()),
                Integer.parseInt(iterationsInputM.getText())));
        multiply.iteration(congruentialMultiResult);

        saveConMultiTxtButton.setDisable(false);
        saveConMultiXlsButton.setDisable(false);
    }

    /**
     * Verifíca si el número de iteraciones es una cantidad válida
     */
    @FXML
    void checkInfiniteGenerationInput() {
        if (!Utils.isValidIterationsNumber(infiniteGenerationInput.getText())) {
            gui.setTextFieldAsError(infiniteGenerationInput);
        } else {
            gui.setTextFieldAsOK(infiniteGenerationInput);
        }
    }

    /**
     * Verifíca si la semilla es una cantidad válida
     */
    @FXML
    void checkSeedInput() {
        if (!Utils.isValidSeed(seedTextField.getText())) {
            gui.setTextFieldAsError(seedTextField);
        } else {
            gui.setTextFieldAsOK(seedTextField);
        }
    }

    /**
     * Sale del software
     */
    @FXML
    void exit() {
        System.exit(0);
    }

    /**
     * Limpia todos los campos en la ventana de Cuadrados medios
     */
    @FXML
    void cleanMiddleSquares() {
        middleSquaresResult.clear();
        saveMiddleSquaresTxtButton.setDisable(true);
        saveMiddleSquaresXlsButton.setDisable(true);
        seedTextField.setText("");
        infiniteGenerationToggle.setSelected(false);
        infiniteGenerationInput.setText("");
        infiniteGenerationInput.setDisable(false);
    }

    private void disableAndEnabled() {
        loadingSpinner.setVisible(true);
        stopMeanSquearesButton.setVisible(true);
        generateMiddleSquearesButton.setDisable(true);
    }

    /**
     * Inicia la generación de números con el método de cuadrados medios
     */
    @FXML
    void generateMiddleSqueares() {
        String seed = seedTextField.getText();
        String iterations = infiniteGenerationInput.getText();
        middleSquaresResult.clear();
        if (Utils.isValidSeed(seed)) {

            if (Utils.isValidIterationsNumber(iterations)) {
                disableAndEnabled();
                startThreadMiddlleSquares(new MiddleSquares(new BigInteger(seed), Integer.parseInt(iterations)));
            } else {
                showAlertCantGenerate();
            }

        } else {
            showAlertCantGenerate();
        }
    }

    /**
     * Inicia el hilo de generación de números con el método de Cuadrados medios
     *
     * @param meanSquares objeto de la clase MeanSquares con la información de la seed y opcionalmente el número de iteraciones
     */
    private void startThreadMiddlleSquares(MiddleSquares meanSquares) {
        try {
            threadMeanSquaresExec = new Thread(() -> {
                meanSquares.generateIn(middleSquaresResult);
                loadingSpinner.setVisible(false);
                stopMeanSquearesButton.setVisible(false);
                generateMiddleSquearesButton.setDisable(false);
            });
            threadMeanSquaresExec.start();
        } catch (IllegalStateException e) {
        }
    }

    /**
     * Detiene el hilo de la ejecución de del método de cuadrados medios
     */
    @FXML
    void stopMiddleSqueares() {
        threadMeanSquaresExec.interrupt();
        threadMeanSquaresExec.stop();
        stopMeanSquearesButton.setVisible(false);
        loadingSpinner.setVisible(false);
        generateMiddleSquearesButton.setDisable(false);
    }

    /**
     * Inicia la generación de números con el método de Distribución Uniforme
     */
    @FXML
    void generateUniformDistribution() {
        uniformDistriResult.clear();
        uniformDistriInput.clear();
        OperationUniformDistribution operationUniformDistribution = new OperationUniformDistribution(
                Integer.parseInt(minInput.getText()),
                Integer.parseInt(maxInput.getText()),
                Integer.parseInt(iterationsUniformDistributionInput.getText()));
        operationUniformDistribution.generateNumber();
        uniformDistriInput.addAll(operationUniformDistribution.getListNumber());
        operationUniformDistribution.generatePseudo();
        uniformDistriResult.addAll(operationUniformDistribution.getListNumberPseudo());
    }

    /**
     * Redirige a la ventana del método de cuadrados medios
     */
    @FXML
    void goToMiddleSquaresTab() {
        mainTabbedPanel.getSelectionModel().select(0);
    }

    /**
     * Redirige a la ventana del método de congruencia lineal
     */
    @FXML
    void goToCongruentialLinealTab() {
        mainTabbedPanel.getSelectionModel().select(1);
    }

    /**
     * Redirige a la ventana del método de congruencia multiplicativa
     */
    @FXML
    void goToCongruentialMultiTab() {
        mainTabbedPanel.getSelectionModel().select(2);
    }

    /**
     * Redirige a la ventana del método de Distribución Uniforme
     */
    @FXML
    void goToUniformTab() {
        mainTabbedPanel.getSelectionModel().select(3);
    }

    /**
     * Redirige a la ventana de la prueba de medias
     */
    @FXML
    void goToTestMeans() {
        cleanMeanTest();
        setResultsInListView(listMeanTest);
        mainTabbedPanel.getSelectionModel().select(4);
    }

    /**
     * Redirige a la ventana de la prueba de varianzas
     */
    @FXML
    void goToTestVariance() {
        cleanVarianceTest();
        setResultsInListView(listVarianceTest);
        mainTabbedPanel.getSelectionModel().select(5);
    }

    /**
     * Redirige a la ventana de la prueba de K-S
     */
    @FXML
    void goToTestKS() {
        cleanKSTest();
        setResultsInListView(listKSTest);
        mainTabbedPanel.getSelectionModel().select(6);
    }


    @FXML
    void openAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Simulación");
        alert.setContentText("Julian David Grijalba Bernal \n Walter Mauricio Cuervo");
        alert.showAndWait();
    }

    private void showAlertCantGenerate() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No se pueden generar");
        alert.setHeaderText("Datos erroneos");
        alert.setContentText("Asegurese que los parámetros de entrada son correctos");
        alert.showAndWait();
    }

    /**
     * Habilita o deshabilita el campo para ingresar la cantidad de números a generar en el método de cuadrados medios
     */
    @FXML
    void switchIterations() {
        infiniteGenerationInput.setDisable(infiniteGenerationToggle.isSelected());
    }

    /**
     * Ejecuta una prueba de medias, tomando los numeros cargados en el ListView
     */
    @FXML
    void testMeans() {
        MeanTest meanTest = new MeanTest(listMeanTest.getItems(), (int) meanAcceptGradesSlider.getValue());
        boolean isValid = meanTest.isValid();
        meanAcceptGradeLabel.setText("" + meanTest.getAcceptationGrade());
        meanAlphaLabel.setText("" + meanTest.getErrorGrade());
        meanMeanLabel.setText("" + meanTest.getAverage());
        meanNLabel.setText("" + meanTest.getNumbersQuantity());
        meanOneAlphaLabel.setText("" + meanTest.getaMedios());
        meanZLabel.setText("" + meanTest.getZ());
        meanLILabel.setText("" + meanTest.getLI());
        meanLSLabel.setText("" + meanTest.getLS());
        meanValidLabel.setText(isValid ? "Válido" : "Inválido");
        meanValidLabel.setTextFill(isValid ? GUIUtils.OK_COLOR : GUIUtils.ERROR_COLOR);
    }

    /**
     * Establece los números a evaluar en el método de evaluación correspondiente, la lista se toma de la pestaña actual
     *
     * @param list ListView correspondiente al método de evaluacion
     */
    private void setResultsInListView(ListView<Double> list) {
        switch (mainTabbedPanel.getSelectionModel().getSelectedIndex()) {
            case 0:
                list.setItems(middleSquaresResult);
                break;
            case 1:
                list.setItems(congruentialLinealResult);
                break;
            case 2:
                list.setItems(congruentialMultiResult);
                break;
            case 3:
                list.setItems(middleSquaresResult);
                break;
            default:
                break;
        }
    }

    /**
     * Ejecuta una prueba de varianzas, tomando los numeros cargados en el ListView
     */
    public void testVariances() {
        VarianceTest varianceTest = new VarianceTest(listVarianceTest.getItems(), (int) varianceAcceptGradesSlider.getValue());
        boolean isValid = varianceTest.isValid();
        varianceAccepGradesLabel.setText("" + varianceTest.getAcceptationGrade());
        varianceAlphaLabel.setText("" + varianceTest.getErrorGrade());
        varianceMeanLabel.setText("" + varianceTest.getAverage());
        varianceNLabel.setText("" + varianceTest.getNumbersQuantity());
        varianceVarianceLabel.setText("" + varianceTest.getVariance());
        varianceAlphaMediosLabel.setText("" + varianceTest.getaMedios());
        varianceUnoAlphaMediosLabell.setText("" + varianceTest.getUnoAMedios());
        varianceChiAlphaLabel.setText("" + varianceTest.getChiSqaure1());
        varianceChiUnoAplhaLabel.setText("" + varianceTest.getChiSqaure2());
        varianceLILabel.setText("" + varianceTest.getLI());
        varianceLSLabel.setText("" + varianceTest.getLS());

        varianceValidLabel.setText(isValid ? "Válido" : "Inválido");
        varianceValidLabel.setTextFill(isValid ? GUIUtils.OK_COLOR : GUIUtils.ERROR_COLOR);
    }

    /**
     * Ejecuta una prueba de K-S, tomando los numeros cargados en el ListView
     */
    public void testKS() {
        KolmogorovSmirnov ks = new KolmogorovSmirnov((int) KSAcceptGradesSlider.getValue(), listKSTest.getItems());
        ks.calculateFinalValue();
        ks.calculateFrequency();
        ks.calculateFrequencyAcumulated();
        ks.calculatedGetProbability();
        ks.calculatedFrequencyExpected();
        ks.calculatedProbabilityExpected();
        ks.calculatedDiference();
        ks.calculatedDMAX();
        ks.calculatedDMAXP();
        boolean isValid = ks.isPseudo();
        ksAcceptationGradesLabel.setText("" + ks.getAceptatio());
        ksAlphaLabel.setText("" + ks.getAlpha());
        ksMeanLabel.setText("" + ks.getMedium());
        ksNLabel.setText("" + ks.getNumbersAmount());
        ksMaxLabel.setText("" + ks.getMAX());
        ksMinLabel.setText("" + ks.getMIN());
        ksDmaxLabel.setText("" + ks.calculatedDMAX());
        ksDmaxPLabel.setText("" + ks.calculatedDMAXP());

        KsValidLabel.setText(isValid ? "Válido" : "Inválido");
        KsValidLabel.setTextFill(isValid ? GUIUtils.OK_COLOR : GUIUtils.ERROR_COLOR);

        XYChart.Series<String, Number> intervalos = new XYChart.Series<>();
        intervalos.setName("intervalos");

        for (int i = 0; i < ks.getListInterval().size(); i++) {
            intervalos.getData().add(new XYChart.Data<>("" + i, ks.getListInterval().get(i).getFrequencyGet()));
        }
        histograma.getData().add(intervalos);
        histograma.getData().get(0).getData().forEach(this::displayLabelForData);
        histograma.setVisible(true);
    }

    /**
     * limpia todos los campos en la ventana de prueba de medias
     */
    private void cleanMeanTest() {
        listMeanTest.setItems(null);
        meanAcceptGradesSlider.setValue(GUIUtils.ACCEPT_GRADES);
        meanAcceptGradeLabel.setText("" + GUIUtils.ACCEPT_GRADES);
        meanAlphaLabel.setText("");
        meanMeanLabel.setText("");
        meanNLabel.setText("");
        meanOneAlphaLabel.setText("");
        meanZLabel.setText("");
        meanLILabel.setText("");
        meanLSLabel.setText("");
        meanValidLabel.setText("");
    }

    /**
     * limpia todos los campos en la ventana de prueba de varianzas
     */
    private void cleanVarianceTest() {
        listVarianceTest.setItems(null);
        varianceAcceptGradesSlider.setValue(GUIUtils.ACCEPT_GRADES);
        varianceAccepGradesLabel.setText("" + GUIUtils.ACCEPT_GRADES);
        varianceAlphaLabel.setText("");
        varianceMeanLabel.setText("");
        varianceNLabel.setText("");
        varianceVarianceLabel.setText("");
        varianceAlphaMediosLabel.setText("");
        varianceUnoAlphaMediosLabell.setText("");
        varianceChiAlphaLabel.setText("");
        varianceChiUnoAplhaLabel.setText("");
        varianceLILabel.setText("");
        varianceLSLabel.setText("");
        varianceValidLabel.setText("");
    }

    /**
     * limpia todos los campos en la ventana de prueba de K-S
     */
    private void cleanKSTest() {
        listKSTest.setItems(null);
        ksAcceptationGradesLabel.setText("" + GUIUtils.ACCEPT_GRADES);
        KSAcceptGradesSlider.setValue(GUIUtils.ACCEPT_GRADES);
        ksAlphaLabel.setText("");
        ksMeanLabel.setText("");
        ksNLabel.setText("");
        ksMinLabel.setText("");
        ksMaxLabel.setText("");
        ksDmaxLabel.setText("");
        ksDmaxPLabel.setText("");
        KsValidLabel.setText("");
        histograma.setVisible(false);
        histograma.getData().clear();
    }

    /**
     * agrega un tooltip a cada data cargado en el barchart con el valor correspondiente
     *
     * @param data XYData incluida en las series del BartChart
     */
    private void displayLabelForData(XYChart.Data<String, Number> data) {
        Tooltip.install(data.getNode(), new Tooltip(data.getYValue().intValue() + ""));
    }

    /**
     * Inicializa la interfaz gráfica
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gui = new GUIUtils();
        middleSquaresResult = FXCollections.observableArrayList();
        numbersList.setItems(middleSquaresResult);
        congruentialLinealResult = FXCollections.observableArrayList();
        listNumbersConLineal.setItems(congruentialLinealResult);
        congruentialMultiResult = FXCollections.observableArrayList();
        listNumbersConMulti.setItems(congruentialMultiResult);
        uniformDistriInput = FXCollections.observableArrayList();
        listRiUniformDistribution.setItems(uniformDistriInput);
        uniformDistriResult = FXCollections.observableArrayList();
        listNiUniformDistribution.setItems(uniformDistriResult);
        meanAcceptGradesSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> meanAcceptGradeLabel.setText("" + (int) newValue.doubleValue()));
        varianceAcceptGradesSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> varianceAccepGradesLabel.setText("" + (int) newValue.doubleValue()));
        KSAcceptGradesSlider.valueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> ksAcceptationGradesLabel.setText("" + (int) newValue.doubleValue()));
    }
}
