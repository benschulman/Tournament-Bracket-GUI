package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.awt.ScrollPane;
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
// 2018 Apr 23, 2018 Sixteen.java
////////////////////////////////////////////////////////////////////////////

/**
 * Generates a scene that represents a tournament bracket of two competing teams.
 */
public class SixteenTeams extends Scene {

    /**
     * Constructor that generates a GUI for the four teams in the given teams ArrayList.
     * 
     * @param root - BorderPane that is the root of the GUI
     * @param width - width of GUI
     * @param height - height of GUI
     * @param fill - Color to fill as background
     * @param teams - List of all teams participating in tournament. Length should be four.
     */
    public SixteenTeams(Parent root, double width, double height, Paint fill,
                    ArrayList<Team> teams) {
        super(root, width, height, fill);

        // Referenes to losers of semi-finals games in order to report 3rd place team at end
        Team gameOneLoser = new Team();
        Team gameTwoLoser = new Team();

        //Creating grid pane
        GridPane gPane = new GridPane();
        gPane.getRowConstraints().add(new RowConstraints(15));
        gPane.setGridLinesVisible(false);
        BorderPane borderPane = ((BorderPane) root);
        borderPane.setCenter(gPane);
        gPane.setAlignment(Pos.CENTER);
        gPane.getStyleClass().add("pane");

        // Drop Shadow effect for the "Tournament Bracket" Title
        DropShadow shad = new DropShadow();
        shad.setOffsetY(3.0f);
        shad.setColor(Color.color(0.4f, 0.4f, 0.4f));

        // Large Title at top of scene
        Text title = new Text("Tournament Bracket");
        title.setId("fancytext"); // gradient coloring effect from blue to black
        title.setEffect(shad); // adding the above dropshadow effect
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
        Text round3 = createRoundHeader("Round 3");
        Text round4 = createRoundHeader("Round 4");

        // Labels for Round 1 Competitors
        // See helper method below
        Label label1 = createTeamLabel(teams, 0);
        Label label2 = createTeamLabel(teams, 15);
        Label label3 = createTeamLabel(teams, 7);
        Label label4 = createTeamLabel(teams, 8);
        Label label5 = createTeamLabel(teams, 3);
        Label label6 = createTeamLabel(teams, 12);
        Label label7 = createTeamLabel(teams, 4);
        Label label8 = createTeamLabel(teams, 11);
        Label label9 = createTeamLabel(teams, 1);
        Label label10 = createTeamLabel(teams, 14);
        Label label11 = createTeamLabel(teams, 6);
        Label label12 = createTeamLabel(teams, 9);
        Label label13 = createTeamLabel(teams, 2);
        Label label14 = createTeamLabel(teams, 13);
        Label label15 = createTeamLabel(teams, 5);
        Label label16 = createTeamLabel(teams, 10);


        // Labels for Round 2 Competitors
        // See helper method below
        Label winner1 = createWinnerLabel("1");
        Label winner2 = createWinnerLabel("2");
        Label winner3 = createWinnerLabel("3");
        Label winner4 = createWinnerLabel("4");
        Label winner5 = createWinnerLabel("5");
        Label winner6 = createWinnerLabel("6");
        Label winner7 = createWinnerLabel("7");
        Label winner8 = createWinnerLabel("8");


        // Labels for Round 3 Competitors
        // See helper method below
        Label winner9 = createWinnerLabel("9");
        Label winner10 = createWinnerLabel("10");
        Label winner11 = createWinnerLabel("11");
        Label winner12 = createWinnerLabel("12");


        // Championship Game Competitors
        // See helper method below
        Label winner13 = createWinnerLabel("13");
        Label winner14 = createWinnerLabel("14");


        // Champion Label to display Champion of tournament
        Label champ = new Label();
        champ.setPrefHeight(15);
        champ.setText("Champion:");

        // runnerUp Label to display runnerUp of tournament
        Label runnerUp = new Label();
        runnerUp.setPrefHeight(15);
        runnerUp.setText("Runner Up:");

        // Third place Label to display runnerUp of tournament
        Label thirdPlace = new Label();
        thirdPlace.setMinHeight(25);
        thirdPlace.setText("Third: ");

        // Round 1 Score Input fields creation.
        // See helper method below
        TextField round1Score1 = createScoreInput("1");
        TextField round1Score2 = createScoreInput("2");
        TextField round1Score3 = createScoreInput("3");
        TextField round1Score4 = createScoreInput("4");
        TextField round1Score5 = createScoreInput("5");
        TextField round1Score6 = createScoreInput("6");
        TextField round1Score7 = createScoreInput("7");
        TextField round1Score8 = createScoreInput("8");
        TextField round1Score9 = createScoreInput("9");
        TextField round1Score10 = createScoreInput("10");
        TextField round1Score11 = createScoreInput("11");
        TextField round1Score12 = createScoreInput("12");
        TextField round1Score13 = createScoreInput("13");
        TextField round1Score14 = createScoreInput("14");
        TextField round1Score15 = createScoreInput("15");
        TextField round1Score16 = createScoreInput("16");

        // Enabling the score inputs for round 1 since, by default, the input labels are disabled
        round1Score1.setDisable(false);
        round1Score2.setDisable(false);
        round1Score3.setDisable(false);
        round1Score4.setDisable(false);
        round1Score5.setDisable(false);
        round1Score6.setDisable(false);
        round1Score7.setDisable(false);
        round1Score8.setDisable(false);
        round1Score9.setDisable(false);
        round1Score10.setDisable(false);
        round1Score11.setDisable(false);
        round1Score12.setDisable(false);
        round1Score13.setDisable(false);
        round1Score14.setDisable(false);
        round1Score15.setDisable(false);
        round1Score16.setDisable(false);



        // Round 2 Score Input fields creation.
        // See helper method below
        TextField round2Score1 = createScoreInput("1");
        TextField round2Score2 = createScoreInput("2");
        TextField round2Score3 = createScoreInput("3");
        TextField round2Score4 = createScoreInput("4");
        TextField round2Score5 = createScoreInput("5");
        TextField round2Score6 = createScoreInput("6");
        TextField round2Score7 = createScoreInput("7");
        TextField round2Score8 = createScoreInput("8");

        // Round 3 Score Input fields creation.
        // See helper method below
        TextField round3Score1 = createScoreInput("1");
        TextField round3Score2 = createScoreInput("2");
        TextField round3Score3 = createScoreInput("3");
        TextField round3Score4 = createScoreInput("4");

        // Round 4 Score Input fields creation.
        // See helper method below
        TextField round4Score1 = createScoreInput("1");
        TextField round4Score2 = createScoreInput("2");



        // Creating Round 1 Submit Buttons
        // See helper method below
        Button submit1 = createSubmitButton(teams, round2Score1, winner1, "1", winner9, "9",
                        winner13, "13", round1Score1, round1Score2, 0, 15, champ, runnerUp);
        Button submit2 = createSubmitButton(teams, round2Score2,winner2,  "2", winner9, "9",
                        winner13, "13", round1Score3, round1Score4, 7, 8, champ, runnerUp);
        Button submit3 = createSubmitButton(teams,round2Score3, winner3,  "3", winner10, "10",
                        winner13, "13", round1Score5, round1Score6, 3, 12, champ, runnerUp);
        Button submit4 = createSubmitButton(teams,round2Score4, winner4,  "4", winner10, "10",
                        winner13, "13", round1Score7, round1Score8, 4, 11, champ, runnerUp);
        Button submit5 = createSubmitButton(teams,round2Score5, winner5,  "1", winner11, "11",
                        winner14, "14", round1Score9, round1Score10, 1, 14, champ, runnerUp);
        Button submit6 = createSubmitButton(teams, round2Score6, winner6, "1", winner11, "11",
                        winner14, "14", round1Score11, round1Score12, 6, 9, champ, runnerUp);
        Button submit7 = createSubmitButton(teams,round2Score7, winner7,  "7", winner12, "12",
                        winner14, "14", round1Score13, round1Score14, 2, 13, champ, runnerUp);
        Button submit8 = createSubmitButton(teams,round2Score8, winner8,  "8", winner12, "12",
                        winner14, "14", round1Score15, round1Score16, 5, 10, champ, runnerUp);


        // Creating Round 2 Submit Buttons
        // See helper method below
        Button submit9 = createRnd2SubmitButton(teams, round3Score1, winner9, "9", winner13, "13",
                        round2Score1, round2Score2, winner1, winner2, champ, runnerUp);

        Button submit10 = createRnd2SubmitButton(teams, round3Score2, winner10, "10", winner13,
                        "13", round2Score3, round2Score4, winner3, winner4, champ, runnerUp);

        Button submit11 = createRnd2SubmitButton(teams, round3Score3, winner11, "11", winner14,
                        "14", round2Score5, round2Score6, winner5, winner6, champ, runnerUp);

        Button submit12 = createRnd2SubmitButton(teams, round3Score4, winner12, "12", winner14,
                        "14", round2Score7, round2Score8, winner7, winner8, champ, runnerUp);


        // Creating Round 3 Submit Buttons
        Button submit13 = createRnd3SubmitButton(gameOneLoser, winner9, winner10, winner13,
                        round4Score1, "13", round3Score1, round3Score2, champ, runnerUp);
        Button submit14 = createRnd3SubmitButton(gameTwoLoser, winner11, winner12, winner14,
                        round4Score2, "14", round3Score3, round3Score4, champ, runnerUp);



        // Create Championship Game Submit Button
        Button submit15 = new Button();
        submit15.setText("Submit Score");
        submit15.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    champ.setText("Champion: ");
                    runnerUp.setText("Runner Up: ");

                    int team1score = Integer.parseInt(round4Score1.getText().trim());
                    int team2score = Integer.parseInt(round4Score2.getText().trim());

                    if (team1score > team2score) {
                        champ.setText("Champion: " + winner13.getText());
                        runnerUp.setText("Runner Up: " + winner14.getText());
                    } else if (team1score < team2score) {
                        champ.setText("Champion: " + winner14.getText());
                        runnerUp.setText("Runner Up: " + winner13.getText());
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

        // Empty labels for spacing purposes
        Label emptyRow1 = new Label(" ");
        Label emptyCol1 = new Label(" ");
        Label emptyCol2 = new Label(" ");
        Label emptyCol3 = new Label(" ");
        emptyCol1.setMinWidth(20);
        emptyCol2.setMinWidth(20);
        emptyCol3.setMinWidth(20);

        gPane.add(round1, 0, 0);
        gPane.add(round2, 3, 0);
        gPane.add(round3, 6, 0);
        gPane.add(round4, 9, 0);

        gPane.add(emptyRow1, 0, 1, 12, 1);

        // Adding round one team labels to grid pane
        gPane.add(label1, 0, 2);
        gPane.add(label2, 0, 4);
        gPane.add(label3, 0, 6);
        gPane.add(label4, 0, 8);
        gPane.add(label5, 0, 10);
        gPane.add(label6, 0, 12);
        gPane.add(label7, 0, 14);
        gPane.add(label8, 0, 16);
        gPane.add(label9, 0, 18);
        gPane.add(label10, 0, 20);
        gPane.add(label11, 0, 22);
        gPane.add(label12, 0, 24);
        gPane.add(label13, 0, 26);
        gPane.add(label14, 0, 28);
        gPane.add(label15, 0, 30);
        gPane.add(label16, 0, 32);

        // Adding round one input fields to grid pane
        gPane.add(round1Score1, 1, 2);
        gPane.add(round1Score2, 1, 4);
        gPane.add(round1Score3, 1, 6);
        gPane.add(round1Score4, 1, 8);
        gPane.add(round1Score5, 1, 10);
        gPane.add(round1Score6, 1, 12);
        gPane.add(round1Score7, 1, 14);
        gPane.add(round1Score8, 1, 16);
        gPane.add(round1Score9, 1, 18);
        gPane.add(round1Score10, 1, 20);
        gPane.add(round1Score11, 1, 22);
        gPane.add(round1Score12, 1, 24);
        gPane.add(round1Score13, 1, 26);
        gPane.add(round1Score14, 1, 28);
        gPane.add(round1Score15, 1, 30);
        gPane.add(round1Score16, 1, 32);

        gPane.add(emptyCol1, 2, 0, 1, 32);

        // Adding round 2 team labels to grid pane
        gPane.add(winner1, 3, 3);
        gPane.add(winner2, 3, 7);
        gPane.add(winner3, 3, 11);
        gPane.add(winner4, 3, 15);
        gPane.add(winner5, 3, 19);
        gPane.add(winner6, 3, 23);
        gPane.add(winner7, 3, 27);
        gPane.add(winner8, 3, 31);

        // Adding round 2 input fields to grid pane
        gPane.add(round2Score1, 4, 3);
        gPane.add(round2Score2, 4, 7);
        gPane.add(round2Score3, 4, 11);
        gPane.add(round2Score4, 4, 15);
        gPane.add(round2Score5, 4, 19);
        gPane.add(round2Score6, 4, 23);
        gPane.add(round2Score7, 4, 27);
        gPane.add(round2Score8, 4, 31);

        gPane.add(emptyCol2, 5, 0, 1, 32);

        // Adding round 3 team labels to grid pane
        gPane.add(winner9, 6, 5);
        gPane.add(winner10, 6, 13);
        gPane.add(winner11, 6, 21);
        gPane.add(winner12, 6, 29);

        // Adding round 3 input fields to grid pane
        gPane.add(round3Score1, 7, 5);
        gPane.add(round3Score2, 7, 13);
        gPane.add(round3Score3, 7, 21);
        gPane.add(round3Score4, 7, 29);

        gPane.add(emptyCol3, 8, 0, 1, 32);

        // Adding semi final team labels to grid pane
        gPane.add(winner13, 9, 9);
        gPane.add(winner14, 9, 25);

        // Adding semi final input fields to grid pane
        gPane.add(round4Score1, 10, 9);
        gPane.add(round4Score2, 10, 25);

        // Adding labels to display champion, runner up, and third place teams at end
        gPane.add(champ, 12, 17);
        gPane.add(runnerUp, 12, 31);
        gPane.add(thirdPlace, 12, 32);

        // Adding the submit buttons
        gPane.add(submit1, 0, 3, 2, 1);
        gPane.add(submit2, 0, 7, 2, 1);
        gPane.add(submit3, 0, 11, 2, 1);
        gPane.add(submit4, 0, 15, 2, 1);
        gPane.add(submit5, 0, 19, 2, 1);
        gPane.add(submit6, 0, 23, 2, 1);
        gPane.add(submit7, 0, 27, 2, 1);
        gPane.add(submit8, 0, 31, 2, 1);
        gPane.add(submit9, 3, 5, 2, 1);
        gPane.add(submit10, 3, 13, 2, 1);
        gPane.add(submit11, 3, 21, 2, 1);
        gPane.add(submit12, 3, 29, 2, 1);
        gPane.add(submit13, 6, 9, 2, 1);
        gPane.add(submit14, 6, 25, 2, 1);
        gPane.add(submit15, 9, 17, 2, 1);


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
     * @param nextTextField Text field associated with the winner of this game, used to enable field when scores are submitted.
     * @param winner The winner of this game
     * @param winnerNum The number of the winner label. To reset winner label
     * @param nextWinner The winner of next rounds game. Used to reset label 
     * @param nextwinnerNum Number of the winner of next rounds game. Used to reset label   
     * @param futureWinner The winner of the game 2 rounds from now. Used to reset label
     * @param futureWinnerNum Number of the winner of game 2 rounds from now.  Used to reset label 
     * @param score1 Score of first team in this game
     * @param score2 Score of second team in this game
     * @param team1Index Index of first team in teams list
     * @param team2Index Index of second team in teams list
     * @param champion The label for champion of the tournament
     * @param runnerUp The label runner up in the tournament
     * @return submit The created Button
     */
    private Button createSubmitButton(ArrayList<Team> teams, TextField nextTextField, Label winner, 
                    String winnerNum, Label nextWinner, String nextWinnerNum, Label futureWinner,
                    String futureWinnerNum, TextField score1, TextField score2, int team1Index,
                    int team2Index, Label champion, Label runnerUp) {
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
                    nextWinner.setText("Winner " + nextWinnerNum + ": ");
                    futureWinner.setText("Winner " + futureWinnerNum + ": ");

                 // Getting score results from text fields
                    int team1Score = Integer.parseInt(score1.getText().trim());
                    int team2Score = Integer.parseInt(score2.getText().trim());

                    if (team1Score < 0 || team2Score < 0) { // Ensuring score input is non-negative
                        System.out.println("Invalid Score: Scores must be positive");
                    } else if (team1Score > team2Score) {
                        winner.setText(teams.get(team1Index).getTeamName()); // Updating winner label to winning
                        // team
                        nextTextField.setDisable(false);
                    } else if (team1Score < team2Score) {
                        winner.setText(teams.get(team2Index).getTeamName());
                        nextTextField.setDisable(false);
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

    /**
     * Used to create the submit buttons for each of the games in Round 2. Overrides handle method
     * of Button in order to correctly handle the user provided scores. Compares the values entered
     * in score fields and updates the correct team label for next round.
     * 
     * Begins by resetting the labels of any future games in the current teams path. Added so that
     * it resets future game labels if user changes outcome of previous games after already moving
     * further in bracket.
     * 
     * @param teams ArrayList of all teams 
     * @param nextTextField Text field associated with the winner of this game, used to enable field when scores are submitted.
     * @param winner The winner of this game
     * @param winnerNum The number of the winner label. To reset winner label
     * @param nextWinner The winner of next rounds game. Used to reset label 
     * @param nextwinnerNum Number of the winner of next rounds game. Used to reset label  
     * @param score1 Score of first team in this game
     * @param score2 Score of second team in this game
     * @param contestant1 The first competitor
     * @param contestant2 The second competitor
     * @param champion The label for champion of the tournament
     * @param runnerUp The label runner up in the tournament
     * @return submit The created Button
     */
    private Button createRnd2SubmitButton(ArrayList<Team> teams, TextField nextTextField,
                    Label winner, String winnerNum, Label nextWinner,
                    String nextWinnerNum, TextField score1, TextField score2, Label contestant1,
                    Label contestant2, Label champion, Label runnerUp) {
        Button submit = new Button();
        submit.setText("Submit Score");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    
                    champion.setText("Champion: ");
                    runnerUp.setText("Runner Up: ");
                        winner.setText("Winner " + winnerNum + ": ");

                        nextWinner.setText("Winner " + nextWinnerNum + ": ");
                    

                    int team1Score = Integer.parseInt(score1.getText().trim());
                    int team2Score = Integer.parseInt(score2.getText().trim());

                    if (team1Score < 0 || team2Score < 0) {
                        System.out.println("Invalid Score: Scores must be positive");
                    } else if (team1Score > team2Score) {
                        winner.setText(contestant1.getText());
                        nextTextField.setDisable(false);
                    } else if (team1Score < team2Score) {
                        winner.setText(contestant2.getText());
                        nextTextField.setDisable(false);
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

    /**
     * This method is a helper method to create the submit buttons for each of the games in round 3.
     * It overrides the handle method of Button in order to correctly handle the user provided
     * scores. It compares the values entered in the respective score fields and updates the correct
     * team labels accordingly.
     * 
     * It begins by resetting the labels of any future games in the current teams path. This is to
     * reset labels if user goes back and changes a games outcome after already moving farther
     * forward in the bracket.
     * 
     * @param loser To store loser of game for runner up calculations later
     * @param contestant1 The first competitor
     * @param contestant2 The second competitor
     * @param winner The winner of this game
     * @param winnerNum The number of the winner label. To update winner label
     * @param score1 Score of contestant1 in this game
     * @param score2 Score of contestant2 in this game
     * @param champ The label for champion of the tournament
     * @param runnerUp The label runner up in the tournament
     * @return submit The created Button
     */
    private Button createRnd3SubmitButton(Team loser, Label contestant1, Label contestant2,
                    Label winner, TextField nextTextField, String winnerNum, TextField score1,
                    TextField score2, Label champ, Label runnerUp) {
        Button submit = new Button();
        submit.setText("Submit Score");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Resetting labels of future games in these teams' subsequent path
                    champ.setText("Champion: ");
                    runnerUp.setText("Runner Up: ");
                    winner.setText("Winner " + winnerNum + ": ");

                    // Getting score results from text fields
                    int team1Score = Integer.parseInt(score1.getText().trim());
                    int team2Score = Integer.parseInt(score2.getText().trim());

                    if (team1Score < 0 || team2Score < 0) { // Ensuring score input is non-negative
                        System.out.println("Invalid Score: Scores must be positive");
                    } else if (team1Score > team2Score) {
                        winner.setText(contestant1.getText()); // Updating winner label to winning
                                                               // team
                        nextTextField.setDisable(false);
                        loser.setTeamName(contestant2.getText()); // Updating loser reference
                        loser.setTeamScore(team2Score);
                    } else if (team1Score < team2Score) {
                        winner.setText(contestant2.getText());
                        nextTextField.setDisable(false);
                        loser.setTeamName(contestant1.getText());
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

    /*
     * Used to create Text fields for score input. Sets size, Prompt text, etc.
     * 
     * @param scoreNumber Numbering of text fields to be displayed in UI 
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

    /*
     * Used to create labels for game that don't yet have a contestant assigned to them. 
     * 
     * @param winnerNumber Numbering of winner labels to be displayed in UI 
     * @returns winner The created Label
     */
    private Label createWinnerLabel(String winnerNumber) {
        Label winner = new Label();
        winner.setPrefHeight(15);
        winner.setText("Winner " + winnerNumber + ": ");
        return winner;
    }

    /*
     * Used to create labels to assign teams to locations in Bracket  
     * 
     * @param teams ArrayList of teams 
     * @param teamIndex The index of the team in teams list
     * @returns winner The created Label
     */
    private Label createTeamLabel(ArrayList<Team> teams, int teamIndex) {
        Label label = new Label();
        label.setPrefHeight(15);
        label.setText(teams.get(teamIndex).getTeamName() + ": ");
        return label;
    }

    /*
     * Used to created the Underlined round headers above each round  
     * 
     * @param roundName Name of round
     * @returns round The created Text
     */
    private Text createRoundHeader(String roundName) {
        Text round = new Text(roundName);
        round.setId("rounds");
        round.maxHeight(15);
        return round;
    }
}
