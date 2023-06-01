package Controller;

import Model.Pet.PetInterface;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HappinessTimer is a Timer subclass that is responsible for decreasing a pet's
 * happiness over time. It takes an interval and a PetInterface object as input.
 * When the timer ticks, it calls the pet's loseHappinessWhileTimePass method to
 * decrease the pet's happiness. The timer will continue to run until it is stopped
 * explicitly.
 */
public class HappinessTimer extends Timer {

  private PetInterface pet;

  /**
   * Constructs a HappinessTimer with the specified interval and pet.
   *
   * @param interval  the interval between timer ticks, in milliseconds
   * @param pet       the PetInterface object whose happiness will be decreased over time
   */
  public HappinessTimer(int interval, PetInterface pet) {
    //interval = interval*1000;
    super(interval, null);
    this.pet = pet;
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        pet.loseHappinessWhileTimePass();
      }
    });
    //start();
  }
}
