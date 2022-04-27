package monkey.nn;

import java.io.File;
import java.io.FileNotFoundException;

public class Test {
	public static void main(String[] args) {
		float[][] goal = {{0, 0}, {0, 1}, {0, 1}, {1, 0}, {0, 0}, {0, 0}, {1, 0}, {0, 1}, {0, 1}, {1, 0}, {0, 1}};
        
        float[][] samp = {
        		/*
        		 * x
        		 * o
        		 * 
        		 * o x x
        		 * x o o
        		 * x o x
        		 */
        { 0, 1, 1, 1, 0, 0, 1, 0, 1, /**/ 1, 0, 0, 0, 1, 1, 0, 1, 0},
        { 0, 1, 1, 0, 1, 0, 0, 1, 0, /**/ 1, 0, 0, 0, 0, 1, 1, 0, 0},
        { 0, 1, 1, 0, 0, 1, 0, 0, 1, /**/ 1, 0, 0, 0, 1, 0, 1, 0, 0},
        { 0, 1, 0, 0, 0, 1, 0, 0, 1, /**/ 0, 0, 1, 0, 1, 0, 1, 0, 0},
        { 0, 1, 0, 0, 1, 1, 1, 0, 1, /**/ 1, 0, 1, 1, 0, 0, 0, 1, 0},
        { 1, 0, 1, 1, 0, 0, 0, 1, 1, /**/ 0, 1, 0, 0, 1, 1, 1, 0, 0},
        { 0, 0, 0, 1, 1, 0, 1, 0, 0, /**/ 0, 0, 1, 0, 0, 1, 0, 0, 1},
        { 1, 1, 1, 0, 0, 0, 0, 0, 0, /**/ 0, 0, 0, 0, 1, 1, 0, 0, 0},
        { 0, 0, 0, 1, 1, 1, 0, 0, 0, /**/ 0, 0, 0, 0, 0, 0, 1, 1, 0},
        { 0, 1, 0, 1, 0, 0, 0, 0, 1, /**/ 0, 0, 1, 0, 1, 0, 1, 0, 0},
        { 0, 0, 1, 0, 1, 0, 1, 0, 0, /**/ 1, 0, 0, 0, 0, 0, 0, 0, 1}
        };
        
        /*NeuralNetwork nn = new NeuralNetwork(new NetLayer[] {
        		new StandardLayer(18, NetActivator.Sigmoid, 0f, 0.5f), 
        		new StandardLayer(324, NetActivator.Sigmoid, 0f, 0.5f), 
        		new StandardLayer(162, NetActivator.Sigmoid, 0f, 0.5f), 
        		new StandardLayer(2, NetActivator.Sigmoid, 0f, 0.5f)});*/
		
        NeuralNetwork nn = null;
		try {
			nn = new NeuralNetwork(new File("C:\\Users\\gener\\Desktop\\Network Saves\\TTT\\TTT Validator\\0003.nstru"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        NetTrainer train = new NetTrainer(nn, samp, goal);
        try {
        	train.STATUS_DISP = false;
        	train.MILI_ITER_DELAY = 0;
        	train.trainBackprop(50000);
        	System.out.println("FINAL FORWARD");
            for (int i = 0; i < samp.length; i++) {
                printFArray(nn.feedForward(samp[i]));
            }
            nn.dumpToFile(new File("C:\\Users\\gener\\Desktop\\Network Saves\\TTT\\TTT Validator\\\\0003.nstru"));
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	private static void printFArray(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
