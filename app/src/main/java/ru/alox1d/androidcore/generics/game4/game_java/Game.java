package ru.alox1d.androidcore.generics.game4.game_java;

public class Game {
    public static void main(String[] args) {

        Scholar scholar1 = new Scholar("Игорь", 13);
        Scholar scholar2 = new Scholar("Мария", 14);
        Scholar scholar3 = new Scholar("Петр", 12);
        Scholar scholar4 = new Scholar("Александра", 11);

        Student student1 = new Student("Евгений", 19);
        Student student2 = new Student("Сергей", 20);

        Employee employee1 = new Employee("Дмитрий", 31);
        Employee employee2 = new Employee("Алексей", 34);

        Team<Scholar> scholarTeam1 = new Team<>("Везучие");
        scholarTeam1.addNewParticipant(scholar1);
        scholarTeam1.addNewParticipant(scholar2);

        Team<Scholar> scholarTeam2 = new Team<>("Красавчики");
        scholarTeam2.addNewParticipant(scholar3);
        scholarTeam2.addNewParticipant(scholar4);

        Team<Student> studentTeam = new Team<>("Космос");
        studentTeam.addNewParticipant(student1);
        studentTeam.addNewParticipant(student2);

        Team<Employee> employeeTeam = new Team<>("Мечта");
        employeeTeam.addNewParticipant(employee1);
        employeeTeam.addNewParticipant(employee2);

        scholarTeam1.playWith(scholarTeam1);
    }
}
