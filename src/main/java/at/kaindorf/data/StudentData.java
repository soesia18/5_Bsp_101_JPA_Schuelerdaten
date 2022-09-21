package at.kaindorf.data;

import at.kaindorf.access.IOAccess;
import at.kaindorf.pojo.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class StudentData {
    private static StudentData instance;
    private final List<Student> studentList;

    private EntityManagerFactory emf;
    private EntityManager em;

    private StudentData () {
        studentList = IOAccess.importData();
        emf = Persistence.createEntityManagerFactory("PU_jpa_schuelerdaten");
        em = emf.createEntityManager();

        studentList.forEach(em::persist);

        em.getTransaction().begin();
        em.getTransaction().commit();
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

    public List<Student> getAllStudentsFromDatabase () {
        commitData();
        return em.createNamedQuery("Student.getAllStudents", Student.class).getResultList();
    }

    public void removeStudent (Student student) {
        em.remove(student);
        studentList.remove(student);
        commitData();
    }

    public void addStudent (Student student) {
        em.persist(student);
        studentList.add(student);
        commitData();
    }

    public void closeEntityManager () {
        em.close();
        emf.close();
    }

    private void commitData() {
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
}
