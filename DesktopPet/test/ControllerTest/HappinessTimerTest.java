package ControllerTest;

import Controller.HappinessTimer;
import Model.Pet.MockPet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * this test might be unstable
 * Due to the imprecision of timers, test cases may produce different results in different runs.
 */
public class HappinessTimerTest {

  @Test
  public void testHappinessTimer() throws InterruptedException {
    MockPet pet = new MockPet(5,100,100);
    int interval = 1000; // 1 second
    HappinessTimer timer = new HappinessTimer(interval, pet);

    timer.start();
    Thread.sleep(interval * 2 + 100); // Sleep for slightly more than 2 intervals

    timer.stop();

    int expectedHappiness = 3;
    assertEquals("Pet happiness should be reduced by 2 after two intervals.", expectedHappiness, pet.getHappiness());
  }
}
