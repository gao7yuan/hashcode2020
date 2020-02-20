package competetion;

import competetion.BookScanning.Library;
import java.awt.print.Book;
import java.io.IOException;
import java.util.List;
import javafx.util.Pair;

public class Solution {

  public static void main(String[] args) throws IOException {

//    BookScanning.Library lib = new BookScanning.Library(xxxx);

    DataPackage dataPackage = ReadFile.ReadFile("a_example.txt");

//    Library lib = new Library();
    List<LibData> libDataList = dataPackage.getLibraries();
    Library[] libraries = new Library[libDataList.size()];
    for (int i = 0; i < libraries.length; i++) {
      Pair<Integer, Integer>[] books = new Pair[libDataList.get(i).getBooks().size()];
      for (int j = 0; j < books.length; j++) {
        books[j] = libDataList.get(i).getBooks().get(j);
      }
      libraries[i] = new Library(books, libDataList.get(i).getSignupCost(),
          libDataList.get(i).getOutputVol());
    }
    BookScanning bookScanning = new BookScanning(dataPackage.totalNumBooks,
        dataPackage.totalDaysAvail, libraries);
    bookScanning.totalScan();
    Output output = new Output(bookScanning.pickedLibraries, bookScanning.bookForLibrary);
    output.writeSolution();
  }

}
