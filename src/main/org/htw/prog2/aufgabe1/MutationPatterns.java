package org.htw.prog2.aufgabe1;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;

import java.io.*;
import java.util.*;

public class MutationPatterns {
    /**
     * Contructor für MutationPatterns. Liest die CSV-Datei infile ein.
     * @param infile Pfad zu CSV-Datei zum Einlesen
     * @throws IOException bei allgemeinen IO-Fehlern
     * @throws FileNotFoundException falls die Datei nicht gefunden wurde
     * @throws FileFormatException falls das Format der Definitionszeile inkorrekt ist oder die Anzahl der Spalten
     * nicht in jeder Zeile gleich ist
     */
    public MutationPatterns(String infile) throws IOException, FileNotFoundException, FileFormatException {
    }

    /**
     * Gibt die Anzahl der eingelesenen Mutationspattern zurück.
     * @return Anzahl der eingelesenen Mutationspattern
     */
    public int getNumberOfMutations() {
        return 0;
    }

    /**
     * Parst die Definitionszeile.
     * @param line Definitionszeile aus der CSV-Datei
     * @return Liste der Medikamentennamen aus der Definitionszeile
     */
    public static List<String> parseDrugs(String line) throws FileFormatException {
        return null;
    }
}
