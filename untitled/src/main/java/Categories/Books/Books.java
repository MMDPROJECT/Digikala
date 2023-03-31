package Categories.Books;

import Accounts.Seller;
import Categories.Product;

public abstract class Books extends Product {
    private final String ISBN;
    private final int pageNumbers;
    private final String author;
    private final String language;

    //Constructor

    public Books(String name, String color, int quantity, double price, Seller seller, String ISBN, int pageNumbers, String author, String language) {
        super(name, color, quantity, price, seller);
        this.ISBN = ISBN;
        this.pageNumbers = pageNumbers;
        this.author = author;
        this.language = language;
    }

    //Getter and Setters
    public String getISBN() {
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
