package com.GUI;

import com.jfoenix.controls.*;
import com.logic.method.CongruenceLinear;
import com.logic.method.CongruenceMultiply;
import com.logic.method.MiddleSquares;
import com.logic.method.operation.OperationCongruenceLinear;
import com.logic.method.operation.OperationCongruenceMultiply;
import com.logic.method.operation.OperationUniformDistribution;
import com.logic.methodTest.MeanTest;
import com.logic.methodTest.PokerTest;
import com.logic.methodTest.VarianceTest;
import com.logic.utils.Utils;
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
import java.util.ResourceBundle;

/**
 * @author Duvis Gómez && Martin Vivanco.
 */
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
    private JFXListView<Double> numbersList;
    @FXML
    private ListView<Double> listNumbersConLineal, listNumbersConMulti, listMeanTest, listVarianceTest, listNiUniformDistribution, listRiUniformDistribution, listKSTest;
    @FXML
    private Button generateMiddleSquearesButton, stopMeanSquearesButto, saveConMultiTxtButton, saveConMultiXlsButton;
    @FXML
    private JFXSlider meanAcceptGradesSlider, varianceAcceptGradesSlider;
    @FXML
    private Label meanAcceptGradeLabel, meanAlphaLabel, meanMeanLabel, meanNLabel, meanOneAlphaLabel, meanZLabel, meanLILabel, meanLSLabel, meanValidLabel,
            varianceAccepGradesLabel, varianceAlphaLabel, varianceMeanLabel, varianceNLabel, varianceVarianceLabel, varianceAlphaMediosLabel, varianceUnoAlphaMediosLabell, varianceChiAlphaLabel, varianceChiUnoAplhaLabel, varianceLILabel, varianceLSLabel, varianceValidLabel,
            pKAcceptationGradesLabel, pKDF, pkOP, pKTP, pKFH, pKST, pKP, pKSummatory, pKQ, pKValidLabel;


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
        seedTextField.setText("");
        infiniteGenerationInput.setText("");
        infiniteGenerationInput.setDisable(false);
    }

    private void disableAndEnabled() {
        loadingSpinner.setVisible(true);
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
    void goToTestPoker() {
        cleanKSTest();
        setResultsInListView(listKSTest);
        mainTabbedPanel.getSelectionModel().select(6);
    }

    private void showAlertCantGenerate() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No se pueden generar");
        alert.setHeaderText("Datos erroneos");
        alert.setContentText("Asegurese que los parámetros de entrada son correctos");
        alert.showAndWait();
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
     * Ejecuta una prueba de Poker, tomando los numeros cargados en el ListView
     */
    public void testPoker() {
        PokerTest pT = new PokerTest(0.05, listKSTest.getItems());
        pT.processPokerTest();
        boolean isValid = (pT.getSummationProbabilities() < 12.591587? true: false);
        pKAcceptationGradesLabel.setText("" + 0.05);
        pKDF.setText("" + pT.getDF());
        pkOP.setText("" + pT.getOP());
        pKTP.setText("" + pT.getTP());
        pKST.setText("" + pT.getST());
        pKFH.setText("" + pT.getFH());
        pKP.setText("" + pT.getP());
        pKQ.setText("" + pT.getQ());
        pKSummatory.setText("" + pT.getSummationProbabilities());
        pKValidLabel.setText(isValid ? "Válido" : "Inválido");
        pKValidLabel.setTextFill(isValid ? GUIUtils.OK_COLOR : GUIUtils.ERROR_COLOR);
        XYChart.Series<String, Number> intervalos = new XYChart.Series<>();
        intervalos.setName("intervalos");
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
        pKAcceptationGradesLabel.setText("" + GUIUtils.ACCEPT_GRADES);
        pKDF.setText("");
        pkOP.setText("");
        pKTP.setText("");
        pKFH.setText("");
        pKST.setText("");
        pKP.setText("");
        pKQ.setText("");
        pKSummatory.setText("");
        pKValidLabel.setText("");
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
    }
}
