package ViewTest;

import View.TotalView;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * the only part can be tested is showSpeach method
 */
public class TotalViewTest {

  @Test
  public void testShowSpeech() {
    TotalView totalView = new TotalView();
    String expectedSpeech = "Hello, I am your pet!";
    totalView.showSpeach(expectedSpeech);
    assertEquals(expectedSpeech, totalView.getPetSpeechLabel().getText());
  }

}
