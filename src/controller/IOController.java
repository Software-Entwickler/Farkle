package controller;

import model.Player;
import view.HighScoreViewController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class IOController {

    private FarkleController farkleController;

    private final ArrayList<HighScoreViewController.PlayerScoreTableCell>
            playerScoreTableCellArrayList = new ArrayList<>();

    public IOController(FarkleController farkleController) {

        this.farkleController = farkleController;

        BufferedReader reader;

        try {

            FileReader fileReader = new FileReader("PlayerScore.txt");
            reader = new BufferedReader(fileReader);

            String line = reader.readLine();
            while (line != null) {
                String [] array = line.split("\\$");
                playerScoreTableCellArrayList.add(new HighScoreViewController.PlayerScoreTableCell(array[0], Integer.parseInt(array[1])));
                line = reader.readLine();
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("There is no data in the game! ");
        }


    }

    public ArrayList<HighScoreViewController.PlayerScoreTableCell> getPlayerScoreTableCells() {

        return playerScoreTableCellArrayList;

    }

    public void setPlayerScoreTableCells() {

        ArrayList<HighScoreViewController.PlayerScoreTableCell> playerScoreCells = new ArrayList<>();

        ArrayList<HighScoreViewController.PlayerScoreTableCell> playerScores = new ArrayList<>(playerScoreTableCellArrayList);

        boolean isTheEnd = farkleController.getRoundController()
                                .isEndOfGame(farkleController.getFarkle().getCurrentGame().getCurrentRound());


        if (isTheEnd) {

            ArrayList<Player> players = farkleController.getFarkle().getCurrentGame().getPlayers();

            for (Player player : players) {
                for (HighScoreViewController.PlayerScoreTableCell playerScoreTableCell : playerScoreTableCellArrayList) {
                    if (player.getUserName().equals(playerScoreTableCell.getName())
                            && player.getScore()> playerScoreTableCell.getScore()) {
                        playerScores.remove(playerScoreTableCell);
                        playerScores.add(new HighScoreViewController
                                .PlayerScoreTableCell(player.getUserName(), player.getScore()));
                    }
                }
            }

            for (Player player : players) {

                boolean pre = true;
                for (HighScoreViewController.PlayerScoreTableCell playerScoreTableCell : playerScores) {
                    if (player.getUserName().equals(playerScoreTableCell.getName())) {
                        pre = false;
                    }
                }

                if (pre)
                    playerScores.add(new HighScoreViewController
                            .PlayerScoreTableCell(player.getUserName(), player.getScore()));

            }


            playerScores.sort(Comparator.comparing(HighScoreViewController.PlayerScoreTableCell::getScore));
            Collections.reverse(playerScores);

            if (playerScores.size()>10) {
                for (int i = 0; i < 10; i++) {
                    playerScoreCells.add(playerScores.get(i));
                }
            } else {
                playerScoreCells = playerScores;
            }

        }

        String str;
        if (playerScoreCells.size()==0) {
            str = playerCellsToString(playerScores);
        } else {
            str = playerCellsToString(playerScoreCells);
        }

        try {

            FileWriter fileWriter = new FileWriter("PlayerScore.txt");

            for (int i = 0; i < str.length(); i++)
                fileWriter.write(str.charAt(i));

            fileWriter.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private String playerCellsToString (ArrayList<HighScoreViewController.PlayerScoreTableCell> players) {

        StringBuilder result = new StringBuilder();

        for (HighScoreViewController.PlayerScoreTableCell player : players) {
            result.append(player.getName()).append("$").append(player.getScore()).append("\n");
        }

        return result.toString();
    }

}
