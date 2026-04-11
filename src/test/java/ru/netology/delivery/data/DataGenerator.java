package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        LocalDate date = LocalDate.now().plusDays(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

    public static String generateCity(Faker faker) {
        String[] cities = {"Москва", "Санкт-Петербург", "Казань", "Новосибирск",
                "Екатеринбург", "Нижний Новгород", "Челябинск", "Самара",
                "Омск", "Ростов-на-Дону", "Уфа", "Красноярск", "Пермь",
                "Воронеж", "Волгоград"};
        Random random = new Random();
        return cities[random.nextInt(cities.length)];
    }

    public static String generateName(Faker faker) {

        String lastName = faker.name().lastName();
        String firstName = faker.name().firstName();
        String fullName = lastName + " " + firstName;

        String[] parts = fullName.split(" ");
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            if (!part.isEmpty()) {
                result.append(part.substring(0, 1).toUpperCase())
                        .append(part.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return result.toString().trim();
    }

    public static String generatePhone(Faker faker) {
        return "+7" + faker.number().digits(10);
    }

    public static class Registration {
        private static Faker faker;

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            faker = new Faker(new Locale(locale));
            String city = generateCity(faker);
            String name = generateName(faker);
            String phone = generatePhone(faker);
            return new UserInfo(city, name, phone);
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}