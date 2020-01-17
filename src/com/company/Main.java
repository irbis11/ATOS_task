package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Book> library = new ArrayList<>();
        commandParse(library);
    }

    public static void commandParse(List<Book> library) {
        Scanner userInput = new Scanner(System.in);
        String command = "";
        while (true) {
            switch (command) {
                case "add":
                    addBook(userInput, library);
                    command = "";
                    break;
                case "remove":
                    removeBook(userInput, library);
                    command = "";
                    break;
                case "list":
                    listAllBooks(library);
                    command = "";
                    break;
                case "search":
                    searchBook(userInput, library);
                    command = "";
                    break;
                case "lent":
                    lentBook(userInput, library);
                    command = "";
                    break;
                case "details":
                    bookDetails(userInput, library);
                    command = "";
                    break;
                case "return":
                    returnBook(userInput, library);
                    command = "";
                    break;
                default:
                    System.out.println("please type one of possible commands: add, remove, list, search, lent, details, return");
                    command = userInput.nextLine();
                    break;
            }
        }
    }

    public static void addBook(Scanner userInput, List<Book> library) {
        System.out.println("please type book title:");
        String title = askForNonEmptyString(userInput);

        System.out.println("please type book year (integer):");
        int year = askForInteger(userInput);

        System.out.println("please type book author:");
        String author = askForNonEmptyString(userInput);

        Book book = new Book(title, year, author);
        library.add(book);
        System.out.println("book was added");
    }

    public static void removeBook(Scanner userInput, List<Book> library) {
        System.out.println("please type book ID:");
        int id = askForInteger(userInput);

        int location = getBookLocation(id, library);
        if (location == -1) {
            System.out.println("no book with given ID");
        } else if (library.get(location).isLent()) {
            System.out.println("not possible, book is lent");
        } else {
            library.remove(library.get(location));
            System.out.println("book was removed");
        }
    }

    public static void listAllBooks(List<Book> library) {
        int freeBooks = 0;
        for (Book book : library) {
            System.out.println(book.toString());
            if (!book.isLent()) {
                freeBooks++;
            }
        }
        System.out.println("there is " + library.size() + " books in total, available books: " + freeBooks);
    }

    public static void searchBook(Scanner userInput, List<Book> library) {
        System.out.println("please type book title or press enter to skip this step:");
        String title = userInput.nextLine();

        System.out.println("please type book year or press enter to skip this step:");
        String temp = userInput.nextLine();
        int year = -1;
        try {
            year = Integer.parseInt(temp);
        } catch (NumberFormatException ignored) {}

        System.out.println("please type book author or press enter to skip this step:");
        String author = userInput.nextLine();

        for (Book book : library) {
            if (!title.isEmpty() && book.getTitle().contains(title)) {
                System.out.println(book.toString());
            } else if (year != -1 && book.getYear() == year) {
                System.out.println(book.toString());
            } else if (!author.isEmpty() && book.getAuthor().contains(author)) {
                System.out.println(book.toString());
            }
        }
    }

    public static void lentBook(Scanner userInput, List<Book> library) {
        System.out.println("please type book ID:");
        int id = askForInteger(userInput);

        System.out.println("please type lender name");
        String lenderName = askForNonEmptyString(userInput);

        int location = getBookLocation(id, library);
        if (location == -1) {
            System.out.println("no book with given ID");
        } else if (library.get(location).isLent()) {
            System.out.println("not possible, book already lent");
        } else {
            library.get(location).lentBook(lenderName);
            System.out.println("book was lend");
        }
    }

    public static void bookDetails(Scanner userInput, List<Book> library) {
        System.out.println("please type book ID:");
        int id = askForInteger(userInput);

        int location = getBookLocation(id, library);
        if (location == -1) {
            System.out.println("no book with given ID");
        } else {
            System.out.println(library.get(location).toString());
        }
    }

    public static void returnBook(Scanner userInput, List<Book> library) {
        System.out.println("please type book ID:");
        int id = askForInteger(userInput);

        int location = getBookLocation(id, library);
        if (location == -1) {
            System.out.println("no book with given ID");
        } else if (!library.get(location).isLent()) {
            System.out.println("not possible, book is not lent");
        } else {
            library.get(location).returnBook();
            System.out.println("book was returned");
        }
    }

    public static int getBookLocation(int id, List<Book> library) {
        for (int i = 0; i < library.size(); i++) {
            if (library.get(i).getID() == id) {
                return i;
            }
        }
        return -1;
    }

    public static String askForNonEmptyString(Scanner userInput) {
        String string = userInput.nextLine();
        while(string.isEmpty()) {
            System.out.println("please type something");
            string = userInput.nextLine();
        }
        return string;
    }

    public static int askForInteger(Scanner userInput) {
        while (!userInput.hasNextInt()) {
            System.out.println("please type integer");
            userInput.nextLine();
        }
        return Integer.parseInt(userInput.nextLine());
    }

}