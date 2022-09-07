import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    final static String fileName = "src/main/resources/city_ru.csv";
    private static List<City> cities = new ArrayList<>();

    public static void main(String[] args) {
    List<List<String>> records = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                 records.add(getRecordFromLine(scanner.nextLine()));
            }
            parseCSVLine(records);

            System.out.println(cities.toString());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(";");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    private static void parseCSVLine(List<List<String>> lines) {
        City city = new City();
        for (List<String> strings: lines
        ) {
            city.setName(strings.get(1));
            city.setRegion(strings.get(2));
            city.setDistrict(strings.get(3));
            city.setPopulation(Integer.valueOf(strings.get(4)));
            if(strings.size()>=6) {
                city.setFoundation(strings.get(5));
            }
            cities.add(city);

        }
    }
}
