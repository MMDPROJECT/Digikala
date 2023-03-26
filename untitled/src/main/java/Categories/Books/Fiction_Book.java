package Categories.Books;

import Accounts.Seller;

import java.util.ArrayList;

public class Fiction_Book extends Books {
    private final ArrayList<String> characters;
    private final String tone;

    //Constructor


    public Fiction_Book(String name, String color, int quantity, double price, Seller seller, int ISBN, int pageNumbers, String author, String language, ArrayList<String> characters, String tone) {
        super(name, color, quantity, price, seller, ISBN, pageNumbers, author, language);
        this.characters = characters;
        this.tone = tone;
    }

    //Getter and Setters
    public ArrayList<String> getCharacters() {
        return characters;
    }

    public String getTone() {
        return tone;
    }

    //Fiction-Book - Related Functions
    public void showCharacters() {
        if (this.characters.size() == 0) {
            System.out.println("This book doesn't have any special character!\n");
        } else {
            System.out.println("List of characters:\n");
            for (String character : this.characters) {
                System.out.println("* " + character);
            }
        }
    }

    public void addCharacter(String character) {
        this.characters.add(character);
    }

    //Overrides

    @Override
    public String toString() {
        return "Fiction_Book{" +
                "characters=" + characters +
                ", tone=" + tone +
                "} " + super.toString();
    }
}
