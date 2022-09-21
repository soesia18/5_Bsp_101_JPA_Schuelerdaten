package at.kaindorf.console;

import at.kaindorf.data.StudentData;
import at.kaindorf.pojo.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int selection;

        do {
            System.out.println("Geben Sie Ihren Modus ein:");
            System.out.println("--------------");
            System.out.println("[1] - Show Data");
            System.out.println("[2] - Delete Student");
            System.out.println("[3] - Insert Student");
            System.out.println("[4] - Change Student");
            System.out.println("[5] - Quit");
            System.out.println("--------------\n");
            Scanner scanner = new Scanner(System.in);

            selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    StudentData.getInstance().getAllStudentsFromDatabase().forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("ID:");
                    int id = scanner.nextInt();

                    Student removeStudent = StudentData.getInstance().findStudentByIdInDB(id);

                    if (removeStudent == null) {
                        System.out.println("Den Student gibt es nicht!");
                    } else {

                        StudentData.getInstance().removeStudent(removeStudent);
                        System.out.println("deleted - " + removeStudent);
                    }
                    break;
                case 3:
                    System.out.println("initials: ");
                    String initials = scanner.next();
                    System.out.println("firstname: ");
                    String firstname = scanner.next();
                    System.out.println("lastname: ");
                    String lastname = scanner.next();
                    System.out.println("classname: ");
                    String classname = scanner.next();

                    Student student = new Student(initials, firstname, lastname, classname);
                    StudentData.getInstance().addStudent(student);
                    break;
                case 4:
                    System.out.println("ID:");
                    int changeId = scanner.nextInt();

                    Student changedStudent = StudentData.getInstance().findStudentByIdInDB(changeId);

                    if (changedStudent == null) {
                        System.out.println("Den Student gibt es nicht!");
                    } else {


                        System.out.println("--------------");
                        System.out.println("Initials: " + changedStudent.getInitials());
                        System.out.println("Firstname: " + changedStudent.getFirstname());
                        System.out.println("Lastname: " + changedStudent.getLastname());
                        System.out.println("Classname: " + changedStudent.getClassname());
                        System.out.println("--------------");

                        System.out.println("ChangedInitials: ");
                        String changedInitials = scanner.next();
                        System.out.println("ChangedFirstname: ");
                        String changedFirstname = scanner.next();
                        System.out.println("ChangedLastname: ");
                        String changedLastname = scanner.next();
                        System.out.println("ChangedClassname: ");
                        String changedClassname = scanner.next();

                        StudentData.getInstance().changeStudent(changedStudent,
                                new Student(changedInitials, changedFirstname, changedLastname, changedClassname));
                    }
                    break;
                default:
            }
        } while (selection != 5);



        StudentData.getInstance().closeEntityManager();
    }

}
