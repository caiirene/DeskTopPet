package Model.Pet;

import Model.Food.FoodInterface;
import Model.Food.FoodType;
import Model.Toy.ToyBox;
import Model.Toy.ToyBoxInterface;
import Model.Toy.ToyInterface;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

public class PetImpl implements PetInterface {
  protected String name = "涩涩";
  protected LocalDate birthDay;
  protected LocalDate birthDayReal;
  protected LocalDate deathDay;
  protected int age = 0; //count as days
  protected int hunger = 100;
  protected int hungryRate = 10;
  protected int happiness = 100;
  protected int lonelyRate = 10;
  /**
   * 影响宠物健康的有：不睡觉，就会持续下降
   *                 吃到毒药、（*吃到腐烂食物）
   *                 饥饿、快乐值下降到0后，健康持续下降
   * 健康一旦归零，立刻死亡
   */
  protected int health = 100;
  protected boolean isSleeping = false;

  //protected int activationRate;
  protected boolean liveOrDead = false;
  protected Map<String, Integer> toyFreshness;
  protected ToyBoxInterface toyBox;

  protected List<String> dreams = new ArrayList<String>();
  private Timer timer;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);


  /**
   * 构造器：全部使用默认值，生成当前时间的生日
   */
  public PetImpl() {
    this.birthDay = LocalDate.now();
    this.birthDayReal = LocalDate.now();
  }

  /**
   * load a pet from previous record
   * para include: deathBoolean, all fields, and !isSleep
   * BUT NO TIME TO IMPL
   * @param record
   */
  public PetImpl(File record) {
    // NO TIME
  }

  /**
   * get the name of pet
   *
   * @return String name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * rename the pet
   * @throws if null pointer as parameter, IllegalArgumentException
   * @param name
   */
  @Override
  public void setName(String name) {
    if (name==null) {throw new IllegalArgumentException();}
    this.name = name;
  }

  @Override
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * get the age
   *
   * @return int age
   */
  @Override
  public int getAge() {
    LocalDate now = LocalDate.now();
    age = (int)ChronoUnit.DAYS.between(birthDay, now);
    return age;
  }

  /**
   * reset the age (day)
   * 开挂专用
   * @param age you want
   */
  @Override
  public void setAge(int age) {
    if (age<0) {throw new IllegalArgumentException();}
    this.age = age;
    LocalDate currentDate = LocalDate.now();
    this.birthDay = currentDate.minusDays(this.age);
  }

  /**
   * get the hunger number
   *
   * @return
   */
  @Override
  public int getHunger() {
    return hunger;
  }

  /**
   * reset the hunger to a number you want
   *
   * @param hunger
   */
  @Override
  public void setHunger(int hunger) {
    if (hunger<=0) {this.hunger = 1;}
    else {
      this.hunger = hunger;
    }
  }
  @Override
  public int getHappiness() {return happiness;}

  /**
   * eat a food object from food box or whatever you have, and bring up the hunger number
   *
   * @param food
   * @throws IllegalArgumentException if null pointer
   */
  @Override
  public void eat(FoodInterface food) {
    if (food==null) {throw new IllegalArgumentException();}
    switch (food.getFoodType()) {
      case POISON:
        int oldHealth = health;
        this.health = Math.max(health - 50, 0);
        System.out.println("吃到毒药，health："+health);
        support.firePropertyChange("healthChange",oldHealth,this.health);
        //这里需要直接通知内部监听器？还是通知controller监听器，然后再让控制器调佣pet的checkDeath？
        break;
      case GOOD:
        //int oldHunger = hunger;
        //this.hunger += food.getRealFoodValue(); //这里实在不行就改成getFoodValue()
        //support.firePropertyChange("hungerChange",oldHunger,this.hunger);
        break;
      case BAD:
        int oldHappiness = happiness;
        this.happiness -= 10;
        support.firePropertyChange("happinessChange",oldHappiness,this.happiness);
        break;
    }
    int oldHunger = hunger;
    this.hunger += food.getRealFoodValue(); //这里实在不行就改成getFoodValue()
    support.firePropertyChange("hungerChange",oldHunger,this.hunger);
  }

  /**
   * play with a toy object from toy box or whatever you have and bring up the happiness
   * NO TIME, NO TEST
   * @param toy
   */
  @Override
  public void play(ToyInterface toy) {
    int oldHappiness = this.happiness;
    String toyName = toy.getToyName();
    int freshness = toyFreshness.getOrDefault(toyName, 100); // 默认新鲜程度为100

    // 计算 happiness 提升
    int happinessBoost = (int) (20 * (freshness / 100.0));

    // 更新 happiness 和新鲜程度
    this.happiness += happinessBoost;
    this.toyFreshness.put(toyName, Math.max(freshness - 20, 0)); // 每次玩耍降低新鲜程度，最低为0
    recoverFreshness();

    //通知监听器
    support.firePropertyChange("happinessChange", oldHappiness, this.happiness);
  }

  /**
   * 这个是对应了上面的方法而写
   * 上面消耗了对玩具的新鲜感，同时也要增加其余玩具的新鲜感
   */
  private void recoverFreshness() {
    for (String toyName : toyFreshness.keySet()) {
      int freshness = toyFreshness.get(toyName);
      toyFreshness.put(toyName, Math.min(freshness + 10, 100)); // 恢复新鲜程度，最高为100
    }
  }

  /**
   * 因为宠物会随时间流逝而失去health，就是会get tired 所以需要通过睡觉来补充health，这里的主要补充途径就是做梦 bring up the health
   */
  @Override
  public void sleep() {
    isSleeping = true;
    support.firePropertyChange("sleepChange",false,true);
  }

  /**
   * 恢复sleep状态为false
   */
  @Override
  public void weakUp() {
    isSleeping = false;
    support.firePropertyChange("sleepChange",true,false);
  }

  /**
   * 这个方法直接接收一个字符串，然后使用格式，添加到dreamList中
   * we do allow empty string here
   * @param dream
   */
  @Override
  public void addDream(String dream) {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    LocalDateTime now = LocalDateTime.now();

    String formattedDate = now.format(dateFormatter);
    String formattedTime = now.format(timeFormatter);

    String formattedDream = formattedDate + " " + formattedTime + "，你曾对我说：\n" + dream + "\n\n";
    dreams.add(formattedDream);
    health += 10;
    this.checkDeath();
  }

  /**
   * 返还一个布尔值，表示宠物死亡与否
   *
   * @return boolean pet dead or not
   */
  @Override
  public boolean getDeathStatus() {
    return liveOrDead;
  }

  /**
   * get health number
   *
   * @return int health
   */
  @Override
  public int getHealth() {
    return health;
  }

  @Override
  public List<String> getDreams() {
    return dreams;
  }

  /**
   * return a toy box that the pet have a pet can only have at most one toy box
   *
   * @return ToyBox object
   */
  @Override
  public ToyBoxInterface getToyBox() {
    return toyBox;
  }

  /**
   * give the pet a toy box if the pet already have a toy box, we just add all toys into the box
   * we do allow null pointer here
   * @param toyBox
   */
  @Override
  public void setToyBox(ToyBoxInterface toyBox) {
    this.toyBox = toyBox;
  }

  /**
   * return some string randomly, 说话内容包括，说自己的生日，表达思念，劝人休息
   *
   * @return string
   */
  @Override
  public String speakRandomly() {
    Random random = new Random();
    // 生成一个 int 类型的随机数
    int randomNumber = random.nextInt(4);
    String birth = "<html>I have came to this world for %d days.<br>Every day of being here is so happy for me.</html>";
    String miss = "I miss you so much, are you missing me?";
    String love = "<html>I think you are the one<br>I will love forever in this world,<br>don't you think so?</html>";
    String care = "<html>Are you taking good care of yourself?<br>If not, I will worry about you so much.</html>";

    switch (randomNumber) {
      case 0:
        return String.format(birth, this.age);
      case 1:
        return miss;
      case 2:
        return love;
      case 3:
        return care;
      default:
        return "I love you.";
    }
  }

  /**
   * say thank you after be feed or played
   *
   * @return string
   */
  @Override
  public String sayThankYou() {
    Random random = new Random();
    // 生成一个 int 类型的随机数
    int randomNumber = random.nextInt(4);

    String one = "Thank you~";
    String two = "(#^.^#)~Thanks";
    String three = "I am so happy~";
    String four = "I think I am the luckiest little \npet in the world.";
    switch (randomNumber) {
      case 0:
        return one;
      case 1:
        return two;
      case 2:
        return three;
      case 3:
        return four;
      default:
        return "I love you.";
    }
  }

  /**
   * 汇报自己处在饥饿状态
   *
   * @return "我饿了“
   */
  @Override
  public String sayHungry() {
    return "I am little bit hungry now...";
  }

  /**
   * 汇报自己处在低happiness状态
   *
   * @return “能陪我玩玩吗？”
   */
  @Override
  public String sayLonely() {
    return "Would you mind... play with me\n for a while, just a little while?";
  }

  /**
   * 汇总自身所有状态，讲出自己几岁了，很高兴认识你 汇报自己获得了几个“梦”，说梦被记录在文件中了
   * last word include how old now, how many dreams collected, and say love
   * @return
   */
  @Override
  public String sayTheLastWord() {
    String lastWord = "<html>I am " + getAge() + " days old now. It was a great<br>"
        + "time to meet you in this world.<br>"
        + "During these " + getAge() + " days,<br>"
        + "you told me " + dreams.size() + " dreams. <br>"
        + "And those dream accompanied me over these " + getAge() + " nights.<br>"
        + "They are the most precious treasures of mine.<br>"
        + "I have kept all of them for you.<br>"
        + "I love you. Goodbye.</html>";

    return lastWord;
  }

  /**
   * 宠物如果不处在sleep状态 根据activationRate降低health
   */
  @Override
  public void loseHealth_GetTiredWhileTimePass() {
    int oldHealth = this.health;
    health = Math.max(health - 10, 0);
    System.out.println("health -10, now health = "+ this.getHealth());
    this.checkDeath();
    //support.firePropertyChange("healthChange", oldHealth, this.health);
  }

  /**
   * lose hunger while time pass rate depends on pet hungerRate
   */
  @Override
  public void loseHungerWhileTimePass() {
    int oldHunger = this.hunger;
    // 在这里编写逻辑以根据时间流逝增加宠物的饥饿感
    // 例如：hunger = Math.max(hunger - 1, 0);
    //System.out.println("hunger-5 in pet");
    hunger = Math.max(hunger - hungryRate, 0);
    System.out.println("loss hunger normally, " + this.hunger);

    support.firePropertyChange("hungerChange", oldHunger, this.hunger);

  }

  /**
   * lose happiness while time pass rate depends on pet lonelyRate
   */
  @Override
  public void loseHappinessWhileTimePass() {
    int oldHappiness = this.happiness;
    happiness = Math.max(happiness - lonelyRate, 0);

    support.firePropertyChange("happinessChange", oldHappiness, this.happiness);
  }

  /**
   * check the health==0 or not if true, pet's liveOrDead turn to true and make the property
   * listener inform the controller (controller should do this check every 0.1 second)
   */
  @Override
  public void checkDeath() {
    boolean oldDead = this.liveOrDead;
    if (health <= 0) {
      this.liveOrDead = true;
      //System.out.println("dead = true");
    }
    //support.firePropertyChange("dead", oldDead, liveOrDead);
    //System.out.println("check death监听器已通知");

    // 手动触发事件
    for (PropertyChangeListener listener : support.getPropertyChangeListeners()) {
      listener.propertyChange(new PropertyChangeEvent(this, "dead", oldDead, liveOrDead));
    }
  }

  /**
   * a getter for controller to register for listener
   * @return PropertyChangeSupport support
   */
  public PropertyChangeSupport getSupport(){return support;}


  /**
   * it is a temp method for project
   * because I have no time for toy
   */
  public void increaseHappiness() {
    int oldHappiness = this.happiness;
    happiness = Math.min(happiness + 10, 100);
    support.firePropertyChange("happiness", oldHappiness, this.happiness);
  }

  /**
   * this is a temp method for project
   * if pet has no food to eat, but I still need something to demo
   * just eatApple()
   */
  public void eatApple() {
    System.out.println("eat apple!!!!!!!!!!!!");
  }

  /**
   * 首先写入临终遗言
   * 然后把dream list逐条写入
   * 输出位置在桌面，绝对路径，所以只能我自己的电脑使用
   */
  public void generateTxtFileFromDreams() {
    System.out.println("生成txt");
    File outputFile = new File("C:\\Users\\asus\\Desktop\\dreams.txt");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      writer.write(lastWordNoHTML());
      writer.newLine();
      for (String dream : dreams) {
        writer.write(dream);
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String lastWordNoHTML() {
    String lastWord = "I am "+getAge()+" days old now. It was a great\ntime to meet you in this world.\n"
        + "During these "+getAge()+" days,\nyou told me "+dreams.size()+" dreams. \n"
        + "And those dream accompanied me over these "+getAge()+" nights.\n"
        + "They are the most precious treasures of mine.\nI have kept all of them for you.\n"
        + "I love you. Goodbye.\n";
    return lastWord;
  }

  public void addPropertyChangeListener(PropertyChangeListener listener) {
    support.addPropertyChangeListener(listener);
  }
}
