package petstore.сonfig;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:api.properties")

public interface ApiConfig extends Config {

    @Key("baseUrl")
    String baseUrl();

    @Key("basePath")
    String basePath();
}
