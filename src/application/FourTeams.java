package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////
// Semester: CS400 Spring 2018
// PROJECT: Tournament Bracket GUI
//
// Authors: Steven Wood, Jacob Latts, Ben Schulman, Dylan Breon
//
// Instructor: Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs: No known bugs
//
// 2018 Apr 23, 2018 Four.java
////////////////////////////////////////////////////////////////////////////
/**
 * Generates a scene that represents a tournament bracket of four competing teams.
 */
public class FourTeams extends Scene {

    /**
     * Constructor that generates a GUI for the four teams in the given teams ArrayList.
     * 
     * @param root - BorderPane that is the root of the GUI
     * @param width - width of GUI
     * @param height - height of GUI
     * @param fill - Color to fill as background
     * @param teams - List of all teams participating in tournament. Length should be four.
     */
    public FourTeams(Parent root, double width, double height, Paint fill, ArrayList<Team> teams) {
        super(root, width, height, fill);

     // References to losers of semi-finals games in order to report 3rd place team at end
        Team gameOneLoser = new Team();
        Team gameTwoLoser = new Team();

        // Create GridPane
        GridPane gPane = new GridPane();
        gPane.setGridLinesVisible(false);

        /*
         * The actual content of the scene will be stored in borderPane, which is a BorderPane
         * sitting in the root ScrollPane
         */
        ScrollPane scrollPane = ((ScrollPane) root);
        BorderPane borderPane = new BorderPane();
        scrollPane.setContent(borderPane);
        borderPane.setCenter(gPane);
        gPane.setAlignment(Pos.CENTER);
        gPane.getStyleClass().add("pane");

     // Drop Shadow effect for the "Tournament Bracket" Title
        DropShadow shad = new DropShadow();
        shad.setOffsetY(3.0f);
        shad.setColor(Color.color(0.4f, 0.4f, 0.4f));

        /// Large Title at top of scene
        Text title = new Text("Tournament Bracket");
        title.setId("fancytext");
        title.setEffect(shad);
        borderPane.setTop(title);
        borderPane.setAlignment(title, Pos.CENTER);

     // Instructions, text to show user how to use the bracket
        Label info = new Label();
        info.setText("INSTRUCTIONS:\n-For each game: Enter each team's score then \n   click submit button between the two teams.\n-After completing all games for a round, move\n    on to next round and repeat process to enter teams' scores.\n After submitting the scores for the championship game the \n   top three contenders will be displayed!");
        info.setFont(Font.font("Ariel", 15));
        borderPane.setLeft((info));
        borderPane.setAlignment(info, Pos.CENTER);

        // Column headers for rounds
        // See helper method below
        Text round1 = createRoundHeader("Round 1");
        Text round2 = createRoundHeader("Round 2");


        // Labels for Round 1 Competitors
        // See helper method below
        Label label1 = createTeamLabel(teams, 0);
        Label label2 = createTeamLabel(teams, 3);
        Label label3 = createTeamLabel(teams, 1);
        Label label4 = createTeamLabel(teams, 2);



        // Labels for Round 2 Competitors
        // See helper method below
        Label winner1 = createWinnerLabel("1");
        Label winner2 = createWinnerLabel("2");

     // Champion Label to display Champion of tournament
        Label champ = new Label();
        champ.setMinHeight(25);
        champ.setText("Champion:");

     // runnerUp Label to display runnerUp of tournament
        Label runnerUp = new Label();
        runnerUp.setMinHeight(25);
        runnerUp.setText("Runner Up:");

     // Third place Label to display runnerUp of tournament
        Label thirdPlace = new Label();
        thirdPlace.setMinHeight(25);
        thirdPlace.setText("Third: ");


        // Creating Empty Labels for spacing purposes
        Label empty1 = new Label();
        empty1.setMinHeight(200);
        empty1.setMinWidth(50);
        empty1.setText(" ");
        Label empty2 = new Label();
        empty2.setMinHeight(200);
        empty2.setMinWidth(100);
        empty2.setText(" ");
        Label empty3 = new Label();
        empty3.setMinHeight(200);
        empty3.setMinWidth(100);
        empty3.setText(" ");
        Label empty4 = new Label();
        empty4.setMaxHeight(300);
        empty4.setText(" ");

        // Round 1 Score Input fields creation.
        // See helper method below
        TextField round1Score1 = createScoreInput("1");
        TextField round1Score2 = createScoreInput("2");
        TextField round1Score3 = createScoreInput("3");
        TextField round1Score4 = createScoreInput("4");
        
     // Enabling the score inputs for round 1 since, by default, the input labels are disabled
        round1Score1.setDisable(false);
        round1Score2.setDisable(false);
        round1Score3.setDisable(false);
        round1Score4.setDisable(false);

        // Round 2 Score Input fields creation.
        // See helper method below
        TextField round2Score1 = createScoreInput("1");
        TextField round2Score2 = createScoreInput("2");

        // Creating Round 1 Submit Buttons
        // See helper method below
        Button submit1 = createSubmitButton(teams, gameOneLoser, round2Score1, winner1, "1",
                        round1Score1, round1Score2, 0, 3, champ, runnerUp);
        Button submit2 = createSubmitButton(teams, gameTwoLoser, round2Score2, winner2, "2",
                        round1Score3, round1Score4, 1, 2, champ, runnerUp);

        // Create Championship Game Submit Button
        Button submit3 = new Button();
        submit3.setText("Submit Score");
        submit3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    champ.setText("Champion: ");
                    runnerUp.setText("Runner Up: ");

                    int team1score = Integer.parseInt(round2Score1.getText().trim());
                    int team2score = Integer.parseInt(round2Score2.getText().trim());

                    if (team1score > team2score) {
                        champ.setText("Champion: " + winner1.getText());
                        runnerUp.setText("Runner Up: " + winner2.getText());
                    } else if (team1score < team2score) {
                        champ.setText("Champion: " + winner2.getText());
                        runnerUp.setText("Runner Up: " + winner1.getText());
                    } else {
                        System.out.println("Teams may not have the same score");
                    }

                    if (gameOneLoser.getTeamScore() > gameTwoLoser.getTeamScore())
                        thirdPlace.setText("Third: " + gameOneLoser.getTeamName());
                    else
                        thirdPlace.setText("Third: " + gameTwoLoser.getTeamName());

                } catch (NumberFormatException e) {
                    System.out.println("Invalid Score");
                }

            }
        });
        
        
      //Adding column headers for each round to grid pane
        gPane.add(round1, 0, 0);
        gPane.add(round2, 3, 0);

        gPane.add(empty4, 0, 1, 7, 1);

        //Adding round one team labels to grid pane
        gPane.add(label1, 0, 2);
        gPane.add(label2, 0, 4);
        gPane.add(label3, 0, 8);
        gPane.add(label4, 0, 10);

        gPane.add(empty1, 0, 6);
        gPane.add(empty2, 2, 2, 1, 7);
        gPane.add(empty3, 5, 2, 1, 7);

     // Adding round 1 submit buttons to grid pane
        gPane.add(submit1, 0, 3, 2, 1);
        gPane.add(submit2, 0, 9, 2, 1);
        
     // Adding championship submit button to grid pane
        gPane.add(submit3, 3, 6, 2, 1);

        // Adding round one input fields to grid pane
        gPane.add(round1Score1, 1, 2);
        gPane.add(round1Score2, 1, 4);
        gPane.add(round1Score3, 1, 8);
        gPane.add(round1Score4, 1, 10);

     // Adding round 2 (championship) team labels to grid pane
        gPane.add(winner1, 3, 3);
        gPane.add(winner2, 3, 9);

     // Adding round 2 (championship) input fields to grid pane
        gPane.add(round2Score1, 4, 3);
        gPane.add(round2Score2, 4, 9);

        // Adding labels to display champion, runner up, and third place teams at end
        gPane.add(champ, 6, 6);
        gPane.add(runnerUp, 6, 11);
        gPane.add(thirdPlace, 6, 12);
    }

    /*
     * Used to created the Underlined round headers above each round
     * 
     * @param roundName Name of round
     * 
     * @returns round The created Text
     */
    private Text createRoundHeader(String roundName) {
        Text round = new Text(roundName);
        round.setId("rounds");
        round.maxHeight(15);
        return round;
    }

    /*
     * Used to create labels to assign teams to locations in Bracket
     * 
     * @param teams ArrayList of teams
     * 
     * @param teamIndex The index of the team in teams list
     * 
     * @returns winner The created Label
     */
    private Label createTeamLabel(ArrayList<Team> teams, int teamIndex) {
        Label label = new Label();
        label.setPrefHeight(15);
        label.setText(teams.get(teamIndex).getTeamName() + ": ");
        return label;
    }

    /*
     * Used to create labels for game that don't yet have a contestant assigned to them.
     * 
     * @param winnerNumber Numbering of winner labels to be displayed in UI
     * 
     * @returns winner The created Label
     */
    private Label createWinnerLabel(String winnerNumber) {
        Label winner = new Label();
        winner.setPrefHeight(15);
        winner.setText("Winner " + winnerNumber + ": ");
        return winner;
    }

    /*
     * Used to create Text fields for score input. Sets size, Prompt text, etc.
     * 
     * @param scoreNumber Numbering of text fields to be displayed in UI
     * 
     * @returns input The created TextField
     */
    private TextField createScoreInput(String scoreNumber) {
        TextField input = new TextField();
        input.setPrefHeight(15);
        input.setMaxWidth(200);
        input.setPromptText("Score " + scoreNumber);
        input.setFocusTraversable(false);
        input.setDisable(true);
        return input;
    }

    /**
     * Used to create the submit buttons for each of the games in Round 1. Overrides handle method
     * of Button in order to correctly handle the user provided scores. Compares the values entered
     * in score fields and updates the correct team label for next round.
     * 
     * Begins by resetting the labels of any future games in the current teams path. Added so that
     * it resets future game labels if user changes outcome of previous games after already moving
     * further in bracket.
     * 
     * @param teams ArrayList of all teams
     * @param nextTextField Text field associated with the winner of this game, used to enable field
     *        when scores are submitted.
     * @param winner The winner of this game
     * @param winnerNum The number of the winner label. To reset winner label
     * @param nextWinner The winner of next rounds game. Used to reset label
     * @param nextwinnerNum Number of the winner of next rounds game. Used to reset label
     * @param futureWinner The winner of the game 2 rounds from now. Used to reset label
     * @param futureWinnerNum Number of the winner of game 2 rounds from now. Used to reset label
     * @param score1 Score of first team in this game
     * @param score2 Score of second team in this game
     * @param team1Index Index of first team in teams list
     * @param team2Index Index of second team in teams list
     * @param champion The label for champion of the tournament
     * @param runnerUp The label runner up in the tournament
     * @return submit The created Button
     */
    private Button createSubmitButton(ArrayList<Team> teams, Team loser, TextField nextTextField,
                    Label winner, String winnerNum, TextField score1, TextField score2,
                    int team1Index, int team2Index, Label champion, Label runnerUp) {
        Button submit = new Button();
        submit.setText("Submit Score");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Resetting labels of future games in these teams' subsequent path
                    champion.setText("Champion: ");
                    runnerUp.setText("Runner Up: ");
                    winner.setText("Winner " + winnerNum + ": ");


                    // Getting score results from text fields
                    int team1Score = Integer.parseInt(score1.getText().trim());
                    int team2Score = Integer.parseInt(score2.getText().trim());

                    if (team1Score < 0 || team2Score < 0) { // Ensuring score input is non-negative
                        System.out.println("Invalid Score: Scores must be positive");
                    } else if (team1Score > team2Score) {
                        winner.setText(teams.get(team1Index).getTeamName()); // Updating winner
                        nextTextField.setDisable(false);
                        loser.setTeamName(teams.get(team2Index).getTeamName()); // Updating loser
                                                                                // reference
                        loser.setTeamScore(team2Score);
                    } else if (team1Score < team2Score) {
                        winner.setText(teams.get(team2Index).getTeamName());
                        nextTextField.setDisable(false);
                        loser.setTeamName(teams.get(team1Index).getTeamName()); // Updating loser
                                                                                // reference
                        loser.setTeamScore(team1Score);
                    } else {
                        System.out.println("Teams may not have the same score");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid Score");
                }

            }
        });
        return submit;
    }
}
