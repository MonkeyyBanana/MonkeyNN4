package monkey.nn;

public enum NetActivator {
	Identity("Identity", 0, "NONE"),
    Rectifier("Recifier", 1, ""),
    Sin("Sine", 2, "Sine 0.01745240"),
    Cos("Cosine", 3, "Cosine 0.99984769"),
    Tan("Tangent", 4, "Tangent 0.01745506"),
    Tanh("Tangent Hyperbolic", 5, "Tangent Hyperbolic"),
    Sinh("Sine Hyperbolic", 6, "Sine Hyperbolic"),
    Exp("Exponent", 7, "Exponent"),
    Sigmoid("Sigmoid", 8, "Sigmoid"),
    Sqrt("Square Root", 9, "Square Root"),
    Pow2("Squared", 10, "Powered By Two"),
    Relu("Rectified Linear Unit", 11, "");

    private String FUNC_TYPE;
    private int FUNC_ID;
    private String FUNC_DESC;

    NetActivator(String fT, int id, String desc) {
        FUNC_TYPE = fT;
        FUNC_ID = id;
        FUNC_DESC = desc;
    }

    public String getName() {return FUNC_TYPE;}
    public int getId() {return FUNC_ID;}
    public String getDesc() {return FUNC_DESC;}
    
    public static NetActivator idToActivator(int id) {
        for (NetActivator act : NetActivator.values())
            if (act.getId() == id)
                return act;
        return null;
    }
    
    public float activator(float x) {
        switch(this.FUNC_ID) {
            case 0:
                return Identity(x);
            case 1:
                return Rectifier(x);
            case 2:
                return Sin(x);
            case 3:
                return Cos(x);
            case 4:
                return Tan(x);
            case 5:
                return TanH(x);
            case 6:
                return SinH(x);
            case 7:
                return Exponential(x);
            case 8:
                return Sigmoid(x);
            case 9:
                return Sqrt(x);
            case 10:
                return Pow2(x);
            case 11:
                return Relu(x);
        }
        return x;
    }

    private float Identity(float x) {
        return x;
    }

    private float Rectifier(float x) {
        if (x != x) 
            return 0.0f;
        if (x < 0.0f)
            return 0.0f;
        if (x > 1.0f)
            return 1.0f;
        return x;
    }

    private float Exponential(float x) {
        float out = (float) Math.exp(x);
        if (x != out)
            return 0.0f;
        return out;
    }

    private float Sin(float x) {
        float out = (float) Math.sin(x);
        if (out != out)
            return 0.0f;
        if (out < 0.0f)
            return 0.0f;
        if (out > 1.0f)
            return 1.0f;
        return out;
    }

    private float Cos(float x) {
        float out = (float) Math.cos(x);
        if (out != out)
            return 0.0f;
        if (out < 0.0f)
            return 0.0f;
        if (out > 1.0f)
            return 1.0f;
        return out;
    }

    private float Tan(float x) {
        float out = (float) Math.tan(x);
        if (out != out)
            return 0.0f;
        if (out < 0.0f)
            return 0.0f;
        if (out > 1.0f)
            return 1.0f;
        return out;
    }

    private float TanH(float x) {
        float out = (float) Math.tanh(x);
        if (out != out)
            return 0.0f;
        if (out < 0.0f)
            return 0.0f;
        if (out > 1.0f)
            return 1.0f;
        return out;
    }

    private float SinH(float x) {
        float out = (float) Math.sinh(x);
        if (out != out)
            return 0.0f;
        if (out < 0.0f)
            return 0.0f;
        if (out > 1.0f)
            return 1.0f;
        return out;
    }

    private float Sigmoid(float x) {
        float out = 1.0f / (1.0f + (float)Math.exp(-x));
        if (out != out)
            return 0.0f;
        if (out < 0.0f)
            return 0.0f;
        if (out > 1.0f)
            return 1.0f;
        return out;
    }

    private float Sqrt(float x) {
        float out = (float) Math.sqrt(x);
        if (out != out)
            return 0.0f;
        if (out < 0.0f)
            return 0.0f;
        if (out > 1.0f)
            return 1.0f;
        return out;
    }

    private float Pow2(float x) {
        float out = (float) Math.pow(x, 2);
        if (out != out)
            return 0.0f;
        if (out < 0.0f)
            return 0.0f;
        if (out > 1.0f)
            return 1.0f;
        return out;
    }
    
    private float Relu(float x) {
        return Math.max(0, x);
    }
}
