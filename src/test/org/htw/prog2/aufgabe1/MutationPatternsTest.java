package org.htw.prog2.aufgabe1;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MutationPatternsTest {

    @Test
    void parseSusceptibilities() {
    }

    @Test
    void parseDrugs_wrongFirstElements() {
        assertThrows(FileFormatException.class, () -> {
            MutationPatterns.parseDrugs("\"Mutation\";\"Number of Sequences\";\"NFV foldn\";\"SQV foldn\";\"IDV foldn\"");
        });
        assertThrows(FileFormatException.class, () -> {
            MutationPatterns.parseDrugs("\"Mutation Patterns\";\"Sequences\";\"NFV foldn\";\"SQV foldn\";\"IDV foldn\"");
        });
    }

    @Test
    void parseDrugs_wrongDrugNames() {
        assertThrows(FileFormatException.class, () -> {
            MutationPatterns.parseDrugs("\"Mutation Patterns\";\"Number of Sequences\";\"NFV\";\"SQV foldn\";\"IDV foldn\"");
        });
        assertThrows(FileFormatException.class, () -> {
            MutationPatterns.parseDrugs("\"Mutation Patterns\";\"Sequences\";\"NFV foldn\";\"SQVfoldn\";\"IDV foldn\"");
        });
    }

    @Test
    void parseDrugs() {
        assertDoesNotThrow(() -> {
            List<String> drugs = MutationPatterns.parseDrugs("\"Mutation Patterns\";\"Number of Sequences\";\"NFV foldn\";\"SQV foldn\";\"IDV foldn\"");
            LinkedList<String> expected = new LinkedList<>();
            expected.add("NFV");
            expected.add("SQV");
            expected.add("IDV");
            assertEquals(expected, drugs);
        });
    }

    @Test
    void constructor_doesNotExist() {
        assertThrows(FileNotFoundException.class, () -> {
            new MutationPatterns("data/IDONOTEXIST");
        });
    }

    @Test
    void constructor_wrongHeaderStart() {
        assertThrows(FileFormatException.class, () -> {
            new MutationPatterns("data/HIVMutationPatterns_wrongHeader.csv");
        });
    }

    @Test
    void constructor_wrongHeaderDrugnames() {
        assertThrows(FileFormatException.class, () -> {
            new MutationPatterns("data/HIVMutationPatterns_wrongHeader2.csv");
        });
    }

    @Test
    void constructor_columnNumbers() {
        assertThrows(FileFormatException.class, () -> {
            new MutationPatterns("data/HIVMutationPatterns_wrongElementNumber.csv");
        });
    }

    @Test
    void constructor_correctFile() {
        assertDoesNotThrow(() -> {
            new MutationPatterns("data/HIVMutationPatternsPI.csv");
        });
    }

    @Test
    void getNumberOfMutations() {
        assertDoesNotThrow(() -> {
            MutationPatterns patterns = new MutationPatterns("data/HIVMutationPatternsPI.csv");
            assertEquals(825, patterns.getNumberOfMutations());
        });
    }

}