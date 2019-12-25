package com.timbuchalka;

public class Main {

    public static void main(String[] args) {
        boolean gameOver = true;
        int score = 800;
        int levelComplted = 5;
        int bonus = 100;

        int highScore = calculteScore(gameOver, score, levelComplted, bonus);
        System.out.println("Your final score was " + highScore);

        score = 10000;
        levelComplted = 8;
        bonus = 200;

        highScore = calculteScore(gameOver, score, levelComplted, bonus);
        System.out.println("Your final score was " + highScore);

        int highSocrePosition = calculateHighScorePosition(1500);
        displayHighScorePosition("Tai", highSocrePosition);

        highSocrePosition = calculateHighScorePosition(900);
        displayHighScorePosition("Tien", highSocrePosition);

        highSocrePosition = calculateHighScorePosition(400);
        displayHighScorePosition("Linh", highSocrePosition);

        highSocrePosition = calculateHighScorePosition(50);
        displayHighScorePosition("Minh", highSocrePosition);
    }

    public static void displayHighScorePosition(String playName, int highSocrePosition) {
        System.out.println(playName + " managed to get into position " + highSocrePosition + " on the high score table");
    }

    public static int calculateHighScorePosition(int playerScore) {
       /* if (playerScore >= 1000) {
            return 1;
        } else if (playerScore >= 500 && playerScore < 1000) {
            return 2;
        } else if (playerScore >= 100 && playerScore < 500) {
            return 3;
        }
        return 4;*/

        int position = 4;
        if (playerScore >= 1000) {
            position = 1;
        } else if (playerScore >= 500) {
            position = 2;
        } else if (playerScore >= 100) {
            position = 3;
        }

        return position;
    }

    public static int calculteScore(boolean gameOver, int score, int levelComplted, int bonus) {
        if (gameOver) {
            int finalScore = score + (levelComplted * bonus);
            finalScore += 1000;
            return finalScore;
        } else {
            return -1;
        }
    }
}
