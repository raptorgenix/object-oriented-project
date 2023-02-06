
import java.util.*;

// import org.junit.Test;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.TestInstance;

// To refresh on inheritance in Java, I looked at https://www.educative.io/blog/java-inheritance-tutorial

// Base class Game
class Game {
    
    // Private fields
    private String name; 
    private double price; 
    private double height; 
    private double width; 
    private double length; 
    private int inventory_num; 
    private int sold_num; 
    private int shelf_position; 
    private String category;
    public Decorator gameDecorator;

    // Total number of games for determining shelf position
    private int num_games = 1; 
    
    public Game(){
        this.name = "name";
        this.price = 0; 
        this.height = 0; 
        this.width = 0; 
        this.length = 0; 
        this.inventory_num = 0; 
        this.sold_num = 0;
        this.shelf_position = num_games; 
        this.category = "gameType";
        // num_games += 1;
        this.gameDecorator = new gameDecorator();
    }

    //Comment
    
    // Parameterized Constructor
    public Game(String name, double price, double height, double width, double length, String gameType, Decorator gameDecorator) {
        this.name = name;
        this.price = price; 
        this.height = height; 
        this.width = width; 
        this.length = length; 
        this.inventory_num = 3; 
        this.sold_num = 0;
        this.shelf_position = num_games; //increments by 1 each time, so starts at a 1-indexed shelf 
        this.category = gameType;
        // num_games += 1;
        this.gameDecorator = gameDecorator;
    }

    // Print Game Details
    public void printGameDetails() {
        System.out.println("Game: " + name);
        System.out.println("Number in Inventory: " + inventory_num);
        System.out.println("Number Sold: " + sold_num); 
        System.out.println("Total Sales: " + (sold_num * price)); 
    }

    //All getters and setters for game variables
    public void setInventory(int x){
        this.num_games = x;
    }
    public String getName(){
        return this.name;
    }
    public int getInventory(){
        return this.num_games;
    }
    public void setShelfPosition(int pos){
       this.shelf_position = pos;
    } 
    public int getShelfPosition(){
        return this.shelf_position;
    }

    public double getPrice(){
        return this.price;
    } 

    public void addSold(){
        this.sold_num += 1;
    } 

    public int getSold(){
        return this.sold_num;
    } 
    
    public double getHeight(){
        return this.height;
    } 

    public double getWidth(){
        return this.width;
    } 

    public double getLength(){
        return this.length;
    }
    public String getCategory(){
        return this.category;
    }
}
// Example of Inheritance: familyGame IS-A Game





//Command pattern structure
interface Request{
    void execute();
}

class gameRequest{
    private Demonstrator demonstrator;

    public void setDemonstrator(Demonstrator dem){
        demonstrator = dem;
    }
    public void demonstrate(){
        demonstrator.demonstrate();
    }
    public void recommend(){
        demonstrator.recommend();
    }
    public void explain(){
        demonstrator.explain();
    }
}

class demonstrateGame implements Request{
    private gameRequest req;
    
    public demonstrateGame(gameRequest req){
        this.req = req;
    }

    public void execute(){
        req.demonstrate();
    }
}

class recommendGame implements Request{
    private gameRequest req;

    public recommendGame(gameRequest req){
        this.req = req;
    }
    public void execute(){
        req.recommend();
    } 
}

class explainGame implements Request{
    private gameRequest req;

    public explainGame(gameRequest req){
        this.req = req;
    }

    public void execute(){
        req.explain();
    }
}


//All decorator work below
interface Decorator{ //Decorator interface for getPrice method base
    
    public double getPrice();
    public void setGame(Game game);
}
class gameDecorator implements Decorator{ //class level to override getprice method and take in game to hold as variable for all further decorators to access
    Game g;
    public void setGame(Game game){
        g = game;
    }
    @Override
    public double getPrice(){
        return g.getPrice();
    };
}
class MonopolyGameDecorator extends gameDecorator{ //overriding getPrice method for monopoly expansion purchase chances
    
    @Override
    public double getPrice(){
        int roll = new Random().nextInt(2);//50-50 chance to purchase
        return g.getPrice() + 2.5*roll;
    }
    
}
class CardGameDecorator extends gameDecorator{  //overriding getPrice method for all card game expansion purchase chances
    
    @Override
    public double getPrice(){
        boolean buyExpansion =  2 > new Random().nextInt(10); //20% chance to purchaes
        if(buyExpansion){
            int roll = new Random().nextInt(6) + 1;
            return g.getPrice() + 1*roll;
        }
        return g.getPrice();
    }
    
}

class MousetrapDecorator extends gameDecorator{ //overriding getPrice method for Mousetrap expansion purchase chances
    
    @Override
    public double getPrice(){
        boolean buyExpansion =  3 > new Random().nextInt(10); //30% chance to purchase
        if(buyExpansion){
            int roll = new Random().nextInt(2) + 1;
            return g.getPrice() + 3*roll;
        }
        return g.getPrice();
    }
    
}

class GloomhavenDecorator extends gameDecorator{ //overriding getPrice method for Gloomhaven expansion purchase chances
    
    @Override
    public double getPrice(){
        boolean buyExpansion =  2 > new Random().nextInt(10); //20% chance to purchase
        if(buyExpansion){
            int roll = new Random().nextInt(4) + 1;
            return g.getPrice() + 4*roll;
        }
        return g.getPrice();
    }
    
}
//End of decorator work

// Derived Classes for Game Types
class familyGame extends Game {
    // No Private Fields Yet
    public familyGame(){
        super();
    };
    // Parameterized Constructor
    public familyGame(String name, double price, double height, double width, double length, Decorator gameDecorator) {
        super(name, price, height, width, length, "Family", gameDecorator); // Calling superclass constructor
    }
    public void printFamilyGameDetails() {
        printGameDetails(); // Calling method from superclass
    }
}

class monopolyGame extends familyGame{
    public monopolyGame(Decorator gameDecorator) {
        super("Monopoly", 24.00, 10.51, 15.75, 1.61, gameDecorator); // Calling superclass constructor
    }
    
}

class clueGame extends familyGame{
    public clueGame(Decorator gameDecorator) {
        super("Clue", 11.99, 10.51, 10.51, 1.61, gameDecorator); // Calling superclass constructor
    }
}
class lifeGame extends familyGame{
    public lifeGame(Decorator gameDecorator) {
        super("Life", 15.00, 10.51, 15.75, 3.19, gameDecorator); // Calling superclass constructor
    }
    
}
class kidsGame extends Game {
    // No Private Fields Yet

