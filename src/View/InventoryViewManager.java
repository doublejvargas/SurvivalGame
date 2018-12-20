package View;

import javafx.animation.AnimationTimer;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Food;
import model.Game;
import java.util.ArrayList;

public class InventoryViewManager {
    private Game game;
    private static final int HEIGHT= 672;
    private static final int WIDTH = 224;
    //private final Canvas INVENTORY_CANVAS;
    private static final Image BACK_PANEL_IMAGE = new Image("View/Resources/minimap backpanel.png");
    //private GraphicsContext gc;
    private SubScene subscene;
    private GridPane meatButtons;

    private class ImageButton extends Parent {
        private ImageView iv;
        private Food.FoodType foodtype;

        public ImageButton(Image released, Image pressed, Food.FoodType foodtype) {
            this.foodtype = foodtype;
            Image NORMAL_IMAGE = released;
            Image PRESSED_IMAGE = pressed;

            this.iv = new ImageView(NORMAL_IMAGE);
            this.getChildren().add(this.iv);

            this.iv.setOnMousePressed(e -> iv.setImage(PRESSED_IMAGE));

            this.iv.setOnMouseReleased(e ->  {
                iv.setImage(NORMAL_IMAGE);
                game.getPlayer().eat(foodtype);
            });

        }


        public Food.FoodType getFoodType() {
            return foodtype;
        }

        public void setFoodtype (Food.FoodType type) {
            foodtype = type;
        }
    }


    public InventoryViewManager(Game game){
        this.game = game;
        //buttonsList = new ArrayList<>();
        //INVENTORY_CANVAS = new Canvas(game.getWIDTH(), game.getHEIGHT());
        meatButtons = new GridPane();
        setUpButtons();
        StackPane sp = new StackPane();
        sp.getChildren().addAll(new ImageView(BACK_PANEL_IMAGE),meatButtons);
        meatButtons.setAlignment(Pos.BOTTOM_CENTER);
        subscene = new SubScene(sp, WIDTH, HEIGHT);

        gameLoop();
    }

    private void setUpButtons() {
        int buttonSize = 85;
        Image grass = new Image("/View/Resources/grass.png", buttonSize, buttonSize, true, true);
        Image bush = new Image("/View/Resources/bush.png", buttonSize, buttonSize, true, true);
        Image bush2 = new Image("/View/Resources/bush_with_berry.png", buttonSize, buttonSize, true, true);
        Image pine = new Image("/View/Resources/pine tree.png", buttonSize, buttonSize, true, true);
        Image cactus = new Image("/View/Resources/cactus.png", buttonSize, buttonSize, true, true);
        Image rock = new Image("/View/Resources/rock.png", buttonSize, buttonSize, true, true);
        Image water = new Image("/View/Resources/water.png", buttonSize, buttonSize, true, true);

        ImageButton rabbitButton = new ImageButton(grass, bush, Food.FoodType.Rabbit);
        ImageButton deerButton = new ImageButton(grass, bush2, Food.FoodType.Deer);
        ImageButton lionButton = new ImageButton(grass, pine, Food.FoodType.Lion);
        ImageButton wolfButton = new ImageButton(grass, cactus, Food.FoodType.Wolf);
        ImageButton fishButton = new ImageButton(grass, rock, Food.FoodType.Fish);
        ImageButton crocodileButton = new ImageButton(grass, water, Food.FoodType.Crocodile);

        for (int y = 0; y < 9; y += 3)
            for (int x = 0; x < 2; x++){
                Text meatText = new Text();
                if (y == 0) {
                    if (x == 0) meatText.setText("Rabbit");
                    else meatText.setText("Deer");
                }
                else if (y == 3) {
                    if (x == 0) meatText.setText("Lion");
                    else meatText.setText("Wolf");
                }
                else if (y == 6) {
                    if (x == 0) meatText.setText("Fish");
                    else meatText.setText("Crocodile");
                }
                meatText.setFont(Font.font("Comic Sans ms", FontWeight.BOLD, 17));
                meatText.setFill(Color.WHITESMOKE);
                GridPane.setValignment(meatText, VPos.BOTTOM);
                GridPane.setHalignment(meatText, HPos.CENTER);
                meatButtons.add(meatText, x, y);
            }

        for (int y = 1; y < 9; y += 3)
            for (int x = 0; x < 2; x++) {
              if (y == 1) {
                  if (x == 0) meatButtons.add(rabbitButton, x, y);
                  else meatButtons.add(deerButton, x, y);
              }
              else if (y == 4) {
                  if (x == 0) meatButtons.add(lionButton, x, y);
                  else meatButtons.add(wolfButton, x, y);
              }
              else if (y == 7) {
                  if (x == 0) meatButtons.add(fishButton, x, y);
                  else meatButtons.add(crocodileButton, x, y);
              }
            }

        for (int y = 2; y < 9; y += 3)
            for (int x = 0; x < 2; x++) {
                Text quantity = new Text("X:" + 0);
                quantity.setFont(Font.font("Comic sans ms", FontWeight.BOLD, 13));
                quantity.setFill(Color.WHITESMOKE);
                GridPane.setHalignment(quantity, HPos.RIGHT);
                GridPane.setValignment(quantity, VPos.TOP);
                meatButtons.add(quantity, x, y);
            }

        meatButtons.setVgap(3);
        meatButtons.setHgap(10);
    }

    public void updateQuantities (GridPane gp) {
        int numRabbit = game.getPlayer().getInventory().getNumberOfRabbits();
        int numDeer = game.getPlayer().getInventory().getNumberOfDeer();
        int numLion = game.getPlayer().getInventory().getNumberOfLions();
        int numWolf = game.getPlayer().getInventory().getNumberOfWolves();
        int numFish = game.getPlayer().getInventory().getNumberOfFish();
        int numCrocodile = game.getPlayer().getInventory().getNumberOfCrocodiles();

        for (Node node : gp.getChildren())
            if (node instanceof Text) switch (GridPane.getRowIndex(node)) {
                case 2:
                    if (GridPane.getColumnIndex(node) == 0) ((Text) node).setText("X:" + numRabbit);
                    else ((Text) node).setText("X:" + numDeer);
                    break;
                case 5:
                    if (GridPane.getColumnIndex(node) == 0) ((Text) node).setText("X:" + numLion);
                    else ((Text) node).setText("X:" + numWolf);
                    break;
                case 8:
                    if (GridPane.getColumnIndex(node) == 0) ((Text) node).setText("X:" + numFish);
                    else ((Text) node).setText("X:" + numCrocodile);
                    break;
                default:
                    break;
            }
    }

    public void gameLoop() {
        AnimationTimer t = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateQuantities(meatButtons);
            }
        };
        t.start();
    }

    /*
    public ArrayList<ImageButton> getButtons() {
        return buttonsList;
    }*/


    public SubScene getSubscene() {
        return subscene;
    }
}