package me.leo.database;

import lombok.RequiredArgsConstructor;
import me.leo.core.Student;
import me.leo.core.StudentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final DbStudentRepository dbStudentRepository;
    private final DbStudentMapper dbStudentMapper;

    @Override
    public Student save(Student student) {
        return dbStudentMapper.toModel(
                dbStudentRepository.save(dbStudentMapper.toEntity(student))
        );
    }

    @Override
    public Optional<Student> findById(long id) {
        return dbStudentRepository.findById(id).map(dbStudentMapper::toModel);
    }

    @Override
    public void deleteById(long id) {
        dbStudentRepository.deleteById(id);
    }
}
