package com.company;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book = new Book("testTitle", 2020, "testAuthor");

    @Test
    void testToString() {
        String expected = "Book{" + "title='testTitle" + '\'' + ", year=2020" + ", author='testAuthor" + '\''
                + ", isLent=false" + ", lenderName='" + '\'' + '}';
        assertEquals(expected, book.toString());
    }

    @Test
    void getTitle() {
        assertEquals("testTitle", book.getTitle());
    }

    @Test
    void getYear() {
        assertEquals(2020, book.getYear());
    }

    @Test
    void getAuthor() {
        assertEquals("testAuthor", book.getAuthor());
    }

    @Test
    void getID() {
        assertEquals(4, book.getID());
    }

    @Test
    void getLenderName() {
        book.lentBook("testName");
        assertEquals("testName", book.getLenderName());
    }

    @Test
    void isLent() {
        assertFalse(book.isLent());
        book.lentBook("testName");
        assertTrue(book.isLent());
    }

    @Test
    void lentBook() {
        assertFalse(book.isLent());
        book.lentBook("testName");
        assertEquals("testName", book.getLenderName());
        assertTrue(book.isLent());
    }

    @Test
    void returnBook() {
        book.lentBook("testName");
        assertTrue(book.isLent());
        book.returnBook();
        assertFalse(book.isLent());
        assertEquals("", book.getLenderName());
    }

}