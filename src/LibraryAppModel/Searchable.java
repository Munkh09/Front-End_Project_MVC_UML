package LibraryAppModel;

public interface Searchable {
    public boolean searchByTitle(String title);
    public boolean searchByAuthor(String author);
    public boolean searchByISBN(int ISBN);
}
