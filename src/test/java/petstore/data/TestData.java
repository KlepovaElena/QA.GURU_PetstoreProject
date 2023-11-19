package petstore.data;

import petstore.utils.RandomUtils;

public class TestData {
    RandomUtils randomUtils = new RandomUtils();

    public String name = randomUtils.getRandomName();
    public String status = randomUtils.getRandomStatus();
}
