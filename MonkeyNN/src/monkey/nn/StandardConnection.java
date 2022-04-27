package monkey.nn;

import java.util.Scanner;

public class StandardConnection extends NetConnection{
	float WEIGHT_MIN;
    float WEIGHT_MAX;
	
	float[][] weights;
    float[][] weightsDelta;
	
	public StandardConnection(StandardLayer prevLayer, StandardLayer nextLayer) {
    	this.prevLayer = prevLayer;
    	this.nextLayer = nextLayer;
    	WEIGHT_MIN = 0;
    	WEIGHT_MAX = .5f;
    	weights = new float[nextLayer.neuronValue.length][prevLayer.neuronValue.length];
    	weightsDelta = new float[nextLayer.neuronValue.length][prevLayer.neuronValue.length];
    	initWeights();
    }
    
    public StandardConnection(String str) {
    	Scanner scan = new Scanner(str);
    	String desc = scan.nextLine();
    	scan.nextLine();
    	WEIGHT_MIN = scan.nextFloat();
    	WEIGHT_MAX = scan.nextFloat();
    	scan.nextLine();
    	weights = Utils.strToMat(scan.nextLine());
    	weightsDelta = Utils.strToMat(scan.nextLine());
    	scan.close();
    }
    
    private void initWeights() {
    	for (int i = 0; i < nextLayer.neuronValue.length; i++)
    		for (int j = 0; j < prevLayer.neuronValue.length; j++) 
    			weights[i][j] = NeuralNetwork.RANDOM.nextFloat() - 0.5f;
    }
    
    public void feedForward() {
    	for (int i = 0; i < nextLayer.neuronValue.length; i++) {
    		nextLayer.neuronValue[i] = 0;
    		
    		// Multiplying all connections between layers represented by weights (previous layer * next layer) output = sum(prev_layer * weight[i, j])
    		for (int j = 0; j < prevLayer.neuronValue.length; j++) 
    			nextLayer.neuronValue[i] += prevLayer.neuronValue[j] * weights[i][j];
    		
    		// Salting the data with bias
    		nextLayer.neuronValue[i] += ((StandardLayer)nextLayer).bias[i];
    		
    		// Rescaled data using activation function
    		nextLayer.neuronValue[i] = nextLayer.activator.activator(nextLayer.neuronValue[i]);
    		
    		nextLayer.runAtForward(i);
    	}
    }
    
    public void backpropEnd(float[] goalExpected) {
    	// Delta of error (value - expected)
    	for (int i = 0; i < nextLayer.neuronValue.length; i++) {
    		((StandardLayer) nextLayer).error[i] = nextLayer.neuronValue[i] - goalExpected[i];
    	}
    	
    	// error calculations
    	for (int i = 0; i < nextLayer.neuronValue.length; i++)
    		((StandardLayer) nextLayer).gamma[i] = ((StandardLayer) nextLayer).error[i] * Utils.deriv(nextLayer.neuronValue[i]);
    	
    	// gamma = overdue errors that carry on from previous layers
    	for (int i = 0; i < nextLayer.neuronValue.length; i++) {
    		for (int j = 0; j < prevLayer.neuronValue.length; j++)
    			weightsDelta[i][j] = ((StandardLayer) nextLayer).gamma[i] * prevLayer.neuronValue[j];
    		((StandardLayer) nextLayer).biasDelta[i] = ((StandardLayer) nextLayer).gamma[i] * ((StandardLayer) nextLayer).bias[i];
    		
    	}
    	/*     loss      gra      
    	 * per prev * cur = o ((a - t) * deri(a) * a)
    	 * 
    	 * for (int i = 0; i < curr.neuron.length; i++) {
    	 * 		neuron.error = loss.(act, exp) * neuron.act.prime(neuron)
    	 * 		for (int j = 0; j < prev.neuron.length; j++)
    	 * 			neuron.weights -= alpha * neuron.error * prev.neuron;
    	 * }
    	 */
    }
    
    public void backpropHidden(float[] nextGamma, float[][] nextWeights) {
    	for (int i = 0; i < nextLayer.neuronValue.length; i++) {
    		((StandardLayer) nextLayer).gamma[i] = 0;
    		
    		for (int j = 0; j < nextGamma.length; j++)
    			((StandardLayer) nextLayer).gamma[i] += nextGamma[j] * nextWeights[j][i];
    		((StandardLayer) nextLayer).gamma[i] *= Utils.deriv(nextLayer.neuronValue[i]);
    	}
    	
    	for (int i = 0; i < nextLayer.neuronValue.length; i++) {
    		for (int j = 0; j < prevLayer.neuronValue.length; j++)
    			weightsDelta[i][j] = ((StandardLayer) nextLayer).gamma[i] * prevLayer.neuronValue[j];
    		((StandardLayer) nextLayer).biasDelta[i] = ((StandardLayer) nextLayer).gamma[i] * ((StandardLayer) nextLayer).bias[i];
    	}
    	/*
    	 * prev curr next
    	 * 
    	 * for (int i = 0; i < curr.neuron.length; i++) {
    	 * 		for (int j = 0; j < next.loss.length; j++)
    	 * 			curr.loss += next.loss[j] * next.weight[j][i];
    	 * 		curr.loss *= curr.activator.prime(curr.neuron)
    	 * 
    	 * 		for (int j = 0; j < prev.neuron.length; j++)
    	 * 			curr.weights -= lr * curr.loss * prev.neuron
    	 * 		curr.bias -= curr.loss * curr.bias * lr
    	 * }
    	 */
    }
    
    public void updateWeights() {
    	for (int i = 0; i < nextLayer.neuronValue.length; i++) {
    		for (int j = 0; j < prevLayer.neuronValue.length; j++)
    			weights[i][j] -= weightsDelta[i][j] * ((StandardLayer) nextLayer).LEARNING_RATE;
    		((StandardLayer) nextLayer).bias[i] -= ((StandardLayer) nextLayer).biasDelta[i] * ((StandardLayer) nextLayer).LEARNING_RATE;
    	}
    }
    
    public String toString() {
    	String str = "NetConnection (1.0) \n{\n" +
    	"" + WEIGHT_MIN + "\n" +
    	"" + WEIGHT_MAX + "\n" +
    	"" + Utils.liquidMatrix(weights) + "\n" +
    	"" + Utils.liquidMatrix(weightsDelta) + "\n" +
    	"}\n";
    	
    	return str;
    }
}
