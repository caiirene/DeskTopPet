package Model.Toy;

import java.util.List;

public class Plasticine extends AbstractToy{


  private ToyType type = ToyType.CHILD_CRAFT;
  private int wetLevel = 100;

  public Plasticine() {
    super("plasticine");
  }


  /**
   * return enum type
   *
   * @return
   */
  @Override
  public ToyType getToyType() {
    return ToyType.CHILD_CRAFT;
  }

  /**
   * if the plasticine become dry, it can not be played
   * @return
   */
  public boolean canPlay() {
    return wetLevel>0;
  }

  /**
   * it will get dry everytime be played
   */
  public void loseWetLevel() {
    wetLevel-=10;
  }

}
