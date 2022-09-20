package at.kaindorf.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
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
