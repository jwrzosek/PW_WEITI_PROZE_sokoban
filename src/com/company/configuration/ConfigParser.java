package com.company.configuration;

import com.company.GameConfiguration;
import com.company.Level;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Klasa odopowiedzialna za sparsowanie danych z pliku konfiguracyjnego o rozszerzeniu .json.
 */

public class ConfigParser {

    /**
     * Funkcja odpowiedzialna za zwrocenie obiektu typu Level potrzebnego do przypisania wspołrzednych
     * odczytanych z pliku konfiguracyjnego.
     * @param path sciezka do pliku konfiguracyjnego
     * @return obiekt klasy Level, zawierajacy dane konfiguracyjne
     * @throws IOException
     */
    public Level getConfig(String path) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(new File(path), Level.class);
    }
}
