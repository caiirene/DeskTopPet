package ViewTest;

import View.BasicPenal;
import org.junit.Test;

import javax.swing.JButton;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * can only test the button not null
 * and test on text of button
 */
public class BasicPanelTest {

  @Test
  public void testBasicPenalConstructor() {
    BasicPenal basicPenal = new BasicPenal();

    JButton eatButton = basicPenal.getEatButton();
    JButton playButton = basicPenal.getPlayButton();
    JButton dreamButton = basicPenal.getDreamButton();

    // Check if buttons are created
    assertNotNull("Eat button should not be null", eatButton);
    assertNotNull("Play button should not be null", playButton);
    assertNotNull("Dream button should not be null", dreamButton);

    // Check if buttons have expected text
    assertEquals("Eat button text should be 'Feed'", "Feed", eatButton.getText());
    assertEquals("Play button text should be 'Play'", "Play", playButton.getText());
    assertEquals("Dream button text should be 'Let's dream'", "Let's dream", dreamButton.getText());
  }
}
