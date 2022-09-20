package at.kaindorf.data;

import at.kaindorf.access.IOAccess;
import at.kaindorf.pojo.Student;

import java.util.List;

public class StudentData {
    private static StudentData instance;
    private final List<Student> studentList;

    private StudentData () {
        studentList = IOAccess.importData();
    }

    public static synchronized StudentData getInstance() {
        if (instance == null) {
            instance = new StudentData();
        }
        return instance;
    }

    public Student findStudentById (int id) {
        return studentList.stream().filter(student -> student.getId() == id).findFirst().get();
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
