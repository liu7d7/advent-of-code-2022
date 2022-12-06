import java.util.Arrays;
import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {
    final Scanner sc = new Scanner(System.in);
    String line = sc.nextLine();
    boolean[] seen = new boolean[26];
    Arrays.fill(seen, false);
    int ans = 0;
    for (int i = 0; i < line.length() - 14; i++) {
      boolean found = true;
      for (int j = i; j < i + 14; j++) {
        System.out.print(line.charAt(j));
        System.out.print("(" + ((int)line.charAt(j) - (int)'a') + ")");
        if (seen[(int)line.charAt(j) - (int)'a']) {
          Arrays.fill(seen, false);
          found = false;
          break;
        }
        seen[(int)line.charAt(j) - (int)'a'] = true;
      }
      System.out.println();
      if (found)
      {
        ans = i + 14;
        break;
      }
    }
    System.out.println(ans);
  }
}
