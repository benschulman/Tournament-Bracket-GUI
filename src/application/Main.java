package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/////////////////////////////////////////////////////////////////////////////
//Semester:         CS400 Spring 2018
//PROJECT:          Tournament Bracket GUI
//
//Authors:          Steven Wood, Jacob Latts, Ben Schulman, Dylan Breon
//
//Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
//Bugs:             No known bugs
//
//2018 Apr 23, 2018 Main.java
////////////////////////////////////////////////////////////////////////////

/**
 * This class generates and places all the components needed to display the tournament on the screen.
 * JavaFX is used to generate the GUI.
 */
public class Main extends Application {

	static ObservableList<String> names = FXCollections.observableArrayList();


	/**
	 * The method that runs on the start of the application
	 *
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {

		//Create the new stage
		primaryStage.setTitle("Tournament Bracket");
		//Gives the ability to scroll
		ScrollPane scrollPane = new ScrollPane();
		//Allows us to retrieve the command line arguments
		final Parameters params = getParameters();
		final List<String> parameters = params.getRaw();

		//Loads in the teams from the file provided in the argument
		ArrayList<Team> teams = null;
		try {
			teams = TeamLoader.teamLoader(parameters.get(0));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//Checks to see what number of teams is provided in the file
		Scene scene = null;
		if (teams.size() == 0) {
			//Creates a simple scene and adds text showing there is no winner
			scene = new Scene(scrollPane, 1600, 900, Color.DARKGRAY);
			GridPane gridPane = new GridPane();
			gridPane.setAlignment(Pos.CENTER);
			scrollPane.setContent(gridPane);
			Text text = new Text("No Teams No winners!");
			gridPane.add(text, 0, 0);
		}
		if(teams.size() == 1){
			//Creates a simple scene and shows that there are no games but one winner
			scene = new Scene(scrollPane, 1600, 900, Color.DARKGRAY);
			GridPane gridPane = new GridPane();
			gridPane.setAlignment(Pos.CENTER);
			scrollPane.setContent(gridPane);
			Text text = new Text("Champion: " + teams.get(0).getTeamName());
			gridPane.add(text, 0, 0);
		}
		else if (teams.size() == 2) //Runs the program for two teams
			scene = new TwoTeams(scrollPane, 1600, 900, Color.DARKGRAY, teams);
		else if (teams.size() == 4) //Runs the program for 4 teams
			scene = new FourTeams(scrollPane, 1600, 900, Color.DARKGRAY, teams);
		else if (teams.size() == 8) //Runs the program for 8 teams
			scene = new EightTeams(scrollPane, 1600, 900, Color.DARKGRAY, teams);
		else if (teams.size() == 16) //Runs the program for 16 teams
			scene = new SixteenTeams(scrollPane, 1600, 900, Color.DARKGRAY, teams);
		else{
			System.out.print("Invalid Number of teams!");
			System.exit(-1);
		}
		scene.getStylesheets().add("application/style.css");

		//Finally set the scene and show so it appears to the user.
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * Main method for the program
	 * Starts the application
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
