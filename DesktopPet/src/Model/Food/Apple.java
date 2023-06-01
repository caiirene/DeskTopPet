package Model.Food;

/**
 * Represents an Apple food object, which extends AbstractFood.
 * 表示一个苹果食物对象，继承自 AbstractFood。
 */
public class Apple extends AbstractFood {

  /**
   * Constructs an Apple food object with specific parameters.
   * 用特定参数构造一个苹果食物对象。
   */
  public Apple() {
    super(20, 1, "apple", FoodType.GOOD);
  }
}
