import java.util.ArrayList;
import java.util.Random;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

// Класс, представляющий игрушку с четырьмя полями: id, название, количество и частота выпадения
class Toy {
    private int id;
    private String name;
    private int quantity;
    private int frequency;

    // Конструктор, принимающий четыре параметра и инициализирующий поля класса
    public Toy(int id, String name, int quantity, int frequency) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.frequency = frequency;
    }

    // Геттеры и сеттеры для полей класса
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    // Метод для вывода информации об игрушке в виде строки
    public String toString() {
        return "Toy{id=" + id + ", name='" + name + "', quantity=" + quantity + ", frequency=" + frequency + "}";
    }
}

