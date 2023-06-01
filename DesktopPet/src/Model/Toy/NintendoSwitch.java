package Model.Toy;

import java.util.List;

public class NintendoSwitch extends AbstractToy{

  private int powerLevel; //0~100, if 0 can't play
  private List<String> gameList; //no time to impl
  private ToyType type = ToyType.DIGITAL;

  public NintendoSwitch() {
    super("NS");
  }

  /**
   * everytime play it, it will lose power of 10
   * 但实际上，我应该计算玩的时间，然后根据时间长短减少电量
   * 但，不可能了
   */
  public void losePower() {
    powerLevel-=10;
  }

  /**
   * no time to impl
   */
  public void recharge() {
    powerLevel+=10;
  }


  /**
   * return toy type
   * @return
   */
  @Override
  public ToyType getToyType() {
    return type;
  }

}
