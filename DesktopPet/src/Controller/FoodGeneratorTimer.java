package Controller;

import Model.Food.Apple;
import Model.Food.Diesel;
import Model.Food.FoodBoxInterface;
import Model.Food.FoodInterface;
import Model.Food.Screwworm;
import Model.Food.Strawberry;
import Model.Pet.PetInterface;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A timer for generating new food items at random intervals and adding them to a food box.
 */
public class FoodGeneratorTimer extends Timer{

  private FoodBoxInterface foodBox;

  /**
   * Constructor for the FoodGeneratorTimer class.
   * @param i The delay between generating new food items.
   * @param foodBox The food box to which new food items will be added.
   */
  public FoodGeneratorTimer(int i, FoodBoxInterface foodBox) {
    super(i,null);

    this.foodBox = foodBox;
    int initialDelay = generateRandomInterval(5, 15) * 1000; // convert seconds to milliseconds
    setInitialDelay(initialDelay);
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        FoodInterface newFood;
        // 生成一个 int 类型的随机数
        int randomNumber = random.nextInt(4);
        switch (randomNumber) {
          case 0:
            newFood = new Apple();
            break;
          case 1:
            newFood = new Diesel();
            break;
          case 2:
            newFood = new Screwworm();
            break;
          case 3:
            newFood = new Strawberry();
            break;
          default:
            newFood = new Apple();
            break;
        }
        FoodDecayTimer newTimer = new FoodDecayTimer(60000, newFood);
        foodBox.addFood(newFood);
        System.out.println("food timer");
      }
    });
  }
  private int generateRandomInterval(int minSeconds, int maxSeconds) {
    Random random = new Random();
    return random.nextInt(maxSeconds - minSeconds + 1) + minSeconds;
  }
}
