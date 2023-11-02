package Database;

/**
 * initSQL class used to store sql statements for initialising the database
 *
 * @author Michael
 */
public class initSQL {

    //SQL statements used to create sql tables
    static final String CREATE_PLAYER_TABLE = "CREATE TABLE PLAYER ("
            + "playerID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
            + "username VARCHAR(255) UNIQUE, "
            + "password VARCHAR(255) NOT NULL, "
            + "highestEarnings DOUBLE NOT NULL"
            + ")";

    static final String CREATE_GAME_TABLE = "CREATE TABLE GAME ("
            + "gameID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
            + "currentRound INT NOT NULL, "
            + "currentEarnings DOUBLE NOT NULL, "
            + "numChoices INT NOT NULL, "
            + "playerID INT, "
            + "FOREIGN KEY (playerID) REFERENCES PLAYER(playerID) ON DELETE CASCADE"
            + ")";
        
    static final String CREATE_ITEM_TABLE = "CREATE TABLE ITEM ("
            + "itemID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,"
            + "itemName VARCHAR (255) NOT NULL, "
            + "itemValue DOUBLE NOT NULL, "
            + "itemDesc VARCHAR (255) NOT NULL "
            + ")";
        
    static final String INSERT_ITEMS = "INSERT INTO ITEM (itemName, itemValue, itemDesc) VALUES "
            + "('Pen', 5.00, 'Mightier than the sword'), "
            + "('Ball', 10.00, 'Have you ever played rugby?'), "
            + "('Movie ticket', 20.00, 'It''s morbin time'), "
            + "('Perfume', 30.00, 'Scent-sational'), "
            + "('Scarf', 40.00, 'Its cold outside'), "
            + "('Hat', 50.00, 'No cap'), "
            + "('Fitness tracker', 75.00, 'Shut up! Count your calories!'), "
            + "('White Tee 8', 100.00, 'Treat me like white tees'), "
            + "('Evisu Jeans', 200.00, 'No way it''s new jeans'), "
            + "('Sneakers', 300.00, 'What shoes he got on'), "
            + "('Coffee Maker', 400.00, 'Tell em bring out the cappuccino'), "
            + "('Headphones', 500.00, 'Noice cancelling'), "
            + "('PS5', 750.00, 'Comes with ps5 exclusives'), "
            + "('Iphone', 1000.00, 'Pear'), "
            + "('Gaming PC', 5000.00, 'The master race'), "
            + "('Holiday to Europe', 10000.00, 'The wonders of the world'), "
            + "('Jewelry', 25000.00, 'Diamonds are formed under pressure'), "
            + "('Painting', 50000.00, 'Now this is art'), "
            + "('World tour', 75000.00, 'Around the world in 365 days'), "
            + "('Smart home system', 100000.00, 'Control your home with a touch'), "
            + "('Home theatre', 200000.00, 'Movie time!'), "
            + "('Yacht', 300000.00, 'Tell em bring the yacht out'), "
            + "('Lamborghini Huracan', 400000.00, 'Hop out the Lambo'), "
            + "('Dinner with Jay Z', 500000.00, 'Description of Item 24'), "
            + "('Private jet', 750000.00, 'Where do you even park this?'), "
            + "('Mansion', 1000000.00, 'The grand prize')";
}