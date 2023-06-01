package Model.Toy;

public class PowerSource extends AbstractToy{

  private ToyType type = ToyType.MATERIAL;

  public PowerSource() {
    super("power source");
  }

  /**
   * return enum type
   *
   * @return
   */
  @Override
  public ToyType getToyType() {
    return null;
  }

}
