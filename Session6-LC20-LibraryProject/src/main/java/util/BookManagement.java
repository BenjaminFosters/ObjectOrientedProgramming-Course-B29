/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import models.Book;
import models.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chevrianszweinevi
 */
public class BookManagement {
    private List<Book> bookList;
    private int incrementID = 0;

    public BookManagement() {
        this.bookList = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        String tempID = String.format("BOOK-%04d", ++incrementID);
        Book newBook = new Book(tempID, title, author);
        bookList.add(newBook);
        System.out.println("Buku berhasil ditambahkan");
    }

    public void listAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("Buku Kosong, Mohon tambah buku.");
            return;
        }
        for (Book book : bookList) {
            System.out.println(book.getId() + " : " + book.getTitle() + " by " + book.getAuthor());
        }
    }

    public void editBook(String id, String newTitle, String newAuthor) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                System.out.println("Informasi Buku telah di update");
                return;
            }
        }
        System.out.println("Buku dengan ID " + id + " tidak ditemukan.");
    }

    public void deleteBook(String id) {
        boolean isRemoved = bookList.removeIf(book -> book.getId().equals(id));
        if (isRemoved) {
            System.out.println("Buku telah dihapus.");
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    public Book findBookById(String id) {
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }
}
