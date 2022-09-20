package at.kaindorf.access;

import at.kaindorf.pojo.Student;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IOAccess {
    public static List<Student> importData () {
        try {
            return Files.lines(Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "schooldata.csv"), StandardCharsets.UTF_8)
                    .skip(1)
                    .map(Student::new)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
