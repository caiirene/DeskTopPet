package Model.Toy;

public interface ToyInterface {


  /**
   * every toy class has the name showing the type(not the enum type)
   * as long as they belong to same class, they have same name, and they are the same
   * @return String toy name
   */
  String getToyName();

  /**
   * return enum type
   * @return
   */
  ToyType getToyType();

  /**
   * as long as they belong to same class, they have same name, and they are the same
   * @param anotherToy
   * @return
   */
  boolean equal(ToyInterface anotherToy);

  /**
   * hashcode base on toy name
   * @return
   */
  int hashcode();

}
