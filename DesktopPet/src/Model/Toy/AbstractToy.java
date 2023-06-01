package Model.Toy;

public abstract class AbstractToy implements ToyInterface{

  protected String toyName;

  public AbstractToy(String toyName) {
    this.toyName = toyName;
  }
  public String getToyName() {
    return toyName;
  }

  /**
   * as long as they belong to same class, they have same name, and they are the same
   * @param anotherToy
   * @return
   */
  @Override
  public boolean equal(ToyInterface anotherToy) {
    return toyName==anotherToy.getToyName();
  }

  /**
   * hashcode base on toy name
   * @return
   */
  @Override
  public int hashcode() {
    return toyName.hashCode();
  }
}
