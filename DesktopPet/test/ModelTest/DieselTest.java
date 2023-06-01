package ModelTest;

import Model.Food.FoodType;
import Model.Food.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the Apple class.
 * Strawberry 类的测试类。
 */
public class DieselTest {
  private Diesel diesel;

  /**
   * Set up the Strawberry object before running tests.
   * 在运行测试前设置 Strawberry 对象。
   */
  @Before
  public void setUp() {
    diesel = new Diesel();
    diesel.notRotten = true;
  }

  /**
   * Test the Strawberry constructor.
   * 测试 Strawberry 构造函数。
   */
  @Test
  public void testAppleConstructor() {
    assertEquals(-50, diesel.getFoodValue());
    assertEquals(0, diesel.getSpoilRate());
    assertEquals("diesel", diesel.getFoodName());
    assertEquals(FoodType.POISON, diesel.getFoodType());
    assertTrue(diesel.getFoodBoolean());
  }

  /**
   * Test the freshness reset of the Apple object.
   * 测试 Apple 对象的新鲜度重置。
   */
  @Test
  public void testResetToFresh() {
    diesel.lossFoodValueWhileTimePass(); // Simulate decay
    diesel.resetToFresh(); // Reset freshness
    assertEquals(-50, diesel.getRealFoodValue());
  }
}
