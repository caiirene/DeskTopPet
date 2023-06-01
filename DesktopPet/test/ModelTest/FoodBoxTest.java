package Model.Food;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for the FoodBox class.
 * FoodBox 类的单元测试。
 */
public class FoodBoxTest {

  private FoodBoxInterface foodBox;
  private FoodInterface apple;
  private FoodInterface strawberry;

  /**
   * Set up the test environment.
   * 设置测试环境。
   */
  @Before
  public void setUp() {
    foodBox = new FoodBox();
    apple = new Apple();
    strawberry = new Strawberry();
  }

  /**
   * Test the addFood method.
   * 测试 addFood 方法。
   */
  @Test
  public void testAddFood() {
    foodBox.addFood(apple);
    foodBox.addFood(strawberry);

    List<FoodInterface> foodList = foodBox.getFoodBox();
    assertEquals(2, foodList.size());
    assertEquals(apple, foodList.get(0));
    assertEquals(strawberry, foodList.get(1));
  }

  /**
   * Test the provideFood method.
   * 测试 provideFood 方法。
   */
  @Test
  public void testProvideFood() {
    foodBox.addFood(apple);
    foodBox.addFood(strawberry);

    FoodInterface providedFood = foodBox.provideFood("apple");
    assertEquals(apple, providedFood);

    List<FoodInterface> foodList = foodBox.getFoodBox();
    assertEquals(1, foodList.size());
    assertEquals(strawberry, foodList.get(0));
  }

  /**
   * Test the getFood method.
   * 测试 getFood 方法。
   */
  @Test
  public void testGetFood() {
    foodBox.addFood(apple);
    foodBox.addFood(strawberry);

    FoodInterface foodAtIndex = foodBox.getFood(1);
    assertEquals(strawberry, foodAtIndex);
  }

  /**
   * Test the getFrontFood method.
   * 测试 getFrontFood 方法。
   */
  @Test
  public void testGetFrontFood() {
    foodBox.addFood(apple);
    foodBox.addFood(strawberry);

    FoodInterface frontFood = foodBox.getFrontFood();
    assertEquals(apple, frontFood);

    List<FoodInterface> foodList = foodBox.getFoodBox();
    assertEquals(1, foodList.size());
    assertEquals(strawberry, foodList.get(0));
  }

  /**
   * Test the getAllListOfFoodName method.
   * 测试 getAllListOfFoodName 方法。
   */
  @Test
  public void testGetAllListOfFoodName() {
    foodBox.addFood(apple);
    foodBox.addFood(strawberry);

    List<String> foodNames = foodBox.getAllListOfFoodName();
    assertEquals(2, foodNames.size());
    assertEquals("apple", foodNames.get(0));
    assertEquals("strawberry", foodNames.get(1));
  }

  /**
   * Test the getDistinctListOfFoodName method.
   * 测试 getDistinctListOfFoodName 方法。
   */
  @Test
  public void testGetDistinctListOfFoodName() {
    foodBox.addFood(apple);
    foodBox.addFood(strawberry);
    foodBox.addFood(new Apple());

    List<String> distinctFoodNames = foodBox.getDistinctListOfFoodName();
    assertEquals(2, distinctFoodNames.size());
    assertTrue(distinctFoodNames.contains("apple"));
    assertTrue(distinctFoodNames.contains("strawberry"));
  }
}
