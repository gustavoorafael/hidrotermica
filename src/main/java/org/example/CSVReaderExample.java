package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderExample {

    public static void main(String[] args) {
        String csvFile = "/arquivoSemNome.csv";
        List<UsinasVO> objetos = lerCSV(csvFile);

        for (UsinasVO objeto : objetos) {
            System.out.println(objeto);
        }
    }

    public static List<UsinasVO> lerCSV(String csvFile) {
        List<UsinasVO> objetos = new ArrayList<>();

        try (Reader reader = new FileReader(csvFile);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                int id = Integer.parseInt(csvRecord.get("id"));
                double vmax = Double.parseDouble(csvRecord.get("vmax"));
                double vmin = Double.parseDouble(csvRecord.get("vmin"));
                double pcv0 = Double.parseDouble(csvRecord.get("pcv0"));
                double pcv1 = Double.parseDouble(csvRecord.get("pcv1"));
                double pcv2 = Double.parseDouble(csvRecord.get("pcv2"));
                double pcv3 = Double.parseDouble(csvRecord.get("pcv3"));
                double pcv4 = Double.parseDouble(csvRecord.get("pcv4"));
                double prodesp = Double.parseDouble(csvRecord.get("prodesp"));
                double teif = Double.parseDouble(csvRecord.get("teif"));
                double iph = Double.parseDouble(csvRecord.get("iph"));
                double pinst = Double.parseDouble(csvRecord.get("pinst"));
                double cfuga = Double.parseDouble(csvRecord.get("cfuga"));
                double cphid = Double.parseDouble(csvRecord.get("cphid"));

                UsinasVO objeto = new UsinasVO(id, vmax, vmin, pcv0, pcv1, pcv2, pcv3, pcv4, prodesp, teif, iph, pinst, cfuga, cphid);
                objetos.add(objeto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objetos;
    }

}