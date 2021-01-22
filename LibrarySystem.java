import java.lang.Exception;

public class LibrarySystem {
   public class LibraryFullException extends Exception {
      LibraryFullException() {
      };

      LibraryFullException(String msg) {
         super(msg);
      }
   }

   public class NoBookException extends Exception {
      NoBookException() {
      };

      NoBookException(String msg) {
         super(msg);
      }
   }

   public class BorrowBookException extends Exception {
      BorrowBookException() {
      };

      BorrowBookException(String msg) {
         super(msg);
      }
   }

   public class Book {
      String bookname;
      boolean borrowed;

      public Book(String bName) {
         bookname = bName;
         borrowed = false;
      }
      public boolean isBorrowed() {
         return borrowed;
      }

      public String getname() {
         return bookname;
      }

   }

   public class Library {
      Book[] bookList=new Book[20];
      int capacity;
      int size;
      
      public Library(int N) {
         capacity=N; size=0;
      }
      
      public void returnBook(String name) throws NoBookException, BorrowBookException {
         int index = findbook(name);
         if(bookList[index].borrowed==true) {
            bookList[index].borrowed=false;
         }
         else {
            throw new BorrowBookException("BorrowBookException");
         }
      }

      public void borrowBook(String name) throws NoBookException, BorrowBookException {
         int index = findbook(name);
         if(bookList[index].borrowed==false) {
            bookList[index].borrowed=true;
         }
         else {
            throw new BorrowBookException("BorrowBookException");
         }
      }

      public int findbook(String name) throws NoBookException{
         for(int i=0;i<size;i++) {
            if(bookList[i].bookname==name) {
               System.out.println("Found book");
               return i;
            }
         }
         throw new NoBookException("NoBookException");
      }

      public void addbook(LibrarySystem.Book book) throws LibraryFullException{
         if(size==capacity) {
            throw new LibraryFullException("LibraryFullException");
         }
         else {
            bookList[size] = book;
            size++;
         }
      }

   }
   
   public static void main(String[] args) {
      LibrarySystem outer = new LibrarySystem();
      Library lib = outer.new Library(1);
      try {
         lib.addbook(outer.new Book("K&R"));
      } catch (LibraryFullException e) {
         System.out.println("Library is Full");
      }
      
      try {
         lib.addbook(outer.new Book("2046"));
      } catch (LibraryFullException e) {
         System.out.println("Library is Full");
      }
      
      try {
         lib.findbook("Window is garbage");
      } catch (NoBookException e) {
         System.out.println("No book with that name");
      } 
      try {
         lib.borrowBook("Window is garbage");
      } catch (NoBookException e) {
         System.out.println("No book with that name");
      } catch (BorrowBookException e) {
         System.out.println("Book is already borrowed");
      }
      
      try {
         lib.borrowBook("K&R");
      } catch (NoBookException e) {
         System.out.println("No book with that name");
      } catch (BorrowBookException e) {
         System.out.println("Book is already borrowed");
      }
      try {
         lib.returnBook("2045");
      } catch (NoBookException e) {
         System.out.println("No book with that name");
      } catch (BorrowBookException e) {
         System.out.println("Book is not borrowed");
      }
   
   }
}
