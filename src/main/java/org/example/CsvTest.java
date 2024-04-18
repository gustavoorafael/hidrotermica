package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvParser.Feature;
import com.google.common.io.Resources;


public class CsvTest {

    public static <T> List<T> getArquivoCsv(String pathArquivo, Class<T> clazz) throws RuntimeException {
        URL resource = Resources.getResource(pathArquivo);
        try {
            File file = new File(resource.toURI());
            return readLines(file, clazz);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    private static <T> List<T> readLines(File file, Class<T> clazz) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.ISO_8859_1);

        List var8;
        try {
            CsvMapper mapper = (new CsvMapper()).enable(Feature.TRIM_SPACES).enable(Feature.IGNORE_TRAILING_UNMAPPABLE);
            Constructor<? extends T> constructor = clazz.getConstructor();
            T instancia = constructor.newInstance();
            CsvSchema schema = CsvSchema.emptySchema().withoutQuoteChar().withColumnSeparator(';').withHeader();
            MappingIterator<T> it = mapper.readerFor(instancia.getClass()).with(schema).readValues(inputStreamReader);
            var8 = it.readAll();
        } catch (Throwable var10) {
            try {
                inputStreamReader.close();
            } catch (Throwable var9) {
                var10.addSuppressed(var9);
            }

            throw var10;
        }

        inputStreamReader.close();
        return var8;
    }

}
