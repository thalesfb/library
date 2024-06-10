package com.ifc.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {
  // Instância única da classe
    private static volatile Library instance;
    
    // Listas de books e usuários
    private List<Book> books;
    private List<User> users;

    // Construtor privado para evitar instanciação externa
    private Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Método público para obter a instância única da classe
    public static Library getInstance() {
        if (instance == null) {
            synchronized (Library.class) {
                if (instance == null) {
                    instance = new Library();
                }
            }
        }
        return instance;
    }
    // Métodos para manipular books e usuários
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
