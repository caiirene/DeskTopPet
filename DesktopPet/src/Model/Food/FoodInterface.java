package Model.Food;

/**
 * FoodBox is a List<Food>.
 * FoodBox 是一个 List<Food>。
 * Food stored here is just a pointer to the real food object.
 * 存储在此处的食物只是指向实际食物对象的指针。
 */
public interface FoodInterface {

  /**
   * Add a pointer to a food object into this box.
   * 将食物对象的指针添加到此盒子中。
   * Add at the back of the list.
   * 添加至列表末尾。
   */
  int getFoodValue();

  /**
   * Returns the food type.
   * 返回食物类型。
   *
   * @return FoodType
   */
  FoodType getFoodType();

  /**
   * A food will have a boolean field to indicate whether it is still eatable or not.
   * 如果食物对象在新鲜度降至 0 时具有 false FoodBoolean，则布尔值为 true。
   *
   * @return boolean
   */
  boolean getFoodBoolean();

  /**
   * Food will lose freshness while time passes.
   * 虽然这里管这个方法叫作随时间流逝而腐烂，但实际上只是腐烂，这里不涉及 timer。
   * Food's rot rate will be considered.
   */
  void lossFoodValueWhileTimePass();

  /**
   * Returns the rot rate.
   * 返回腐烂速率。
   * Rot rate means a food will lose this amount of freshness every 60 seconds.
   *
   * @return int
   */
  int getSpoilRate();

  /**
   * Sets the rot rate.
   * 设置腐烂速率。
   * Although a food object will have an initial rot rate, you can set it if you want.
   * 我们允许负数速率。
   *
   * @param rate the new rot rate
   */
  void setSpoilRate(int rate);

  /**
   * Returns the calculated food value.
   * 返回计算过的食物价值。
   *
   * @return int food value after calculating the freshness
   */
  int getRealFoodValue();

  /**
   * Resets the freshness of the food to 1.00.
   * 重置食物的新鲜度为 1.00。
   */
  void resetToFresh();

  /**
   * Returns the food name, such as apple, banana, etc.
   * 返回食物名称，如苹果、香蕉等。
   *
   * @return String
   */
  String getFoodName();

}
