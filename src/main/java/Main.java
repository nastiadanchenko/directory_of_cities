import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<City> cities = CityUtils.parse();

        CityUtils.print(cities);

        CityUtils.print(CityUtils.sortCityByName(cities));

        CityUtils.print(CityUtils.sortCityByDistrictAndName(cities));

    }
}
