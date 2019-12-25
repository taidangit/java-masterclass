package com.timbuchalka;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        FootballPlayer joe = new FootballPlayer("Joe");
        BaseballPlayer pat = new BaseballPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");

        Team<FootballPlayer> adelaideCrows = new Team<>("Adelaide Crows");
        adelaideCrows.addPlayer(joe);
        //adelaideCrows.addPlayer(pat);
        //adelaideCrows.addPlayer(beckham);

        System.out.println(adelaideCrows.numPlayer());

        Team<BaseballPlayer> baseballPlayerTeam = new Team<>("Chicago cubs");
        baseballPlayerTeam.addPlayer(pat);

        Team<SoccerPlayer> soccerPlayerTeam = new Team<>("this won't work");
        soccerPlayerTeam.addPlayer(beckham);


        Team<FootballPlayer> melbourne = new Team<>("Melbourne");
        FootballPlayer banks = new FootballPlayer("Gordon");
        melbourne.addPlayer(banks);

        Team<FootballPlayer> hawthorn = new Team<>("Hawthonrn");
        Team<FootballPlayer> fremantle = new Team<>("Fremantle");

        hawthorn.matchResult(fremantle, 1, 0);

        hawthorn.matchResult(adelaideCrows, 3, 8);

        adelaideCrows.matchResult(fremantle, 2, 1);
        //adelaideCrows.matchResult(baseballPlayerTeam,1,1);

        System.out.println("Ranking");
        System.out.println(adelaideCrows.getName() + ":" + adelaideCrows.ranking());
        System.out.println(hawthorn.getName() + ":" + hawthorn.ranking());
        System.out.println(fremantle.getName() + ":" + fremantle.ranking());

        System.out.println(adelaideCrows.compareTo(melbourne));
        System.out.println(adelaideCrows.compareTo(hawthorn));
        System.out.println(hawthorn.compareTo(adelaideCrows));
        System.out.println(melbourne.compareTo(fremantle));

    }
}
