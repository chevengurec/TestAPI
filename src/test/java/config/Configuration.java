package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface Configuration extends Config {
String baseUrl();
String create();
String get();
String getAll();
String patch();
String delete();


}
