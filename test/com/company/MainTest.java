package com.company;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    private List<Book> library = new ArrayList<>();

    private Scanner setConsoleInput(String consoleInput) {
        System.setIn(new ByteArrayInputStream(consoleInput.getBytes()));
        return new Scanner(System.in);
    }

    private ByteArrayOutputStream getConsoleOutput() {
        PrintStream stdout = System.out;
        System.setOut(stdout);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true));
        return output;
        //        System.setOut(stdout);
    }
    
    @Test
    void addBook() {
        assertEquals(0, library.size());
        String testInput = "testTitle" + "\n" + 2020 + "\n" + "testAuthor";
        Scanner scanner = setConsoleInput(testInput);
        Main.addBook(scanner, library);
        assertEquals(1, library.size());
    }

    @Test
    void removeBook() {
        Book book = new Book("testTitle", 2020, "testAuthor");
        library.add(book);
        assertEquals(1, library.size());
        Scanner scanner = setConsoleInput(String.valueOf(book.getID()));
        Main.removeBook(scanner, library);
        assertEquals(0, library.size());
    }

    @Test
    void listAllBooks() {
        Book book = new Book("testTitle", 2020, "testAuthor");
        library.add(book);
        book = new Book("testTitle2", 2222, "testAuthor2");
        library.add(book);
        String temp1 = "Book{" + "title='testTitle" + '\'' + ", year=2020" + ", author='testAuthor" + '\''
                + ", isLent=false" + ", lenderName='" + '\'' + '}';
        String temp2 = "Book{" + "title='testTitle2" + '\'' + ", year=2222" + ", author='testAuthor2" + '\''
                + ", isLent=false" + ", lenderName='" + '\'' + '}';
        String temp3 = "there is 2" + " books in total, available books: 2";
        String newLine = System.getProperty("line.separator");
        String expected = temp1 + newLine + temp2 + newLine + temp3 + newLine;

        ByteArrayOutputStream consoleOutput = getConsoleOutput();
        Main.listAllBooks(library);
        assertEquals(expected, consoleOutput.toString());
    }

    @Test
    void searchBook() {
        Book book = new Book("testTitle", 2020, "testAuthor");
        library.add(book);
        String temp1 = "please type book title or press enter to skip this step:";
        String temp2 = "please type book year or press enter to skip this step:";
        String temp3 = "please type book author or press enter to skip this step:";
        String temp4 = "Book{" + "title='testTitle" + '\'' + ", year=2020" + ", author='testAuthor" + '\''
                + ", isLent=false" + ", lenderName='" + '\'' + '}';
        String newLine = System.getProperty("line.separator");
        String expected = temp1 + newLine + temp2 + newLine + temp3 + newLine + temp4 + newLine;

        String testInput = "testTitle" + "\n" + "\n" + "\n";
        Scanner scanner = setConsoleInput(testInput);
        ByteArrayOutputStream consoleOutput = getConsoleOutput();
        Main.searchBook(scanner, library);
        assertEquals(expected, consoleOutput.toString());

        testInput = "\n" + 2020 + "\n" + "\n";
        scanner = setConsoleInput(testInput);
        consoleOutput = getConsoleOutput();
        Main.searchBook(scanner, library);
        assertEquals(expected, consoleOutput.toString());

        testInput = "\n" + "\n" + "testAuthor" + "\n";
        scanner = setConsoleInput(testInput);
        consoleOutput = getConsoleOutput();
        Main.searchBook(scanner, library);
        assertEquals(expected, consoleOutput.toString());
    }

    @Test
    void lentBook() {
        Book book = new Book("testTitle", 2020, "testAuthor");
        library.add(book);
        String temp1 = "please type book ID:";
        String temp2 = "please type lender name";
        String newLine = System.getProperty("line.separator");

        assertFalse(book.isLent());
        String testInput = book.getID() + "\n" + "testName" + "\n";
        Scanner scanner = setConsoleInput(testInput);
        Main.lentBook(scanner, library);
        assertTrue(book.isLent());

        testInput = (book.getID() + 1) + "\n" + "testName" + "\n";
        String expected = "no book with given ID";
        scanner = setConsoleInput(testInput);
        ByteArrayOutputStream consoleOutput = getConsoleOutput();
        Main.lentBook(scanner, library);
        assertEquals(temp1 + newLine + temp2 + newLine + expected + newLine, consoleOutput.toString());

        testInput = book.getID() + "\n" + "testName" + "\n";
        expected = "not possible, book already lent";
        scanner = setConsoleInput(testInput);
        consoleOutput = getConsoleOutput();
        Main.lentBook(scanner, library);
        assertEquals(temp1 + newLine + temp2 + newLine + expected + newLine, consoleOutput.toString());
    }

    @Test
    void bookDetails() {
        Book book = new Book("testTitle", 2020, "testAuthor");
        library.add(book);
        String temp1 = "please type book ID:";
        String newLine = System.getProperty("line.separator");

        String testInput = book.getID() + "\n";
        String expected = "Book{" + "title='testTitle" + '\'' + ", year=2020" + ", author='testAuthor" + '\''
                + ", isLent=false" + ", lenderName='" + '\'' + '}';
        Scanner scanner = setConsoleInput(testInput);
        ByteArrayOutputStream consoleOutput = getConsoleOutput();
        Main.bookDetails(scanner, library);
        assertEquals(temp1 + newLine + expected + newLine, consoleOutput.toString());

        testInput = (book.getID() + 1) + "\n" + "testName" + "\n";
        expected = "no book with given ID";
        scanner = setConsoleInput(testInput);
        consoleOutput = getConsoleOutput();
        Main.bookDetails(scanner, library);
        assertEquals(temp1 + newLine + expected + newLine, consoleOutput.toString());
    }

    @Test
    void returnBook() {
        Book book = new Book("testTitle", 2020, "testAuthor");
        book.lentBook("testName");
        library.add(book);
        String temp1 = "please type book ID:";
        String newLine = System.getProperty("line.separator");

        assertTrue(book.isLent());
        String testInput = book.getID() + "\n" + "testName" + "\n";
        Scanner scanner = setConsoleInput(testInput);
        Main.returnBook(scanner, library);
        assertFalse(book.isLent());

        testInput = (book.getID() + 1) + "\n" + "testName" + "\n";
        String expected = "no book with given ID";
        scanner = setConsoleInput(testInput);
        ByteArrayOutputStream consoleOutput = getConsoleOutput();
        Main.returnBook(scanner, library);
        assertEquals(temp1 + newLine + expected + newLine, consoleOutput.toString());

        testInput = book.getID() + "\n" + "testName" + "\n";
        expected = "not possible, book is not lent";
        scanner = setConsoleInput(testInput);
        consoleOutput = getConsoleOutput();
        Main.returnBook(scanner, library);
        assertEquals(temp1 + newLine + expected + newLine, consoleOutput.toString());
    }

    @Test
    void getBookLocation() {
        Book book = new Book("testTitle", 2020, "testAuthor");
        library.add(book);
        int location = Main.getBookLocation(book.getID(), library);
        assertEquals(0, location);
        location = Main.getBookLocation(book.getID() + 1, library);
        assertEquals(-1, location);
    }

    @Test
    void askForNonEmptyString() {
        String testInput = "test";
        Scanner scanner = setConsoleInput(testInput);
        String test = Main.askForNonEmptyString(scanner);
        assertEquals(testInput, test);
    }

    @Test
    void askForInteger() {
        String testInput = "1";
        Scanner scanner = setConsoleInput(testInput);
        int test = Main.askForInteger(scanner);
        assertEquals(Integer.parseInt(testInput), test);
    }

}