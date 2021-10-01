package start.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service // @Component해도 됨, API layer와 Service를 잇는 방법 : @Autowired, @Service, @Component
//controller에서 객체가 생성될 때 어디를 참고할 지를 알려주도록 만든 annotation이 @Service와 @Component
public class StudentService { // 핵심 로직인 있는 곳

    private final StudentRepository studentRepository;

    @Autowired // final 값 가져와서 DI, 객체를 생성해서 매개변수로 넣어주는 역할!!!
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {

        return studentRepository.findAll();
        // StudentRepository interface가 JpaRepository를 extend하기에 여러 메서드 사용 가능!
    }
}
