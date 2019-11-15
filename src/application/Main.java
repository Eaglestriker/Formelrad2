package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Formelrad Application
 * 
 * @author Tim Dubath und Yannick Ruck
 * @version 13.11.2019
 */
public class Main extends Application {

	Label lbwarning = new Label();
	Pane root = new Pane();

	public void exceptionHandling(String message) {
		lbwarning.setText("");
		lbwarning.setText(message);
		lbwarning.relocate(25, 480);
		lbwarning.setFont(Font.font(15));
		lbwarning.setTextFill(Color.web("red"));
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			lbwarning.setText("");
			root.getChildren().add(lbwarning);
			primaryStage.setHeight(550);

			String warning = "Sie haben mehr als zwei Zahlen eingegeben, \n dies wird falsche Resultate hervorbringen!";
			String warning2 = "Bitte Zahlen und keine Buchstaben eingeben.";

			// Creating an image
			Image image = new Image(getClass().getResourceAsStream("formelradelektronik.gif"));
			ImageView imageView = new ImageView(image);
			imageView.setX(10);
			imageView.setY(10);
			imageView.setFitHeight(300);
			imageView.setFitWidth(300);
			imageView.setPreserveRatio(true);
			root.getChildren().add(imageView);

			Label lbleistung = new Label("Leistung:");
			lbleistung.relocate(10, 285);
			lbleistung.setFont(Font.font(15));
			root.getChildren().add(lbleistung);

			TextField txLeistung = new TextField();
			txLeistung.relocate(100, 285);
			txLeistung.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txLeistung);

			Label lblSpannung = new Label("Spannung:");
			lblSpannung.relocate(10, 325);
			lblSpannung.setFont(Font.font(15));
			root.getChildren().add(lblSpannung);

			TextField txSpannung = new TextField();
			txSpannung.relocate(100, 325);
			txSpannung.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txSpannung);

			Label lblStrom = new Label("Strom:");
			lblStrom.relocate(10, 365);
			lblStrom.setFont(Font.font(15));
			root.getChildren().add(lblStrom);

			TextField txStrom = new TextField();
			txStrom.relocate(100, 365);
			txStrom.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txStrom);

			Label lblWiderstand = new Label("Widerstand:");
			lblWiderstand.relocate(10, 405);
			lblWiderstand.setFont(Font.font(15));
			root.getChildren().add(lblWiderstand);

			TextField txWiderstand = new TextField();
			txWiderstand.relocate(100, 405);
			txWiderstand.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txWiderstand);

			Button btnBerechnen = new Button();
			btnBerechnen.relocate(100, 445);
			btnBerechnen.setText("Berechnen");
			root.getChildren().add(btnBerechnen);

			btnBerechnen.setOnAction(e -> {
				double power = 0.0;
				double tension = 0.0;
				double current = 0.0;
				double resistence = 0.0;
				
				txLeistung.setStyle("-fx-text-inner-color: black;");
				txSpannung.setStyle("-fx-text-inner-color: black;");
				txStrom.setStyle("-fx-text-inner-color: black;");
				txWiderstand.setStyle("-fx-text-inner-color: black;");
				
				lbwarning.setText("");

				int zaehler = 0;

				try {
					if (!txLeistung.getText().isEmpty()) {
						power = Double.parseDouble(txLeistung.getText());
						zaehler = zaehler + 1;
					} else {
						txLeistung.setStyle("-fx-text-inner-color: red;");
					}

					if (!txSpannung.getText().isEmpty()) {
						tension = Double.parseDouble(txSpannung.getText());
						zaehler = zaehler + 1;
					} else {
						txSpannung.setStyle("-fx-text-inner-color: red;");
					}

					if (!txStrom.getText().isEmpty()) {
						current = Double.parseDouble(txStrom.getText());
						zaehler = zaehler + 1;
					} else {
						txStrom.setStyle("-fx-text-inner-color: red;");
					}

					if (!txWiderstand.getText().isEmpty()) {
						resistence = Double.parseDouble(txWiderstand.getText());
						zaehler = zaehler + 1;
					} else {
						txWiderstand.setStyle("-fx-text-inner-color: red;");
					}

					if (zaehler >= 3) {
						exceptionHandling(warning);
					}
				} catch (Exception z) {
					exceptionHandling(warning2);

					power = (Double) null;
					tension = (Double) null;
					current = (Double) null;
					resistence = (Double) null;

					z.printStackTrace();
				}

				Calculator myCalculator = new Calculator(power, tension, current, resistence);

				myCalculator.calculate();

				txLeistung.setText(Double.toString(myCalculator.getLeistung()));
				txSpannung.setText(Double.toString(myCalculator.getSpannung()));
				txStrom.setText(Double.toString(myCalculator.getStrom()));
				txWiderstand.setText(Double.toString(myCalculator.getWiderstand()));
				
				
				
			});

			Scene scene = new Scene(root, 330, 490);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Formelrad");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
