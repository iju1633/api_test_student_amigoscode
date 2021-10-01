package start.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration // 구성을 의미, 여기서 DB에 넣을 data 작성!, class 단위에 작성 가능
public class StudentConfig {

    @Bean // 메서드 단위에만 작성 가능
    CommandLineRunner commandLineRunner(StudentRepository repository) { // application 실행시 초기 코드로써 실행됨
        return args -> { // 방법 익혀두기
            Student mariam = new Student(
                    "Mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    21
            );

            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, Month.JANUARY, 5),
                    21
            );

            repository.saveAll(
                    List.of(mariam, alex)
            ); // 객체를 리스트로 변환해서 매개변수로 집어넣음
        };
    }
}
