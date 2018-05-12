import neuralnetwork.NeuralNode;
import neuralnetwork.TrainingSet;

public class Program {
    public static void main(String[] args){
        TrainingSet[] trainingSet = {
                new TrainingSet(new double[]{0,0f, 0,0f} , 0.0f),
                new TrainingSet(new double[]{1,0f, 0,0f} , 0.0f),
                new TrainingSet(new double[]{0,0f, 1,0f} , 0.0f),
                new TrainingSet(new double[]{1,0f, 1,0f} , 1.0f)
        };

        NeuralNode node = new NeuralNode(trainingSet);
        node.Train(10);


    }
}
