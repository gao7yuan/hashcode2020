package competetion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataPackage {
  int totalNumBooks;
  int totalNumLib;
  int totalDaysAvail;
  int[] scoreReference;
  List<LibData> libraries;

  public DataPackage(int totalNumBooks, int totalNumLib, int totalDaysAvail, int[] scoreReference) {
    this.totalNumBooks = totalNumBooks;
    this.totalNumLib = totalNumLib;
    this.totalDaysAvail = totalDaysAvail;
    this.scoreReference = scoreReference;
    this.libraries = new ArrayList<LibData>();
  }

  public void addLibrary(LibData libData) {
    this.libraries.add(libData);
  }

  public int getTotalNumBooks() {
    return totalNumBooks;
  }

  public int getTotalNumLib() {
    return totalNumLib;
  }

  public int getTotalDaysAvail() {
    return totalDaysAvail;
  }

  public int[] getScoreReference() {
    return scoreReference;
  }

  public List<LibData> getLibraries() {
    return libraries;
  }

}
