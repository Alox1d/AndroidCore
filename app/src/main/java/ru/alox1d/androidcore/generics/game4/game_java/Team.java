package ru.alox1d.androidcore.generics.game4.game_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team<T extends Participant> {
    private String name;
    private List<T> participants = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addNewParticipant(T participant) {
        participants.add(participant);
        System.out.println("В комманду \"" + name + "\" добавлен участник " + participant.getName()
                + ", возраст: " + participant.getAge());
    }

    public void playWith(Team<T> team) {
        Random random = new Random();
        int i = random.nextInt(2);
        String winner = i == 0 ? this.name : team.name;
        System.out.println("Победила комманда \"" + winner + "\"");
    }
}
