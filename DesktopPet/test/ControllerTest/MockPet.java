package Model.Pet;

import Model.Food.FoodInterface;
import Model.Toy.ToyBoxInterface;
import Model.Toy.ToyInterface;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * just a mock class for PetInterface
 * used for timer test
 */
public class MockPet implements PetInterface {

  private int happiness;
  private int health;
  private int hunger;

  public MockPet(int happiness, int health, int hunger) {
    this.happiness = happiness;
    this.health = health;
    this.hunger = hunger;
  }
  @Override
  public String speakRandomly() {
    return "Hello!";
  }

  @Override
  public void loseHungerWhileTimePass() {
    hunger = Math.max(hunger - 25, 0);
  }

  public int getHunger() {
    return hunger;
  }

  @Override
  public void loseHealth_GetTiredWhileTimePass() {
    health = Math.max(health - 15, 0);
  }

  public int getHealth() {
    return health;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public void setName(String name) {

  }

  @Override
  public int getAge() {
    return 0;
  }

  @Override
  public void setAge(int age) {

  }

  @Override
  public void setHunger(int hunger) {

  }

  @Override
  public void eat(FoodInterface food) {

  }

  @Override
  public void play(ToyInterface toy) {

  }

  @Override
  public void sleep() {

  }

  @Override
  public void weakUp() {

  }

  @Override
  public void addDream(String dream) {

  }

  @Override
  public boolean getDeathStatus() {
    return false;
  }

  @Override
  public ToyBoxInterface getToyBox() {
    return null;
  }

  @Override
  public void setToyBox(ToyBoxInterface toyBox) {

  }

  @Override
  public String sayThankYou() {
    return null;
  }

  @Override
  public String sayHungry() {
    return null;
  }

  @Override
  public String sayLonely() {
    return null;
  }

  @Override
  public String sayTheLastWord() {
    return null;
  }

  @Override
  public void loseHappinessWhileTimePass() {
    happiness = Math.max(happiness - 1, 0);
  }

  @Override
  public void checkDeath() {

  }

  @Override
  public void increaseHappiness() {

  }

  @Override
  public void eatApple() {

  }

  @Override
  public void generateTxtFileFromDreams() {

  }

  @Override
  public PropertyChangeSupport getSupport() {
    return null;
  }

  public int getHappiness() {
    return happiness;
  }

  @Override
  public void setHealth(int health) {

  }

  @Override
  public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {

  }

  @Override
  public List<String> getDreams() {
    return null;
  }

  // ...其他方法的实现...
}
