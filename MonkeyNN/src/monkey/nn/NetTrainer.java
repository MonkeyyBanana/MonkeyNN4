package monkey.nn;

import java.util.*;

public class NetTrainer {
    NeuralNetwork nn;
    float[][] TRAINING_DATA;
    float[][] TRAINING_GOAL;
    boolean STATUS_DISP = false;
    int MILI_ITER_DELAY = 0;

    public NetTrainer(NeuralNetwork nn, float[][] trainingData, float[][] trainingGoal) {
        this.nn = nn;
        TRAINING_DATA = trainingData;
        TRAINING_GOAL = trainingGoal;
    }

    public void trainBackprop(int iterations) throws InterruptedException {
        long startingMilli = (new Date()).getTime();
        
        for (int i = 0; i < iterations; i++) {
            if (STATUS_DISP)
                System.out.println("Iteration = " + i);
            for (int j = 0; j < TRAINING_GOAL.length; j++) {
                float[] curr = nn.feedForward(TRAINING_DATA[j]);
                
                nn.backProp(TRAINING_GOAL[j]);
                
                /*for (int k = 0; k < nn.layers.length; k++) {
                    float[] print = nn.layers[k].outputs;
                    for (int p = 0; p < print.length; p++)
                        System.out.print(print[p] + " ");
                    System.out.println();
                }
                System.out.println("----");*/
                
                if (STATUS_DISP) {
                    System.out.print("Result Of Forward = ");
                    printFArray(curr);
                    System.out.println();
                    System.out.println("Updated Neuron Connections");
                    for (int k = 1; k <= nn.connections.length; k++) {
                        String[] det = layerDetails(nn.connections[k - 1]);
                        for (int p = 0; p < det.length; p++) {
                            System.out.println("Layer " + k + det[p]);
                        }
                        System.out.println();
                    }
                }
            }
            Thread.sleep(MILI_ITER_DELAY);
        }
        
        long timeSpent = (new Date()).getTime() - startingMilli;
        System.out.println("Time Elapsed: " + (timeSpent / 1000.0) + " Seconds, Iterated " + iterations + " Times");
    }

    public void trainMutation(int iterations, float fitness) {

    }
    
    private String[] layerDetails(NetConnection nl) {
        String[] out = new String[((StandardConnection)nl).weights.length];
        
        for (int i = 0; i < out.length; i++) {
            out[i] = "(Position " + i + ")\nWeights: ";
            for (int j = 0; j < ((StandardConnection)nl).weights[i].length; j++) {
                out[i] += ((StandardConnection)nl).weights[i][j] + " ";
            }
        }
        
        return out;
    }
    
    private void printFArray(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
}
