package at.kaindorf.pojo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Student.getAllStudents",query = "SELECT s FROM Student s ORDER BY s.id")
})
public class Student {
    @Id
    @GeneratedValue
    private int id;

    @NonNull
    private String initials;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;

    @NonNull
    private String classname;

    public Student (String line){
        String[] lineData = line.split(";");

        this.initials = lineData[0];
        this.lastname = lineData[2];
        this.firstname = lineData[3];
        this.classname = lineData[4];
    }
}
