package Categories.Books;

import Accounts.Seller;
import Categories.Product;

import java.util.ArrayList;

public abstract class Books extends Product {
    private final int ISBN;
    private final int pageNumbers;
    private final String author;
    private final String language;

    //Constructor


    public Books(String name, String color, int quantity, double price, Seller seller, ArrayList<String> comments, int ISBN, int pageNumbers, String author, String language) {
        super(name, color, quantity, price, seller, comments);
        this.ISBN = ISBN;
        this.pageNumbers = pageNumbers;
        this.author = author;
        this.language = language;
    }

    //Getter and Setters
    public int getISBN() {
        return ISBN;
    }

    public int getPageNumbers() {
        return pageNumbers;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    //Override

    @Override
    public String toString() {
        return "Book{" +
                "ISBN=" + ISBN +
                ", pageNumbers=" + pageNumbers +
                ", author=" + author +
                ", language=" + language +
                "} " + super.toString();
    }
}
