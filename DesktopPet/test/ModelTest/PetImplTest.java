package ModelTest;

import static org.junit.Assert.*;

import Model.Food.Apple;
import Model.Food.FoodInterface;
import Model.Food.FoodType;
import Model.Pet.PetImpl;
import Model.Pet.PetInterface;
import Model.Toy.ToyBox;
import Model.Toy.ToyBoxInterface;
import Model.Toy.ToyInterface;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/**
 * test for pet
 */
public class PetImplTest {
  private PetInterface pet;

  @Before
  public void setUp() {
    pet = new PetImpl();
  }

  /**
   * getName method test
   */
  @Test
  public void testName() {
    String expectedName = "TestPet";
    pet.setName(expectedName);
    assertEquals(expectedName, pet.getName());
  }

  /**
   * setName method with null parameter
   * should throw exceptions
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNameNull() {
    pet.setName(null);
  }

  /**
   * test setAge then getAge
   */
  @Test
  public void testAge() {
    int expectedAge = 10;
    pet.setAge(expectedAge);
    assertEquals(expectedAge, pet.getAge());
  }

  /**
   * since we don't allow negative age,
   * exception should be thrown
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAgeNegative() {
    pet.setAge(-1);
  }

  /**
   * test setter and getter for hunger
   */
  @Test
  public void testHunger() {
    int expectedHunger = 50;
    pet.setHunger(expectedHunger);
    assertEquals(expectedHunger, pet.getHunger());
  }

  /**
   * test eat good food
   */
  @Test
  public void testEat() {
    int initialHunger = pet.getHunger();
    FoodInterface food = new Apple();
    pet.eat(food);
    assertEquals(initialHunger + food.getRealFoodValue(), pet.getHunger());
  }

  /**
   * test eat with null para
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEatNull() {
    pet.eat(null);
  }


  /**
   * test getter method for hunger
   */
  @Test
  public void testGetHunger() {
    int initialHunger = pet.getHunger();
    assertEquals("Initial hunger should be 100.", 100, initialHunger);
  }

  /**
   * test for getHappiness 方法
   */
  @Test
  public void testGetHappiness() {
    int initialHappiness = pet.getHappiness();
    assertEquals("Initial happiness should be 100.", 100, initialHappiness);
  }

  /**
   * test for addDream()
   * the dream added should be formatted
   */
  @Test
  public void testAddDream() {
    String dream = "I want to be a super pet!";
    pet.addDream(dream);
    List<String> dreams = pet.getDreams();
    assertFalse("Dream list should not contain the original dream.", dreams.contains(dream));

    // Check if the formatted dream is added to the dream list
    boolean foundFormattedDream = false;
    for (String formattedDream : dreams) {
      if (formattedDream.contains(dream)) {
        foundFormattedDream = true;
        break;
      }
    }
    assertTrue("Dream list should contain the formatted dream.", foundFormattedDream);
  }

  /**
   * test for GetDeathStatus method
   */
  @Test
  public void testGetDeathStatus() {
    boolean initialDeathStatus = pet.getDeathStatus();
    assertFalse("Initial death status should be false.", initialDeathStatus);
    pet.setHealth(0);
    pet.checkDeath();
    initialDeathStatus = pet.getDeathStatus();
    assertTrue("Initial death status should be false.", initialDeathStatus);
  }

  /**
   * test for getHealth() method
   */
  @Test
  public void testGetHealth() {
    int initialHealth = pet.getHealth();
    assertEquals("Initial health should be 100.", 100, initialHealth);
  }

  /**
   * add all possible string to list
   * then test speakRandomly method
   */
  @Test
  public void testSpeakRandomly() {
    Set<String> expectedResponses = new HashSet<>(Arrays.asList(
        "<html>I have came to this world for " + pet.getAge() + " days.<br>Every day of being here is so happy for me.</html>",
        "I miss you so much, are you missing me?",
        "<html>I think you are the one<br>I will love forever in this world,<br>don't you think so?</html>",
        "<html>Are you taking good care of yourself?<br>If not, I will worry about you so much.</html>",
        "I love you."
    ));

    for (int i = 0; i < 100; i++) {
      String response = pet.speakRandomly();
      assertTrue("Unexpected response: " + response, expectedResponses.contains(response));
    }
  }

  /**
   * similar to last test
   * test on sayThankYou() method
   */
  @Test
  public void testSayThankYou() {
    Set<String> possibleResponses = new HashSet<>();
    possibleResponses.add("Thank you~");
    possibleResponses.add("(#^.^#)~Thanks");
    possibleResponses.add("I am so happy~");
    possibleResponses.add("I think I am the luckiest little \npet in the world.");

    String response = pet.sayThankYou();
    assertTrue("Response should be one of the possible responses.", possibleResponses.contains(response));
  }

  /**
   * test sayHungry method
   */
  @Test
  public void testSayHungry() {
    String response = pet.sayHungry();
    assertEquals("Response should be 'I am little bit hungry now...'", "I am little bit hungry now...", response);
  }

  /**
   * test sayLonely method
   */
  @Test
  public void testSayLonely() {
    String response = pet.sayLonely();
    assertEquals("Response should be 'Would you mind... play with me\n for a while, just a little while?'", "Would you mind... play with me\n for a while, just a little while?", response);
  }

  /**
   * test sayLastWord
   */
  @Test
  public void testSayTheLastWord() {
    pet.setAge(10);
    pet.addDream("Dream 1");
    pet.addDream("Dream 2");

    String expected = "<html>I am 10 days old now. It was a great<br>"
        + "time to meet you in this world.<br>"
        + "During these 10 days,<br>"
        + "you told me 2 dreams. <br>"
        + "And those dream accompanied me over these 10 nights.<br>"
        + "They are the most precious treasures of mine.<br>"
        + "I have kept all of them for you.<br>"
        + "I love you. Goodbye.</html>";

    String response = pet.sayTheLastWord();
    assertEquals("Response should match the expected string.", expected, response);
  }

  /**
   * testLoseHealth_GetTiredWhileTimePass
   */
  @Test
  public void testLoseHealth_GetTiredWhileTimePass() {
    pet.setHealth(50);
    pet.loseHealth_GetTiredWhileTimePass();
    assertEquals("Health should be reduced by 15.", 35, pet.getHealth());
  }

  /**
   * testLoseHungerWhileTimePass
   */
  @Test
  public void testLoseHungerWhileTimePass() {
    pet.setHunger(80);
    pet.loseHungerWhileTimePass();
    assertEquals("Hunger should be reduced by 25.", 55, pet.getHunger());
  }

  /**
   * test for checkDeath method
   */
  @Test
  public void testCheckDeath() {
    pet.setHealth(10);
    pet.checkDeath();
    assertFalse("Pet should not be dead with health > 0.", pet.getDeathStatus());

    pet.setHealth(0);
    final boolean[] deadPropertyChanged = {false};
    pet.addPropertyChangeListener( new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        deadPropertyChanged[0] = true;
      }
    });
    pet.checkDeath();
    assertTrue("Pet should be dead with health == 0.", pet.getDeathStatus());
    assertTrue("PropertyChangeListener should be notified of the 'dead' property change.", deadPropertyChanged[0]);
  }

}