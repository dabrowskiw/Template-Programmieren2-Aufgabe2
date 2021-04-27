# Aufgabe zu Woche 4

In dieser Woche bauen wir sauberere Fehlerbehandlung in das Einlesen der Sequenzdateien ein und beginnen mit dem Einlesen der Mutationsliste.

Hinweis: An den Stellen, an denen Sie etwas ändern müssen, habe ich den Code wieder komplett entfernt. Sie können gerne einfach Ihren Code aus der letzten Aufgabe einfügen und von dort aus weiterarbeiten, oder - falls das gar nicht klappt - den Code aus der Musterlösung nehmen.

## Fehlerbehandlung

Das, was wir in der letzten Woche mit ```isValid()``` in ```SequenceFile``` ist eigentlich nicht schön - es bedeutet, dass man bei jeder Verwendung von SequenceFile erstmal überprüfen muss, ob das SequenceFile eigentlich nutzbar ist. Stattdessen kann man den Programmfluss eigentlich direkt abbrechen, wenn eine Datei z.B. das falsche Format hat.

Genau das sollen Sie nun implementieren: In dem Paket ```org.htw.prog2.aufgabe1.exceptions``` gibt es eine Klasse ```FileFormatException```. Erweitern Sie diese so, dass sie über ihren Constructor eine Fehlermeldung nehmen kann. Bauen Sie dann SeqFile so um, dass:

* ```isValid()``` nicht mehr vorhanden ist
* Bei Fehlern beim Einlesen im Constructor Exceptions fliegen (achten Sie auf die genauen Fehlertexte, diese werden von den Tests überprüft):
    * Falls die Datei nicht existiert: Eine ```FileNotFoundException``` (das ist die, die sowieso in diesem Fall von ```FileReader``` geworfen wird)
    * Bei sonstigen Lesefehlern: Eine ```IOException``` (das ist die, die sowieso in diesem Fall von ```readLine()``` geworfen wird)
    * Falls die erste Zeile nicht mit ">" beginnt: ```FileFormatException``` mit der Fehlermeldung "FASTA File does not start with sequence header line."
    * Falls zwei Zeilen hintereinander mit ">" beginnen: ```FileFormatException``` mit der Fehlermeldung "Two header lines are directly following each other." 
    * Falls die letzte Zeile mit ">" beginnt: ```FileFormatException``` mit der Fehlermeldung "The last line is a sequence header." 
    
## Einlesen der Mutationspattern: MutationPatterns

Zudem beginnen wir in dieser Woche mit dem Einlesen der Mutationspattern-CSV. Implementieren Sie in der Klasse MutationPatterns die entsprechende Funktionalität.

### Constructor 

Der Constructor soll den Dateinamen einer CSV-Datei mit Mutationspattern als Eingabe nimmt. Diese Datei soll nach folgenden Regeln eingelesen werden:
* Alle Zeilen, die mit "#" beginnen, sollen ignoriert werden
* Die erste Zeile (außer Zeilen, die mit "#" beginnen) soll eine Definitionszeile mit Medikamentennamen sein (siehe nächster Abschnitt)
* Jede spätere Zeile muss entweder leer sein (Länge 0, wird dann ignoriert) oder die gleiche Anzahl von Elementen (getrennt mit ";") enthalten, wie die Definitionszeile

Dabei sollen folgende Exceptions geworfen werden:
* Falls die Datei nicht existiert: Eine ```FileNotFoundException``` (das ist die, die sowieso in diesem Fall von ```FileReader``` geworfen wird)
* Bei sonstigen Lesefehlern: Eine ```IOException``` (das ist die, die sowieso in diesem Fall von ```readLine()``` geworfen wird)
* Falls die erste Zeile (außer Zeilen, die mit "#" beginnen) keine Definitionszeile ist: Eine ```FileFormatException``` mit der Fehlermeldung: "First line of mutation pattern CSV file must be a header".
* Falls eine Zeile nach der Definitionszeile nicht genauso viele Elemente enthält, wie die Definitionszeile: Eine ```FileFormatException``` mit der Fehlermeldung: "All lines in a CSV file must have the same number of elements".


### Parsen von Medikamentennamen

Medikamentennamen sind in einer Definitionszeile beschrieben, die die erste Zeile in der Datei (außer Zeilen die mit "#" beginnen) sein muss. Diese Definitionszeile beginnt mit dem Text ```"Mutation Patterns";"Number of Sequences";```, gefolgt von einem oder mehr Medikameninformationen. Dabei ist eine Medikamentendefinition wie folgt aufgebaut: ```"<Medikamentenname> foldn"```. Beispielsweise würde die Zeile ```"Mutation Patterns";"Number of Sequences";"NFV foldn";"SQV foldn"``` die zwei Medikamentennamen "NFV" und "SQV" beschreiben.

Das Parsen dieser Medikamentennamen soll in der Methode ```public static List<String> parseDrugs(String line)``` implementiert werden, die eine ```List<String>``` mit den eingelesenen Medikamentennamen zurückgibt.

Falls ein Eintrag in der Definitionszeile:
* "Mutation Patterns" lautet, aber nicht das erste Element ist
* "Number of Sequences" lautet, aber nicht das zweite Element ist
* Weder das erste noch das zweite Element ist und nicht mit ``` foldn"``` endet
soll eine ```FileFormatException``` geworfen werden.

### Einlesen der restlichen Zeilen

Die restlichen Zeilen werden wir in der nächsten Woche parsen. Zunächst sollen Sie diese nur zählen. Implementieren Sie entsprechend die Methode ```public int getNumberOfMutations()``` so, dass sie die eingelesene Zahl der Mutationen (ohne Definitionszeile, leere Zeilen und Zeilen die mit "#" beginnen) zurückgibt. 

## Endlich eine main!

Implementieren Sie abschließend in ```HIVDiagnostics``` die Methode ```public static void main(String[] args)```. Diese soll entsprechend der übergebenen Parameter die angegebenen FASTA-Dateien und die CSV-Datei einlesen und folgendes ausgeben:

```text
Eingelesene Mutationen: <Anzahl der Mutationen>
Länge der eingelesenen Referenzsequenz: <Anzahl Zeichen in Referenzsequenz> Aminosäuren
Anzahl der eingelesenen Patientensequenzen: <Anzahl Sequenzen in Patienten-FASTA-Datei>
```