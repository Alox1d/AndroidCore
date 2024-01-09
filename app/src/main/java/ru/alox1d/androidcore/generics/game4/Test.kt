package generics.game4


fun main() {
    // Members
    val scholar1 = Schoolar("Игорь", 13)
    val scholar2 = Schoolar("Мария", 14)
    val scholar3 = Schoolar("Петр", 12)
    val scholar4 = Schoolar("Александра", 11)

    val student1 = Student("Евгений", 19)
    val student2 = Student("Сергей", 20)

    val employee1 = Employee("Дмитрий", 31)
    val employee2 = Employee("Алексей", 34)

    // NO generic usage
//    val scholarTeam1 = Team("Везучие")
//    scholarTeam1.addNewParticipant(scholar1)
//    scholarTeam1.addNewParticipant(student1)
//    scholarTeam1.addNewParticipant(employee1)

    // Teams
    val schoolTeam = Team<Schoolar>("SchoolTeam")
    schoolTeam.addNewParticipant(scholar1)
    val studTeam = Team<Student>("StudentTeam")
    studTeam.addNewParticipant(student1)
    val empTeam = Team<Employee>("EmpTeam")
    empTeam.addNewParticipant(employee2)
    val schoolTeam2 = Team<Schoolar>("SchoolTeam2")
    schoolTeam.addNewParticipant(scholar2)

    // Plays
    schoolTeam.playWith(schoolTeam2)
    //NO - scholar can't play with students
//    schoolTeam.playWith(studTeam)


}