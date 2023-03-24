package Categories.Books;

import Accounts.Seller;

import java.util.ArrayList;
import java.util.UUID;

public class Poetry_Book extends Books{
    private String poeticForm;
    private int verseNumber;

    //Constructor


    public Poetry_Book(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, int ISBN, int pageNumbers, String author, String language, String poeticForm, int verseNumber) {
        super(name, color, quantity, price, seller, comments, ISBN, pageNumbers, author, language);
        this.poeticForm = poeticForm;
        this.verseNumber = verseNumber;
    }

    //Getter and Setters
    public String getPoeticForm() {
        return poeticForm;
    }

    public int getVersesNumber() {
        return verseNumber;
    }

    //Overrides

    @Override
    public String toString() {
        return "Poetry_Book{" +
                "poeticForm=" + poeticForm +
                ", verseNumber=" + verseNumber +
                "} " + super.toString();
    }
}
