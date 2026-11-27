/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.libraryproject;

import java.util.Scanner;
import models.Book;
import models.User;
import util.BookManagement;
import util.OrderManagement;
import util.UserManagement;

/**
 *
 * @author chevrianszweinevi
 */
public class LibraryProject {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserManagement userMgmt = new UserManagement();
    private static final BookManagement bookMgmt = new BookManagement();
    private static final OrderManagement orderMgmt = new OrderManagement();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n========================================");
            System.out.println("       LIBRARY MANAGEMENT SYSTEM        ");
            System.out.println("========================================");
            System.out.println("1. Kelola User");
            System.out.println("2. Kelola Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("0. Keluar");
            System.out.println("----------------------------------------");
            System.out.print("Masukan opsi: ");
            
            choice = safeIntInput();

            switch (choice) {
                case 1:
                    menuUser();
                    break;
                case 2:
                    menuBook();
                    break;
                case 3:
                    menuOrder();
                    break;
                case 0:
                    System.out.println("Baibai :3c");
                    break;
                default:
                    System.out.println("Pilihan invalid, silahkan pilih dari 1-3.");
            }
        } while (choice != 0);
    }

    // user
    private static void menuUser() {
        int subChoice;
        do {
            System.out.println("\nUser Management");
            System.out.println("1. Tambah User");
            System.out.println("2. Tampilkan Semua User");
            System.out.println("3. Edit Nama User");
            System.out.println("4. Hapus User");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");

            subChoice = safeIntInput();

            switch (subChoice) {
                case 1:
                    System.out.print("Nama User: ");
                    String name = scanner.nextLine().trim();
                    userMgmt.addUser(name);
                    break;
                case 2:
                    System.out.println("\nDaftar User:");
                    userMgmt.listAllUsers();
                    break;
                case 3:
                    System.out.print("Masukkan ID User yang akan diedit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Masukkan Nama Baru: ");
                    String newName = scanner.nextLine();
                    userMgmt.editUser(editId, newName);
                    break;
                case 4:
                    System.out.print("Masukkan ID User yang akan dihapus: ");
                    String delId = scanner.nextLine();
                    userMgmt.deleteUser(delId);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (subChoice != 0);
    }

    // book
    private static void menuBook() {
        int subChoice;
        do {
            System.out.println("\nManajemen Buku:");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tampilkan Semua Buku");
            System.out.println("3. Edit Data Buku");
            System.out.println("4. Hapus Buku");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");
            
            subChoice = safeIntInput();

            switch (subChoice) {
                case 1:
                    System.out.print("Masukkan Judul Buku: ");
                    String title = scanner.nextLine();
                    System.out.print("Masukkan Penulis Buku: ");
                    String author = scanner.nextLine();
                    bookMgmt.addBook(title, author);
                    break;
                case 2:
                    System.out.println("\nDaftar Buku:");
                    bookMgmt.listAllBooks();
                    break;
                case 3:
                    System.out.print("Masukkan ID Buku yang akan diedit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Masukkan Judul Baru: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Masukkan Penulis Baru: ");
                    String newAuthor = scanner.nextLine();
                    bookMgmt.editBook(editId, newTitle, newAuthor);
                    break;
                case 4:
                    System.out.print("Masukkan ID Buku yang akan dihapus: ");
                    String delId = scanner.nextLine();
                    bookMgmt.deleteBook(delId);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (subChoice != 0);
    }

    // order
    private static void menuOrder() {
        int subChoice;
        do {
            System.out.println("\nPeminjaman:");
            System.out.println("1. Pinjam Buku");
            System.out.println("2. Perpanjang Durasi");
            System.out.println("3. Pengembalian Buku");
            System.out.println("4. Tampilkan History");
            System.out.println("0. Kembali");
            System.out.print("Pilih menu: ");
            
            subChoice = safeIntInput();

            switch (subChoice) {
                case 1:
                    System.out.print("Masukkan ID User: ");
                    String userId = scanner.nextLine();
                    User user = userMgmt.findUserById(userId);
                    if (user == null) {
                        System.out.println("User tidak ditemukan.");
                        break;
                    }

                    System.out.print("Masukkan ID Buku: ");
                    String bookId = scanner.nextLine();
                    Book book = bookMgmt.findBookById(bookId);
                    if (book == null) {
                        System.out.println("Buku tidak ditemukan.");
                        break;
                    }

                    System.out.print("Masukkan Durasi Peminjaman (hari): ");
                    int duration = safeIntInput();

                    orderMgmt.createOrder(user, book, duration);
                    break;

                case 2:
                    System.out.print("Masukkan ID Peminjaman yang akan diekstensi: ");
                    String extOrderId = scanner.nextLine();
                    System.out.print("Masukkan Tambahan Durasi (hari): ");
                    int extraDays = safeIntInput();
                    orderMgmt.addDuration(extOrderId, extraDays);
                    break;

                case 3:
                    System.out.print("Masukkan ID pengembalian buku: ");
                    String retOrderId = scanner.nextLine();
                    orderMgmt.finishOrder(retOrderId);
                    break;

                case 4:
                    System.out.println("\nHistory Pinjaman:");
                    orderMgmt.listAllOrders();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (subChoice != 0);
    }

    private static int safeIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Input harus angka, coba lagi: ");
            }
        }
    }
}
