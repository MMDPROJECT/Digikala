package Categories.Books;

import Categories.Product;

public abstract class Books extends Product{
    private int ISBN;
    private int pageNumbers;
    private String author;
    private String language;

    //Constructor
    public Books(int ISBN, int pageNumbers, String author, String language, String name, String color, double price, int quantity) {
        super(name, price, color, quantity);
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
