import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class p4p2Test {
    public Register register = new Register();
    public Cashier cashiers[] = { new Cashier("Ernie", 5, new ErnieStrat()), new Cashier("Burt", 10, new BurtStrat()), 
                                new Cashier("Bart", 2, new BartStrat())};
    public Announcer a = lazyAnnouncer.getInstance();
    public Game games[] = new Game[12];
    public Baker b = new Baker("Baker");
    public randomNameGenerator random = new randomNameGenerator(); 


    @Test
    public void testRegister(){
        
        register.addCash(50.00);
        assertEquals(50.00, register.getCash(), 0.001);
        System.out.println("testRegister Success \n"); //does't get here unless it passes the test
    }


    @Test
    public void testCount(){
        cashiers[0].addListener(a);
        cashiers[0].Count(register); //should've noticed it was low (at zero since the add cash 50 was in previous test), added to 1000
        assertEquals(1000.00, register.getCash(), 0.001);
        System.out.println("testCount Success \n"); //does't get here unless it passes the test
    }
    @Test
    public void testErnieStack(){
        cashiers[0].addListener(a);
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
        cashiers[0].Stack(games, cashiers[0]); //this is Ernie, the games array is affected globally
        Game correct_stack[] = {Pokemon,Netrunner,Catan,Magic,Candyland,Connect4,Mousetrap,Monopoly,Clue,Life,Risk, Gloomhaven};
        assertArrayEquals(correct_stack, games);
        System.out.println("testErnieStack Success \n");
    }

    @Test
    public void testBurtStack(){
        cashiers[1].addListener(a);
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
        cashiers[1].Stack(games, cashiers[1]); //this is burt, the games array is affected globally
        Game correct_stack[] = {Monopoly,Life,Mousetrap,Candyland,Risk,Gloomhaven,Clue,Connect4,Magic,Catan,Netrunner, Pokemon};
        assertArrayEquals(correct_stack, games);
        System.out.println("testBurtStack Success \n");
    }

    @Test
    public void testBartStack(){
        cashiers[2].addListener(a);
        //familyGame Monopoly = new familyGame("Monopoly", );
        monopolyGame Monopoly = new monopolyGame(new MonopolyGameDecorator());
        games[0] = Monopoly; 
        Monopoly.setInventory(7);
        clueGame Clue = new clueGame(new gameDecorator());
        games[1] = Clue; 
        Clue.setInventory(7);
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
        Pokemon.setInventory(7);
        netrunnerGame Netrunner = new netrunnerGame(new CardGameDecorator());
        games[8] = Netrunner; 
        catanGame Catan = new catanGame(new gameDecorator());
        games[9] = Catan; 
        riskGame Risk = new riskGame(new gameDecorator());
        games[10] = Risk; 
        gloomhavenGame Gloomhaven = new gloomhavenGame(new GloomhavenDecorator()); 
        games[11] = Gloomhaven; 
        cashiers[2].Stack(games, cashiers[2]); //this is burt, the games array is affected globally
        Game correct_stack[] = {Monopoly,Clue,Pokemon,Mousetrap,Candyland,Life,Risk,Gloomhaven,Connect4,Magic,Catan,Netrunner};
        assertArrayEquals(correct_stack, games);
        System.out.println("testBartStack Success \n");
    }


    @Test
    public void testOrder(){
        cashiers[2].addListener(a);
        //familyGame Monopoly = new familyGame("Monopoly", );
        monopolyGame Monopoly = new monopolyGame(new MonopolyGameDecorator());
        games[0] = Monopoly; 
        Monopoly.setInventory(0); //24.00 * 3 = 72
        clueGame Clue = new clueGame(new gameDecorator());
        games[1] = Clue; 
        Clue.setInventory(0); //11.99 * 3 = 35.97
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
        Pokemon.setInventory(0); //8.49 * 3 = 25.47 // 133.44 altogether to be taken from the cash register
        netrunnerGame Netrunner = new netrunnerGame(new CardGameDecorator());
        games[8] = Netrunner; 
        catanGame Catan = new catanGame(new gameDecorator());
        games[9] = Catan; 
        riskGame Risk = new riskGame(new gameDecorator());
        games[10] = Risk; 
        gloomhavenGame Gloomhaven = new gloomhavenGame(new GloomhavenDecorator()); 
        games[11] = Gloomhaven; 
        register.addCash(500); //500 - 133.44/2 = 433.28 should be left in the cash registere
        cashiers[2].Order(games, register, b, 0, 0); //this is burt, the games array is affected globally
        //Game correct_stack[] = {Monopoly,Clue,Pokemon,Mousetrap,Candyland,Life,Risk,Gloomhaven,Connect4,Magic,Catan,Netrunner};
        assertEquals(433.28, register.getCash(),.001);
        System.out.println("testOrder Success \n");
    }

    @Test
    public void testArrive(){
        cashiers[2].addListener(a);
        //familyGame Monopoly = new familyGame("Monopoly", );
        monopolyGame Monopoly = new monopolyGame(new MonopolyGameDecorator());
        games[0] = Monopoly; 
        Monopoly.setInventory(0); //24.00 * 3 = 72
        clueGame Clue = new clueGame(new gameDecorator());
        games[1] = Clue; 
        Clue.setInventory(0); //11.99 * 3 = 35.97
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
        Pokemon.setInventory(0); //8.49 * 3 = 25.47 // 133.44 altogether to be taken from the cash register
        netrunnerGame Netrunner = new netrunnerGame(new CardGameDecorator());
        games[8] = Netrunner; 
        catanGame Catan = new catanGame(new gameDecorator());
        games[9] = Catan; 
        riskGame Risk = new riskGame(new gameDecorator());
        games[10] = Risk; 
        gloomhavenGame Gloomhaven = new gloomhavenGame(new GloomhavenDecorator()); 
        games[11] = Gloomhaven; 
        register.addCash(500); //500 - 133.44/2 = 433.28 should be left in the cash registere
        Game order[] = cashiers[2].Order(games, register, b, 0, 0); //this is burt, the games array is affected globally
        cashiers[2].Arrive(games, order, 1); //should've addded 3 of each Pokemon Clue and Monopoly, so he should stack those first
        cashiers[2].Stack(games, cashiers[2]); //this is burt, the games array is affected globally
        Game correct_stack[] = {Monopoly,Clue,Pokemon,Mousetrap,Candyland,Life,Risk,Gloomhaven,Connect4,Magic,Catan,Netrunner};
        assertArrayEquals(correct_stack, games);
        System.out.println("testArrive Success \n");
    }

    @Test
    public void testBakerAddPackage(){
        b.addPackage();
        b.addPackage();
        b.addPackage(); //initialized at 1, so should be 4
        assertEquals(4, b.getNumPackagees());
        System.out.println("testBakerAddPackage Success \n");
    }

    @Test
    public void testBakerSubtractPackage(){
        b.setPackages(4); //also testing set

        b.subtractPackage();
        b.subtractPackage(); //4-2 = 2
        assertEquals(2, b.getNumPackagees());
        System.out.println("testBakerSubtract Success \n");
    }

    @Test
    public void testBakeryDelivery(){
        b.addListener(a);
        b.Delivery(); //has 1 package and 0 cash, so should now have 12 cash
        assertEquals(12, b.getCash());
        System.out.println("testBakerDelivery Success \n");
    }

    @Test
    public void testGameCategory(){
        monopolyGame Monopoly = new monopolyGame(new MonopolyGameDecorator());
        assertEquals("Family", Monopoly.getCategory()); //important that this works because it also proves most of the super attributes work
        System.out.println("testGameCategory Success \n");
    }

    @Test
    public void testInsuranceRestock(){
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
        Employee employees[] = new Employee[3];
        GameStore g = new GameStore(games, employees, b, "Eager", random);
        for(int i = 0; i < 12; i++){
            assertEquals(games[i].getName(), g.insuranceGameRestock()[i].getName());
        }
        System.out.println("testInsuranceRestock Success \n");
    }

    @Test
    public void testCustomerFactoryKid(){
        CustomerFactory cf = new CustomerFactory();
        KidCustomer kid = new KidCustomer(random.getName());
        assertEquals(kid.getClass(), cf.createCustomer("Kids", random).getClass());
        System.out.println("testCustomerFactoryKid Success \n");
    }
    @Test
    public void testCustomerFactoryBoard(){
        CustomerFactory cf = new CustomerFactory();
        BoardCustomer board = new BoardCustomer(random.getName());
        assertEquals(board.getClass(), cf.createCustomer("Board", random).getClass());
        System.out.println("testCustomerFacotryBoard Success \n");
    }

    @Test
    public void testCustomerBonus(){
        BoardCustomer board = new BoardCustomer(random.getName());
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

        board.assign_bonus(games); //should assign a bonus to only the games that are board games

        for(int i = 0; i < 12; i++){
            if(games[i].getCategory() == "Board"){ //meaning there should be a bonus
                boolean correct = (board.bonuses[i] > 0); //true if it's got a bonus there
                assertEquals(true, correct);
            }
            else{
                boolean correct = (board.bonuses[i] > 0); //true if it's got a bonus there
                assertEquals(false, correct);
            }
        }
        System.out.println("testCustomerBonus Success \n");
    }




        

    

}
