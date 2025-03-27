import java.util.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<Address, Integer> costPerAddress = new HashMap<>(Map.of(
            new Address("Санкт-Петербург", "Россия"), 1000,
            new Address("Москва", "Россия"), 800,
            new Address("Токио", "Япония"), 2500,
            new Address("Сиэтл", "США"), 2800,
            new Address("Нью-Йорк", "США"), 2650,
            new Address("Сингапур", "Сингапур"), 2250
    ));
    private static final Set<String> uniqueCountry = new HashSet<>(); // Список уникальных стран в заказах
    public static int totalCostDelivery = 0; // Общая сумма всех доставок в заказах
    private static int userChoice; // Выбор пользователя в меню

    public static void main(String[] args) {
        while (true) {
            menu();
            switch (userChoice) {
                case 1: // Создаем новый заказ на доставку
                    try {
                        formOrder();
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка! Вес - это числовое значение, попробуйте еще раз.");
                    }
                    break;
                case 2: // Показываем сумму всех доставок
                    showCostAllDelivery(0);
                    break;
                case 3: // Показываем количество уникальных стран доставок
                    showUniqueCountries(null, true);
                    break;
                case 4: // Завершаем программу
                    System.out.println("Программа завершена.");
                    return;
                default:
                    System.out.println("Такой команды нет!");
            }
        }
    }

    public static void menu() {
        System.out.print("""
                
                1. Создать новый заказ
                2. Общая сумма всех доставок
                3. Количество уникальных стран для доставки
                4. Завершить программу
                Ваш выбор:\s"""
        );
        try {
            userChoice = Integer.parseInt(sc.nextLine());
            System.out.println();
        } catch (NumberFormatException e) {
            System.out.println("\nОшибка: введите числовое значение от 1 до 4!");
        }
    }

    private static void formOrder() {
        System.out.println("Заполнение нового заказа.");
        System.out.print("Введите страну: ");
        String country = sc.nextLine().trim();
        System.out.print("Введите город: ");
        String city = sc.nextLine().trim();
        System.out.print("Введите вес (кг): ");
        int weight = Integer.parseInt(sc.nextLine());

        Integer cost = costPerAddress.get(new Address(city, country));
        if (cost != null) {
            System.out.println("Стоимость доставки составит: " + cost * weight);
            showCostAllDelivery(cost * weight);
            showUniqueCountries(country, false);
        } else {
            System.out.println("Доставки по этому адресу нет");
        }
    }

    private static void showCostAllDelivery(int currentCost) {
        totalCostDelivery += currentCost;
        System.out.println("Общая стоимость всех доставок: " + totalCostDelivery);
    }

    private static void showUniqueCountries(String country, boolean showNow) {
        if (showNow) {
            System.out.println("Количество уникальных стран, куда отправлен заказ: " + uniqueCountry.size());
        } else {
            uniqueCountry.add(country);
        }
    }
}