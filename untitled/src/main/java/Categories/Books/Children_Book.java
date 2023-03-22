package Categories.Books;

public class Children_Book extends Books{
    private String readingLevel;
    private String theme;

    //Constructor
    public Children_Book(int ISBN, int pageNumbers, String author, String language, String name, String color, double price, int quantity, String readingLevel, String theme) {
        super(ISBN, pageNumbers, author, language, name, color, price, quantity);
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
        return "Children_Book{" + "readingLevel='" + readingLevel + '\'' + ", theme='" + theme + '\'' + + "title=" + getName() + ", price=" + this.getPrice() + ", color=" + this.getColor() + ", quantity available=" + this.getQuantity() + ", page numbers=" + this.getPageNumbers() + ", author=" + this.getAuthor() + ", language=" + this.getLanguage() + ", reading-level=" + this.getReadingLevel() + ", theme=" + this.getTheme() +'}';
    }
}
