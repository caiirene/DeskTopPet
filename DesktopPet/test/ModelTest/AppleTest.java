package Model.Food;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the Apple class.
 * Apple 类的测试类。
 */
public class AppleTest {
  private Apple apple;

  /**
   * Set up the Apple object before running tests.
   * 在运行测试前设置 Apple 对象。
   */
  @Before
  public void setUp() {
    apple = new Apple();
    apple.notRotten = true;
  }

  /**
   * Test the Apple constructor.
   * 测试 Apple 构造函数。
   */
  @Test
  public void testAppleConstructor() {
    assertEquals(20, apple.getFoodValue());
    assertEquals(1, apple.getSpoilRate());
    assertEquals("apple", apple.getFoodName());
    assertEquals(FoodType.GOOD, apple.getFoodType());
    assertTrue(apple.getFoodBoolean());
  }

  /**
   * Test the freshness reset of the Apple object.
   * 测试 Apple 对象的新鲜度重置。
   */
  @Test
  public void testResetToFresh() {
    apple.lossFoodValueWhileTimePass(); // Simulate decay
    apple.resetToFresh(); // Reset freshness
    assertEquals(20, apple.getRealFoodValue());
    assertTrue(apple.getFoodBoolean());
  }
}
