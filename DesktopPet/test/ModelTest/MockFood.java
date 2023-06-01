package ModelTest;

import Model.Food.FoodInterface;
import Model.Food.FoodType;

/**
 * just a mock class for test only
 */
public class MockFood implements FoodInterface {
  private int foodValue;
  private FoodType foodType;

  public MockFood(int foodValue, FoodType foodType) {
    this.foodValue = foodValue;
    this.foodType = foodType;
  }

  @Override
  public int getRealFoodValue() {
    return foodValue;
  }

  @Override
  public void resetToFresh() {

  }

  @Override
  public String getFoodName() {
    return null;
  }

  @Override
  public int getFoodValue() {
    return 0;
  }

  @Override
  public FoodType getFoodType() {
    return foodType;
  }


  @Override
  public boolean getFoodBoolean() {
    return false;
  }

  @Override
  public void lossFoodValueWhileTimePass() {

  }

  @Override
  public int getSpoilRate() {
    return 0;
  }

  @Override
  public void setSpoilRate(int rate) {

  }
}