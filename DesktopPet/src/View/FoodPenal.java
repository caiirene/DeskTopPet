package View;

import Model.Food.FoodInterface;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 我来解释一下这个FoodPenal:
 * 首先，这个JPenal不是时刻都存在的。它是在Feed按键按下后才被构造，所以就需要给构造器一个参数
 * 我们通过这个参数，构造不同的FoodPanel
 * 理论上，如果FoodBox中没有食物的话，是不可以产生这个FoodPanel的，
 * 但是介于这是个半成品，就在没有食物时简单添加了一个apple按键（是个空按键，不会增加饱食度）
 *
 * Let me explain this FoodPenal:
 * Firstly, this JPenal does not always exist. It is constructed only after the feed button is pressed, so a parameter needs to be given to the constructor
 * We use this parameter to construct different FoodPanels
 * In theory, if there is no food in the FoodBox, this FoodPanel cannot be generated,
 * But since this is a non-finished project, an Apple button was simply added when there is no food available (it is an empty button and will not increase satiety)
 */
public class FoodPenal extends JPanel {

  private List<JButton> foodButtonList = new ArrayList<JButton>();

  public FoodPenal(List<String> foodList) {
    if (foodList.size()==0) {
      System.out.println("创建foodPanel的时候就没有食物，我好心送一个苹果吧");
      JButton temApple = new JButton("apple");
      this.add(temApple);
      foodButtonList.add(temApple);
    }
    else {
      setLayout(new GridLayout(foodList.size(), 1)); // 使用 GridLayout 布局管理器，并指定行数和列数

      //System.out.println("food panel内部");
      for (String food : foodList) {
        JButton foodButton = new JButton(food);
        this.add(foodButton);
        foodButtonList.add(foodButton);
        //System.out.println(foodButton.getText());
      }
    }
  }

  public List<JButton> getFoodButtonList() {
    return foodButtonList;
  }

}
