package Categories.Books;

import Accounts.Seller;

public class Children_Book extends Books {
    private final String readingLevel;
    private final String theme;

    //Constructor


    public Children_Book(String name, String color, int quantity, double price, Seller seller, String ISBN, int pageNumbers, String author, String language, String readingLevel, String theme) {
        super(name, color, quantity, price, seller, ISBN, pageNumbers, author, language);
        this.readingLevel = readingLevel;
        this.theme = theme;
    }

    //Getters and Setters
    public String getReadingLevel() {
        return readingLevel;
    }

    public String getTheme() {
        return theme;
    }

    //Override

    @Override
    public String toString() {
        return "Children_Book{" +
                "readingLevel=" + readingLevel +
                ", theme=" + theme +
                "} " + super.toString();
    }
}
