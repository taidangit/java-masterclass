package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        League<Team<FootballPlayer>> footballLeague = new League<>("AFL");
        Team<FootballPlayer> adelaideCrows = new Team<>("Adelaide Crows");
        Team<FootballPlayer> melbourne = new Team<>("Melbourne");
        Team<FootballPlayer> ahawthorn = new Team<>("Ahawthorn");
        Team<FootballPlayer> frematle = new Team<>("Frematle");


        ahawthorn.matchResult(frematle, 1, 0);
        ahawthorn.matchResult(adelaideCrows, 3, 8);
        adelaideCrows.matchResult(frematle, 2, 1);

        footballLeague.add(adelaideCrows);
        footballLeague.add(melbourne);
        footballLeague.add(ahawthorn);


        footballLeague.showLeagueTable();

    }
}
