interface VendingMachineState {

  void addNickel(VendingMachine v);

  void addDime(VendingMachine v);

  void addQuarter(VendingMachine v);

  int getBalance();
}

class VendingMachine {

  public VendingMachine() {
    _state = Credit0.instance(this);
  }
  // methods welcome(), displayBalance() etc. as before
  void welcome() {
    System.out.println("Welcome. Please enter $0.25 to buy product.");
  }

  void displayBalance() {
    System.out.println("balance is now: " + _state.getBalance());
  }

  void refund(int i) {
    System.out.println("refunding: " + i);
  }

  void dispenseProduct() {
    System.out.println("dispensing product...");
  }

  void changeState(VendingMachineState s) {
    _state = s;
    displayBalance();
  }

  public void addNickel() {
    _state.addNickel(this);
  }

  public void addDime() {
    _state.addDime(this);
  }

  public void addQuarter() {
    _state.addQuarter(this);
  }

  private VendingMachineState _state;
}

class Credit0 implements VendingMachineState {
  private Credit0(){ }
  private static Credit0 _theInstance;
  static Credit0 instance(VendingMachine v) {
    if (_theInstance == null) {
      _theInstance = new Credit0();
    }
    v.welcome(); return _theInstance;
  }
  public void addNickel(VendingMachine v) {
    v.changeState(Credit5.instance()); }
  public void addDime(VendingMachine v) {
    v.changeState(Credit10.instance()); }
  public void addQuarter(VendingMachine v) {
    v.dispenseProduct();
    v.changeState(Credit0.instance(v)); }
  public int getBalance() { return 0; }
}

class Credit5 implements VendingMachineState {

  private Credit5() {}

  private static Credit5 _theInstance;

  static Credit5 instance() {
    if (_theInstance == null) {
      _theInstance = new Credit5();
    }
    return _theInstance;
  }

  public void addNickel(VendingMachine v) {
    v.changeState(Credit10.instance());
  }

  public void addDime(VendingMachine v) {
    v.changeState(Credit15.instance());
  }

  public void addQuarter(VendingMachine v) {
    v.dispenseProduct();
    v.refund(5);
    v.changeState(Credit5.instance());
  }

  public int getBalance() {
    return 5;
  }
}

class Credit10 implements VendingMachineState {

  private Credit10() {}

  private static Credit10 _theInstance;

  static Credit10 instance() {
    if (_theInstance == null) {
      _theInstance = new Credit10();
    }
    return _theInstance;
  }

  public void addNickel(VendingMachine v) {
    v.changeState(Credit15.instance());
  }

  public void addDime(VendingMachine v) {
    v.changeState(Credit20.instance());
  }

  public void addQuarter(VendingMachine v) {
    v.dispenseProduct();
    v.refund(10);
    v.changeState(Credit10.instance());
  }

  public int getBalance() {
    return 10;
  }
}

class Credit15 implements VendingMachineState {

  private Credit15() {}

  private static Credit15 _theInstance;

  static Credit15 instance() {
    if (_theInstance == null) {
      _theInstance = new Credit15();
    }
    return _theInstance;
  }

  public void addNickel(VendingMachine v) {
    v.changeState(Credit20.instance());
  }

  public void addDime(VendingMachine v) {
    v.dispenseProduct();
    v.changeState(Credit0.instance(v));
  }

  public void addQuarter(VendingMachine v) {
    v.dispenseProduct();
    v.refund(15);
    v.changeState(Credit0.instance(v));
  }

  public int getBalance() {
    return 15;
  }
}

class Credit20 implements VendingMachineState {

  private Credit20() {
  }

  private static Credit20 _theInstance;

  static Credit20 instance() {
    if (_theInstance == null) {
      _theInstance = new Credit20();
    }
    return _theInstance;
  }

  public void addNickel(VendingMachine v) {
    v.dispenseProduct();
    v.changeState(Credit0.instance(v));
  }

  public void addDime(VendingMachine v) {
    v.dispenseProduct();
    v.refund(5);
    v.changeState(Credit0.instance(v));
  }

  public void addQuarter(VendingMachine v) {
    v.dispenseProduct();
    v.refund(20);
    v.changeState(Credit0.instance(v));
  }

  public int getBalance() {
    return 20;
  }
}


public class StateExample {
  public static void main(String[] args) {
    VendingMachine v = new VendingMachine();
    System.out.println("I am now adding a nickel...");
    v.addNickel();
    System.out.println("I am now adding a dime...");
    v.addDime();
    System.out.println("I am now adding a nickel...");
    v.addNickel();
    System.out.println("I am now adding a quarter...");
    v.addQuarter();
    System.out.println("Should I add more money???");
  }
}
