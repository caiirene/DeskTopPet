package ViewTest;

import View.FoodPenal;
import org.testng.annotations.Test;

import javax.swing.JButton;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for the FoodPanel class.
 */
public class FoodPanelTest {

  /**
   * Tests that the FoodPanel is initialized with the correct number of food buttons.
   */
  @Test
  public void testFoodPenalConstructorWithNonEmptyFoodList() {
    List<String> foodList = Arrays.asList("apple", "strawberry", "diesel", "screwworm");
    FoodPenal foodPenal = new FoodPenal(foodList);

    List<JButton> foodButtonList = foodPenal.getFoodButtonList();

    // Check if the foodButtonList has the same size as the input foodList
    assertEquals("Food button list should have the same size as the input food list", foodList.size(), foodButtonList.size());

    // Check if the buttons have the expected text
    for (int i = 0; i < foodList.size(); i++) {
      assertEquals("Food button text should match the corresponding food name", foodList.get(i), foodButtonList.get(i).getText());
    }
  }

  /**
   * Tests that the FoodPanel initializes with a default food button when the food list is empty.
   */
  @Test
  public void testFoodPenalConstructorWithEmptyFoodList() {
    List<String> emptyFoodList = Arrays.asList();
    FoodPenal foodPenal = new FoodPenal(emptyFoodList);

    List<JButton> foodButtonList = foodPenal.getFoodButtonList();

    // Check if the foodButtonList has exactly 1 button (the default apple button)
    assertEquals("Food button list should have exactly 1 button (the default apple button)", 1, foodButtonList.size());

    // Check if the button has the expected text
    assertEquals("The default button text should be 'apple'", "apple", foodButtonList.get(0).getText());
  }
}
