package ModelTest;

import Model.Food.FoodType;
import Model.Food.Screwworm;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * just test on constructor of Screwworm class
 */
public class ScrewwormTest {

  @Test
  public void testScrewwormConstructor() {
    Screwworm screwworm = new Screwworm();

    assertEquals("Screwworm foodValue should be 10", 10, screwworm.getFoodValue());
    assertEquals("Screwworm decayRate should be 5", 5, screwworm.getSpoilRate());
    assertEquals("Screwworm name should be 'screwworm'", "screwworm", screwworm.getFoodName());
    assertEquals("Screwworm type should be BAD", FoodType.BAD, screwworm.getFoodType());
  }
}
