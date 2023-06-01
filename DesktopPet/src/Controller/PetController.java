package Controller;

import Model.Food.Apple;
import Model.Food.Diesel;
import Model.Food.FoodBox;
import Model.Food.FoodBoxInterface;
import Model.Food.FoodInterface;
import Model.Food.Screwworm;
import Model.Food.Strawberry;
import Model.Pet.PetImpl;
import Model.Pet.PetInterface;
import View.FoodPenal;
import View.TotalView;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class PetController implements ActionListener, PropertyChangeListener {

  FoodBoxInterface foodBox;
  private PetInterface pet;
  private TotalView view;
  private Timer hungerTimer;
  private Timer checkDeathTimer;
  private Timer speakRandomlyTimer;
  private Timer tiredTimer;
  private Timer emergencyLoseHealthTimer;
  private Timer foodGeneratorTimer;
  private Timer happinessTimer;

  /**
   * new game constructor
   * new pet with none parameters
   */
  public PetController() {
    pet = new PetImpl();
    //pet.getSupport().addPropertyChangeListener(this);
    view = new TotalView();
    view.basicPenal.getEatButton().addActionListener(this);
    view.basicPenal.getPlayButton().addActionListener(this);
    view.basicPenal.getDreamButton().addActionListener(this);

    pet.getSupport().addPropertyChangeListener(this);
    //System.out.println("Listeners count: " + pet.getSupport().getPropertyChangeListeners().length);

    //System.out.println("start create food box");
    foodBox = new FoodBox();
    foodBox.addFood(new Apple());
    foodBox.addFood(new Strawberry());
    foodBox.addFood(new Screwworm());
    foodBox.addFood(new Diesel());
    if (foodBox==null) {System.out.println("null food box");}

    //////////////////创建timer//////////////////////////////////

    //食品生产开始 generate food
    foodGeneratorTimer = new FoodGeneratorTimer(30000,foodBox);
    foodGeneratorTimer.start();

    //normal hunger time 普通饥饿时间
    hungerTimer = new HungerTimer(30000,pet); //30秒
    hungerTimer.start();
    //normal speak time
    speakRandomlyTimer = new SpeakTimer(5000,pet,view); //首次5秒
    speakRandomlyTimer.start();
    //normal health time
    tiredTimer = new HealthTimer(60000,pet); //60秒
    tiredTimer.start();
    //normal happiness time
    happinessTimer = new HappinessTimer(60000,pet); //60秒
    happinessTimer.start();
    //create a emergency timer, but not start yet
    emergencyLoseHealthTimer = new HealthTimer(2000, pet); //2秒

    //这个监测死亡的timer比较特殊，直接放在controller中进行定义
    createCheckDeathTimer();

  }
  /**
   * load game from recorded file
   * new pet object with a lot of parameters
   * NO TIME TO FINISH
   */
  public PetController(int anything) {
    // ......
  }


  @Override
  public void actionPerformed(ActionEvent e) {

    //FEED按键
    if (e.getSource() == view.basicPenal.getEatButton() && pet.getDeathStatus()!=true) {
      //先从foodBox中获取一串不重复的String名单
      List<String> foodList = foodBox.getDistinctListOfFoodName();

      FoodPenal foodPenal = new FoodPenal(foodList);
      //从foodPanel中获取list
      List<JButton> foodButtonList = foodPenal.getFoodButtonList();
      //遍历每个按键，安装监听器为一个新建的ActionListener，使用空降类
      for (JButton button : foodButtonList) {
        button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e2) {
            FoodInterface providedFood = foodBox.provideFood(button.getText());
            if(providedFood!=null) {
              pet.eat(providedFood);

              //显示3秒的吃饭动画
              view.frame.setPetGif(view.frame.eatingGifPath);
              int delay = 3000; // 3 seconds in milliseconds
              Timer timer = new Timer(delay, event -> {
                view.frame.updateGifBasedOnHunger(pet.getHunger(),pet.getHappiness());
              });
              timer.setRepeats(false);
              timer.start();
            }
            else {pet.eatApple();} //如果暂时还没有食物就吃过苹果吧

            view.frame.getContentPane().remove(foodPenal); // 删除 temFoodPanel
            view.frame.revalidate(); // 触发界面重新布局
            view.frame.repaint(); // 重绘界面
            String thanks = pet.sayThankYou();
            view.showSpeach(thanks);
          }
        });
      }

      view.frame.getContentPane().add(foodPenal, BorderLayout.WEST);
      //view.basicPenal.add(foodPenal); // 添加 foodPenal 到 basicPenal
      view.basicPenal.revalidate(); // 触发界面重新布局
      view.basicPenal.repaint(); // 重绘界面
    }

    //PLAY
    if (e.getSource() == view.basicPenal.getPlayButton() && pet.getDeathStatus()!=true) {
      pet.increaseHappiness();
      //显示3秒的玩耍动画
      view.frame.setPetGif(view.frame.eatingGifPath);
      int delay = 3000; // 3 seconds in milliseconds
      Timer timer = new Timer(delay, event -> {
        view.frame.updateGifBasedOnHunger(pet.getHunger(),pet.getHappiness());
      });
      timer.setRepeats(false);
      timer.start();
      String thanks = pet.sayThankYou();
      view.showSpeach(thanks);

      //做梦按键 DREAM
    } else if (e.getSource() == view.basicPenal.getDreamButton() && pet.getDeathStatus()!=true) {
      JTextArea dreamTextArea = new JTextArea(10, 30); // 设置行数和列数
      dreamTextArea.setLineWrap(true);
      dreamTextArea.setWrapStyleWord(true);
      JScrollPane scrollPane = new JScrollPane(dreamTextArea);

      int result = JOptionPane.showConfirmDialog(view.frame, scrollPane, "Enter your pet's dream:", JOptionPane.OK_CANCEL_OPTION);

      if (result == JOptionPane.OK_OPTION) {
        String dream = dreamTextArea.getText();
        if (dream != null && !dream.trim().isEmpty()) {
          pet.addDream(dream);
        }
      }
    }
  }
  @Override
  public void propertyChange(PropertyChangeEvent evt) {

    //死了
    //if any chance pet dead, all timer stop, and generate txt file
    if ("dead".equals(evt.getPropertyName())) {
      //System.out.println("监听器收到");
      boolean isDead = (boolean) evt.getNewValue();
      if (isDead == true) {
        System.out.println("在这里触发死亡 GIF");
        // 在这里触发死亡 GIF
        view.frame.updateGifBasedOnHealth(pet.getHealth());

        /////////////////////////
        // 停止所有 hungerTimer //
        /////////////////////////

        hungerTimer.stop();
        checkDeathTimer.stop();
        speakRandomlyTimer.stop();
        tiredTimer.stop();
        emergencyLoseHealthTimer.stop();
        happinessTimer.stop();
        foodGeneratorTimer.stop();

        //last word
        String lastWord = pet.sayTheLastWord();
        view.showSpeach(lastWord);

        // 调用生成 txt 文件的方法，例如：
        pet.generateTxtFileFromDreams();
      }

      //饿了，分两种情况，一个是饿急了，另一个就是正常饿了。但无论哪一种，gif都要据情况更新
      //get hungry: emergency {start+end} || normal hungry
    } else if ("hungerChange".equals(evt.getPropertyName())) {
      //System.out.println("hunger监听器已收到");
      int newHunger = (int)evt.getNewValue();

      if (newHunger<=0) {
        emergencyLoseHealthTimer.start();
        String reallyHungry = pet.sayHungry();
        view.showSpeach(reallyHungry);
        speakRandomlyTimer.stop();
        hungerTimer.stop();
      } else if (newHunger > 0 && emergencyLoseHealthTimer!=null) {
        emergencyLoseHealthTimer.stop();
        speakRandomlyTimer.start();
        hungerTimer.start();
      }
      view.frame.updateGifBasedOnHunger(newHunger, pet.getHappiness());

      //HEALTH CHANGE
      //更新gif的同时调用checkDeath。任何对health的改变都会在此触发death check，所以确保任何死亡都将被监测到
    } else if ("healthChange".equals(evt.getPropertyName())) {
      view.frame.updateGifBasedOnHealth(pet.getHealth());
      pet.checkDeath();

      //HAPPY CHECK
    } else if ("happinessChange".equals(evt.getPropertyName())) {
      if (pet.getHappiness()<=20) {
        String reallyLonely = pet.sayLonely();
        view.showSpeach(reallyLonely);
      }
      view.frame.updateGifBasedOnHunger(pet.getHunger(),pet.getHappiness());
    }
  }


  private void createCheckDeathTimer() {
    int checkInterval = 100; // Check every 0.1 seconds (100 milliseconds)
    checkDeathTimer = new Timer(checkInterval, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //System.out.println("death check in death timer");
        pet.checkDeath();
      }
    });
    checkDeathTimer.start();
  }

}