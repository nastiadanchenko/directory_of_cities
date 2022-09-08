import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CityUtils {
    final static String FILE_NAME = "src/main/resources/city_ru.csv";

    /**
     * Загрузка данных о городах в массив
     *
     * @return массив с данными о городах
     */
    public static List<City> parse() {
        List<City> cities = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                cities.add(parse(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return cities;
    }

    /**
     * Вывод в консоль массив городов
     *
     * @param cities массив городов
     */
    public static void print(List<City> cities) {
        cities.forEach(System.out::println);
    }

    /**
     * Сортировка массива городов по наименованию города в алфавитном порядке по убыванию
     * без учета регистра
     *
     * @param cities массив городов
     * @return {@link City}
     */
    public static List<City> sortCityByName(List<City> cities) {
        Comparator<City> comparator = (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName());
        cities.sort(comparator);
        return cities;
    }

    /**
     * Сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа
     * в алфавитном порядке по убыванию с учетом регистра
     *
     * @param cities список городов
     * @return {@link City}
     */
    public static List<City> sortCityByDistrictAndName(List<City> cities) {
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
        return cities;
    }

    /**
     * Разбор строки с данными о городе
     *
     * @param line строка с данными
     * @return {@link City}
     */
    private static City parse(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.skip("\\d*");
        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        String foundation = null;
        if (scanner.hasNext()) { // В файле с городами возможно отсутствие данного значения
            foundation = scanner.next();
        }
        scanner.close();

        return new City(name, region, district, population, foundation);
    }


}