    public kidsGame(){
        super();
    };
    // Parameterized Constructor
    public kidsGame(String name, double price, double height, double width, double length, Decorator gameDecorator) {
        super(name, price, height, width, length, "Kids", gameDecorator); // Calling superclass constructor
    }

    public void printKidsGameDetails() {
        printGameDetails(); // Calling method from superclass
    }
}


class mousetrapGame extends kidsGame{
    public mousetrapGame(Decorator gameDecorator) {
        super("Mousetrap", 15.00, 10.51, 15.75, 3.19, gameDecorator); // Calling superclass constructor
    }
    
}
class candylandGame extends kidsGame{
    public candylandGame(Decorator gameDecorator) {
        super("Candyland", 12.99, 10.51, 15.75, 1.46, gameDecorator); // Calling superclass constructor
    }
    
}
class connectfourGame extends kidsGame{
    public connectfourGame(Decorator gameDecorator) {
        super("Connect4", 6.99, 10.51, 10.51, 2.13, gameDecorator); // Calling superclass constructor
    }
    
}


class cardGame extends Game {
    // No Private Fields Yet
    public cardGame(){
        super();
    };
    // Parameterized Constructor
    public cardGame(String name, double price, double height, double width, double length, Decorator gameDecorator) {
        super(name, price, height, width, length, "Card", gameDecorator); // Calling superclass constructor
    }

    public void printCardGameDetails() {
        printGameDetails(); // Calling method from superclass
    }
}

class magicGame extends cardGame{
    public magicGame(Decorator gameDecorator) {
        super("Magic", 38.99, 9.49, 10, 2.76, gameDecorator); // Calling superclass constructor
    }
    
}

class pokemonGame extends cardGame{
    public pokemonGame(Decorator gameDecorator) {
        super("Pokemon", 8.49, 0.75, 2, 3, gameDecorator); // Calling superclass constructor
    }
    
}
class netrunnerGame extends cardGame{
    public netrunnerGame(Decorator gameDecorator) {
        super("Netrunner", 11.00, 1, 2.75, 5, gameDecorator); // Calling superclass constructor
    }
    
}


class boardGame extends Game {
    // No Private Fields Yet
    public boardGame(){
        super();
    };

    // Parameterized Constructor
    public boardGame(String name, double price, double height, double width, double length, Decorator gameDecorator) {
        super(name, price, height, width, length, "Board", gameDecorator); // Calling superclass constructor
    }

    public void printBoardGameDetails() {
        printGameDetails(); // Calling method from superclass
    }
}

class catanGame extends boardGame{
    public catanGame(Decorator gameDecorator) {
        super("Catan", 41.75, 3, 9.5, 11.63, gameDecorator); // Calling superclass constructor
    }
    
}

class riskGame extends boardGame{
    public riskGame(Decorator gameDecorator) {
        super("Risk", 29.99, 10.51, 15.75, 1.97, gameDecorator); // Calling superclass constructor
    }
    
}
class gloomhavenGame extends boardGame{
    public gloomhavenGame(Decorator gameDecorator) {
        super("Gloomhaven", 34.99, 10.51, 15.75, 1.97, gameDecorator); // Calling superclass constructor
    }
    
}
class Register {
    // Private Fields
    private double cash; 
    private int num_games_sold; 
    // The number of days the store has been open
    private int days; 
    private int refills;


    // Parameterized Constructor
    public Register() {
        this.cash = 0; 
        this.num_games_sold = 0; 
        this.days = 0; 
    }

    // Print Reigster Details
    public void printRegisterDetails() {
        System.out.println("Day " + days); 
        System.out.println("Amount of Cash in Register: $" + cash); 
        System.out.println("Total number of games sold: " + num_games_sold); 
    }
    public void Refill(){
        this.refills += 1;
    }
    public int getRefills(){
        return this.refills;
    }

    public void addDay() {
        this.days += 1;
    }

    public void gameBought(int total_games_sold, double total_transaction_price) {
        num_games_sold += total_games_sold; 
        cash += total_transaction_price;  
    }

    public double getCash() {
        return cash; 
    }

    // Example of strong cohesion: this method accomplishes a single, simple task. 
    public void addCash(double amount_added) {
        this.cash += amount_added; 
    }

    public void subtractCash(double amount_subtracted) {
        this.cash -= amount_subtracted; 
    }

    public void emptyRegister() {
        cash = 0; 
    }

}

interface Employee {
    // Parameterized Constructor

    // Print Details
    public void printEmployeeDetails();
}

//The demonstrator class is implemented
class Demonstrator implements Employee {
    public String name;
    public boolean working;

    String gameRequested;
    String customerName;

    public Demonstrator(Announcer a){

        working = true;
        int rand = new Random().nextInt(100);
        if(rand < 20){
            this.name = "Dale";
        }
        if(rand < 40){
            this.name = "Dom";
        }
        if(rand < 60){
            this.name = "Dan";
        }
        if(rand < 80){
            this.name = "Karen";
        }
        else{
            this.name = "Jess";
        }
        this.addListener(a);
        this.tellListener(this.name + " is the new Demonstrator");

    }
    
    Announcer listener;
    public void addListener(Announcer a){
        this.listener = a;
    }
    public void tellListener(String message){
        this.listener.announce(message);
    }

    public void demonstrate(){
        this.tellListener(this.name + " the Demonstrator demonstrates " + this.gameRequested + " to " + this.customerName + " the customer");
    }
    public void recommend(){
        this.tellListener(this.name + " the Demonstrator recommends " + this.gameRequested + " to " + this.customerName + " the customer");
    }
    public void explain(){
        this.tellListener(this.name + " the Demonstrator explains " + this.gameRequested + " to " + this.customerName + " the customer");
    }
    public void nothing(){
        //no action, customer entered
    }

    public void setGame(String gameName){
        gameRequested = gameName;
    }

    public void setCustomer(String customer){
        customerName = customer;
    }
    public void runAway(){
        working = false;
        this.tellListener(this.name + " the Demonstrator RAN AWAY SCREAMING! COOKIE MONSTER!");
    }

    public void leave(){
        working = false;
        this.tellListener(this.name + " the Demonstrator has left.");
    }

    public void printEmployeeDetails() {
        System.out.println("Name: " + name); 
        System.out.println("Working?");
        if (working == false) {
            System.out.println("No");
        }
        else {
            System.out.println("Yes"); 
        }
    }
}

