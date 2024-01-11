import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

// Класс, содержащий основную логику программы
public class ToyShop {
    // Список, хранящий все игрушки в магазине
    private static ArrayList<Toy> toys = new ArrayList<>();

    // Список, хранящий призовые игрушки, ожидающие выдачи
    private static ArrayList<Toy> prizes = new ArrayList<>();

    // Метод, добавляющий новую игрушку в список игрушек
    public static void addToy(int id, String name, int quantity, int frequency) {
        // Создаем объект класса Toy с заданными параметрами
        Toy toy = new Toy(id, name, quantity, frequency);
        // Добавляем объект в список игрушек
        toys.add(toy);
        // Выводим сообщение об успешном добавлении
        System.out.println("Игрушка успешно добавлена: " + toy);
    }

    // Метод, изменяющий частоту выпадения игрушки по ее id
    public static void changeFrequency(int id, int frequency) {
        // Проходим по списку игрушек и ищем игрушку с заданным id
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                // Устанавливаем новую частоту для найденной игрушки
                toy.setFrequency(frequency);
                // Выводим сообщение об успешном изменении
                System.out.println("Частота выпадения игрушки успешно изменена: " + toy);
                // Прерываем цикл
                break;
            }
        }
    }

    // Метод, выбирающий призовую игрушку из списка игрушек с учетом частоты выпадения
    public static Toy choosePrize() {
        // Создаем массив из частот выпадения игрушек
        int[] frequencies = new int[toys.size()];
        // Проходим по списку игрушек и заполняем массив частот
        for (int i = 0; i < toys.size(); i++) {
            frequencies[i] = toys.get(i).getFrequency();
        }
        // Вычисляем сумму всех частот
        int sum = 0;
        for (int f : frequencies) {
            sum += f;
        }
        // Генерируем случайное число от 0 до суммы частот
        Random random = new Random();
        int r = random.nextInt(sum);
        // Проходим по массиву частот и находим индекс, соответствующий случайному числу
        int index = 0;
        int cumulative = 0;
        for (int i = 0; i < frequencies.length; i++) {
            cumulative += frequencies[i];
            if (r < cumulative) {
                index = i;
                break;
            }
        }
        // Возвращаем игрушку с найденным индексом
        return toys.get(index);
    }

    // Метод, организующий розыгрыш игрушек и добавляющий их в список призовых игрушек
    public static void drawPrizes(int n) {
        // Проходим n раз и выбираем призовую игрушку
        for (int i = 0; i < n; i++) {
            // Выбираем призовую игрушку из метода choosePrize
            Toy prize = choosePrize();
            // Добавляем призовую игрушку в список призовых игрушек
            prizes.add(prize);
            // Уменьшаем количество игрушки на 1
            prize.setQuantity(prize.getQuantity() - 1);
            // Выводим сообщение о выбранной призовой игрушке
            System.out.println("Выбрана призовая игрушка: " + prize);
        }
    }

    // Метод, получающий призовую игрушку из списка призовых игрушек и записывающий ее в файл
    public static void getPrize() {
        // Проверяем, что список призовых игрушек не пуст
        if (!prizes.isEmpty()) {
            // Получаем первую игрушку из списка призовых игрушек
            Toy prize = prizes.get(0);
            // Удаляем первую игрушку из списка призовых игрушек
            prizes.remove(0);
            // Создаем объект класса PrintWriter для записи в файл
            try (PrintWriter writer = new PrintWriter(new File("prizes.txt"))) {
                // Записываем информацию о призовой игрушке в файл
                writer.println(prize);
            } catch (IOException e) {
                // Обрабатываем исключение ввода-вывода
                e.printStackTrace();
            }
            // Выводим сообщение о полученной призовой игрушке
            System.out.println("Получена призовая игрушка: " + prize);
        } else {
            // Выводим сообщение о том, что список призовых игрушек пуст
            System.out.println("Нет призовых игрушек для выдачи");
        }
    }

    // Главный метод программы
    public static void main(String[] args) {
        // Добавляем несколько игрушек в список игрушек
        addToy(1, "Конструктор", 10, 20);
        addToy(2, "Робот", 5, 10);
        addToy(3, "Кукла", 15, 30);
        addToy(4, "Машинка", 8, 15);
        addToy(5, "Мяч", 12, 25);

        // Изменяем частоту выпадения игрушки с id 3 на 40
        changeFrequency(3, 40);

        // Организуем розыгрыш 5 игрушек
        drawPrizes(5);

        // Получаем призовую игрушку и записываем ее в файл
        getPrize();
    }
}
