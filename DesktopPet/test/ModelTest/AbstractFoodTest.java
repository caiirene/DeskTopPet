package Model.Food;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the AbstractFood class.
 * AbstractFood 类的单元测试。
 */
public class AbstractFoodTest {

  private static final int FOOD_VALUE = 20;
  private static final int SPOIL_RATE = 1;
  private static final String FOOD_NAME = "testFood";
  private static final FoodType FOOD_TYPE = FoodType.GOOD;

  private AbstractFood testFood;


  /**
   * Set up the test environment.
   * 设置测试环境。
   */
  @Before
  public void setUp() {
    testFood = new AbstractFood(FOOD_VALUE, SPOIL_RATE, FOOD_NAME, FOOD_TYPE) {
    };
    testFood.notRotten = true;
  }

  /**
   * Test the getFoodValue method.
   * 测试 getFoodValue 方法。
   */
  @Test
  public void testGetFoodValue() {
    assertEquals(FOOD_VALUE, testFood.getFoodValue());
  }

  /**
   * Test the getSpoilRate method.
   * 测试 getSpoilRate 方法。
   */
  @Test
  public void testGetSpoilRate() {
    assertEquals(SPOIL_RATE, testFood.getSpoilRate());
  }

  /**
   * Test the getFoodName method.
   * 测试 getFoodName 方法。
   */
  @Test
  public void testGetFoodName() {
    assertEquals(FOOD_NAME, testFood.getFoodName());
  }

  /**
   * Test the getFoodType method.
   * 测试 getFoodType 方法。
   */
  @Test
  public void testGetFoodType() {
    assertEquals(FOOD_TYPE, testFood.getFoodType());
  }

  /**
   * Test the getFoodBoolean method.
   * 测试 getFoodBoolean 方法。
   */
  @Test
  public void testGetFoodBoolean() {
    assertTrue(testFood.getFoodBoolean());
  }

  /**
   * Test the lossFoodValueWhileTimePass method.
   * 测试 lossFoodValueWhileTimePass 方法。
   */
  @Test
  public void testLossFoodValueWhileTimePass() {
    testFood.lossFoodValueWhileTimePass();
    assertEquals(FOOD_VALUE - SPOIL_RATE, testFood.getRealFoodValue());
    assertTrue(testFood.getFoodBoolean());

    for (int i = 0; i < FOOD_VALUE / SPOIL_RATE; i++) {
      testFood.lossFoodValueWhileTimePass();
    }
    assertEquals(0, testFood.getRealFoodValue());
    assertFalse(testFood.getFoodBoolean());
  }

  /**
   * Test the setSpoilRate method.
   * 测试 setSpoilRate 方法。
   */
  @Test
  public void testSetSpoilRate() {
    int newSpoilRate = 2;
    testFood.setSpoilRate(newSpoilRate);
    assertEquals(newSpoilRate, testFood.getSpoilRate());
  }

  /**
   * Test the resetToFresh method.
   * 测试 resetToFresh 方法。
   */
  @Test
  public void testResetToFresh() {
    testFood.lossFoodValueWhileTimePass();
    testFood.resetToFresh();
    assertEquals(FOOD_VALUE, testFood.getRealFoodValue());
  }
}
