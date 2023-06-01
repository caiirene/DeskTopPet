package ControllerTest;

import Controller.SpeakTimer;
import Model.Pet.MockPet;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * this test might be unstable
 * Due to the imprecision of timers, test cases may produce different results in different runs.
 */
public class SpeakTimerTest {

  @Test
  public void testSpeakTimer() throws InterruptedException {
    MockPet pet = new MockPet(5, 100, 100);
    MockTotalView view = new MockTotalView();
    int interval = 1; // 1 second
    SpeakTimer timer = new SpeakTimer(interval, pet, view);

    timer.start();
    Thread.sleep(10000); // Sleep for 10 seconds

    timer.stop();

    int speechCount = view.getSpeechCount();
    // Due to the random nature of the intervals, it's not possible to predict the exact count,
    // but we can verify that some speeches have occurred within the given time frame.
    assertTrue("Pet should have spoken at least once during the 10 seconds.", speechCount > 0);
  }
}