//Lazy and eager announcer
interface Announcer extends Employee {
    // abstract Announcer getInstance();
    void announce(String message);
    void arrive();
    void leave();
}
class lazyAnnouncer implements Announcer{
    public String name;  // Need to be public so that the subclass cashier can use these attributes
    // Working = 1, Not working = 0
    public boolean working;

    private static lazyAnnouncer instance = null;
	private lazyAnnouncer(String name)
	{
        this.name = name;
		System.out.println("Singleton being initialized");
	}
	
	public static lazyAnnouncer getInstance()
	{
		if(instance == null)
			instance = new lazyAnnouncer("Guy Lazy");
		return instance;
	}
    public void announce(String message){
            System.out.println(message);
    }
    public void arrive(){
        System.out.println(name + " the announcer has arrived.");
    }
    public void leave(){
        System.out.println(name + " the announcer has left.");
    }

    public void printEmployeeDetails() {
        System.out.println("Name: " + name); 
        System.out.println("Working?");
        if (working == false) {
            System.out.println("No");
        }
        else {
            System.out.println("Yes"); 
        }
    }
}

class eagerAnnouncer implements Announcer{
    public String name;  // Need to be public so that the subclass cashier can use these attributes
    // Working = 1, Not working = 0
    public boolean working;

    private static eagerAnnouncer instance = new eagerAnnouncer("Guy Eager");
	
	private eagerAnnouncer(String name)
	{
        this.name = name;
		System.out.println("Singleton being initialized");
	}
	
	public static eagerAnnouncer getInstance()
	{
		return instance;
	}
    public void announce(String message){
        System.out.println(message);
    }
    public void arrive(){
        System.out.println(name + " the announcer has arrived.");
    }
    public void leave(){
        System.out.println(name + " the announcer has left.");
    }

    public void printEmployeeDetails() {
        System.out.println("Name: " + name); 
        System.out.println("Working?");
        if (working == false) {
            System.out.println("No");
        }
        else {
            System.out.println("Yes"); 
        }
    }
}

class Baker implements Employee {
    public String name;  // Need to be public so that the subclass cashier can use these attributes
    // Working = 1, Not working = 0
    public boolean working;

    // The number of 1 dozen packages the baker will drop off
    int packages = 1; 
    int cash = 0; 
    // Price per cookie
    double sale_price = 2.00;

    Announcer listener;

    public Baker(String name) {
        this.name = name;
    }

    public void printEmployeeDetails() {
        System.out.println("Name: " + name); 
        System.out.println("Working?");
        if (working == false) {
            System.out.println("No");
        }
        else {
            System.out.println("Yes"); 
        }
    }

    public void addListener(Announcer a){
        this.listener = a;
    }
    public void tellListener(String message){
        this.listener.announce(message);
    }

    public void Arrive() {
        // Change to send this info to Guy
        // System.out.println(this.name + " the baker has arrived.");
        this.tellListener(this.name + " the baker has arrived.");
    }

    public int addPackage(){
        this.packages += 1; 
        return packages; 
    }

    public int subtractPackage(){
        this.packages -= 1; 
        return packages; 
    }

    public int getNumPackagees(){
        return this.packages; 
    }

    public int getCash() {
        return this.cash; 
    }

    public int Delivery(){
        // Change to send this info to Guy
        // System.out.println(this.name + " the baker has delievered " + this.packages + " packages of cookies in exchange for " + (this.packages * 24.00));
        this.tellListener(this.name + " the baker has delievered " + this.packages + " packages of cookies in exchange for " + (this.packages * 24.00));
        this.cash += (this.packages * 12.00);
        // System.out.println(this.name + " the baker is now leaving.");
        this.tellListener(this.name + " the baker is now leaving.");
        return packages; 
    }
    //This is for use in the robbery
    public int setPackages(int n) {
        this.packages = n; 
        return this.packages; 
    }
}
// Example of polymorphism: If a message is sent to cashier, the method lookup starts at cashier 
// and goes up to more generalized employee class if a specific cashier method is not found. 

// Strategy pattern implemented here for stacking based on employee
interface Strategy {
    public void stack(Game games[], Cashier cashier);
 }

class BurtStrat implements Strategy{
    @Override
    public void stack(Game games[], Cashier cashier) {
        int n = games.length;
        for (int i = 0; i < n; i++){
            // Find the minimum element in unsorted array
            int min_index = i;
            for (int j = i+1; j < n; j++){
                if (games[j].getWidth() > games[min_index].getWidth()){ //finding the one with the max width to put earlier in array
                    min_index = j;
                }
            }

            // Swap the found minimum element with the first
            // element
            Game temp = games[min_index];
            games[min_index] = games[i];
            games[i] = temp;
            games[i].setShelfPosition(i+1); //nwe position on the shelf is i+1, since i is zero-indexed and our shelf is 1-indexed
            // System.out.println(employee.name + " stacks " + games[i].getInventory() + " " + games[i].getName()
            //                     +  " games in shelf position " + games[i].getShelfPosition() + " (game width = " 
            //                     + games[i].getWidth() + "\")");
            cashier.tellListener(cashier.name + " stacks " + games[i].getInventory() + " " + games[i].getName()
                                +  " games in shelf position " + games[i].getShelfPosition() + " (game width = " 
                                + games[i].getWidth() + "\")");

        }
    } 
 }
class ErnieStrat implements Strategy{
    @Override
    public void stack(Game games[], Cashier cashier) {
        int n = games.length;
        for (int i = 0; i < n; i++){
            // Find the minimum element in unsorted array
            int min_index = i;
            for (int j = i+1; j < n; j++){
                if (games[j].getHeight() < games[min_index].getHeight()){ //finding the one with the minimum height to put earlier in array
                    min_index = j;
                }     
            } 
            Game temp = games[min_index];
            games[min_index] = games[i];
            games[i] = temp;
            games[i] = temp;
            games[i].setShelfPosition(i+1); //new position on the shelf is i+1
            // System.out.println(employee.name + " stacks " + games[i].getInventory() + " " + games[i].getName()
            //                     + " games in shelf position " + games[i].getShelfPosition() + " (pile height = " 
            //                     + games[i].getHeight() + "\")");
            cashier.tellListener(cashier.name + " stacks " + games[i].getInventory() + " " + games[i].getName()
                                +  " games in shelf position " + games[i].getShelfPosition() + " (pile height = " 
                                + games[i].getHeight() + "\")");
        }
    } 
 }

