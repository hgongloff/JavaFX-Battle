package application;

import java.io.File;
import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Name: Hunter Gongloff


public class Main extends Application
{
	private int playerScore; // Keeps track of players score.
	private int computerScore; // Keeps track of computers score.
	private int playerValue; // Keeps track of current player cards value.
	private int computerValue; // Keeps track of current computer cards value.
	private String playerName; // Stores inputed player name. (default is Player)
	private String computerName; // Stores inputed computer player name. (default is Computer)
	private ArrayList<Card> deckOfCards; // Array List that stores all the Card objects to make a deck.
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			// Creates a gridPane to show the GUI.
			GridPane grid = new GridPane();
			// Makes an array of Buttons.
			Button[] buttons = new Button[5];
			// Creates the default player and computer names.
			playerName = "Player";
			computerName = "Computer";
			
			// This takes a mp3 sound to play whenever cards are drawn.
			String musicFile = "DrawCard.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			// This takes a mp3 sound to play whenever cards are drawn.
			String winFile = "WinTheme.mp3";
			Media winSound = new Media(new File(winFile).toURI().toString());
			MediaPlayer winPlayer = new MediaPlayer(winSound);
			// This takes a mp3 sound to play whenever cards are drawn.
			String loseFile = "LoseTheme.mp3";
			Media loseSound = new Media(new File(loseFile).toURI().toString());
			MediaPlayer losePlayer = new MediaPlayer(loseSound);
			
			// This pops up the rules for how to play the game.
			Alert rules = new Alert(AlertType.INFORMATION);
			rules.setTitle("Battle Rules");
			rules.setHeaderText(null);
			rules.setContentText("Welcome to Battle! \nThis a card game based on the classic game of war. "
					+ "Battle is more simple to understand and play. Before you get started, here are a few rules! \n"
					+ "1: First there is going to be two pop up windows. They will instruct you to enter one name"
					+ " for the player (thats you) and one name for the computer player (thats your opponent). If you"
					+ " don't want to enter a name, that ok a default name will be given for you. \n"
					+ "2: After the names are inputted, the real game starts. The main game window will pop up. "
					+ " To begin the game, all you need to do is draw a card from the deck in the middle by clicking on it.\n"
					+ "3: Once you draw a card, your opponent will draw one as well. A battle will start between"
					+ " your two cards. The winner is decided by who has the higher card (Ace is highest, then King, Queen, etc.)."
					+ "If your card is the higher of the 2, then you win a point. If it is lower, then your opponent wins a point."
					+ "If you both draw the same card, then its a tie and no one gets any points. \n"
					+ "Now that you have read the rules, you are ready to begin! Good luck and have fun!");

			rules.showAndWait();
			
			// This makes a text input pop up and asks the user to enter a name for themselves.
			TextInputDialog playerNameInput = new TextInputDialog();
			playerNameInput.setHeaderText("Please input your name \n(default name is Player)");
			playerNameInput.setContentText("Please input your name: ");
			playerNameInput.setTitle("Player Name Input");
			playerNameInput.showAndWait();
			playerName = playerNameInput.getResult();
			
			// This makes a text input pop up and asks the user to enter a name for their opponent.
			TextInputDialog computerNameInput = new TextInputDialog();
			computerNameInput.setHeaderText("Please input a name for your opponent \n(default name is Computer)");
			computerNameInput.setContentText("Please input a name for your opponent: ");
			computerNameInput.setTitle("Opponent Name Input");
			computerNameInput.showAndWait();
			computerName = computerNameInput.getResult();
			
			
			
			// Checks if user inputed a name for the player.
			if (playerName == null || playerName.trim().equals(""))
			{
				playerName = "Player";
			}
			// Checks if user inputed a name for the computer player.
			if (computerName == null || computerName.trim().equals(""))
			{
				computerName = "Computer";
			}
			
			// Initializes the array of Buttons.
			for (int i = 0; i < buttons.length; i++)
			{
				buttons[i] = new Button();
			}
			
			// Creates deck of Cards ArrayList 
			deckOfCards = new ArrayList<Card>();
			
