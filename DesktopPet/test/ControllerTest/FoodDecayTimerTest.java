package ControllerTest;

import Controller.FoodDecayTimer;
import Model.Food.FoodInterface;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.Assert.*;

/**
 * this test might be unstable
 * Due to the imprecision of timers, test cases may produce different results in different runs.
 */
public class FoodDecayTimerTest {

  @Test
  public void testFoodDecayTimer() throws InterruptedException {
    FoodInterface food = new TestFood(10, true);
    FoodDecayTimer timer = new FoodDecayTimer(100, food);

    ActionListener listener = new ActionListener() {
      int tickCount = 0;

      @Override
      public void actionPerformed(ActionEvent e) {
        tickCount++;
        System.out.println("Tick count: " + tickCount + ", food value: " + food.getFoodValue());
      }
    };
    timer.addActionListener(listener);

    // Wait for 700 milliseconds
    Thread.sleep(700);

    timer.stop();

    assertEquals("Food value should be reduced by 7 after 700ms.", 4, food.getFoodValue());

    // Set isFood to false
    ((TestFood) food).setIsFood(false);

    // Restart the timer
    timer.restart();

    // Wait for another 700 milliseconds
    Thread.sleep(700);

    timer.stop();

    // Check if food value remains the same since isFood is set to false
    assertEquals("Food value should remain the same when isFood is false.", 4, food.getFoodValue());
  }

}
