package View;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * this total view include: frame/basic panel/speaking label
 * since frame and basic panel are mostly independent, so they are public field
 * the only method useful here is the showSpeach
 */
public class TotalView {

  public WindowFrame frame = new WindowFrame();
  public BasicPenal basicPenal = new BasicPenal();
  private JLabel petSpeechLabel;
  private JTextArea petSpeechTextArea;

  public TotalView() {

    frame.getContentPane().add(basicPenal, BorderLayout.SOUTH);
    this.frame.pack();
    this.frame.setVisible(true);
    frame.myAddMouseListener();

    //宠物对话框初始化
    petSpeechLabel = new JLabel();
    petSpeechLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    petSpeechLabel.setHorizontalAlignment(JLabel.CENTER);
    petSpeechLabel.setVerticalAlignment(JLabel.TOP);
    petSpeechLabel.setBounds(20, 200, 200, 30);
    frame.add(petSpeechLabel);

  }

  public BasicPenal getBasicPenal() {
    return basicPenal;
  }

  /**
   * 这个方法在宠物需要说话时调用。
   * 这个方法接收一个string作为参数，然后修改JLabel的文字显示，
   * 所以，JLabel对象是固定的，只是调用此方法来修改文字，可以避免生成多个JLabel导致前一个label没删除，使得文字重叠
   * @param String speach
   */
  public void showSpeach(String speach) {
    //System.out.println(speach);
    petSpeechLabel.setText(speach);
  }

  /**
   * for test only
   * @return
   */
  public JLabel getPetSpeechLabel() {
    return petSpeechLabel;
  }
}
