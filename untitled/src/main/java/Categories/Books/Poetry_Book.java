package Categories.Books;

public class Poetry_Book extends Books{
    private String poeticForm;
    private int verseNumber;

    //Constructor
    public Poetry_Book(int ISBN, int pageNumbers, String author, String language, String name, String color, double price, int quantity, String poeticForm, int versesNumber) {
        super(ISBN, pageNumbers, author, language, name, color, price, quantity);
        this.poeticForm = poeticForm;
        this.verseNumber = versesNumber;
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
                "poeticForm='" + poeticForm + '\'' +
                ", verseNumber=" + verseNumber +
                "} " + super.toString();
    }
}
