package petstore.utils;

import com.github.javafaker.Faker;

public class RandomUtils {

    Faker faker = new Faker();

    public String getRandomName() {
        return faker.cat().name();
    }
    public String getRandomStatus() {return faker.options().option("available", "pending", "sold");}

}
