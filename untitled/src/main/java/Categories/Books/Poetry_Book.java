package Categories.Books;

public class Poetry_Book extends Books{
    private String poeticForm;
    private int versesNumber;

    //Constructor
    public Poetry_Book(int ISBN, int pageNumbers, String author, String language, String name, String color, double price, int quantity, String poeticForm, int versesNumber) {
        super(ISBN, pageNumbers, author, language, name, color, price, quantity);
        this.poeticForm = poeticForm;
        this.versesNumber = versesNumber;
    }

    //Getter and Setters
    public String getPoeticForm() {
        return poeticForm;
    }

    public int getVersesNumber() {
        return versesNumber;
    }

    //Overrides
    @Override
    public String toString() {
        return "Poetry_Book{" + "title=" + this.getName() + ", price=" + this.getPrice() + ", color=" + this.getColor() + ", quantity available=" + this.getQuantity() + ", page numbers=" + this.getPageNumbers() + ", author=" + this.getAuthor() + ", language=" + this.getLanguage() + ", poeticForm='" + poeticForm + ", versesNumber=" + versesNumber + '}';
    }
}
