package Controller;

import Model.Pet.PetInterface;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HungerTimer is a Timer subclass that is responsible for decreasing a pet's
 * hunger over time. It takes an interval and a PetInterface object as input.
 * When the timer ticks, it calls the pet's loseHungerWhileTimePass method to
 * decrease the pet's hunger. The timer will continue to run until it is stopped
 * explicitly.
 */
public class HungerTimer extends Timer {
  private PetInterface pet;

  /**
   * Constructs a HungerTimer with the specified interval and pet.
   *
   * @param interval  the interval between timer ticks, in milliseconds
   * @param pet       the PetInterface object whose hunger will be decreased over time
   */
  public HungerTimer(int interval, PetInterface pet) {

    super(interval, null);
    this.pet = pet;
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        pet.loseHungerWhileTimePass();
      }
    });
  }
}
