package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pizza {

  /**
   * Given the maximum slices allowed and the number of slices of each type of pizza,
   * decide which types of pizza to pick. At most one piece of each type is allowed.
   * @param M - maximum slices of pizza allowed.
   * @param N - number of types of pizza.
   * @param slices - number of slices of each type of pizza.
   * @return the indices indicating types of pizza to pick
   */
  static int pickTypes(int M, int N, int[] slices) {
    // record what type to pick
    List<Integer> picked = new ArrayList<>();
    // dp[i][j] means the last pizza type picked is at index i - 1, and before this one,
    // the pizza type picked is at index j - 1
    int[][] dp = new int[N + 1][N + 1];
    // base cases
    for (int j = 0; j <= N; j++) {
      dp[0][j] = 0; // if picked nothing, slices is zero
    }
    for (int i = 1; i <= N; i++) {
      // pick i - 1 only
      if (slices[i - 1] <= M) {
        dp[i][0] = slices[i - 1];
      } else {
        dp[i][0] = 0;
      }
    }
    for (int i = 1; i <= N; i++) {
      System.out.println("i = " + i);
      for (int j = 1; j < i; j++) {
        for (int k = 0; k < j; k++) {
          if (dp[j][k] + slices[i - 1] <= M) {
            System.out.println("comparing dp[" + j + "][" + k + "] "+ dp[i][j] + " and dp[" + j + "][" + k + "] " + dp[j][k] + " + " + slices[i - 1]);
            dp[i][j] = Math.max(dp[i][j], dp[j][k] + slices[i - 1]);
            System.out.println("dp[" + i + "][" + j + "] updated to " + dp[i][j]);
          }
        }
        System.out.println("dp[" + i + "][" + j + "] = " + dp[i][j]);
      }
    }

    for (int i = 0; i <= N; i++) {
      for (int j = 0; j <= N; j++) {
        System.out.print(dp[i][j] + " ");
      }
      System.out.print("\n");
    }

    // to find max in dp, which represents maximum slices of pizza we can pick
    int r = 0, c = 0;
    for (int i = 0; i <= N; i++) {
      for (int j = 0; j < i; j++) {
        if (dp[i][j] > dp[r][c]) {
          r = i;
          c = j;
        }
      }
    }

//    picked.add(r);
//    System.out.println("adding " + r);
//
//    int prevSlices = dp[r][c] - slices[r - 1];
//    while (prevSlices > 0) {
//      picked.add(c);
//      System.out.println("adding " + c);
//      prevSlices = dp[r][c] - slices[r - 1];
//      //search on row = c for dp = prevSlices
//      for (int i = 1; i < c; i++) {
//        if (dp[c][i] == prevSlices) {
//          r = c;
//          c = i;
//          break;
//        }
//      }
//    }
//    Collections.reverse(picked);
    return dp[r][c];
  }

  public static void main(String[] args) {
//    List<Integer> res1 = pickTypes(10, 4, new int[]{1, 2, 3, 5});
//    System.out.println("Types picked for res1:");
//    for (int type : res1) {
//      System.out.println(type);
//    }
    System.out.println(pickTypes(20, 4, new int[]{1, 2, 3, 5}));
  }

}