			// This creates images for the back of the cards
			ImageView backOfCard = new ImageView(new Image("file:Cards\\Red_back.jpg", 140, 186, false, false, false));
			ImageView backOfCard2 = new ImageView(new Image("file:Cards\\blue_back.jpg", 140, 186, false, false, false));
			ImageView backOfCard3 = new ImageView(new Image("file:Cards\\Gray_back.jpg", 140, 186, false, false, false));
			
			// These for loops create an entire deck of cards using Card objects for each 
			// individual card in a deck.
			for (int i = 2; i < 15; i++)
			{
				Card card = new Card(i + "C" , i);
				ImageView cardImage = new ImageView(new Image("file:Cards\\"+card.getCardName()+".jpg", 140, 186, false, false, false));
				card.setCardImage(cardImage);
				deckOfCards.add(card);
			}
			for (int i = 2; i < 15; i++)
			{
				Card card = new Card(i + "D" , i);
				ImageView cardImage = new ImageView(new Image("file:Cards\\"+card.getCardName()+".jpg", 140, 186, false, false, false));
				card.setCardImage(cardImage);
				deckOfCards.add(card);
			}
			for (int i = 2; i < 15; i++)
			{
				Card card = new Card(i + "H" , i);
				ImageView cardImage = new ImageView(new Image("file:Cards\\"+card.getCardName()+".jpg", 140, 186, false, false, false));
				card.setCardImage(cardImage);
				deckOfCards.add(card);
			}
			for (int i = 2; i < 15; i++)
			{
				Card card = new Card(i + "S" , i);
				ImageView cardImage = new ImageView(new Image("file:Cards\\"+card.getCardName()+".jpg", 140, 186, false, false, false));
				card.setCardImage(cardImage);
				deckOfCards.add(card);
			}
			
			// This sets up the grid pane by deciding its dimensions 
			// and gaps of each image in the pane.
			grid.setPadding(new Insets(25, 25, 25, 25));
			grid.setMinSize(300, 300);
			grid.setHgap(93);
			grid.setVgap(100);
			
			// Shuffles the cards so the order is random every time the game is played.
			deckOfCards = shuffle(deckOfCards); 
			
			
			// Creates the labels for the Player's Score and the Computer's Score.
			Label playerLabel = new Label("" + playerName + "'s \nScore: " + playerScore);
			playerLabel.setFont(new Font(32));
			playerLabel.setTextFill(Color.web("#0000FF"));
			Label computerLabel = new Label("" + computerName + "'s\n Score: " + computerScore);
			computerLabel.setFont(new Font(32));
			computerLabel.setTextFill(Color.web("#FF0000"));
			
			// Creates a label for the bottom of the game to display random messages
			Label message = new Label(" 	     Welcome to War!");
			message.setTextFill(Color.web("#CD853F"));
			message.setFont(new Font(44));
			
			
			// Adds all the labels and buttons to the grid pane.
			grid.add(playerLabel, 0, 0, 1, 1);
			grid.add(computerLabel, 2, 0, 1, 1);
			grid.add(buttons[0], 0, 1);
			grid.add(buttons[1], 1, 1);
			grid.add(buttons[2], 2, 1);
			grid.add(message, 0, 2, 3, 1);
			
			// Sets the graphics of the buttons to display the cards.
			buttons[0].setGraphic(backOfCard2);
			buttons[1].setGraphic(backOfCard);
			buttons[2].setGraphic(backOfCard3);
			
