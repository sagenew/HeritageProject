package сoursework.heritage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //аннотация которая обозначает, что это spring boot приложение
//а значит, что программа должна быть запущена как spring boot
@EnableAutoConfiguration//включает автоконфигурацю и тем самым избавляет нас от написания
//дополнительных классов конфигураций
//по желанию можно написать свою конфигурацию. приложение выберет именно Вашу кастомноую конфигурацию

public class HeritageApplication {
    public static void main(String[] args) {
        SpringApplication.run(HeritageApplication.class);   //запуск приложения

    }
}
