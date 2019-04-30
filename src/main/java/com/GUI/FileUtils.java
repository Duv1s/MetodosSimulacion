package com.GUI;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

class FileUtils {
    /**
     * Guarda una lista de números como archivo de texto plano
     *
     * @param numbers lista de números a guardar
     * @param window  Ventana principal de la aplicación que se toma como parent para el FileChooser
     */
    public static void savePlainText(List<String> numbers, Window window) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(window);
        writeAsPlainText(numbers, file);
    }

    /**
     * Guarda una lista de números como un archivo correspondiente a una hoja de excel
     * @param numbers lista de números a guardar
     * @param window Ventana principal de la aplicación que se toma como parent para el FileChooser
     * @param sheetName Nombre de la hoja de excel
     */
    public static void saveExcel(List<String> numbers, Window window,String sheetName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx"));
        File file = fileChooser.showSaveDialog(window);
        writeAsExcel(numbers, file,sheetName);
    }

    /**
     * Escribe una lista de Strings en el archivo especificado
     * @param numbers Lista de números a guardar
     * @param file Archivo a escribir
     */
    private static void writeAsPlainText(List<String> numbers, File file) {
        if (file != null) {
            StringBuilder builder = new StringBuilder();
            numbers.forEach(e -> builder.append(e).append("\n"));
            builder.delete(builder.length() - 1, builder.length());
            try {
                org.apache.commons.io.FileUtils.writeStringToFile(file, builder.toString(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * Escribe una lista de Strings en el archivo especificado
     * @param numbers Lista de números a guardar
     * @param file Archivo a escribir
     * @param sheetName Nombre de la hoja de excel
     */
    private static void writeAsExcel(List<String> numbers, File file,String sheetName) {
        if (file != null) {

            XSSFWorkbook fWorkbook = new XSSFWorkbook();
            XSSFSheet fSheet = fWorkbook.createSheet(sheetName);
            for (int i = 0; i < numbers.size(); i++) {
                fSheet.createRow(i).createCell(0).setCellValue(numbers.get(i));
            }
            try {
                fWorkbook.write(new FileOutputStream(file));
                fWorkbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
