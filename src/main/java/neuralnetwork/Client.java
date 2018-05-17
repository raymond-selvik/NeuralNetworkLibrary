package neuralnetwork;

import java.util.ArrayList;
import java.util.List;

public class Client {
    ANN ann;

    public void Go(){
        ann = new ANN(2, 1, 1, 2, 0.8);

        List<Double> result;

        double sumSquareError = 0;

        for(int i = 0; i < 100; i++)
        {
            sumSquareError = 0;
            result = Train(1, 1, 0);
            sumSquareError += Math.pow(result.get(0) - 0, 2);
            result = Train(1, 0, 1);
            sumSquareError += Math.pow(result.get(0) - 1, 2);
            result = Train(0, 1, 1);
            sumSquareError += Math.pow(result.get(0) - 1, 2);
            result = Train(0, 0, 1);
            sumSquareError += Math.pow(result.get(0) - 1, 2);
        }

        System.out.println("SSE: " + sumSquareError);

        result = Train(1, 1, 0);
        System.out.println(" 1 1 " + result.get(0));
        result = Train(1, 0, 1);
        System.out.println(" 1 0 " + result.get(0));
        result = Train(0, 1, 1);
        System.out.println(" 0 1 " + result.get(0));
        result = Train(0, 0, 1);
        System.out.println(" 0 0 " + result.get(0));
    }

    List<Double> Train(double i1, double i2, double o)
    {
        List<Double> inputs = new ArrayList<Double>();
        List<Double> outputs = new ArrayList<Double>();
        inputs.add(i1);
        inputs.add(i2);
        outputs.add(o);
        return (ann.Train(inputs,outputs));
    }
}
