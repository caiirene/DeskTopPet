package Model.Toy;

import java.util.List;

public interface ToyBoxInterface {

  /**
   * add a toy in back of list if no duplicate
   */
  void addToy(ToyInterface toy);

  /**
   * get a toy by checking a string name if have
   */
  ToyInterface getToy(String toyName);

  /**
   * just return a List<>
   * @return
   */
  List<ToyInterface> getToyList();

}
