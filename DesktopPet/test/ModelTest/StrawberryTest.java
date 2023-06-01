package ModelTest;

import Model.Food.FoodType;
import Model.Food.Strawberry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the Apple class.
 * Strawberry 类的测试类。
 */
public class StrawberryTest {
  private Strawberry strawberry;

  /**
   * Set up the Strawberry object before running tests.
   * 在运行测试前设置 Strawberry 对象。
   */
  @Before
  public void setUp() {
    strawberry = new Strawberry();
    strawberry.notRotten = true;
  }

  /**
   * Test the Strawberry constructor.
   * 测试 Strawberry 构造函数。
   */
  @Test
  public void testAppleConstructor() {
    assertEquals(30, strawberry.getFoodValue());
    assertEquals(5, strawberry.getSpoilRate());
    assertEquals("strawberry", strawberry.getFoodName());
    assertEquals(FoodType.GOOD, strawberry.getFoodType());
    assertTrue(strawberry.getFoodBoolean());
  }

  /**
   * Test the freshness reset of the Apple object.
   * 测试 Apple 对象的新鲜度重置。
   */
  @Test
  public void testResetToFresh() {
    strawberry.lossFoodValueWhileTimePass(); // Simulate decay
    strawberry.resetToFresh(); // Reset freshness
    assertEquals(30, strawberry.getRealFoodValue());
    assertTrue(strawberry.getFoodBoolean());
  }
}
