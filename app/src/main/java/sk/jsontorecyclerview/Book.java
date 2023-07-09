package sk.jsontorecyclerview;

import android.content.Context;


public class Book {
    private int bookID;
    private String bookISBN;
    private String bookName;
    private String bookAuthor;
    private String bookPublisher;
    private boolean bookInLibrary;
    private boolean bookIsRead;
    private String bookImageURL;

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int _bookID) {
        bookID = _bookID;
    }
    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String _bookISBN) {
        if (!(_bookISBN == null) && !(_bookISBN.trim().equals(""))) bookISBN = _bookISBN;
    }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String _bookName) {
        if (!(_bookName == null) && !(_bookName.trim().equals(""))) bookName = _bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String _bookAuthor) {
        if (!(_bookAuthor == null) && !(_bookAuthor.trim().equals(""))) bookAuthor = _bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String _bookPublisher) {
        if (!(_bookPublisher == null) && !(_bookPublisher.trim().equals("")))
            bookPublisher = _bookPublisher;
    }

    public boolean getBookInLibrary() {
        return bookInLibrary;
    }

    public void setBookInLibrary(boolean _bookInLibrary) {
        bookInLibrary = _bookInLibrary;
    }

    public boolean getBookIsRead() {
        return bookIsRead;
    }

    public void setBookIsRead(boolean _bookRead) {
        bookIsRead = _bookRead;
    }

    public String getBookImageURL() {
        return bookImageURL;
    }

    public void setBookImageURL(String bookImageURL) {
        this.bookImageURL = bookImageURL;
    }

    Context context;

    public Book(Context context) {
        this.context = context;
    }

}