// ------------------------------------------------------------------------
/*
    Project 2: Keno Lottery Game
        This program simulates the lottery game Keno. Players can pick
        1, 4, 8, or 10 numbers and draw 1, 2, 3, or 4 times. How much money
        players win depends on how many of their numbers match the winning
        numbers drawn.

    Date: 03/19/23
    CS 342 Spring 2023

    Contributors:
        Jennifer Le, jle34
        Sebastian Kowalczyk, skowa3
 */
// -------------------------------------------------------------------------
import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.*;
import javafx.geometry.Pos;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Circle;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.Optional;

public class JavaFXTemplate extends Application {
    private MenuBar menu_bar;
    private MenuItem newLook;
    private Popup rules_popup, odds_popup;
    private boolean game_has_started = false;
    final int[] selectedSpot = {0};
    final int[] numDrawings = {0};
    final boolean[] spots_chosen = {false};
    final boolean[] draws_chosen = {false};
    final int[] numSelected = {0};
    private HBox spots_hbox, drawings_hbox;
    private GridPane selection_grid, drawing_grid;
    private Button random_button, begin_button, next_drawing;
    private Label number_hits, drawing_winning, total_winnings;
    private int totalPayout = 0;
    private int round_total = 0;
    ArrayList<Integer> picked_numbers = new ArrayList<>();
    private int draw = 1;
    String color_theme_grid, color_theme_red, color_theme_yellow;
    String color_theme_green, color_theme_buttons, final_color;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }
    // ----------------------------------------------------------------
    // creates original color theme for grid, buttons, and other components
    public void create_color_theme1() {
        color_theme_grid = "-fx-text-fill: #000000; -fx-background-color: #00BBB8; " +
                "-fx-border-color: #008785; -fx-border-width: 5px; -fx-font-size: 15;";

        color_theme_red = "-fx-text-fill: #000000; -fx-background-color: red; " +
                "-fx-border-color: #008785; -fx-border-width: 5px; -fx-font-size: 15;";

        color_theme_yellow = "-fx-text-fill: #000000; -fx-background-color: yellow; " +
                "-fx-border-color: #008785; -fx-border-width: 5px; -fx-font-size: 15;";

        color_theme_green = "-fx-text-fill: #000000; -fx-background-color: #51FF71; " +
                "-fx-border-color: #008785; -fx-border-width: 5px; -fx-font-size: 15;";

        color_theme_buttons = "-fx-text-fill: #FFFFFF; -fx-background-color: #BD4E9E; " +
                "-fx-border-color: #9F0074; -fx-border-width: 5px; -fx-font-size: 15;";

        final_color = "#00BBB8";
    }
    // ----------------------------------------------------------------
    // creates a second color theme when "new look" menu item is pressed
    public void create_color_theme2() {
        color_theme_grid = "-fx-text-fill: #000000; -fx-background-color: #FFCF98; " +
                "-fx-border-color: #FFAF54; -fx-border-width: 5px; -fx-font-size: 15;";

        color_theme_red = "-fx-text-fill: #000000; -fx-background-color: #FF5C5C; " +
                "-fx-border-color: #FFAF54; -fx-border-width: 5px; -fx-font-size: 15;";

        color_theme_yellow = "-fx-text-fill: #000000; -fx-background-color: yellow; " +
                "-fx-border-color: #FFAF54; -fx-border-width: 5px; -fx-font-size: 15;";

        color_theme_green = "-fx-text-fill: #000000; -fx-background-color: #B1FFA1; " +
                "-fx-border-color: #FFAF54; -fx-border-width: 5px; -fx-font-size: 15;";

        color_theme_buttons = "-fx-text-fill: #000000; -fx-background-color: #ECCCFF; " +
                "-fx-border-color: #DEA5FF; -fx-border-width: 5px; -fx-font-size: 15;";

        final_color = "#FFD29D";
    }
    // ----------------------------------------------------------------
    // create popups for menu options "Game Rules" and "Odds of Winning"
    // Populate popups with uneditable text areas of rules and odds
    public void create_popups() {
        // create text area for rules of game
        String rules = " \n... Rules of Game ...\n\n" +
                       " 1) Choose 1, 4, 8 or 10 numbers.\n\n" +
                       " 2) Choose 1, 2, 3, or 4 drawings.\n\n" +
                       " 3) Click on a yellow spot to de-select a number,\n" +
                       "    manually or randomly chosen.\n\n" +
                       " 4) You cannot change anything after clicking \"Begin Drawing\",\n" +
                       "    so finalize your decision before clicking on this.\n\n" +
                       " 5) The amount you win depends on how many of your numbers matches\n" +
                       "    the winning numbers drawn.\n\n" +
                       "    10 Spot Game\n" +
                       "        10 Matches -> $100,000\n" +
                       "        9 Matches  -> $4,250\n" +
                       "        8 Matches  -> $450\n" +
                       "        7 Matches  -> $40\n" +
                       "        6 Matches  -> $15\n" +
                       "        5 Matches  -> $2\n" +
                       "        0 Matches  -> $5\n\n" +
                       "    8 Spot Game\n" +
                       "        8 Matches -> $10,000\n" +
                       "        7 Matches -> $750\n" +
                       "        6 Matches -> $50\n" +
                       "        5 Matches -> $12\n" +
                       "        4 Matches -> $2\n\n" +
                       "    4 Spot Game\n" +
                       "        4 Matches -> $75\n" +
                       "        3 Matches -> $5\n" +
                       "        2 Matches -> $1\n\n" +
                       "    1 Spot Game\n" +
                       "        1 Match -> $2\n\n" +
                       " 6) You can play multiple rounds by click \"Play Again\".\n\n" +
                       " 7) Change the theme by going to menu > new look.\n    Click this again" +
                       " to change back to the original theme.\n\n" +
                       "   Good Luck!";

        TextArea rules_of_game = new TextArea();
        rules_of_game.setEditable(false);
        rules_of_game.setText(rules);
        rules_of_game.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 16 ");
        rules_of_game.setPrefSize(625, 400);

        // create text area for odds of winning
        String Odds = " \n\n... Overall Odds of Winning ...\n\n" +
                      "   10 Spot Game: 1 in 9.05\n" +
                      "   8 Spot Game:  1 in 9.77\n" +
                      "   4 Spot Game:  1 in 3.86\n" +
                      "   1 Spot Game:  1 in 4.00\n ";
        TextArea odds_of_winning = new TextArea();
        odds_of_winning.setEditable(false);
        odds_of_winning.setText(Odds);
        odds_of_winning.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 16 ");
        odds_of_winning.setPrefSize(300, 200);

        // create popups for each menu option
        rules_popup = new Popup();
        odds_popup = new Popup();

        // create image for close popup button for rules popup
        Image pic = new Image("close.png");
        ImageView x = new ImageView(pic);
        x.setFitHeight(20); x.setFitWidth(20);
        x.setPreserveRatio(true);

        // create image for close popup button for odds popup
        Image pic2 = new Image("close.png");
        ImageView x2 = new ImageView(pic2);
        x2.setFitHeight(20); x2.setFitWidth(20);
        x2.setPreserveRatio(true);

        // create button to close rules popup
        Button close_popup1 = new Button();
        close_popup1.setGraphic(x);
        close_popup1.setOnAction(e -> rules_popup.hide());

        // create button to close odds popup
        Button close_popup2 = new Button();
        close_popup2.setGraphic(x2);
        close_popup2.setOnAction(e -> odds_popup.hide());

        // add content to popups
        rules_popup.getContent().addAll(rules_of_game, close_popup1);
        odds_popup.getContent().addAll(odds_of_winning, close_popup2);
    }
    // ----------------------------------------------------------------
    // creates the menu bar to be displayed in all scenes but the final
    // populates menu bar with multiple options such as quitting the game
    public void create_menu_bar(Stage primaryStage) {
        // create menu bar and menu
        menu_bar = new MenuBar();
        Menu menu = new Menu("Menu");

        // create options for menu
        MenuItem rules = new MenuItem("Rules");
        MenuItem odds = new MenuItem("Odds of Winning");
        MenuItem quit = new MenuItem("Quit Game");

        // add "new look" menu option when game starts
        if (game_has_started) {
            newLook = new MenuItem("New Look");
            newLook.setOnAction(e -> {
                // add confirmation so user doesn't change look and restart on accident
                Alert alert = new Alert(AlertType.CONFIRMATION,
                        "Changing the look will restart the round. Press OK to continue");
                Optional<ButtonType> result = alert.showAndWait();

                // add event handler for the OK button
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // toggle color themes
                    if (color_theme_grid.equals("-fx-text-fill: #000000; -fx-background-color: #00BBB8; " +
                            "-fx-border-color: #008785; -fx-border-width: 5px; -fx-font-size: 15;")) {
                        create_color_theme2();
                    } else {
                        create_color_theme1();
                    }
                    // restart from selection screen
                    draw = 1;
                    selectedSpot[0] = 0;
                    numDrawings[0] = 0;
                    spots_chosen[0] = false;
                    draws_chosen[0] = false;
                    numSelected[0] = 0;
                    picked_numbers.clear();
                    round_total = 0;
                    display_game_scene(primaryStage);
                }
            });
        }
        // add options to menu
        if (game_has_started) {
            menu.getItems().addAll(rules, odds, newLook, quit);
        } else {
            menu.getItems().addAll(rules, odds, quit);
        }

        // add menu to menu_bar
        menu_bar.getMenus().add(menu);

        // create and populate popups for rules and odds
        create_popups();

        // create an event handler for each menu options
        rules.setOnAction(e -> rules_popup.show(primaryStage));
        odds.setOnAction(e -> odds_popup.show(primaryStage));
        quit.setOnAction(e -> {
            // add confirmation so user doesn't quit on accident
            Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to quit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                System.exit(0);
            }
        });
    }
    // ----------------------------------------------------------------
    // takes the number of matches to winning numbers and returns money
    // earned from one drawing
    public int money_earned(Integer numbersHit) {
        // money earned from one drawing
        Integer prize_money = 0;

        // prizes for 10 spots
        if (selectedSpot[0] == 10) {
            int[] prizes = {5, 0, 0, 0, 0, 2, 15, 40, 450, 4250, 100000};
            prize_money = prizes[numbersHit];
        }
        // prizes for 8 spots
        else if (selectedSpot[0] == 8) {
            int[] prizes = {0, 0, 0, 0, 2, 12, 50, 750, 10000};
            prize_money = prizes[numbersHit];
        }
        // prizes for 4 spots
        else if (selectedSpot[0] == 4) {
            int[] prizes = {0, 0, 1, 5, 75};
            prize_money = prizes[numbersHit];
        }
        // prizes for 1 spot
        else {
            int[] prizes = {0, 2};
            prize_money = prizes[numbersHit];
        }
        return prize_money;
    }
    // ----------------------------------------------------------------
    // called by game_scene once user clicks "begin drawing", randomly
    // selects the winning numbers and displays them one at a time
    public void start_drawing(Stage primaryStage) {
        ArrayList<Integer> winning_numbers = new ArrayList<>();

        // populate winning numbers with unique random numbers
        for(int i = 0; i < 20; i++) {
            int number = (int)(Math.random() * 80) + 1;
            while(winning_numbers.contains((Object) number)) {
                number = (int)(Math.random() * 80) + 1;
            }
            winning_numbers.add(number);
        }
        // see how many picked numbers matches winning numbers
        Integer numbersHit = 0;
        for(int k = 0; k < 20; k++) {
            if(picked_numbers.contains(winning_numbers.get(k))) {
                numbersHit++;
            }
        }
        // calculate money earned from one draw and add to total for round
        round_total += money_earned(numbersHit);
        totalPayout += money_earned(numbersHit);

        // create a grid pane to display winning and picked numbers
        drawing_grid = new GridPane();
        drawing_grid.setAlignment(Pos.CENTER);

        // populate grid pane with buttons with no event handler
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                int number = i * 10 + j + 1; // calculate the number to label the button
                Button button = new Button(Integer.toString(number));
                button.setPrefSize(50, 50);
                button.setStyle(color_theme_grid);
                drawing_grid.add(button, j, i);
            }
        }
        // display grid and other components to user
        display_drawing(primaryStage, numbersHit);

        SequentialTransition seqTransition = new SequentialTransition();

        // change squares with picked numbers to yellow
        for (int number : picked_numbers) {
            drawing_grid.getChildren().get(number - 1).setStyle(color_theme_yellow);
        }
        // change squares with winning numbers to red or green
        for (int number : winning_numbers) {
            // create pause
            PauseTransition pause = new PauseTransition(Duration.seconds(.75));

            // add the pause transition to the sequential transition
            seqTransition.getChildren().add(pause);

            // add an event listener to the pause transition to clear the style after the pause
            pause.setOnFinished(event -> {
                if (picked_numbers.contains(number)) {
                    drawing_grid.getChildren().get(number - 1).setStyle(color_theme_green);
                } else {
                    drawing_grid.getChildren().get(number - 1).setStyle(color_theme_red);
                }
            });
        }
        // make results visible once all winning numbers are shown
        seqTransition.setOnFinished(event -> {
            next_drawing.setVisible(true);
            number_hits.setVisible(true);
            drawing_winning.setVisible(true);
            total_winnings.setVisible(true);
        });
        seqTransition.play();
    }
    // ----------------------------------------------------------------
    // called by start_drawing once winning numbers have been selected
    // makes drawing components visible to user
    public void display_drawing(Stage primaryStage, Integer numberHits) {
        // create label to explain different color meaning
        String colors = "" +
                "Yellow  ->  Numbers you picked\n" +
                "Red       ->  Miss: Winning Numbers you didn't pick\n" +
                "Green   ->  Hit: Winning Numbers you picked\n";
        Label colors_meanings = new Label(colors);

        // create button for draw number
        Button draw_number = new Button("Draw Number " + draw + " out of " + numDrawings[0]);
        draw_number.setStyle(color_theme_buttons);

        // create labels for results of drawings
        number_hits = new Label("You matched " + numberHits + " winning numbers.");
        drawing_winning = new Label("You won " + money_earned(numberHits) + "$ from this draw.");
        total_winnings = new Label( "total winnings from this round: " + round_total +
                "$      total winnings from all rounds: " + totalPayout + "$");

        // create button for next drawing
        next_drawing = new Button("Next Drawing");
        next_drawing.setStyle(color_theme_buttons);
        next_drawing.setOnAction( e -> {
            if (draw <= numDrawings[0]) {
                start_drawing(primaryStage);
            } else {
                display_final_scene(primaryStage);
            }
        });
        // hide results before drawing is finished
        next_drawing.setVisible(false);
        number_hits.setVisible(false);
        drawing_winning.setVisible(false);
        total_winnings.setVisible(false);

        // create menu_bar so users can review rules and odds
        create_menu_bar(primaryStage);

        // create vbox for all drawing components
        VBox drawing = new VBox(7, menu_bar, colors_meanings, draw_number, drawing_grid,
                number_hits, drawing_winning, total_winnings, next_drawing);
        drawing.setAlignment(Pos.TOP_CENTER);

        // set up scene and display
        Scene newScene = new Scene(drawing, 800, 800);
        newScene.getRoot().setStyle("-fx-font-size: 15");
        primaryStage.setScene(newScene);
        primaryStage.show();

        draw++;
    }
    // ----------------------------------------------------------------
    // called by display drawing once all drawings are done
    // displays amount won from this round and all rounds
    public void display_final_scene(Stage primaryStage) {
        //show the total payout as an alert
        Alert alert2 = new Alert(AlertType.INFORMATION);
        alert2.setTitle("Results");
        alert2.setHeaderText("No more drawings ...");
        alert2.setContentText("You won $" + round_total + " for this round.\n" +
                "You won $" + totalPayout + " total.");
        alert2.showAndWait();

        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.BLACK);

        // create a circle to represent the explosion
        Circle circle = new Circle(400, 300, 20, Color.WHITE);
        circle.setBlendMode(BlendMode.SCREEN);
        root.getChildren().add(circle);

        // create a box blur effect to create the glowing effect of the explosion
        BoxBlur boxBlur = new BoxBlur(10, 10, 3);

        // create a timeline to animate the explosion
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> {
                    circle.setEffect(boxBlur);
                    circle.setFill(Paint.valueOf(final_color));
                }),
                new KeyFrame(Duration.seconds(2), event -> {
                    circle.setEffect(null);
                    circle.setFill(Color.TRANSPARENT);
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // create a scale transition to increase the size of the circle rapidly
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(4), circle);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(scene.getWidth() / 20);
        scaleTransition.setToY(scene.getHeight() / 20);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(false);
        scaleTransition.play();

        // Create "Play again" button
        Button play_again = new Button("Play Again");
        play_again.setOnAction( e-> {
            // reset everything but total winnings
            draw = 1;
            selectedSpot[0] = 0;
            numDrawings[0] = 0;
            spots_chosen[0] = false;
            draws_chosen[0] = false;
            numSelected[0] = 0;
            picked_numbers.clear();
            round_total = 0;
            display_game_scene(primaryStage);
        });
        play_again.setPrefSize(200, 50);

        // create a vbox to hold winnings and play again button
        Label winnings = new Label("You won $" + round_total + " from this round.");
        winnings.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 30");
        winnings.setAlignment(Pos.CENTER);
        VBox all = new VBox(20, winnings, play_again);
        all.setStyle(color_theme_buttons);
        all.setPrefSize(800, 50);
        all.setAlignment(Pos.CENTER);

        // display other components after the animation is done
        scaleTransition.setOnFinished(e -> {
            root.getChildren().add(all);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // ----------------------------------------------------------------
    // called by display_game_scene, creates area for the selection
    // of number of spots and number of drawings
    public void create_spots_drawings_boxes() {
        // create a HBoxes for the number of spots and drawings
        spots_hbox = new HBox();
        drawings_hbox = new HBox();

        ToggleGroup group = new ToggleGroup();
        final Integer[] number_of_spots = {1, 4, 8, 10};

        // populate number of spots selection area
        for (Integer spot: number_of_spots) {
            // create new radio button and customize
            RadioButton radioButton = new RadioButton(Integer.toString(spot));
            radioButton.setPrefSize(50, 50);
            radioButton.setStyle(color_theme_buttons + "-fx-font-size: 13;");
            radioButton.setToggleGroup(group);

            // add to HBox and create event handler
            final int finalI = spot;
            spots_hbox.getChildren().add(radioButton);
            selectedSpot[0] = finalI;          // set selectedSpot[0] to default value
            radioButton.setOnAction(e -> {
                selectedSpot[0] = finalI;      // update selectedSpot[0]
                spots_chosen[0] = true;
            });
        }

        // populate number of drawings selection area
        ToggleGroup group2 = new ToggleGroup();
        for (int i = 1; i < 5; i++) {
            RadioButton radioButton = new RadioButton(Integer.toString(i));
            radioButton.setPrefSize(50, 50);
            radioButton.setStyle(color_theme_buttons + "-fx-font-size: 13;");
            radioButton.setToggleGroup(group2);
            drawings_hbox.getChildren().add(radioButton);

            // add to HBox and create event handler
            final int finalI = i;
            radioButton.setOnAction(e -> {
                numDrawings[0] = finalI;        // update selectedSpot[0]
                draws_chosen[0] = true;
            });
        }
    }
    // ----------------------------------------------------------------
    // called by display_game_scene, creates a grid pane of 80 toggle
    // buttons, changes the color of each button when clicked
    public void create_selection_grid(){
        // create new grid pane for selection of numbers
        selection_grid = new GridPane();
        selection_grid.setAlignment(Pos.CENTER);

        // populate grid pane with buttons
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                int number = i * 10 + j + 1; // calculate the number to label the button
                Button button = new Button(Integer.toString(number));
                button.setPrefSize(50, 50);
                button.setStyle(color_theme_grid);

                button.setOnAction(event -> {
                    // if number of spots has not been chosen
                    if (!spots_chosen[0]){
                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                "Please select the number of spots you want to play first.");
                        alert.showAndWait();
                    }
                    // if user wants to de-select a number
                    else if (button.getStyle().equals(color_theme_yellow)) {
                        button.setStyle(color_theme_grid);
                        numSelected[0]--;
                        // remove the deselected number from picked numbers arrayList
                        Integer number_to_remove = Integer.parseInt(button.getText());
                        picked_numbers.remove((Object) number_to_remove);
                    }
                    // if user chooses number and number of spots has not been reached
                    else if (numSelected[0] < selectedSpot[0]) {
                        button.setStyle(color_theme_yellow);
                        numSelected[0]++;
                        // add the selected number to picked numbers arrayList
                        picked_numbers.add(Integer.parseInt(button.getText()));
                    }
                    // if number of spots has been reached
                    else if (numSelected[0] >= selectedSpot[0] ){
                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                "You can only select " + selectedSpot[0] + " items at a time." +
                                        " Change your selection by clicking the number again.");
                        alert.showAndWait();
                    }
                });
                selection_grid.add(button, j, i);
            }
        }
    }
    // ----------------------------------------------------------------
    // called by display_game_scene, creates a button for random selection
    // of numbers and adds randomly selected numbers to picked numbers list
    public void create_random_button() {
        // create a new button for random selections of the grid
        random_button = new Button("Random Selection");
        random_button.setPrefSize(150, 75);
        random_button.setStyle(color_theme_buttons);

        random_button.setOnAction(event -> {
            // make user select number of spots they want first
            if (!spots_chosen[0]){
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "Please select the number of spots you want to play first.");
                alert.showAndWait();
            } else {
                // clear the grid of yellow squares if any
                for (Integer picked_number: picked_numbers) {
                    selection_grid.getChildren().get(picked_number - 1).setStyle(color_theme_grid);
                }
                // clear picked numbers in case user has already chosen some numbers
                picked_numbers.clear();

                // pick unique random numbers
                for (int i = 0; i < selectedSpot[0]; i++) {
                    int random_number = (int) (Math.random() * 80 + 1);

                    // check to see if the random number has already been chosen
                    while (picked_numbers.contains((Object) random_number)) {
                        random_number = (int) (Math.random() * 80 + 1);
                    }
                    // add random number to picked numbers and change corresponding square to yellow
                    picked_numbers.add(random_number);
                    selection_grid.getChildren().get(random_number - 1).setStyle(color_theme_yellow);
                }
                numSelected[0] = selectedSpot[0];
            }
        });
    }
    // ----------------------------------------------------------------
    // called by display_game_scene, creates a button to begin drawing
    // gives warning if user has not selected everything they need to
    public void create_begin_button(Stage primaryStage) {
        // create a "begin drawing" button
        begin_button = new Button("Begin Drawing");
        begin_button.setPrefSize(150, 75);
        begin_button.setStyle(color_theme_buttons);

        // event handler for the begin button
        begin_button.setOnAction(event -> {
            // if the user has not selected the number of spots
            if (!spots_chosen[0]){
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "Please select the number of spots you want to play first.");
                alert.showAndWait();
            }
            // if user has not selected the number of drawings
            else if (!draws_chosen[0]) {
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "Please select the number of drawings you want to play first.");
                alert.showAndWait();
            }
            // if user has not selected enough spots
            else if (numSelected[0] < selectedSpot[0]) {
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "Please select " + (selectedSpot[0] - numSelected[0]) + " more spots.");
                alert.showAndWait();
            }
            // if user changes number of spots to a lower one after selecting numbers
            else if (numSelected[0] > selectedSpot[0]) {
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "You chose " + selectedSpot[0] + " spots but have selected "
                                + numSelected[0] + " numbers.\nPlease remove "
                                + (numSelected[0] - selectedSpot[0]) + " numbers or click " +
                                "\"random selection\" to choose again.");
                alert.showAndWait();
            }
            // if the user has selected the number of spots and drawings, display the drawing screen
            // pass the value of spots into the drawing screen
            else {
                start_drawing(primaryStage);
            }
        });
    }
    // ----------------------------------------------------------------
    // displays bet card components such as the numbers grid, number
    // of spots and drawings selection, "begin drawing" button, etc.
    public void display_game_scene(Stage primaryStage) {
        // so new look is added as a menu option
        game_has_started = true;

        // create menu bar
        create_menu_bar(primaryStage);

        // create HBoxes and labels for number of spots and drawings selection
        create_spots_drawings_boxes();
        Label label = new Label("Number of Spots: ");
        Label label2 = new Label("Number of Drawings: ");

        // Organize spots and drawings selection with VBoxes
        VBox Vbox1 = new VBox(label, spots_hbox);
        VBox Vbox2 = new VBox(label2, drawings_hbox);
        VBox spots_drawings = new VBox(10, Vbox1, Vbox2);

        // create random selection button and add to scene using HBox
        create_random_button();
        HBox top_part = new HBox(30, spots_drawings, random_button);
        top_part.setAlignment(Pos.CENTER);

        // combine top components all in one VBox
        VBox all_top_components = new VBox(10, menu_bar, top_part);

        // create and populate numbers selection grid pane
        create_selection_grid();

        // group selection grid and clarification label together
        Label label3 = new Label("Click spot to select number");
        VBox grid = new VBox(label3, selection_grid);
        grid.setAlignment(Pos.CENTER);

        // create begin drawing button and add to all components
        create_begin_button(primaryStage);
        VBox all_components = new VBox(10, all_top_components, grid, begin_button);
        all_components.setAlignment(Pos.TOP_CENTER);

        // set up scene and display
        Scene scene = new Scene(all_components, 800, 800);
        scene.getRoot().setStyle("-fx-font-size: 15");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    // ----------------------------------------------------------------
    // display first welcome screen and creates a color theme
    public void display_welcome_screen(Stage primaryStage) {
        // create color theme and menu bar
        create_color_theme1();
        create_menu_bar(primaryStage);

        // create start button and customize
        Button start_button = new Button("Start Playing!");
        start_button.setPrefSize(250, 100);
        start_button.setStyle(color_theme_grid + "-fx-font-size: 25; -fx-font-family: 'Marker Felt' ");

        // create welcome message and customize
        TextField welcome_message = new TextField();
        welcome_message.setEditable(false);
        welcome_message.setText("WELCOME TO . . .");
        welcome_message.setStyle(color_theme_grid + "-fx-font-size: 40; -fx-font-family: 'Marker Felt'");
        welcome_message.setAlignment(Pos.CENTER);

        // image of keno
        Image keno = new Image("Keno.png");
        ImageView keno_image = new ImageView(keno);
        keno_image.setFitWidth(600);
        keno_image.setPreserveRatio(true);

        // create vbox and center
        VBox vbox = new VBox(15, menu_bar, welcome_message, keno_image, start_button);
        vbox.setAlignment(Pos.TOP_CENTER);

        // set up scene and display
        Scene scene = new Scene(vbox, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();

        // event handler for start button
        start_button.setOnAction(e -> display_game_scene(primaryStage));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Keno");

        // start with a welcome screen
        display_welcome_screen(primaryStage);
    }
}

