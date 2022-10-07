interface I {
  void doSomething();
}

class D implements I {
  public void doSomething() {
    System.out.print("Hello from Class D");
  }
}

class E implements I {
  public void doSomething() {
    System.out.print("Hello from Class EEEEEEE");
  }
}


class C {
  private I i;

  public C() {
    this.i = new E();
  }

  public void doSomething() {
    this.i.doSomething();
  }
}

public class Delegation {
  public static void main(String[] args) {
      C c = new C();
      c.doSomething();
  }
}

