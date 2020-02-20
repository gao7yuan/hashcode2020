package competetion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.util.Pair;

public class BookScanning {

  Set<Integer> scanned = new HashSet<>();
  int[] scores; // score of each book. size = # of books
  int days; // total days allowed
  List<Integer> pickedLibraries = new ArrayList<>();
  Map<Integer, List<Integer>> bookForLibrary = new HashMap<>();

  int totalScan(Library[] libraries) {
    int totalScore = 0;
    int currentScore = -1;
    while (scanned.size() < scores.length && this.days > 0 && currentScore != 0) {
      currentScore = this.scan(libraries);
      totalScore += currentScore;
    }
    return totalScore;
  }

  /**
   * Pick what library to scan for this iteration and return the score gained for this iteration.
   * @param libraries
   * @return
   */
  int scan(Library[] libraries) {
    for (Library library : libraries) {
      if (library.finished) {
        continue;
      }
      library.setScore(this.days - library.time);
    }
    Library max = null;
    int startIndex = 0;
    while (startIndex < libraries.length) {
      if (!libraries[startIndex].finished) {
        max = libraries[startIndex];
        break;
      }
      startIndex++;
    }
    if (max == null) {
      return 0;
    }

    int pickedIndex = startIndex;

    for (int i = startIndex; i < libraries.length; i++) {
      if (libraries[i].finished) {
        continue;
      }
      if (libraries[i].score > max.score) {
        max = libraries[i];
        pickedIndex = i;
      }
    }

    // picked library
    // add lib to lib list
    this.pickedLibraries.add(pickedIndex);
    this.bookForLibrary.put(pickedIndex, new ArrayList<>(max.pickedBooks));
    this.days -= max.time;
    max.finished = true;

    return max.score;
  }

  class Library {
    Pair<Integer, Integer>[] books;
    int time; // for sign up
    int maxNumBooks;
    int score;
    boolean finished;
    List<Integer> pickedBooks = new ArrayList<>();

    public Library(Pair<Integer, Integer>[] books, int time, int maxNumBooks) {
      this.books = books;
      this.time = time;
      this.maxNumBooks = maxNumBooks;
      sortBooks();
      this.score = 0;
      this.finished = false;
    }

    public void sortBooks() {
      Arrays.sort(books, (book1, book2) -> book2.getValue() - book1.getValue());
    }


    /**
     * Considering total scores of books we can scan, plus the cost of signing up.
     */
    public void setScore(int daysRemaining) {
      if (daysRemaining <= 0) {
        this.score = 0;
        this.finished = true;
        return;
      }
      int totalBookScore = 0;
      int cnt = this.maxNumBooks * daysRemaining; // number of books we can scan per day
      int totalBooks = this.books.length;
      for (int i = 0; i < totalBooks; i++) {
        if (scanned.contains(this.books[i].getKey())) {
          continue;
        }
        totalBookScore += this.books[i].getValue();
        this.pickedBooks.add(this.books[i].getKey());
        cnt--;
        if (cnt <= 0) {
          break;
        }
      }
      this.score = totalBookScore;
    }
  }
  

}
