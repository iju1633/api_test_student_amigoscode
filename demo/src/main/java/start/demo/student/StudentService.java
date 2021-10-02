package start.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    // POST
    public void addNewStudent(Student student) { // 가져온 student의 메일 확인해서 있으면 추가안하고 없으면 추가하는 로직 구현할 것
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    //DELETE
    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if(!exists){
            throw new IllegalStateException("student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional // 더욱 자세히 알아볼 것 -> 노션 참고
    public void updateStudent(Long studentId, String name, String email) {

        // 받은 id값으로 student가 있으면 객체 생성하고 아님 에러 보내기
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exists"));

        // id로 찾은 학생의 이름이 존재한다면, 받은 이름으로 갱신
        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        // id로 찾은 학생의 이메일이 존재하는데, 받은 메일로 다른 학생이 존재한다면 에러 보내고 아니라면 이메일 갱신하기
        if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }

}
