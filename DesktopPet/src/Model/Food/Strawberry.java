package Model.Food;
/**
 * Represents an Strawberry food object, which extends AbstractFood.
 * 表示一个草莓食物对象，继承自 AbstractFood。
 */
public class Strawberry extends AbstractFood {
  /**
   * Constructs an Strawberry food object with specific parameters.
   * 用特定参数构造一个草莓食物对象。
   */
  public Strawberry() {
    super(30,5,"strawberry", FoodType.GOOD);
  }
}
