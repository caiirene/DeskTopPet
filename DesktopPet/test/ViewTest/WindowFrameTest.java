package ViewTest;

import View.WindowFrame;
import org.junit.Test;

import javax.swing.ImageIcon;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the WindowFrame class.
 */
public class WindowFrameTest {

  /**
   * Tests if the pet's gif is updated based on its hunger.
   */
  @Test
  public void testUpdateGifBasedOnHunger() {
    WindowFrame windowFrame = new WindowFrame();

    // Test low hunger and happiness
    windowFrame.updateGifBasedOnHunger(50, 30);
    assertEquals("Gif should be lowHungerGifPath", windowFrame.lowHungerGifPath, ((ImageIcon) windowFrame.getPetLabel().getIcon()).getDescription());

    // Test normal hunger and happiness
    windowFrame.updateGifBasedOnHunger(70, 50);
    assertEquals("Gif should be normalGifPath", windowFrame.normalGifPath, ((ImageIcon) windowFrame.getPetLabel().getIcon()).getDescription());
  }

  /**
   * Tests if the pet's gif is updated based on its health.
   */
  @Test
  public void testUpdateGifBasedOnHealth() {
    WindowFrame windowFrame = new WindowFrame();

    // Test dead
    windowFrame.updateGifBasedOnHealth(0);
    assertEquals("Gif should be deadGifPath", windowFrame.deadGifPath, ((ImageIcon) windowFrame.getPetLabel().getIcon()).getDescription());

    windowFrame.resetGif();
    // Test alive
    windowFrame.updateGifBasedOnHealth(10);
    assertEquals("Gif should be normalGifPath", windowFrame.normalGifPath, ((ImageIcon) windowFrame.getPetLabel().getIcon()).getDescription());
  }
}