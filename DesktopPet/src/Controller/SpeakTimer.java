package Controller;

import Model.Pet.PetInterface;
import View.TotalView;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * A timer for the pet to speak randomly at intervals.
 */
public class SpeakTimer extends Timer {

  private PetInterface pet;
  private TotalView view;

  /**
   * Constructor for the SpeakTimer class.
   * @param interval The delay between the pet's speeches.
   * @param pet The pet to speak.
   * @param view The view to display the pet's speeches.
   */
  public SpeakTimer(int interval, PetInterface pet, TotalView view) {
    super(interval * 1000, null);
    this.pet = pet;
    this.view = view;

    int initialDelay = generateRandomInterval(5, 15) * 1000; // convert seconds to milliseconds
    setInitialDelay(initialDelay);
    addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String message = pet.speakRandomly();
        view.showSpeach(message);

        // Set the time interval for the next speech
        int nextInterval = generateRandomInterval(5, 15) * 1000; // convert seconds to milliseconds
        setInitialDelay(nextInterval);
        restart();
        //System.out.println("speak randomly timer");
      }
    });
    setRepeats(false);
    //start();
  }

  private int generateRandomInterval(int minSeconds, int maxSeconds) {
    Random random = new Random();
    return random.nextInt(maxSeconds - minSeconds + 1) + minSeconds;
  }
}
