package ControllerTest;

import View.TotalView;

/**
 * just a mock class for test only
 */
public class MockTotalView extends TotalView {
  private int speechCount;

  public MockTotalView() {
    speechCount = 0;
  }

  @Override
  public void showSpeach(String message) {
    speechCount++;
  }

  public int getSpeechCount() {
    return speechCount;
  }
}
