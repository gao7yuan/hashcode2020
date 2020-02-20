package competetion;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;

public class LibData {
  int numBooks;
  int signupCost;
  int outputVol;
  List<Pair<Integer, Integer>> books;

  public LibData() {
    this.books = new ArrayList<>();
  }

  public void setNumBooks(int numBooks) {
    this.numBooks = numBooks;
  }

  public void setSignupCost(int signupCost) {
    this.signupCost = signupCost;
  }

  public void setOutputVol(int outputVol) {
    this.outputVol = outputVol;
  }

  public void addBookPair(Pair<Integer, Integer> book) {
    this.books.add(book);
  }

  public int getNumBooks() {
    return numBooks;
  }

  public int getSignupCost() {
    return signupCost;
  }

  public int getOutputVol() {
    return outputVol;
  }

  public List<Pair<Integer, Integer>> getBooks() {
    return books;
  }
}