class BartStrat implements Strategy{
    @Override
    public void stack(Game games[], Cashier cashier) {
        int n = games.length;
        int more_than_one = 0;

        for(int i = 0; i < n; i++){
            if(games[i].getInventory() > 1){
                more_than_one += 1; //keeps track of how many totoal
            }
        }
        for (int i = 0; i < n; i++){
            // Find the minimum element in unsorted array
            int min_index = i;
            if(more_than_one > 0){
                for(int j = i; i < n; j++){
                    if(games[j].getInventory() > 1){
                        Game temp = games[i];
                        games[i] = games[j];
                        games[j] = temp; //makes sure the first index has an inventory of > 1
                        break;
                    }
                }
                for (int j = i+1; j < n; j++){
                    if ((games[j].getWidth() > games[min_index].getWidth()) && (games[j].getInventory() > 1)){
                        //finding the one with the minimum width also has inventory greater than 1
                        min_index = j;
                    }     
                }
                more_than_one -= 1; //so it will get to zero when there are none with inventory > 1 
            }
            else{
                for (int j = i+1; j < n; j++){
                    if (games[j].getWidth() > games[min_index].getWidth()){ 
                        //finding the one with the minimum width to put earlier in array
                        min_index = j;
                    }     
                }  
            }
            Game temp = games[min_index];
            games[min_index] = games[i];
            games[i] = temp;
            games[i] = temp;
            games[i].setShelfPosition(i+1); //new position on the shelf is i+1
            cashier.tellListener(cashier.name + " stacks " + games[i].getInventory() + " " + games[i].getName()
                                +  " games in shelf position " + games[i].getShelfPosition() + " (game width = " 
                                + games[i].getWidth() + "\")");
        }
    } 
 }

class Cashier implements Employee {

    public String name;  // Need to be public so that the subclass cashier can use these attributes
    // Working = 1, Not working = 0
    public boolean working;
    // Private Fields

    // Example of encapsulation: The marking of vacuum_damage as "private" ensures other classes cannot access it directly. 
    private int vacuum_damage; 
    private Strategy strategy;
    Announcer listener;

    // Parametized Constructor
    public Cashier(String name, int vacuum_damage, Strategy strat) {
        this.name = name; // Calling superclass constructor
        this.vacuum_damage = vacuum_damage; 
        this.strategy = strat;
    }

    // Print Cashier Details
    public void printEmployeeDetails() {
        System.out.println("Name: " + name); 
        System.out.println("Working?");
        if (working == false) {
            System.out.println("No");
        }
        else {
            System.out.println("Yes"); 
        }
    }

    public void addListener(Announcer a){
        this.listener = a;
    }
    public void tellListener(String message){
        this.listener.announce(message);
    }

    public void sendRequest(Request request){
        request.execute();
    }


    public Game[] Arrive(Game games[], Game order[], int day) {
        // System.out.println(this.name + " the cashier has arrived on Day " + day);
        this.tellListener(this.name + " the cashier has arrived on Day " + day);
        // Check if new games have arrived, add the new games to the inventory and announce their arrival
        for(int i = 0; i < games.length; i++){
            if(order[i] != null){ //if we marked this index as having been ordered
                games[i].setInventory(3); //we just have to set it to 3 since we know it was just 0
                // System.out.println("Just received 3 copies of " + games[i].getName()); //always 3 copies, don't need to assign positions yet
                this.tellListener("Just received 3 copies of " + games[i].getName());
            }
        }
        return games; //so GameStore can see these updates
    }

    public void Count(Register register) {
        double cash = register.getCash(); 
        // System.out.println("Amount of Cash: " + cash);
        this.tellListener("Amount of Cash: " + cash);

        if(cash < 100) {
            register.addCash(1000); 
            register.Refill();
            // System.out.println("$1000 dollars was added to the register!");
            this.tellListener("$1000 dollars was added to the register!");
        }
    }

    // Example of abstraction: the vacuum method only takes in the necessery values it needs to compute if/what game was damaged; it
    //doesn't take in employee, for example, and abstracts that information away. 
    public void Vacuum(Game games[], Game damaged_games[]) {
        //System.out.println(this.name + " is vacuuming the store.");
        this.tellListener(this.name + " is vacuuming the store.");
        int games_damaged = 1; 
        // Example of Identity: Each game stored has a certain index in the games array that acts as a unique identifier.
        boolean damage = new Random().nextInt(100) < this.vacuum_damage; //vacuum_damage is 5 for bernie, 10 for the other guy... 0-99 # so < that potential is %
        if (damage){
            gameDamage(games, games_damaged, damaged_games);
        }
    }

    // Example of delegation: the game damage functionality becomes it's own class so that both the vacuum and the cookie monster
    // class can use it. 
    public void gameDamage(Game games[], int num_games_damaged, Game damaged_games[]) {
        int vacuum_result = -1; //starts at -1, doesn't change if no damage
        // If a random game was damaged, we need to announce the game, take it from the inventory and place it into the damaged game container
        for(int i=0; i < num_games_damaged; i++) {
            int counter = 0;
            while(counter < 20){
                vacuum_result = new Random().nextInt(games.length);
                if(games[vacuum_result].getInventory() >= 1){ //if there is at least one in stock to delete
                    break; //get out of the while loop, we know our return_ value is what we want
                }
                counter += 1;
            }
            System.out.println(this.name + " damaged a copy of " + games[vacuum_result].getName());   
            System.out.println("Here");
            int in_stock = games[vacuum_result].getInventory();
            games[vacuum_result].setInventory(in_stock - 1); //subtract 1... we know from vaccum method that this val must be >=1
            Game temp = games[vacuum_result]; //creates shallow copy
            if(damaged_games[vacuum_result] == null){ //meaning we have not damaged this copy
                damaged_games[vacuum_result] = temp;
                damaged_games[vacuum_result].setInventory(1);
            }
            else{ //meaning we have already damaged this product
                Game damaged = damaged_games[vacuum_result];
                damaged.setInventory(damaged.getInventory() + 1); //adds 1 to the inventory in damaged
            }

        }
    }


    //Stack just calls the strategy interface
    public void Stack(Game games[], Employee employee){
        this.strategy.stack(games, this);
    }

