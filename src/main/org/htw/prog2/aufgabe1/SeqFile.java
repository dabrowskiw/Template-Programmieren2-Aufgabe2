package org.htw.prog2.aufgabe1;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;

public class SeqFile {
    HashSet<String> seqs = new HashSet<>();
    String firstSeq = "";

    public SeqFile(String filename) throws IOException, FileFormatException {
    }

    /**
     * Reads the specified FASTA file.
     * @param filename The path to the FASTA file
     * @return false if the file could not be parsed (wrong format, does not exist), true otherwise.
     */
    private void readFile(String filename) throws IOException, FileFormatException {
    }

    /**
     * Adds the sequence in the passed StringBuilder to the internal list and also sets the first sequence if it
     * is still empty.
     * @param seq SequenceBuilder to get the sequence from.
     * @return The length of the added sequence.
     */
    private int addSequence(StringBuilder seq) {
        return 0;
    }

    public int getNumberOfSequences() {
        return 0;
    }

    public HashSet<String> getSequences() {
        return null;
    }

    public String getFirstSequence() {
        return null;
    }
}
