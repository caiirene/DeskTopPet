package Model.Toy;

import java.util.List;

/**
 * ToyBox is an object stored a List<ToyInterface>
 * pet can add one kind of toy only once, no duplicate allowed
 * remove method not needed for now
 */
public class ToyBox implements ToyBoxInterface{

  private List<ToyInterface> toyList;

  /**
   * add a toy in back of list if no duplicate
   *
   * @param newToy
   */
  @Override
  public void addToy(ToyInterface newToy) {
    for (ToyInterface toy : toyList) {
      if (toy.equal(newToy)) {
        System.out.println("已经有这个玩具了");
        return;
      }
    }
    toyList.add(newToy);
  }

  /**
   * get a toy by checking a string name if have
   *
   * @param toyName
   */
  @Override
  public ToyInterface getToy(String toyName) {
    for (ToyInterface toy : toyList) {
      if (toy.getToyName()==toyName) {
        return toy;
      }
    }
    System.out.println("没有该玩具");
    return null;
  }

  /**
   * just return a List<>
   * @return
   */
  public List<ToyInterface> getToyList() {
    return toyList;
  }

}
