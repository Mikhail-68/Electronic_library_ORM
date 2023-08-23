package ru.egorov.electroniclibrary.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int id;

    @Column(name = "name")
    @Size(min = 2, max = 50, message = "Длина поля должна быть от 2 до 50 символов")
    @Pattern(regexp = "[A-ZА-Я]{1}[a-zа-я]{1,44}\\s{1}[A-ZА-Я]{1}\\.[A-ZА-Я]{1}\\.", message = "Поле должно соответствовать шаблону: <Достоевский Ф.М.>")
    private String name;

    public Author() {
    }

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
