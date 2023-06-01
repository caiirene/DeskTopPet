package Model.Toy;

/**
 * it is a material
 * if pet have wood cutting tool,
 * woodBlock can create a new LegoBlock object and add to toy box
 */
public class WoodBlock implements ToyInterface{

  private int amount;
  private ToyType type;
  private String toyName;

  public WoodBlock() {
    amount = 10;
    type = ToyType.MATERIAL;
    toyName = "Wood Block";
  }
  /**
   * every toy class has the name showing the type(not the enum type) as long as they belong to same
   * class, they have same name, and they are the same
   *
   * @return String toy name
   */
  @Override
  public String getToyName() {
    return toyName;
  }

  /**
   * return enum type
   *
   * @return
   */
  @Override
  public ToyType getToyType() {
    return type;
  }

  /**
   * as long as they belong to same class, they have same name, and they are the same
   *
   * @param anotherToy
   * @return
   */
  @Override
  public boolean equal(ToyInterface anotherToy) {
    return this.toyName==anotherToy.getToyName();
  }

  /**
   * hashcode base on toy name
   *
   * @return
   */
  @Override
  public int hashcode() {
    return 0;
  }
}
