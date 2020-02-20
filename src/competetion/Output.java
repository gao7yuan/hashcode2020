package competetion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Output {
  private List<Integer> libraries;
  private Map<Integer, List<Integer>> map;

  public Output(List<Integer> libraries, Map<Integer, List<Integer>> map) {
    this.libraries = libraries;
    this.map = map;
  }

  public void writeSolution() throws IOException {
    FileWriter writer;
    writer = new FileWriter("Solution.txt");
    // write the first line
    writer.write(this.libraries.size() + "\n");

    for (int i = 0; i < this.libraries.size(); i ++) {
      StringBuilder sb = new StringBuilder();
      // append library id and total number of books
      int libraryId = libraries.get(i);
      List<Integer> books = this.map.get(libraryId);
      // append each books in the library
      List<Integer> bookId = this.map.get(libraries.get(i));
      sb.append(libraryId).append(" ").append(books.size()).append("\n");
      for (int j = 0; j < bookId.size() - 1; j ++) {
        sb.append(bookId.get(j)).append(" ");
      }
      sb.append(bookId.get(bookId.size() - 1)).append("\n");
      writer.write(sb.toString());
    }
    writer.close();

  }
}
