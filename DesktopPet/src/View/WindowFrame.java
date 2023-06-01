package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WindowFrame extends JFrame {

  private JButton closeButton;
  private JLabel petLabel;
  private Point mouseClickPoint;
  public String normalGifPath = "C:\\Users\\asus\\Documents\\GitHub\\cs5004-irene-new\\DesktopPet\\src\\View\\t天使.gif";
  public String lowHungerGifPath = "C:\\Users\\asus\\Documents\\GitHub\\cs5004-irene-new\\DesktopPet\\src\\View\\t天使1.gif";
  public String eatingGifPath = "C:\\Users\\asus\\Documents\\GitHub\\cs5004-irene-new\\DesktopPet\\src\\View\\t天使2.gif";
  //public String normalGifPath = "C:\\Users\\asus\\Documents\\GitHub\\cs5004-irene-new\\ChatGPTTeachingPet\\src\\天使死亡.png";
  public String deadGifPath = "C:\\Users\\asus\\Documents\\GitHub\\cs5004-irene-new\\DesktopPet\\src\\View\\t天使死亡.png";

  public WindowFrame() {
    this.setSize(100,400);
    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //设置背景透明
    this.setUndecorated(true);
    this.setBackground(new Color(0, 0, 0, 0));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // 设置窗口在屏幕右下角
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation(screenSize.width - this.getWidth()-200, screenSize.height - this.getHeight()+100);

    //设置宠物图像
    ImageIcon imageIcon = new ImageIcon(normalGifPath);
    this.petLabel = new JLabel(imageIcon);
    this.getContentPane().add(petLabel);

    // 创建关闭按钮
    closeButton = new JButton("X");
    closeButton.addActionListener(e -> this.dispose());
    closeButton.setContentAreaFilled(false); // 设置按钮背景透明
    closeButton.setFocusPainted(false); // 去掉焦点边框
    closeButton.setBorder(BorderFactory.createEmptyBorder()); // 去掉边框
    closeButton.setFont(new Font("Arial", Font.PLAIN, 10)); // 设置字体大小

    this.getContentPane().add(closeButton, BorderLayout.NORTH);
    closeButton.addActionListener(e -> {
      this.dispose();
      System.exit(0); // 结束程序
    });
  }

  public void myAddMouseListener() {
    //添加鼠标监听器，为了能够拖动无框宠物
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        mouseClickPoint = e.getPoint();
      }
    });
    this.addMouseMotionListener(new MouseAdapter() {

      @Override
      public void mouseDragged(MouseEvent e) {
        Point currentPoint = e.getLocationOnScreen();
        WindowFrame.this.setLocation(currentPoint.x - mouseClickPoint.x, currentPoint.y - mouseClickPoint.y);
      }
    });
  }

  public void setPetGif(String gifPath) {
    petLabel.setIcon(new ImageIcon(gifPath));
  }
  public void updateGifBasedOnHunger(int hunger, int happiness) {
    if (hunger <= 60 || happiness<=40) {
      setPetGif(lowHungerGifPath);
    } else {
      setPetGif(normalGifPath);
    }
  }

  public void updateGifBasedOnDeath(boolean dead) {
    if (dead) {
      setPetGif(deadGifPath);
    }
  }

  public void updateGifBasedOnHealth(int health) {
    if (health<=0) {
      setPetGif(deadGifPath);
    }
  }

  /**
   * for test only
   * @return
   */
  public JLabel getPetLabel() {
    return petLabel;
  }

  /**
   * for test only
   */
  public void resetGif() {
    setPetGif(normalGifPath);
  }
}