    public Game[] Order(Game games[], Register register, Baker baker, int num_cookies, int num_cookie_packages){
        Game order[] = new Game[games.length];
        for(int i = 0; i < games.length; i++){
            if(games[i].getInventory() == 0){ //if there are none left
                order[i] = games[i]; //save this object id to add to inventory upon tomorrow's arrive... keep index the same, rest are NULL
                register.addCash(-3 * (games[i].getPrice()/2)); //subtracts 3 * 1/2 the price of the game
                if(register.getCash() < 0){
                    // System.out.println(("We took a bit of a loan to order games, no worries"));
                    this.tellListener("We took a bit of a loan to order games, no worries");
                }
            }
            else{
                order[i] = null; //otherwise makes sure that this index is null
            }
        }
        // Cookie orders for Gonger
        if (num_cookies == 0 && num_cookie_packages > 1) {
            baker.subtractPackage(); 
        }
        else if (num_cookies > 0) {
            baker.addPackage(); 
        }
        return order;
    }
    //Poisson Distribution: https://stackoverflow.com/questions/1241555/algorithm-to-generate-poisson-and-binomial-random-numbers
    public static int getPoisson(double lambda) {
        double L = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;
      
        do {
          k++;
          p *= Math.random();
        } while (p > L);
      
        return k - 1;
    }

public int Open(Game games[], Register register, int cookie_total, Game damaged_games[], randomNameGenerator random){
        // System.out.println(this.name + " is opening the store");
        this.tellListener(this.name + " is opening the store");

        Demonstrator demonstrator = new Demonstrator(this.listener);
        boolean demonstratorPresent = true;
        CustomerFactory customerFactory = new CustomerFactory();

        int num_customers = getPoisson(3);

        int purchase_chance = 0; // Define purchase chance here so that cookies can factor into purchase chance
        //Check to see if each customer purchases each game 
        for (int i = 0; i < num_customers; i++){ //loop through customers
            int rand = new Random().nextInt(100);
            Customer c;

            //RANDOM ASSIGN OF CUSTOMER USING FACTORY PATTERN

            if(rand < 20){ //20%
                c = customerFactory.createCustomer("Kids", random);
                c.assign_bonus(games); //gets the bonuses assigned for that customer
            }
            else if(rand < 40){ //20%
                c = customerFactory.createCustomer("Family", random);
                c.assign_bonus(games); //gets the bonuses assigned for that customer
            }
            else if(rand < 50){ //10%
                c = customerFactory.createCustomer("Card", random);
                c.assign_bonus(games); //gets the bonuses assigned for that customer
            }
            else if(rand < 99){ //49%
                c = customerFactory.createCustomer("Board", random);
                c.assign_bonus(games); //gets the bonuses assigned for that customer
            }
            else{ //1%
                //c = new CookieMonster();
                c = customerFactory.createCustomer("Cookie Monster", random);
                c.assign_bonus(games); //gets the bonuses assigned for that customer
            }

            
            boolean purchased_something = false; //stays at false until the customer has purchased at least something
            // Check to see if each customer decides to purchase cookies: 50/50 yes/no
            int purchased_cookies = new Random().nextInt(2);
            // There is a 1% chance that this customer is the Cookie Monster


            //CUSTOMER REQUESTS FOR DEMONSTRATIONS
            int numRequests = new Random().nextInt(4);
            // if(demonstratorPresent == false){ //if there is no demonstrator, we need to get a new one
            //     demonstrator = new Demonstrator();
            //     demonstratorPresent = true;
            // }
            if(c.get_name() == "Cookie Monster"){//Demonstrator runs away if the cookie monster is there
                demonstrator.runAway();
                demonstratorPresent = false;
            }
            else{
                for(int j = 0; j < numRequests; j++){

                    int gameSelected = new Random().nextInt(12); 
                    String gameName = games[gameSelected].getName();
                    demonstrator.setGame(gameName);
                    demonstrator.setCustomer(c.get_name());
                    int demonstratorDecision = new Random().nextInt(100);
                    if(!demonstratorPresent){
                        demonstratorDecision = 200; //exception case to indicate also that demonstrator is not here today
                    }
                    if(demonstratorDecision < 25){//Demonstrate
                        gameRequest gamereq = new gameRequest();
                        gamereq.setDemonstrator(demonstrator);
                        demonstrateGame demonstrate = new demonstrateGame(gamereq);
                        this.sendRequest(demonstrate);
                        c.add_bonus(gameSelected);

                    }
                    if(demonstratorDecision < 55){//Recommend
                        gameRequest gamereq = new gameRequest();
                        gamereq.setDemonstrator(demonstrator);
                        recommendGame recommend = new recommendGame(gamereq);
                        this.sendRequest(recommend);
                        c.add_bonus(gameSelected);

                    }
                    if(demonstratorDecision < 75){//Explain
                        gameRequest gamereq = new gameRequest();
                        gamereq.setDemonstrator(demonstrator);
                        explainGame explain = new explainGame(gamereq);
                        this.sendRequest(explain);
                        c.add_bonus(gameSelected);

                    }
                    else if (demonstratorDecision < 100){
                        this.tellListener(c.get_name() + " didn't want any more demonstrations and went inside.");
                        break;
                    }
                    else{
                        break; //break here because the demonstrator is not there
                    }
                }
            }
            


            if (c.get_name() == "Cookie Monster" && cookie_total != 0) {
                cookie_total = 0; 
                this.tellListener("The cookie monster ate all of the cookies!");
                // Determine game damage and announce via Guy
                int num_games_damaged = new Random().nextInt(6) + 1;
                this.tellListener(num_games_damaged + " games were damaged by the cookie monster.");
                gameDamage(games, num_games_damaged, damaged_games);
            }
            if (purchased_cookies == 1) { // Customer purchased cookies
                int num_cookies_purchased = new Random().nextInt(4);
                if (cookie_total == 0) {
                    purchase_chance -= 10; 
                    this.tellListener("Sorry, no cookies left to buy.");
                }
                else if (num_cookies_purchased <= cookie_total) {
                    cookie_total -= num_cookies_purchased; 
                    register.addCash(num_cookies_purchased * 2.00);
                    purchase_chance += 20;
                    this.tellListener(num_cookies_purchased + " cookies purchased.");
                }
                else {
                    num_cookies_purchased = cookie_total; 
                    cookie_total = 0; 
                    register.addCash(num_cookies_purchased * 2.00);
                    purchase_chance += 20;
                    this.tellListener(num_cookies_purchased + " cookies purchased.");
                }
            }
            for(int j = 0; j < games.length; j++){ //loop through games
                // if(c.get_chance(j) != 0){
                //     System.out.println("GOT A CHANCE");
                // }
                purchase_chance += 22 - (2*games[j].getShelfPosition()) + c.get_chance(j); //only pass the index, will return either 0 or the bonus
                boolean purchased = new Random().nextInt(100) < purchase_chance; //decide whether or not the customer will purchase
                if (purchased && games[j].getInventory() > 0){
                    games[j].setInventory(games[j].getInventory() - 1);
                    games[j].addSold();
                    purchased_something = true;

                    //DECORATOR IMPLEMENTATION

                    double price = games[j].gameDecorator.getPrice();

                    //This is the only line where the decorator is actually used

                    register.addCash(price);
                    // System.out.println(this.name + " sold a " + games[j].getName() + " game to customer " 
                    //     + (i+1) + " for " + games[j].getPrice());
                    this.tellListener(this.name + " sold a " + games[j].getName() + " game to customer " 
                        + (i+1) + " for " + price);
                }
                boolean another_copy = false;
                if (purchased && games[j].getInventory() > 0){ //we do this a second time to see if they buy aother copy
                    another_copy = new Random().nextInt(2) == 0;
                    if(another_copy){
                        games[j].setInventory(games[j].getInventory() - 1);
                        // System.out.println(this.name + " sold another " + games[j].getName() 
                        //     + " game to customer " + (i+1) + " for " + games[j].getPrice());
                        this.tellListener(this.name + " sold another " + games[j].getName() 
                            + " game to customer " + (i+1) + " for " + games[j].gameDecorator.getPrice());
                        games[j].addSold();
                    }
                }
            }
            if(purchased_something == false){
                // System.out.println("Customer " + (i+1) + " did not buy anything");
                this.tellListener("Customer " + (i+1) + " did not buy anything");
            }
        }
        demonstrator.leave();
        if(num_customers == 0){
            // System.out.println("We had no customers today...");
            this.tellListener("We had no customers today...");
        }
        return cookie_total; 
    }

