package Model.Food;

/**
 * Abstract food class including nearly all required methods.
 * 抽象食物类，包括几乎所有需要的方法。
 * The most important method is the getRealFoodValue method.
 * 最重要的方法是 getRealFoodValue 方法。
 * It has a constructor, but must be constructed in concrete class.
 * 它有一个构造器，但必须在具体的类中构造。
 * Concrete class has all the default parameters.
 * 具体类包含所有默认参数。
 */
public abstract class AbstractFood implements FoodInterface {

  protected int foodValue;
  protected int realFoodValue;
  protected int spoilRate;
  public boolean notRotten;
  protected FoodType type;
  protected String foodName;

  public AbstractFood(int foodValue, int spoilRate, String foodName, FoodType type) {
    this.realFoodValue = foodValue;
    this.foodValue = foodValue;
    this.spoilRate = spoilRate;
    this.type = type;
    this.foodName = foodName;
  }

  /**
   * Returns the original food value.
   * 返回食物原本的价值。
   *
   * @return int the food value of original
   */
  @Override
  public int getFoodValue() {
    return foodValue;
  }

  /**
   * Returns the food type.
   * 返回食物类型。
   *
   * @return FoodType
   */
  @Override
  public FoodType getFoodType() {
    return type;
  }

  /**
   * A food will have a boolean field to indicate whether it is still eatable or not.
   * 食物将具有一个布尔字段，表示它是否仍然可以食用。
   * If a food object has false FoodBoolean when freshness gets to 0, else boolean is true.
   * 如果食物对象在新鲜度降至 0 时具有 false FoodBoolean，则布尔值为 true。
   *
   * @return boolean
   */
  @Override
  public boolean getFoodBoolean() {
    return notRotten;
  }

  /**
   * Food will lose freshness while time passes.
   * 食物会在时间流逝时失去新鲜度。
   * Although this method is called "lossFoodValueWhileTimePass", it only deals with decay and does not involve a timer.
   * 虽然这个方法叫作 "lossFoodValueWhileTimePass"，但它实际上只处理腐烂，不涉及计时器。
   * The decay depends on the food's rot rate.
   * 腐烂取决于食物的腐烂速率。
   */
  @Override
  public void lossFoodValueWhileTimePass() {
    realFoodValue = Math.max(realFoodValue - spoilRate, 0);
    if (realFoodValue <= 0) {
      notRotten = false;
    }
  }

  /**
   * Returns the rot rate.
   * 返回腐烂速率。
   * Rot rate means a food will lose this amount of freshness every 60 seconds.
   * 腐烂速率意味着食物每 60 秒失去这一部分新鲜度。
   *
   * @return int
   */
  @Override
  public int getSpoilRate() {
    return spoilRate;
  }

  /**
   * Sets the rot rate.
   * 设置腐烂速率。
   * Although a food object will have an initial rot rate, you can set it if you want.
   * 虽然食物对象会有一个初始的腐烂速率，但如果您想要，可以设置它。
   * We allow negative rate.
   * 我们允许负数速率。
   *
   * @param rate the new rot rate
   */
  @Override
  public void setSpoilRate(int rate) {
    this.spoilRate = rate;
  }

  /**
   * Returns the calculated food value.
   * 返回计算过的食物价值。
   *
   * @return int food value after calculating the freshness
   */
  @Override
  public int getRealFoodValue() {
    return realFoodValue;
  }

  /**
   * Resets the freshness of the food to 1.00.
   * 重置食物的新鲜度为 1.00。
   */
  @Override
  public void resetToFresh() {
    this.realFoodValue = this.foodValue;
  }

  /**
   * Returns the food name, such as apple, banana, etc.
   * 返回食物名称，如苹果、香蕉等。
   *
   * @return String
   */
  @Override
  public String getFoodName() {
    return foodName;
  }
}
