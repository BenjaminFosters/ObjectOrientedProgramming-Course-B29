/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import models.Book;
import models.Order;
import models.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chevrianszweinevi
 */
public class OrderManagement {
    private List<Order> orderList;
    private int incrementID = 0;

    public OrderManagement() {
        this.orderList = new ArrayList<>();
    }

    public void createOrder(User user, Book book, int durationDays) {
        if (!book.isAvailable()) {
            System.out.println("Gagal meminjam: Buku \"" + book.getTitle() + "\" sedang dipinjam orang lain.");
            return;
        }

        String tempID = String.format("ORD-%04d", ++incrementID);
        Order newOrder = new Order(tempID, user, book, durationDays);
        orderList.add(newOrder);
        book.setAvailable(false);

        System.out.println("Peminjaman berhasil.");
    }

    public void addDuration(String orderId, int additionalDays) {
        if (additionalDays <= 0) {
            System.out.println("Ekstensi harus lebih dari 0.");
            return;
        }

        for (Order order : orderList) {
            if (order.getOrderId().equals(orderId)) {
                if (order.isFinished()) {
                    System.out.println("Peminjaman ini sudah selesai, durasi tidak bisa ditambah.");
                    return;
                }
                int currentDuration = order.getDurationDays();
                order.setDurationDays(currentDuration + additionalDays);
                System.out.println("Durasi berhasil diperpanjang menjadi " + order.getDurationDays() + " hari.");
                return;
            }
        }
        System.out.println("Peminjaman tidak ditemukan.");
    }

    public void finishOrder(String orderId) {
        for (Order order : orderList) {
            if (order.getOrderId().equals(orderId)) {
                if (order.isFinished()) {
                    System.out.println("Peminjaman ini sebelumnya sudah diselesaikan.");
                    return;
                }
                order.setFinished(true);
                order.getBook().setAvailable(true); 
                System.out.println("Buku telah dikembalikan");
                return;
            }
        }
        System.out.println("ID peminjaman tidak ditemukan");
    }

    public void listAllOrders() {
        if (orderList.isEmpty()) {
            System.out.println("Belum ada transaksi peminjaman.");
            return;
        }
        for (Order order : orderList) {
            if (!order.isFinished()) {
                System.out.println(order.getOrderId() + " : " + order.getUser().getName() + " / " + order.getBook().getTitle() + " by " + order.getBook().getAuthor() + " / " + order.getDurationDays() + " days left.");
            } else {
                System.out.println(order.getOrderId() + " : " + order.getUser().getName() + " / " + order.getBook().getTitle() + " by " + order.getBook().getAuthor() + " / Returned");
            }
        }
    }
}
