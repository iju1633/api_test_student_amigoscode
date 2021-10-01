package start.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // GETMapping 어노테이션이 붙은 클래스를 rest api로 만들어 줌!
@RequestMapping(path= "api/v1/student") // 아무것도 안하면 localhost:8080에 잡힘
public class StudentController { // api에 필요한 모든 리소스들이 들어 있는 곳

    private final StudentService studentService; // Service와 잇기 위해 선언

    @Autowired // final로 설정한 것이 아래 매개변수와 연결됨!(객체 생성해서 Dependency Injection 해줌)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping// 요청 들어오면 해야할 것으로 설정됨^^(이해)
    public List<Student> getStudents() {
        return studentService.getStudents();
    }
}
