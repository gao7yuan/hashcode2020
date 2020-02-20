package competetion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javafx.util.Pair;

public class BookScanning {

  Set<Integer> scanned = new HashSet<>();
  int[] scores;
  int days;


  int scan(Library[] libraries) {
    return 0;
  }

  class Library {
    Pair<Integer, Integer>[] books;
    int time; // for sign up
    int maxNumBooks;
    double score;

    public Library(Pair<Integer, Integer>[] books, int time, int maxNumBooks) {
      this.books = books;
      this.time = time;
      this.maxNumBooks = maxNumBooks;
      sortBooks();
      this.score = 0;
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
        cnt--;
        if (cnt <= 0) {
          break;
        }
      }
      this.score = totalBookScore;
    }
  }

}
