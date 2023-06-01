package Model.Food;
/**
 * Represents an Screwworm food object, which extends AbstractFood.
 * 表示一个蛆虫食物对象，继承自 AbstractFood。
 */
public class Screwworm extends AbstractFood {

  /**
   * Constructs an Screwworm food object with specific parameters.
   * 用特定参数构造一个蛆虫食物对象。
   */
  public Screwworm() {
    super(10,5,"screwworm", FoodType.BAD);
  }
}