    public void Close(){
        // System.out.println(this.name + " is leaving and the store is closed.");
        this.tellListener(this.name + " is leaving and the store is closed.");
    }

}
// This is the random name generator that utilizes a circular array
class randomNameGenerator{
    private String firstNames[] = new String[20]; 
    private String lastNames[] = new String[20]; 
    private String fname = ""; 
    private String lname = ""; 
    private int firstNameCounter = 0;  
    private int lastNameCounter = 0; 

    public randomNameGenerator() {
    // Most popular baby names this year: https://www.ssa.gov/oact/babynames/
    firstNames[0] = "Liam"; 
    firstNames[1] = "Olivia"; 
    firstNames[2] = "Noah"; 
    firstNames[3] = "Emma"; 
    firstNames[4] = "Oliver"; 
    firstNames[5] = "Ava"; 
    firstNames[6] = "Elijah"; 
    firstNames[7] = "Charlotte"; 
    firstNames[8] = "William"; 
    firstNames[9] = "Sophia"; 
    firstNames[10] = "James"; 
    firstNames[11] = "Amelia"; 
    firstNames[12] = "Benjamin"; 
    firstNames[13] = "Isabella"; 
    firstNames[14] = "Lucas"; 
    firstNames[15] = "Mia"; 
    firstNames[16] = "Henry"; 
    firstNames[17] = "Evelyn"; 
    firstNames[18] = "Alexander"; 
    firstNames[19] = "Harper"; 
    // Most common last names: https://www.infoplease.com/us/population/most-common-last-names-us
    lastNames[0] = "Smith"; 
    lastNames[1] = "Johnson"; 
    lastNames[2] = "Williams"; 
    lastNames[3] = "Brown"; 
    lastNames[4] = "Jones"; 
    lastNames[5] = "Garcia"; 
    lastNames[6] = "Miller"; 
    lastNames[7] = "Davis"; 
    lastNames[8] = "Rodriguez"; 
    lastNames[9] = "Martinez"; 
    }
    //Circular Array Implementation
    public String getName() {
        if (firstNameCounter < 19) {
            this.fname = firstNames[firstNameCounter]; 
            firstNameCounter++; 
        }
        else if (firstNameCounter == 19) {
            firstNameCounter = 0; 
            this.fname = firstNames[firstNameCounter]; 
            firstNameCounter++;
        }
        if (lastNameCounter < 9) {
            this.lname = lastNames[lastNameCounter]; 
            lastNameCounter++; 
        }
        else if (lastNameCounter == 9) {
            lastNameCounter = 0; 
            this.lname = lastNames[lastNameCounter]; 
            lastNameCounter++;
        }
        String name = this.fname + " " + this.lname; 
        return name; 
    }
}

class GameStore{
    private Game[] games_;
    private Game[] order_;
    private Game[] damaged_;
    private Employee[] employees_;
    private Register register_;
    private int day = 0;
    private int num_cookies = 0;
    private int num_cookie_packages = 0; 
    private int num_cookies_sold = 0; 
    private int total_cookies_sold = 0; 
    private Baker baker_; 
    private Announcer announcer_;
    private randomNameGenerator random_;

    public GameStore(Game games[],Employee employees[], Baker baker, String announcer, randomNameGenerator random){ //paramaterized constructor
        this.games_ = new Game[games.length];
        this.order_ = new Game[games.length]; //this is for temporarily holding those that are being ordered
        this.damaged_ = new Game[games.length];
        this.employees_ = new Employee[employees.length];
        this.register_ = new Register();
        this.baker_ = baker; 
        this.random_ = random;
        if(announcer == "Eager"){
            this.announcer_ = eagerAnnouncer.getInstance();
        }
        else{ //most likely "Lazy", but default is to do it lazily
            this.announcer_ = lazyAnnouncer.getInstance();
        }
        // System.arraycopy(games, 0, this.games_, 0, games.length-1);
        for(int i = 0 ; i < games.length; i++){
            this.games_[i] = games[i];
            order_[i] = null;
            damaged_[i] = null;//sets these guys at null to compare later
        }
        System.arraycopy(employees, 0, this.employees_, 0, employees.length); //copies those arrays over
        System.out.println(this.games_[1].getHeight());

    }
    public Employee decideEmployee(){
        int coin = new Random().nextInt(this.employees_.length);
        return this.employees_[coin]; //random chance for any employee... not just 50/50 , it's 50/50 if there are only two employes
    }
    //Insurance replaces everything as if it were day 1 after the store is robbed
    public Game[] insuranceGameRestock(){
        Game games[] = new Game[12];
        //familyGame Monopoly = new familyGame("Monopoly", );
        monopolyGame Monopoly = new monopolyGame(new MonopolyGameDecorator());
        games[0] = Monopoly; 
        clueGame Clue = new clueGame(new gameDecorator());
        games[1] = Clue; 
        lifeGame Life = new lifeGame(new gameDecorator());
        games[2] = Life; 
        mousetrapGame Mousetrap = new mousetrapGame(new MousetrapDecorator());
        games[3] = Mousetrap; 
        candylandGame Candyland = new candylandGame(new gameDecorator());
        games[4] = Candyland; 
        connectfourGame Connect4 = new connectfourGame(new gameDecorator());
        games[5] = Connect4; 
        magicGame Magic = new magicGame(new CardGameDecorator());
        games[6] = Magic; 
        pokemonGame Pokemon = new pokemonGame(new CardGameDecorator()); 
        games[7] = Pokemon; 
        netrunnerGame Netrunner = new netrunnerGame(new CardGameDecorator());
        games[8] = Netrunner; 
        catanGame Catan = new catanGame(new gameDecorator());
        games[9] = Catan; 
        riskGame Risk = new riskGame(new gameDecorator());
        games[10] = Risk; 
        gloomhavenGame Gloomhaven = new gloomhavenGame(new GloomhavenDecorator()); 
        games[11] = Gloomhaven; 
        return games; 
    }

