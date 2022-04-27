package monkey.nn;

import java.util.Scanner;

public class StandardLayer extends NetLayer{
	
	float LEARNING_RATE;
	
	float BIAS_MIN;
	float BIAS_MAX;
	
	float[] bias;
	float[] biasDelta;
    float[] gamma;
    float[] error;

	public StandardLayer(int neurons) {
		this(neurons, NetActivator.Relu);
	}
	
	public StandardLayer(int neurons, NetActivator activator) {
		this(neurons, activator, 0, 0.5f, 0.03f);
    }
    
    public StandardLayer(int neurons, NetActivator activator, float learningRate) {
    	this(neurons, activator, 0, 0.5f, learningRate);
    }
    
    public StandardLayer(int neurons, NetActivator activator, float biasMin, float biasMax) {
    	this(neurons, activator, biasMin, biasMax, 0.03f);
    }
    
    public StandardLayer(int neurons, NetActivator activator, float biasMin, float biasMax, float learningRate) {
    	BIAS_MIN = biasMin;
    	BIAS_MAX = biasMax;
    	LEARNING_RATE = learningRate;
    	this.activator = activator;
    	neuronValue = new float[neurons];
    	gamma = new float[neurons];
    	bias = new float[neurons];
    	biasDelta = new float[neurons];
    	error = new float[neurons];
    	initBias();
    	LAYER_SIGNATURE = "Standard Layer";
    }
    
    public StandardLayer(String str) {
    	Scanner scan = new Scanner(str);
    	String desc = scan.nextLine();
    	//LAYER_SIGNATURE = desc.split(" ")[0];
    	//NETWORK_VERSION = desc.split(" ")[1];
    	scan.nextLine();
    	LEARNING_RATE = scan.nextFloat();
    	BIAS_MIN = scan.nextFloat();
    	BIAS_MAX = scan.nextFloat();
    	scan.nextLine();
    	bias = Utils.strToArr(scan.nextLine());
    	biasDelta = Utils.strToArr(scan.nextLine());
    	gamma = Utils.strToArr(scan.nextLine());
    	error = Utils.strToArr(scan.nextLine());
    	neuronValue = Utils.strToArr(scan.nextLine());
    	activator = NetActivator.idToActivator(scan.nextInt());
    	scan.close();
    	LAYER_SIGNATURE = "Standard Layer";
    }
    
    private void initBias() {
    	for (int i = 0; i < bias.length; i++)
    		bias[i] = NeuralNetwork.RANDOM.nextFloat() * (BIAS_MAX - BIAS_MIN) + BIAS_MAX;
    }
    
    public String toString() {
    	String str = LAYER_SIGNATURE + "(" + NETWORK_VERSION + ") \n{\n" +
    	"" + LEARNING_RATE + "\n" +
    	"" + BIAS_MIN + "\n" + 
    	"" + BIAS_MAX + "\n" + 
    	"" + Utils.liquidArray(bias) + "\n" + 
    	"" + Utils.liquidArray(biasDelta) + "\n" +
    	"" + Utils.liquidArray(gamma) + "\n" +
    	"" + Utils.liquidArray(error) + "\n" +
    	"" + Utils.liquidArray(neuronValue) + "\n" +
    	"" + activator.getId() + "\n}\n";
    	
    	return str;
    }
    
	@Override
	public void runAtForward(int i) {
		// Nothing Done On Forward Event
		
	}

	@Override
	public void runAtBackprop(int i) {
		// Nothing Done On Back Event
		
	}

}
