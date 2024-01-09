package generics.game4

import kotlin.random.Random

class Team<T : Participant>(val name: String) {
    private val participants: MutableList<T> = mutableListOf()

    fun addNewParticipant(participant: T) {
        participants.add(participant)
        println("In team $name added a member ${participant.name}")
    }

    fun playWith(team: Team<T>) {
        val i = Random.Default.nextInt(2)
        val winnerName = if (i == 0) name else team.name
        println("Team $winnerName has won")
    }
}