    public void run_day(){
        this.day += 1;
        announcer_.arrive();
        Cashier cashier = (Cashier)decideEmployee(); //gets the employee for the day
        cashier.addListener(this.announcer_);
        baker_.addListener(this.announcer_);
        this.games_ = cashier.Arrive(this.games_, this.order_, this.day); //arrive is what returns + 1 onto the day variable... so day 1 is day 1, not zero
        cashier.Count(this.register_);
        // The baker arrives after the cash has been counted
        this.baker_.Arrive(); 
        num_cookie_packages = this.baker_.Delivery(); 
        num_cookies = num_cookie_packages * 12; 
        register_.subtractCash(num_cookies); //Sale price is 2.00, so half is 1.00
        cashier.Vacuum(this.games_, this.damaged_);
        cashier.Stack(this.games_, cashier);
        num_cookies_sold = num_cookies; 
        num_cookies = cashier.Open(this.games_, this.register_, num_cookies, this.damaged_, this.random_);
        num_cookies_sold -= num_cookies;
        this.announcer_.announce(num_cookies_sold + " cookies were sold today.");
        total_cookies_sold += num_cookies_sold; 
        this.order_ = cashier.Order(this.games_, this.register_, this.baker_, this.num_cookies, this.num_cookie_packages);
        cashier.Close();
        //There is a 1/30 chance that the store is robbed after day 1
        if (this.day == 1) {
            int robbed = new Random().nextInt(29);
            if (robbed == 0) { //The store was robbed; all money, cookies and games are removed. 
                games_=insuranceGameRestock(); 
                num_cookie_packages = this.baker_.setPackages(1); 
                num_cookies = 0; 
                this.register_.emptyRegister();
                this.announcer_.announce("The store was robbed after day 1. All money, cookies and games are gone."); 
                this.day += 2; 
                this.announcer_.announce("The store was closed for day 2 as insurance replaces the stock. It is now day 3."); 
            }
        }
        announcer_.leave();
    }
    public void printResults(){
        for(int i = 0; i < this.games_.length; i++){
            System.out.println(this.games_[i].getName() + " sold " + this.games_[i].getSold() + " copies for a total of $" 
            + (this.games_[i].getSold() * this.games_[i].getPrice()));
        }
        System.out.println("Damaged contents:");
        for(int i = 0; i < this.damaged_.length; i++){
            if(damaged_[i] != null){
                System.out.println(damaged_[i].getName());
            }
        }
        System.out.println("Final state of the register: " + this.register_.getCash() 
                + " having been filled up " + this.register_.getRefills() + " times.");
        System.out.println("Total amount paid to Gonger: " + this.baker_.getCash());
        System.out.println("Total cookies bought: " + this.baker_.getCash());
        System.out.println("Total cookies sold: " + total_cookies_sold);
    }
}
// Customer interface/classes
interface Customer {
    void assign_bonus(Game games[]);
    int get_chance(int index);
    void add_bonus(int index);
    String get_name();
}

class KidCustomer implements Customer{
    private String name;


    public int bonuses[] = new int[12];
    KidCustomer(String name){this.name = name;}
    public void assign_bonus(Game games[]){
        boolean one = false;
        boolean two = false;
        boolean three = false;
        for(int i = 0; i < games.length; i++){
            if(games[i].getCategory() == "Kids"){ //should only be 3 of these
                while(true){ //until we find a bonus chance to assign
                    int rand = new Random().nextInt(3) + 1; //gives us a number 1-3
                    if(rand == 1 && one == false){
                        bonuses[i] = rand*10;
                        one = true; //so that we dont assign this again
                        break; 
                    }
                    if(rand == 2 && two == false){
                        bonuses[i] = rand*10;
                        two = true; //so that we dont assign this again
                        break;
                    }
                    if(rand == 3 && three == false){
                        bonuses[i] = rand*10;
                        three = true; //so that we dont assign this again
                        break;
                    }
                }
            }
            else{
                bonuses[i] = 0; //sets all the other games to bonus chance 0
            }
        }
    }

    public void add_bonus(int index){
        bonuses[index] = bonuses[index] + 10;
    } 
    public String get_name(){
        return name;
    }



    public int get_chance(int index){ //we only want to pass the index into this, easier to acces via bonuses[index]
        return bonuses[index];
    }
}
class FamilyCustomer implements Customer{
    
    private String name;
    public int bonuses[] = new int[12];
    FamilyCustomer(String name){this.name = name;}
    public void assign_bonus(Game games[]){
        boolean one = false;
        boolean two = false;
        boolean three = false;
        for(int i = 0; i < games.length; i++){
            if(games[i].getCategory() == "Family"){ //should only be 3 of these
                while(true){ //until we find a bonus chance to assign
                    int rand = new Random().nextInt(3) + 1; //gives us a number 1-3
                    if(rand == 1 && one == false){
                        bonuses[i] = rand*10;
                        one = true; //so that we dont assign this again
                        break; 
                    }
                    if(rand == 2 && two == false){
                        bonuses[i] = rand*10;
                        two = true; //so that we dont assign this again
                        break;
                    }
                    if(rand == 3 && three == false){
                        bonuses[i] = rand*10;
                        three = true; //so that we dont assign this again
                        break;
                    }
                }
            }
            else{
                bonuses[i] = 0; //sets all the other games to bonus chance 0
            }
        }
    }

