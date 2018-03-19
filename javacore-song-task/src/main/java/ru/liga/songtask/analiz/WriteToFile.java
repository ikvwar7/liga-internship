package ru.liga.songtask.analiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    private FileWriter file;
    private static Logger logger = LoggerFactory.getLogger(FileWriter.class);

    public void close() {
        try {
            file.close();
        } catch (IOException e) {
            logger.error("Ошибка " + e.getMessage());
        }
    }

    public WriteToFile(String str) {
        try {
            file = new FileWriter(str);
        } catch (IOException e) {
            logger.error("Ошибка " + e.getMessage());
        }
    }

    public void write(String str) {
        try {
            file.write(str);
        } catch (IOException e) {
            logger.error("Ошибка: " + e.getMessage());
        }
    }

    public void write(Integer str) {
        try {
            file.write(Integer.toString(str));
        } catch (IOException e) {
            logger.error("Ошибка " + e.getMessage());
        }
    }
}
