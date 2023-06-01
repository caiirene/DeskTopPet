package View;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * this panel is a constance panel
 * always shown and never change
 * include: feed/play/dream
 */
public class BasicPenal extends JPanel {

  private JButton eatButton;
  private JButton playButton;
  private JButton dreamButton;

  public BasicPenal() {
    // 设定布局
    setLayout(new GridLayout(1, 3));

    // 初始化按钮
    eatButton = new JButton("Feed");
    playButton = new JButton("Play");
    dreamButton = new JButton("Let's dream");

    // 将按钮添加到面板上
    add(eatButton);
    add(playButton);
    add(dreamButton);
  }

  /**
   * three getters for controller to use
   * @return
   */
  public JButton getEatButton() {
    return eatButton;
  }
  public JButton getPlayButton() {
    return playButton;
  }
  public JButton getDreamButton() {
    return dreamButton;
  }

}
