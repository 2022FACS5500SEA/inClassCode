import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Example from http://www.javacamp.org/designPattern/

interface Employee {
  public abstract double earnings();
}

class Manager implements Employee {
  private double weeklySalary;
  private String name;
  public Manager(String name, double s) {
    this.name = name;
    setWeeklySalary(s);
  }

  void setWeeklySalary(double s) {
    if (s > 0) {
      weeklySalary = s;
    } else
      weeklySalary = 0;
  }

  public double earnings() {
    return weeklySalary;
  }
  public String getName() {
    return name;
  }
  public String toString() {
    return "Manager: " + getName();
  }
}

class PieceWorker implements Employee {
  private double wagePerPiece;
  private int quantity;
  private String name;
  public PieceWorker(String name, double w, int q) {
    this.name = name;
    setWagePerPiece(w);
    setQuantity(q);
  }

  void setWagePerPiece(double w) {
    if (w > 0)
      wagePerPiece = w;
    else
      wagePerPiece = 0;
  }

  void setQuantity(int q) {
    if ( q > 0)
      quantity = q;
    else
      quantity = 0;
  }
  public String getName() {
    return name;
  }
  public double earnings() {
    return quantity * wagePerPiece;
  }

  public String toString() {
    return "Piece worker: " + getName();
  }
}

class HourlyWorker implements Employee {
  private double hourlyWage;
  private double hours;
  private String name;
  public HourlyWorker(String name, double w, double h) {
    this.name = name;
    setHourlyWage(w);
    setHours(h);
  }

  void setHourlyWage(double w) {
    if (w > 0)
      hourlyWage = w;
    else
      hourlyWage = 0;
  }

  void setHours(double h) {
    if ( 0 <= h && h < 168)
      hours = h;
    else
      hours = 0;
  }
  public String getName() {
    return name;
  }
  public double earnings() {
    return hourlyWage * hours;
  }
  public String toString() {
    return "Hourly worker: " + getName();
  }
}

class CommissionWorker implements Employee {
  private double salary;
  private double commission;
  private double totalSales;
  private String name;
  public CommissionWorker(String name,
      double salary, double commission, double totalSales) {
    this.name = name;
    setSalary(salary);
    setCommission(commission);
    setTotalSales(totalSales);
  }
  void setSalary(double s) {
    if( s > 0)
      salary = s;
    else
      salary = 0;
  }
  void setCommission(double c) {
    if ( c > 0)
      commission = c;
    else
      commission = 0;
  }
  void setTotalSales(double ts) {
    if (ts > 0 )
      totalSales = ts;
    else
      totalSales = 0;
  }
  public String getName() {
    return name;
  }
  public double earnings() {
    return salary + commission/100*totalSales;
  }
  public String toString() {
    return "Commission worker:"
        + getName();
  }
}

public class IteratorExample {
  public static void main(String[] args) {
    List<Employee> list  = new ArrayList();
    list.add(new Manager("Bill", 800.00));
    list.add(new CommissionWorker("Newt", 400.0, 3.75, 159.99));
    list.add(new PieceWorker("Al", 2.5, 200));
    list.add(new HourlyWorker("Babara", 13.75, 40));
    list.add(new Manager("Peter", 1200.00));
    list.add(new CommissionWorker("Margret", 600.0,5.5, 200.25));
    list.add(new PieceWorker("Mark", 4.5, 333));
    list.add(new HourlyWorker("William", 31.25, 50));

    System.out.println("Use built-in iterator:");
    Iterator iterator = list.iterator();
    while(iterator.hasNext()) {
      Employee em = (Employee)iterator.next();
      System.out.print(em + " earns $");
      System.out.println(em.earnings());
    }
  }
}