			// These pause the transitions of the card animations and the sounds.
			PauseTransition pause = new PauseTransition(Duration.seconds(0.75));
			PauseTransition pause2 = new PauseTransition(Duration.seconds(1.5));
			PauseTransition pause3 = new PauseTransition(Duration.seconds(2.25));
			PauseTransition sound1 = new PauseTransition(Duration.seconds(0.8));
			PauseTransition sound2 = new PauseTransition(Duration.seconds(1.2));
			
			
			// When the middle button is pushed this activates.
			buttons[1].setOnAction(e ->
			{
				// Makes the middle button unable to be pushed until both cards are drawn.
				buttons[1].setDisable(true);
				// Plays drawing sound when cards are drawn.
				mediaPlayer.play();
				// Makes the first and second button disappear.
				buttons[0].setVisible(false);
				buttons[2].setVisible(false);
				// Plays the first pause transition to wait 0.75 s before showing first card.
				pause.play();
				pause.setOnFinished(event ->{
					// Randomly draws a card from the deck and displays it to the user.
					int playerCard = ((int) Math.random() * deckOfCards.size());
					buttons[0].setGraphic(deckOfCards.get(playerCard).getCardImage());
					playerValue = deckOfCards.get(playerCard).getCardValue();
					deckOfCards.remove(playerCard);
					buttons[0].setVisible(true);
				});
				pause2.play();
				pause2.setOnFinished(event ->{
					// Randomly draws a second card for the opponent and displays it to the user.
					int computerCard = ((int) Math.random() * deckOfCards.size());
					buttons[2].setGraphic(deckOfCards.get(computerCard).getCardImage());
					computerValue = deckOfCards.get(computerCard).getCardValue();
					deckOfCards.remove(computerCard);
					buttons[2].setVisible(true);
					
				});
				// Plays a drawing sound and then restarts the sound.
				sound1.play();
				sound1.setOnFinished(event ->{
					mediaPlayer.stop();
				});
				sound2.play();
				sound2.setOnFinished(event ->{
					mediaPlayer.play();
				});
				// After both cards are drawn compares the values to see who won.
				pause3.play();
				pause3.setOnFinished(event ->{
				int results = battle(playerValue, computerValue);
				mediaPlayer.stop();
				// If the player won then they get their score increased by one.
				// If the computer won then they get their score increased by one. 
				// If its a tie then no one gets any points and their scores stay the same.
				if (results == 1)
				{
					playerScore++;
					playerLabel.setText(playerName + "'s \nScore: " + playerScore);
				} else if (results == 2)
				{
					computerScore++;
					computerLabel.setText(computerName + "'s \nScore: " + computerScore);
				} else
				{
					
				}
				// When the deck is empty the game ends and a winner is decided.
				if(deckOfCards.isEmpty()) 
				{
						// The deck disappears after all the cards are drawn
						buttons[1].setVisible(false);

						// Depending on who wins the following messages are displayed
						if (playerScore > computerScore)
						{
							winPlayer.play();
							message.setText((" 	       You Win!!!"));

						} else if (computerScore > playerScore)
						{
							losePlayer.play();
							message.setText((" 	       You Lose..."));
						}
						else if ( computerScore == playerScore)
						{
							message.setText((" 	       You Tied."));
						}
				}
				
				buttons[1].setDisable(false);
				});
				
			});
			
			// Hides the 2 buttons from the user so they don't appear until cards are drawn.
			buttons[0].setVisible(false);
			buttons[2].setVisible(false);
			
			
			// Builds the scene and displays it to the user.
			Scene scene = new Scene(grid, 725, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ArrayList<Card> shuffle(ArrayList<Card> deckOfCards)
	{
		// Creates a new arrayList to shuffle the cards into.
		ArrayList<Card> shuffledCards = new ArrayList<>();
		
		// Keeps track of what the original size of the given deck is.
		int originalSize = deckOfCards.size();
		
		// Loop runs to add cards to the new array list and take out 
		// cards out of the old array list.
		for (int i = 0; i < originalSize; i++)
		{
			
			shuffledCards.add(deckOfCards.remove((int) (Math.random() * deckOfCards.size())));
			
			
		}
		
		// Returns the new shuffled array List.
		return shuffledCards;
	}
	
	public int battle(int playerValue, int computerValue) 
	{
		// Determines who wins the battle by using the given values.
		// Returns a 1 if player wins, a 2 if computer wins and 
		// a 0 if its a tie.
		if(playerValue > computerValue) 
		{
			return 1;
		} else if(computerValue > playerValue) 
		{
			return 2;
		} else {
			return 0;
		}
	
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
































































































