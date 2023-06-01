package Model.Food;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The FoodBox class represents a collection of FoodInterface objects.
 * It allows users to add, remove, and retrieve food items from the box.
 */
public class FoodBox implements FoodBoxInterface{

  private List<FoodInterface> foodBox;

  public FoodBox() {
    foodBox = new ArrayList<>();
  }

  /**
   * add a pointer to a food object into this box add at back of the list
   * @param food the FoodInterface object to add to the FoodBox
   * @throws IllegalArgumentException if the food parameter is null
   */
  @Override
  public void addFood(FoodInterface food) {
    if (food==null) {throw new IllegalArgumentException();}
    this.foodBox.add(food);
  }

  /**
   * provide a type of food if it is in this box
   * scan from the front remove the food after providing
   * 优先提供还没腐败，但不是很新鲜了的食物
   * @param foodName
   * @return FoodInterface food
   */
  @Override
  public FoodInterface provideFood(String foodName) {
    if (foodBox.size()==0) {
      System.out.println("根本没有食物");
      throw new IllegalArgumentException("There is no food in the box.");
    }
    for (FoodInterface food : foodBox) {
      if (food.getFoodName()==foodName && food.getFoodBoolean()) {
        FoodInterface returnFood = food;
        foodBox.remove(food);
        return returnFood;
      }
    }
    //如果上面没有return我们就再检查一边是否有腐败了的食物
    for (FoodInterface food : foodBox) {
      if (food.getFoodName()==foodName) {
        FoodInterface returnFood = food;
        foodBox.remove(food);
        return returnFood;
      }
    }
    System.out.println("没有该食物");
    return null;
  }

  /**
   * return a food by searching index and then remove it, also free the food
   * 通常来讲不会用这个方法
   * @param index
   * @return
   */
  @Override
  public FoodInterface getFood(int index) {
    if (foodBox.size()-1 < index) {
      System.out.println("超出index范围");
      throw new IllegalArgumentException();
    }
    return foodBox.get(index);
  }

  /**
   * without providing an index, just get a food in index 0 and then remove it
   * 其实对应的是“随便喂一口”
   * @return
   */
  @Override
  public FoodInterface getFrontFood() {
    if (foodBox.size()<=0) {
      System.out.println("空盒子");
      throw new IllegalArgumentException();
    }
    FoodInterface returnFood = foodBox.get(0);
    foodBox.remove(0);
    return returnFood;
  }

  /**
   * search all food, remove those food already have 0 freshness
   */
  @Override
  public void removeBadFood() {
    for (FoodInterface food : foodBox) {
      if (!food.getFoodBoolean()) {
        foodBox.remove(food);
      }
    }
  }

  /**
   * return the entire list
   *
   * @return
   */
  @Override
  public List<FoodInterface> getFoodBox() {
    return foodBox;
  }

  /**
   * map all food to long list of String
   */
  public List<String> getAllListOfFoodName() {
    List<String> foodNames = new ArrayList<>();
    for (FoodInterface food : foodBox) {
      foodNames.add(food.getFoodName());
    }
    return foodNames;
  }

  /**
   * make the string list a set
   * 只有不重复部分，为了方便生成JButton
   * @return
   */
  public List<String> getDistinctListOfFoodName() {
    Set<String> distinctFoodNames = new HashSet<>();
    for (FoodInterface food : foodBox) {
      distinctFoodNames.add(food.getFoodName());
    }
    return new ArrayList<>(distinctFoodNames);
  }


}
