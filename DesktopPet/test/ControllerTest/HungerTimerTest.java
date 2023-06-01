package ControllerTest;

import Controller.HungerTimer;
import Model.Pet.MockPet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * this test might be unstable
 * Due to the imprecision of timers, test cases may produce different results in different runs.
 */
public class HungerTimerTest {

  @Test
  public void testHungerTimer() throws InterruptedException {
    MockPet pet = new MockPet(5, 100, 100);
    int interval = 1000; // 1 second
    HungerTimer timer = new HungerTimer(interval, pet);

    timer.start();
    Thread.sleep(interval * 2 + 100); // Sleep for slightly more than 2 intervals

    timer.stop();

    int expectedHunger = 50;
    assertEquals("Pet hunger should be reduced by 50 after two intervals.", expectedHunger, pet.getHunger());
  }
}
