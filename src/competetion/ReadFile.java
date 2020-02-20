package competetion;

import java.io.BufferedReader;
import java.io.FileReader;
import javafx.util.Pair;


public class ReadFile {
  public static DataPackage ReadFile(String args) {
    try (BufferedReader br = new BufferedReader(new FileReader(args))) {
      String line;

      line = br.readLine();
      String[] line1 = line.split(" ");

      line = br.readLine();
      String[] line2 = line.split(" ");
      int[] valueTable = new int[line2.length];
      for (int i = 0; i < line2.length; i++) {
        valueTable[i] = Integer.parseInt(line2[i]);
      }

      DataPackage dp = new DataPackage(Integer.parseInt(line1[0]),
          Integer.parseInt(line1[1]),
          Integer.parseInt(line1[2]),
          valueTable);

      boolean readingMeta = true;
      LibData ld = new LibData();
      while ((line = br.readLine()) != null) {
        String[] lineElements = line.split(" ");
        if (readingMeta) {
          if (lineElements.length != 3) {
            break;
          }
          ld.setNumBooks(Integer.parseInt(lineElements[0]));
          ld.setSignupCost(Integer.parseInt(lineElements[1]));
          ld.setOutputVol(Integer.parseInt(lineElements[2]));
          readingMeta = false;
        } else {
          for (String element : lineElements) {
            int bookNumber = Integer.parseInt(element);
            Pair<Integer,Integer> pair = new Pair<>(bookNumber, valueTable[bookNumber]);
            ld.addBookPair(pair);
          }
          dp.addLibrary(ld);
          ld = new LibData();
          readingMeta = true;
        }
      }
      return dp;
    }catch (Exception exception) {
      exception.printStackTrace();
    }
    return null;
  }

//  public static void main(String[] args) {
//    DataPackage dp = ReadFile.ReadFile(args[0]);
//    System.out.println(dp.getTotalNumBooks());
//    System.out.println(dp.getTotalNumLib());
//    System.out.println(dp.getTotalDaysAvail());
//    int[] referemceTable = dp.getScoreReference();
//    for (int a : referemceTable) {
//      System.out.println(a);
//    }
//    LibData lib2 = dp.getLibraries().get(0);
//    System.out.println(lib2.getNumBooks());
//    System.out.println(lib2.getSignupCost());
//    System.out.println(lib2.getOutputVol());
//    for (Pair pair : lib2.getBooks()) {
//      System.out.println(pair.getKey() + " : " + pair.getValue());
//    }
//
//    LibData lib = dp.getLibraries().get(1);
//    System.out.println(lib.getNumBooks());
//    System.out.println(lib.getSignupCost());
//    System.out.println(lib.getOutputVol());
//    for (Pair pair : lib.getBooks()) {
//      System.out.println(pair.getKey() + " : " + pair.getValue());
//    }
//
//    ;
//
//    System.out.println(lib2.getBooks().get(0).getValue());
//
//  }
}