    public void add_bonus(int index){
        bonuses[index] = bonuses[index] + 10;
    } 

    public String get_name(){
        return name;
    }

    public int get_chance(int index){ //we only want to pass the index into this, easier to acces via bonuses[index]
        return bonuses[index];
    }
}
class CardCustomer implements Customer{
    private String name;
    public int bonuses[] = new int[12];
    CardCustomer(String name){this.name = name;}
    public void assign_bonus(Game games[]){
        boolean one = false;
        boolean two = false;
        boolean three = false;
        for(int i = 0; i < games.length; i++){
            if(games[i].getCategory() == "Card"){ //should only be 3 of these
                while(true){ //until we find a bonus chance to assign
                    int rand = new Random().nextInt(3) + 1; //gives us a number 1-3
                    if(rand == 1 && one == false){
                        bonuses[i] = rand*10;
                        one = true; //so that we dont assign this again
                        break; 
                    }
                    if(rand == 2 && two == false){
                        bonuses[i] = rand*10;
                        two = true; //so that we dont assign this again
                        break;
                    }
                    if(rand == 3 && three == false){
                        bonuses[i] = rand*10;
                        three = true; //so that we dont assign this again
                        break;
                    }
                }
            }
            else{
                bonuses[i] = 0; //sets all the other games to bonus chance 0
            }
        }
    }

    public void add_bonus(int index){
        bonuses[index] = bonuses[index] + 10;
    } 

    public String get_name(){
        return name;
    }

    public int get_chance(int index){ //we only want to pass the index into this, easier to acces via bonuses[index]
        return bonuses[index];
    }
}
class BoardCustomer implements Customer{
    private String name;
    public int bonuses[] = new int[12];
    BoardCustomer(String name){this.name = name;}
    public void assign_bonus(Game games[]){
        boolean one = false;
        boolean two = false;
        boolean three = false;
        for(int i = 0; i < games.length; i++){
            if(games[i].getCategory() == "Board"){ //should only be 3 of these
                while(true){ //until we find a bonus chance to assign
                    int rand = new Random().nextInt(3) + 1; //gives us a number 1-3
                    if(rand == 1 && one == false){
                        bonuses[i] = rand*10;
                        one = true; //so that we dont assign this again
                        break; 
                    }
                    if(rand == 2 && two == false){
                        bonuses[i] = rand*10;
                        two = true; //so that we dont assign this again
                        break;
                    }
                    if(rand == 3 && three == false){
                        bonuses[i] = rand*10;
                        three = true; //so that we dont assign this again
                        break;
                    }
                }
            }
            else{
                bonuses[i] = 0; //sets all the other games to bonus chance 0
            }
        }
    }

    public void add_bonus(int index){
        bonuses[index] = bonuses[index] + 10;
    } 

    public String get_name(){
        return name;
    }

    public int get_chance(int index){ //we only want to pass the index into this, easier to acces via bonuses[index]
        return bonuses[index];
    }
}

class CookieMonster implements Customer{
    private String name = "Cookie Monster";
    int bonuses[] = new int[12];
    public void assign_bonus(Game games[]){
        for(int i = 0; i < games.length; i++){
            bonuses[i] = 0;
        }
    }
    public void add_bonus(int index){
        bonuses[index] = bonuses[index] + 10;
    } 
    public int get_chance(int index){
        return bonuses[index];
    }
    public String get_name(){
        return name;
    }
}

class CustomerFactory { //help from https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/
    public Customer createCustomer(String category, randomNameGenerator random){
        if (category == null || category.isEmpty())
            return null;
        if ("Kids".equals(category)) {
            return new KidCustomer(random.getName());
        }
        else if ("Board".equals(category)) {
            return new BoardCustomer(random.getName());
        }
        else if ("Card".equals(category)) {
            return new CardCustomer(random.getName());
        }
        else if("Family".equals(category)){
            return new FamilyCustomer(random.getName());
        }
        else if("Cookie Monster".equals(category)){
            return new CookieMonster();
        }
        return null;
    }
}


class p4p2 {
    public static void main(String[] args) {
        Game games[] = new Game[12];
        //familyGame Monopoly = new familyGame("Monopoly", );
        monopolyGame Monopoly = new monopolyGame(new MonopolyGameDecorator());
        games[0] = Monopoly; 
        clueGame Clue = new clueGame(new gameDecorator());
        games[1] = Clue; 
        lifeGame Life = new lifeGame(new gameDecorator());
        games[2] = Life; 
        mousetrapGame Mousetrap = new mousetrapGame(new MousetrapDecorator());
        games[3] = Mousetrap; 
        candylandGame Candyland = new candylandGame(new gameDecorator());
        games[4] = Candyland; 
        connectfourGame Connect4 = new connectfourGame(new gameDecorator());
        games[5] = Connect4; 
        magicGame Magic = new magicGame(new CardGameDecorator());
        games[6] = Magic; 
        pokemonGame Pokemon = new pokemonGame(new CardGameDecorator()); 
        games[7] = Pokemon; 
        netrunnerGame Netrunner = new netrunnerGame(new CardGameDecorator());
        games[8] = Netrunner; 
        catanGame Catan = new catanGame(new gameDecorator());
        games[9] = Catan; 
        riskGame Risk = new riskGame(new gameDecorator());
        games[10] = Risk; 
        gloomhavenGame Gloomhaven = new gloomhavenGame(new GloomhavenDecorator()); 
        games[11] = Gloomhaven; 
        for(int i = 0; i < 12; i++){
            games[i].gameDecorator.setGame(games[i]); //this adds the price information needed to gameDecorator
        }
        Employee employees[] = { new Cashier("Ernie", 5, new ErnieStrat()), new Cashier("Burt", 10, new BurtStrat()), 
                                new Cashier("Bart", 2, new BartStrat())};
        employees[1].printEmployeeDetails();
        Baker baker = new Baker("Gonger");
        // int rand = new nextInt(2);
        // if(rand == 0){
        //     Announcer announcer = new Announcer("Guy");
        // }
        
        baker.printEmployeeDetails();
        randomNameGenerator random = new randomNameGenerator();
        GameStore simulation = new GameStore(games, employees, baker, "Eager", random);
        for(int i = 0; i < 30; i++){
            simulation.run_day();
            System.out.println("\n");
        }
        simulation.printResults();
        }
}
