package Model.Food;
/**
 * Represents a Diesel food object, which extends AbstractFood.
 * 表示一个苹果食物对象，继承自 AbstractFood。
 */
public class Diesel extends AbstractFood {

  /**
   * Constructs a Diesel food object with specific parameters.
   * 用特定参数构造一个柴油食物对象。
   */
  public Diesel() {
    super(-50, 0, "diesel", FoodType.POISON);
  }
}
