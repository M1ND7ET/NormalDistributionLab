package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GaussianAverages {
    private HashMap<Double, Integer> eachValueAmount;
    private Random randomInstance;
    private double[] bounds;
    private ArrayList<Double> sequence;
    private double mathExpectation;
    private double mode;
    private double median;
    private double harmonicMean;
    private double arithmeticMean;
    private double geometricMean;
    private double rootMeanSquare;
    private double standardDeviation;
    private double dispersion;


    GaussianAverages(double[] bounds, int dimensionOfTheSequence){
        this.bounds = bounds;
        randomInstance = new Random();
        sequence = new ArrayList<Double>();
        eachValueAmount = new HashMap<>();
        for(int i = 0; i < dimensionOfTheSequence; i++){
            sequence.add(randomize());
        }
        sequence.sort(Double::compareTo);

        for(double lowerLimit = bounds[0] - Math.abs((bounds[1] - bounds[0]) * 0.5), upperLimit = lowerLimit += ((bounds[1] - bounds[0]) / 25), i = 0;
            upperLimit <= bounds[1] + Math.abs((bounds[1] - bounds[0]) * 0.5); lowerLimit = upperLimit, upperLimit += ((bounds[1] - bounds[0]) / 50)){
            int occurrences = 0;
            double value;
            while(i < sequence.size() && (value = sequence.get((int) i)) > lowerLimit && value <= upperLimit){
                occurrences++;
                i++;
            }
            eachValueAmount.put(((upperLimit + lowerLimit) / 2), occurrences);
        }
        detMathExpectation();
        detMode();
        detMedian();
        detArithmeticMean();
        detHarmonicMean();
        detRootMeanSquare();
        detGeometricMean();
        detStandardDeviation();
    }

    private void detMathExpectation(){
        mathExpectation = bounds[0] + (bounds[1] - bounds[0]) / 2;
    }

    private void detMode(){
        ArrayList<Integer> values = new ArrayList<Integer>(eachValueAmount.values());
        values.sort(Integer::compareTo);
        for(Double eachDouble : eachValueAmount.keySet()){
            if(eachValueAmount.get(eachDouble) == values.get(values.size() - 1)) mode = eachDouble;
        }
    }

    private void detMedian(){
        if(sequence.size() % 2 == 0){
            median = (sequence.get(sequence.size() / 2) + sequence.get((sequence.size() / 2) + 1)) / 2;
        }
        else median = sequence.get((sequence.size() / 2) + 1);
    }

    private void detArithmeticMean(){
        double total = 0;
        for(Double each : sequence){
            total += each;
        }
        arithmeticMean = total / sequence.size();
    }

    private void detHarmonicMean(){
        double total = 0;
        for(Double each : sequence){
            total +=  (1 / each);
        }
        harmonicMean = sequence.size() / total;
    }

    private  void detRootMeanSquare(){
        double total = 0;
        for(Double each : sequence){
            total += each * each;
        }
        rootMeanSquare = Math.sqrt(total / sequence.size());
    }
    private void detGeometricMean(){
        if(bounds[0] <= 0 || bounds[1] <= 0){
            geometricMean = 0;
        }
        else{
            double total = 1;
            for(Double each : sequence){
                total *= each;
            }
            geometricMean = Math.pow(total, ((double) 1 / sequence.size()));
        }
    }

    private void detStandardDeviation(){
        double total = 0;
        for(Double each : sequence){
            total += Math.pow((each - arithmeticMean), 2 );
        }
        standardDeviation = Math.sqrt(((double)1 / sequence.size()) * total);
    }

    private void detDispersion(){
        dispersion = 0;
    }

    private double randomize(){
        double slide;
        if(Math.abs(bounds[0]/bounds[1]) != 1 ) slide = bounds[0] + ( (bounds[1] - bounds[0]) / 2 );
        else slide = 0;
        return  (( randomInstance.nextGaussian() * ( (double) 1/3 ))) * (Math.abs((bounds[0] - bounds[1])) / 2) + slide;
    }

    public double[] distribute() {
        return new double[]{mathExpectation, mode, median, arithmeticMean, harmonicMean, rootMeanSquare,
                geometricMean, standardDeviation, dispersion};
    }

    public HashMap<Double, Integer> getEachValueAmount() {
        return eachValueAmount;
    }
}

