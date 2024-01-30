package hw02;

import java.util.Scanner;

public class MainShop {
    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        // Инициализация магазина игрушек
        toyStore.addToy(new Toy(1, "Кукла", 2, 20));
        toyStore.addToy(new Toy(2, "Мяч", 2, 30));
        toyStore.addToy(new Toy(3, "Машинка", 2, 20));
        toyStore.addToy(new Toy(4, "Хряк поролоновый", 2, 15));
        toyStore.addToy(new Toy(5, "Чупа-Чупс", 2, 15));

        Scanner scanner = new Scanner(System.in);

        // Главное меню
        while (true) {
            System.out.println("\nДоступные операции: 1 - Доступные игрушки /2 - Разыграть игрушку /3 - Изменить количество и 'вес' игрушки /4 - добавить новую игрушку /5 - Выход");
            System.out.print("Выберите действие: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Некорректный ввод. Введите число!");
                System.out.print("Выберите действие: ");
                scanner.next();
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    toyStore.printAvailableToys();
                    break;
                case 2:
                    toyStore.play();
                    break;
                case 3:
                    toyStore.editToyById();
                    break;
                case 4:
                    toyStore.addNewToy();
                    break;
                case 5:
                    System.out.println("Гуд Бай!.");
                    System.exit(0);
                default:
                    System.out.println("Недопустимая операция. Попробуйте снова!");
            }
        }
    }
}
