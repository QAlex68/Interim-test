package hw02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Класс, представляющий магазин игрушек
class ToyStore {
    private List<Toy> toys; // Список доступных игрушек в магазине
    private List<Toy> prizePool; // Список игрушек, ожидающих выдачу
    private int sum = 0;

    public ToyStore() {
        this.toys = new ArrayList<>();
        this.prizePool = new ArrayList<>();
    }

    // Метод для добавления новой игрушки в магазин
    public void addToy(Toy toy) {
        toys.add(toy);
    }

    // Метод для проведения розыгрыша
    public void play() {

        for (Toy toy : toys) {
            sum += toy.getQuantity();
        }
        if (sum == 0) {
            System.out.println("Все игрушки закончились! Выигрыш невозможен!!");
            System.out.println("Либо добавьте новые либо исправьте количество!!");
            return;
        }
        sum = 0;
        // Вычисляем общую частоту выпадения всех игрушек
        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        // Генерируем случайное значение в диапазоне от 0 до totalWeight
        double randomValue = new Random().nextDouble() * totalWeight;
        for (Toy toy : toys) {
            randomValue -= toy.getWeight();
            if (randomValue <= 0) {
                if (toy.getQuantity() == 0) {
                    System.out.println("Игрушки " + toy.getName() + " Закончились!");
                    break;
                }
                prizePool.add(toy);
                break;
            }
        }
        // Если есть выигранная игрушка, выводим сообщение и записываем в файл
        if (!prizePool.isEmpty()) {
            Toy wonToy = prizePool.remove(0);
            System.out.println("Поздравляем! Вы выиграли игрушку: " + wonToy.getName());
            wonToy.setQuantity(wonToy.getQuantity() - 1);
            // Запись выигранной игрушки в файл
            writeToFile(wonToy);
        } else {
            System.out.println(" Удача любит смелых!! Ничего не выиграно. Выигрыш еще возможен!");
        }
    }

    // Метод для записи выигранной игрушки в файл
    private void writeToFile(Toy toy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt", true))) {
            writer.write("ID: " + toy.getId() + ", Название: " + toy.getName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для вывода доступных игрушек
    public void printAvailableToys() {
        System.out.println("Доступные игрушки:");
        for (Toy toy : toys) {
            System.out.println("ID: " + toy.getId() + ", Название: " + toy.getName() + ", Количество: " + toy.getQuantity() + ", Частота выпадения: " + toy.getWeight() + "%");
        }
        System.out.println();
    }

    // Метод для добавления новой игрушки с автоматическим присвоением id
    public void addNewToy() {
        Scanner scanner = new Scanner(System.in);
        // Используем id последнего элемента списка + 1
        int newId = toys.isEmpty() ? 1 : toys.get(toys.size() - 1).getId() + 1;

        System.out.print("Введите название новой игрушки: ");
        String name = scanner.nextLine();

        System.out.print("Введите количество новой игрушки: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Некорректный ввод. Введите число!");
            System.out.print("Введите новое количество игрушки: ");
            scanner.next();
        }
        int quantity = scanner.nextInt();
        System.out.print("Введите частоту выпадения (вес) новой игрушки: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Некорректный ввод. Введите число!");
            System.out.print("Введите новую частоту выпадения (вес) игрушки: ");
            scanner.next();
        }
        int weight = scanner.nextInt();
        Toy newToy = new Toy(newId, name, quantity, weight);
        toys.add(newToy);
        System.out.println("Новая игрушка успешно добавлена.");
    }

    // Редактируем параметры количество и (вес) частоту выпадения игрушки по Id
    public void editToyById() {
        Scanner scanner = new Scanner(System.in);
        printAvailableToys();
        System.out.print("Введите ID игрушки для изменения параметров: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Некорректный ввод. Введите число!");
            System.out.print("Введите ID игрушки для изменения параметров:");
            scanner.next();
        }
        int id = scanner.nextInt();
        boolean result = false;
        for (Toy toy : toys) {
            if (id == toy.getId()) {
                result = true;
            }
        }
        if (result) {
            System.out.print("Введите новое количество игрушки: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Некорректный ввод. Введите число!");
                System.out.print("Введите новое количество игрушки: ");
                scanner.next();
            }
            int quantity = scanner.nextInt();
            System.out.print("Введите новую частоту выпадения (вес) игрушки: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Некорректный ввод. Введите число!");
                System.out.print("Введите новую частоту выпадения (вес) игрушки: ");
                scanner.next();
            }
            int weight = scanner.nextInt();
            for (Toy toy : toys) {
                if (id == toy.getId()) {
                    toy.setQuantity(quantity);
                    toy.setWeight(weight);
                }
            }
            System.out.println("Игрушка с ID " + id + " успешно отредактирована!");
        } else {
            System.out.println("Игрушки с ID " + id + " не существует!");
        }
    }
}
