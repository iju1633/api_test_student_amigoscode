package start.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // @RestController, @Service 붙였듯이^^
public interface StudentRepository extends JpaRepository<Student, Long> { // Data access를 위한 인터페이스!
    // Db와 소통하는 창구, Student라는 class와 DB작업을 할 것이고, primary key인 id의 형식이 Long이니까

    @Query("SELECT s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
