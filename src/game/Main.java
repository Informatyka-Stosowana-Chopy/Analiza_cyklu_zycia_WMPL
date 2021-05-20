package game;

import java.util.Scanner;

//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Zombie");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
public class Main {
    public static void main(String[] args) {
        Game game = new Game(10, 10);
        String path;
        Scanner scan = new Scanner(System.in);
        System.out.println("start gry!");
        String shouldPlay = "y";
        while (shouldPlay == "y") {
            while (!game.isOver()) {
                System.out.println(game.getGameDisplay());
                game.step();
            }
            System.out.println("Koniec gry. Czy chcesz zagrac ponownie? (y/n): ");
            shouldPlay = scan.nextLine();

            game.restart();
        }
    }

}
