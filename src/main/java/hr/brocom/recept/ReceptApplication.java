package hr.brocom.recept;

import hr.brocom.recept.ygo_poc.YgoPoc;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReceptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReceptApplication.class, args);
        YgoPoc.print();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
