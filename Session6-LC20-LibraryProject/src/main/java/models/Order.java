/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author chevrianszweinevi
 */
public class Order {
    private String orderId;
    private User user;
    private Book book;
    private int durationDays;
    private boolean isFinished;

    public Order(String orderId, User user, Book book, int durationDays) {
        this.orderId = orderId;
        this.user = user;
        this.book = book;
        this.durationDays = durationDays;
        this.isFinished = false;
    }

    public String getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }
}
