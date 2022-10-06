import java.util.HashSet;
import java.util.Set;

class Maze {
  Maze() {
    System.out.println("creating a Maze");
  }
  void addRoom(Room r) {
    if (!_rooms.contains(r)) {
      _rooms.add(r);
    }
  }
  private Set<Room> _rooms = new HashSet<Room>();
}

abstract class MapSite {
// enter() method omitted
}

enum Direction {
  North, South, East, West
}

class Room extends MapSite {
  Room() {
    _roomNr = _roomCnt++;
    System.out.println("creating Room #" + _roomNr);
  }
  void setSide(Direction d, MapSite site) {
    switch (d) {
      case North:
        _northSide = site;
      case South:
        _southSide = site;
      case East:
        _eastSide = site;
      case West:
        _westSide = site;
    }
    System.out.println("setting " + d.toString() + " side of " + this.toString() + " to " + site.toString());
  }

  MapSite getSide(Direction d) {
    MapSite result = null;
    switch(d){
      case North:
        result = _northSide;
      case South:
        result = _southSide;
      case East:
        result = _eastSide;
      case West:
        result = _westSide;
    }
    return result;
  }
  public String toString() {
    return "Room #" + new Integer(_roomNr).toString();
  }
  private int _roomNr;
  private static int _roomCnt = 1;
  private MapSite _northSide;
  private MapSite _southSide;
  private MapSite _eastSide;
  private MapSite _westSide;
}

class Wall extends MapSite {

  Wall() {
    _wallNr = _wallCnt++;
    System.out.println("creating Wall #" + new Integer(_wallNr).toString());
  }

  public String toString() {
    return "Wall #" + new Integer(_wallNr).toString();
  }

  private int _wallNr;
  private static int _wallCnt = 1;
}

class Door extends MapSite {

  Door(Room r1, Room r2) {
    _doorNr = _doorCnt++;
    System.out.println("creating a Door #" + _doorNr + " between " + r1
        + " and " + r2);
    _room1 = r1;
    _room2 = r2;
  }

  public String toString() {
    return "Door #" + new Integer(_doorNr).toString();
  }

  private static int _doorCnt = 1;
  private int _doorNr;
  private Room _room1;
  private Room _room2;
}

// Refactored code using Abstract Factory pattern
class MazeFactory {
  public Maze makeMaze() {
    return new Maze();
  }
  public Wall makeWall() {
    return new Wall();
  }
  public Room makeRoom() {
    return new Room();
  }
  public Door makeDoor(Room r1, Room r2) {
    return new Door(r1, r2);
  }
}

class MazeGame {
  public Maze createMaze(MazeFactory factory) {
    Maze aMaze = factory.makeMaze();
    Room r1 = factory.makeRoom();
    Room r2 = factory.makeRoom();
    Door theDoor = factory.makeDoor(r1, r2);
    aMaze.addRoom(r1);
    aMaze.addRoom(r2);
    r1.setSide(Direction.North, factory.makeWall());
    r1.setSide(Direction.East, theDoor);
    r1.setSide(Direction.South, factory.makeWall());
    r1.setSide(Direction.West, factory.makeWall());
    r2.setSide(Direction.North, factory.makeWall());
    r2.setSide(Direction.East, factory.makeWall());
    r2.setSide(Direction.South, factory.makeWall());
    r2.setSide(Direction.West, theDoor);
    return aMaze;
  }
}

// new features
class Spell { }

class EnchantedRoom extends Room {
  EnchantedRoom(Spell s) {
    super();
    /* ... */
  }
  public String toString() {
    return "enchanted " + super.toString();
  }
}

class DoorNeedingSpell extends Door {

  DoorNeedingSpell(Room r1, Room r2) {
    super(r1, r2);
    /* ... */
  }

  public String toString() {
    return super.toString() + " (needing spell)";
  }
}

class EnchantedMazeFactory extends MazeFactory {
  public Room makeRoom() {
    return new EnchantedRoom(castSpell());
  }

  public Door makeDoor(Room r1, Room r2) {
    return new DoorNeedingSpell(r1, r2);
  }

  protected static Spell castSpell() {
    return new Spell();
  }
}

public class MainV2 {
  public static void main(String[] args) {
    MazeFactory factory = new MazeFactory();
//    MazeFactory factory = new EnchantedMazeFactory();
    MazeGame game = new MazeGame();
    game.createMaze(factory);
  }
}
