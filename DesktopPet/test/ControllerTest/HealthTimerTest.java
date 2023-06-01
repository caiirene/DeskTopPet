package ControllerTest;

import Controller.HealthTimer;
import Model.Pet.MockPet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * this test might be unstable
 * Due to the imprecision of timers, test cases may produce different results in different runs.
 */
public class HealthTimerTest {

  @Test
  public void testHealthTimer() throws InterruptedException {
    MockPet pet = new MockPet(5, 100,100);
    int interval = 1000; // 1 second
    HealthTimer timer = new HealthTimer(interval, pet);

    timer.start();
    Thread.sleep(interval * 2 + 100); // Sleep for slightly more than 2 intervals

    timer.stop();

    int expectedHealth = 70;
    assertEquals("Pet health should be reduced by 30 after two intervals.", expectedHealth, pet.getHealth());
  }
}
