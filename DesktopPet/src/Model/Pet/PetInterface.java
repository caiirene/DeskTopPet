package Model.Pet;

import Model.Food.*;
import Model.Toy.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;


/**
 * 宠物类介绍：
 * 宠物有名字，生日，饥饿值，快乐值，健康值，布尔值死亡。然后你可以赋予它食盒，玩具盒。
 * 饥饿值，随时间流逝而下降，提升方法为喂食
 *      饥饿值降为0以后，逐步降低健康值
 * 快乐值，随时间流逝而下降，提升方法为玩
 *      快乐值降为0以后，逐步降低健康值
 * 健康值，随时间流逝而下降，提僧方法为睡觉（同时也会受其余两值影响）
 * 宠物睡觉的时候会接收用户输入，“梦”，并存储进List<String>，同时记录每次用户输入的时间
 * 宠物在死后，会将所有“梦”合并成一个txt文件
 */
public interface PetInterface {

  /**
   * get the name of pet
   * @return String name
   */
  String getName();

  /**
   * rename the pet
   * @param name
   */
  void setName(String name);

  /**
   * get the age
   * @return int age
   */
  int getAge();

  /**
   * reset the age (day)
   * @param age you want
   */
  void setAge(int age);

  /**
   * get the hunger number
   * @return
   */
  int getHunger();

  /**
   * reset the hunger to a number you want
   * @param hunger
   */
  void setHunger(int hunger);

  /**
   * eat a food object from food box or whatever you have,
   * and bring up the hunger number
   * @param food
   */
  void eat(FoodInterface food);


  /**
   * play with a toy object from toy box or whatever you have
   * and bring up the happiness
   * @param toy
   */
  void play(ToyInterface toy);

  /**
   * 因为宠物会随时间流逝而失去health，就是会get tired
   * 所以需要通过睡觉来补充health，这里的主要补充途径就是做梦
   * bring up the health
   */
  void sleep();

  /**
   * 恢复sleep状态为false
   */
  void weakUp();

  /**
   * 这个方法直接接收一个字符串，然后添加到dreamList中
   */
  void addDream(String dream);

  /**
   * 返还一个布尔值，表示宠物死亡与否
   * @return boolean pet dead or not
   */
  boolean getDeathStatus();

  /**
   * get health number
   * @return int health
   */
  int getHealth();

  /**
   * return a toy box that the pet have
   * a pet can only have at most one toy box
   * @return ToyBox object
   */
  ToyBoxInterface getToyBox();

  /**
   * give the pet a toy box
   * if the pet already have a toy box, we just add all toys into the box
   * @param toyBox
   */
  void setToyBox(ToyBoxInterface toyBox);

  /**
   * return some string randomly,
   * 说话内容包括，说自己的生日，表达思念，劝人休息
   * @return string
   */
  String speakRandomly();

  /**
   * say thank you after be feed or played
   * @return string
   */
  String sayThankYou();

  /**
   * 汇报自己处在饥饿状态
   * @return "我饿了“
   */
  String sayHungry();


  /**
   * 汇报自己处在低happiness状态
   * @return “能陪我玩玩吗？”
   */
  String sayLonely();

  /**
   * 汇总自身所有状态，讲出自己几岁了，很高兴认识你
   * 汇报自己获得了几个“梦”，说梦被记录在文件中了
   * @return
   */
  String sayTheLastWord();

  /**
   * 宠物如果不处在sleep状态
   * 根据activationRate降低health
   */
  void loseHealth_GetTiredWhileTimePass();

  /**
   * lose hunger while time pass
   * rate depends on pet hungerRate
   */
  void loseHungerWhileTimePass();
  /**
   * lose happiness while time pass
   * rate depends on pet lonelyRate
   */
  void loseHappinessWhileTimePass();

  /**
   * check the health==0 or not
   * if true, pet's boolean dead turn to true
   * and make the property listener inform the controller
   * (controller should do this check every 0.1 second)
   */
  void checkDeath();

  /**
   * it is a temp method for project
   * because I have no time for toy
   */
  void increaseHappiness();

  /**
   * this is a temp method for project
   * if pet has no food to eat, but I still need something to demo
   * just eatApple()
   */
  void eatApple();

  /**
   * 首先写入临终遗言
   * 然后把dream list逐条写入
   * 输出位置在桌面，绝对路径，所以只能我自己的电脑使用
   */
  void generateTxtFileFromDreams();

  /**
   * a getter for controller to register for listener
   * @return PropertyChangeSupport support
   */
  PropertyChangeSupport getSupport();


  /**
   * just a getter for int happiness
   * @return int happiness
   */
  int getHappiness();

  /**
   * just a setter for test
   * @param health
   */
  void setHealth(int health);

  void addPropertyChangeListener(PropertyChangeListener propertyChangeListener);

  /**
   * test专用
   * @return
   */
  List<String> getDreams();
}
