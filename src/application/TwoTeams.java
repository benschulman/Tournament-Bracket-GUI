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
// 2018 Apr 23, 2018 Two.java
////////////////////////////////////////////////////////////////////////////

/**
 * Generates a scene that represents a tournament bracket of two competing teams.
 */
public class TwoTeams extends Scene {

    /**
     * Constructor that generates a GUI for the two teams in the given teams ArrayList.
     * 
     * @param root - BorderPane that is the root of the GUI
     * @param width - width of GUI
     * @param height - height of GUI
     * @param fill - Color to fill as background
     * @param teams - List of all teams participating in tournament. Length should be two.
     */
    public TwoTeams(Parent root, double width, double height, Paint fill, ArrayList<Team> teams) {
        super(root, width, height, fill);

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

        // Large Title at top of scene
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

        /*
         * 
         * Creates the "round" labels
         * 
         */
        // Round 1 label
        Text round1 = new Text("Round 1");
        round1.setId("rounds");
        round1.minHeight(25);


        /*
         * 
         * Create the labels for each winner for each game
         * 
         */
        // Champion Label
        Label champ = new Label();
        champ.setMinHeight(25);
        champ.setText("Champion:");

        // Runner Up label
        Label runnerUp = new Label();
        runnerUp.setMinHeight(25);
        runnerUp.setText("Runner Up:");


        // Labels for Round 1 Competitors
        // See helper method below
        Label label1 = createTeamLabel(teams, 0);
        Label label2 = createTeamLabel(teams, 1);

        // Score Input fields creation.
        // See helper method below
        TextField score1 = createScoreInput("1");
        TextField score2 = createScoreInput("2");

        // Creating Empty Labels for spacing purposes
        Label emptyRow = new Label();
        emptyRow.setText(" ");
        Label emptyCol = new Label();
        emptyCol.setMinWidth(100);
        emptyCol.setText(" ");

        /*
         * 
         * Create the action listeners for submit buttons. This handle allows champion and second
         * place to be displayed after scores are entered.
         * 
         */
        // Submit scores button
        Button submit1 = new Button();
        submit1.setText("Submit Score");
        submit1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Retrieve scores entered by user. If scores are not numbers,
                    // NumberFormatException will be thrown
                    int team1score = Integer.parseInt(score1.getText().trim());
                    int team2score = Integer.parseInt(score2.getText().trim());

                    // Do different tasks depending on who won
                    if (team1score > team2score) {
                        champ.setText("Champion: " + teams.get(0).getTeamName());
                        runnerUp.setText("Runner Up: " + teams.get(1).getTeamName());
                    } else if (team1score < team2score) {
                        champ.setText(("Champion: " + teams.get(1).getTeamName()));
                        runnerUp.setText("Runner Up: " + teams.get(0).getTeamName());
                    } else {
                        System.out.println("Teams may not have the same score");
                    }
                }
                // Score entered was not a number so the game cannot be scored
                catch (NumberFormatException e) {
                    System.out.println("Invalid Score");
                }

            }
        });

        /*
         * Add all elements to their respective positions in the grid
         */
        gPane.add(round1, 0, 0); // Column Header
        gPane.add(emptyRow, 0, 1, 4, 1);
        gPane.add(label1, 0, 2); // first team label
        gPane.add(score1, 1, 2); // first teams score

        gPane.add(submit1, 0, 3, 2, 1); // submit button

        gPane.add(label2, 0, 4); // second team label
        gPane.add(score2, 1, 4); // second team score

        gPane.add(emptyCol, 2, 0, 1, 5);

        gPane.add(champ, 3, 3); // champion label
        gPane.add(runnerUp, 3, 6); // runner up label



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
     * Used to create Text fields for score input. Sets size, Prompt text, etc.
     * 
     * @param scoreNumber Numbering of text fields to be displayed in UI
     * 
     * @returns input The created TextField
     */
    private TextField createScoreInput(String scoreNumber) {
        TextField input = new TextField();
        input.setMaxHeight(20);
        input.setMaxWidth(200);
        input.setPromptText("Score " + scoreNumber);
        input.setFocusTraversable(false);
        return input;
    }

}
