package monkey.nn;

import java.util.Scanner;

public class Utils {
	public static float deriv(float x) {
		return x * (1 - x);
	}
	
	public static String liquidArray(float[] arr) {
        String str = "a " + arr.length + " ";
        for (int i = 0; i < arr.length; i++)
            str += arr[i] + " ";
        return str;
    }
    
    public static String liquidMatrix(float[][] mat) {
        String str = "m " + mat.length + " " + mat[0].length + " ";
        for (int i = 0; i < mat.length; i++) 
            for (int j = 0; j < mat[0].length; j++)
                str += mat[i][j] + " ";
        return str;
    }
    
    public static float[] strToArr(String str) {
        Scanner scan = new Scanner(str);
        scan.next();
        float[] arr = new float[scan.nextInt()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = scan.nextFloat();
        scan.close();
        return arr;
    }
    
    public static float[][] strToMat(String str) {
        Scanner scan = new Scanner(str);
        scan.next();
        float[][] mat = new float[scan.nextInt()][scan.nextInt()];
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[i].length; j++)
                mat[i][j] = scan.nextFloat();
        scan.close();
        return mat;
    }
}
