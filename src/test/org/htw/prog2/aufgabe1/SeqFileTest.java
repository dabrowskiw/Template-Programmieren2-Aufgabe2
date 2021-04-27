package org.htw.prog2.aufgabe1;

import org.htw.prog2.aufgabe1.exceptions.FileFormatException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class SeqFileTest {

    @Test
    void throws_doesNotExist() {
        Exception e = assertThrows(FileNotFoundException.class, () -> {
            SeqFile seqfile = new SeqFile("data/DOESNOTEXIST");
        });
    }

    @Test
    void throws_wrongFormat() {
        Exception e = assertThrows(FileFormatException.class, () -> {
            SeqFile seqfile = new SeqFile("data/HIVMutationPatternsPI.csv");
        });
        assertEquals("FASTA File does not start with sequence header line.", e.getMessage());
    }

    @Test
    void doesntThrow_correctFormat() {
        assertDoesNotThrow(() -> {
            SeqFile seqfile = new SeqFile("data/protease_reference.fasta");
        });
    }

    @Test
    void getNumberOfSequences_single() {
        SeqFile seqfile = null;
        try {
            seqfile = new SeqFile("data/protease_reference.fasta");
        } catch(Exception e) {
            fail("Es sollte keine Exception fliegen.");
        }
        assertEquals(1, seqfile.getNumberOfSequences());
    }

    @Test
    void getNumberOfSequences_multiple() {
        SeqFile seqfile = null;
        try {
            seqfile = new SeqFile("data/protease_sequences.fasta");
        } catch(Exception e) {
            fail("Es sollte keine Exception fliegen.");
        }
        assertEquals(1000, seqfile.getNumberOfSequences());
    }

    @Test
    void getSequences_single() {
        SeqFile seqfile = null;
        try {
            seqfile = new SeqFile("data/protease_reference.fasta");
        } catch(Exception e) {
            fail("Es sollte keine Exception fliegen.");
        }
        assertEquals(1, seqfile.getSequences().size());
        assertTrue(seqfile.getSequences().contains("PQVTLWQRPIVTIKIGGQLKEALLDTGADDTVLEEMSLPGKWKPKMIGGIGGFIKVRQYDQVSIEICGHKAIGTVLIGPTPVNIIGRNLLTQLGCTLNF"));
    }

    @Test
    void getSequences_multiple() {
        SeqFile seqfile = null;
        try {
            seqfile = new SeqFile("data/protease_sequences.fasta");
        } catch(Exception e) {
            fail("Es sollte keine Exception fliegen.");
        }
        assertEquals(1000, seqfile.getSequences().size());
        assertTrue(seqfile.getSequences().contains("PQVTLRQRPIVCIKIGGSLKEALLDTGADDTVLEEMSLPGKWKPKMIGGYGGFIKVRQYDQVSIEICGHKAIGTVLIGPTPVNIIGRNLLTQLGCTLNF"));
        assertTrue(seqfile.getSequences().contains("PQVVVWQRPIVKIKIGGQLKEALLDTGADDTVLEEMSLDGKWKPKMIGGIGGRIKRRQYDQVSIEICGDKLIGTELIGPTPVNIGGTNLLTQLGCTLNF"));
    }

    @Test
    void getFirstSequence_single() {
        SeqFile seqfile = null;
        try {
            seqfile = new SeqFile("data/protease_reference.fasta");
        } catch(Exception e) {
            fail("Es sollte keine Exception fliegen.");
        }
        assertEquals("PQVTLWQRPIVTIKIGGQLKEALLDTGADDTVLEEMSLPGKWKPKMIGGIGGFIKVRQYDQVSIEICGHKAIGTVLIGPTPVNIIGRNLLTQLGCTLNF", seqfile.getFirstSequence());
    }

    @Test
    void getFirstSequence_multiple() {
        SeqFile seqfile = null;
        try {
            seqfile = new SeqFile("data/protease_sequences.fasta");
        } catch(Exception e) {
            fail("Es sollte keine Exception fliegen.");
        }
        assertEquals("PQVTLRQRPIVCIKIGGSLKEALLDTGADDTVLEEMSLPGKWKPKMIGGYGGFIKVRQYDQVSIEICGHKAIGTVLIGPTPVNIIGRNLLTQLGCTLNF", seqfile.getFirstSequence());
    }
}