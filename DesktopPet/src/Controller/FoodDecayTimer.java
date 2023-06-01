package Controller;

import Model.Food.FoodInterface;
import Model.Pet.PetInterface;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * FoodDecayTimer is a Timer subclass that is responsible for decaying a food item's
 * freshness over time. It takes an interval and a FoodInterface object as input.
 * When the timer ticks, it calls the food's lossFoodValueWhileTimePass method to
 * decrease the food's freshness. The timer will continue to run as long as the
 * food item's freshness remains greater than zero.
 */
public class FoodDecayTimer extends Timer{

  private FoodInterface food;

  /**
   * Constructs a FoodDecayTimer with the specified interval and food.
   *
   * @param i     the interval between timer ticks, in milliseconds
   * @param food  the FoodInterface object to decay over time
   */
  public FoodDecayTimer(int i, FoodInterface food) {
    super(i,null);
    this.food = food;
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (food.getFoodBoolean()!=false) {
          food.lossFoodValueWhileTimePass();
        }
        else {FoodDecayTimer.this.stop();}
      }
    });
    start();
  }

}
