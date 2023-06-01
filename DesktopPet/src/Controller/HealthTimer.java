package Controller;

import Model.Pet.PetInterface;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * HealthTimer is a Timer subclass that is responsible for decreasing a pet's
 * health over time. It takes an interval and a PetInterface object as input.
 * When the timer ticks, it calls the pet's loseHealth_GetTiredWhileTimePass method to
 * decrease the pet's health. The timer will continue to run until it is stopped
 * explicitly.
 */
public class HealthTimer extends Timer {

  private PetInterface pet;

  /**
   * Constructs a HealthTimer with the specified interval and pet.
   *
   * @param interval  the interval between timer ticks, in milliseconds
   * @param pet       the PetInterface object whose health will be decreased over time
   */
  public HealthTimer(int interval, PetInterface pet) {
    //interval = interval*1000;
    super(interval, null);
    this.pet = pet;
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        pet.loseHealth_GetTiredWhileTimePass();
        //System.out.println("health timer");
      }
    });
    //start();
  }
}
