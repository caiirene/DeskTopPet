package ControllerTest;

import Model.Food.FoodInterface;
import Model.Food.FoodType;

/**
 * just a mock class of food
 */
public class TestFood implements FoodInterface {
  private int foodValue;
  private boolean isFood;

  public TestFood(int foodValue, boolean isFood) {
    this.foodValue = foodValue;
    this.isFood = isFood;
  }


  @Override
  public int getFoodValue() {
    return this.foodValue;
  }

  /**
   * Returns the food type. 返回食物类型。
   *
   * @return FoodType
   */
  @Override
  public FoodType getFoodType() {
    return null;
  }

  @Override
  public boolean getFoodBoolean() {
    return this.isFood;
  }

  @Override
  public void lossFoodValueWhileTimePass() {
    this.foodValue--;
  }

  /**
   * Returns the rot rate. 返回腐烂速率。 Rot rate means a food will lose this amount of freshness every 60
   * seconds.
   *
   * @return int
   */
  @Override
  public int getSpoilRate() {
    return 0;
  }

  /**
   * Sets the rot rate. 设置腐烂速率。 Although a food object will have an initial rot rate, you can set it
   * if you want. 我们允许负数速率。
   *
   * @param rate the new rot rate
   */
  @Override
  public void setSpoilRate(int rate) {

  }

  /**
   * Returns the calculated food value. 返回计算过的食物价值。
   *
   * @return int food value after calculating the freshness
   */
  @Override
  public int getRealFoodValue() {
    return 0;
  }

  /**
   * Resets the freshness of the food to 1.00. 重置食物的新鲜度为 1.00。
   */
  @Override
  public void resetToFresh() {

  }

  /**
   * Returns the food name, such as apple, banana, etc. 返回食物名称，如苹果、香蕉等。
   *
   * @return String
   */
  @Override
  public String getFoodName() {
    return null;
  }

  public void setIsFood(boolean b) {
    isFood = b;
  }
}
