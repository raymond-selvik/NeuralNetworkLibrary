package neuralnetwork;

public class TrainingSet {
    private double[] input;
    private double output;

    public TrainingSet(double[] input, double output) {
        this.input = input;
        this.output = output;
    }

    public double getInput(int i) {
        return input[i];
    }

    public double[] getInputs(){
        return input;
    }

    public double getOutput() {
        return output;
    }
}
