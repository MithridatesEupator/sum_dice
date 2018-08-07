import java.util.*;

public class functions {

    public static ArrayList<Integer> diceSum(Integer n) {
        ArrayList<Integer> finalArray = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1));
        for (int i = 1; i < n; i++) {
            ArrayList<Integer> orderArray = range(i, 6 * i);
            finalArray = multipler_polynomial(
                    finalArray,
                    orderArray,
                    new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1)),
                    new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6))
            );
            finalArray = clean(finalArray);
        }
        return finalArray;
    }
    public static ArrayList<Integer> range(int start, int end) {
        ArrayList<Integer> finalArray = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            finalArray.add(i);
        }
        return finalArray;
    }

    public static ArrayList<Integer> multipler_polynomial(ArrayList<Integer> firstPolynomial, ArrayList<Integer> firstOrder, ArrayList<Integer> secondPolynomial, ArrayList<Integer> secondOrder) {
        ArrayList<Integer> finalPolynomial = new ArrayList<>();
        ArrayList<Integer> finalOrder = new ArrayList<>();
        for(int i = 0; i < firstPolynomial.size(); i++) {
            for(int j = 0; j < secondPolynomial.size(); j++) {
            finalPolynomial.add(firstPolynomial.get(i) * secondPolynomial.get(j));
            finalOrder.add(firstOrder.get(i) + secondOrder.get(j));
            }
        };
        finalPolynomial.addAll(finalOrder);
        return finalPolynomial;
    }

    public static ArrayList<Integer> clean(ArrayList<Integer> polynomial) {
        int indexCutter = polynomial.size() / 2;
        ArrayList<Integer> tempPolynomial = new ArrayList<>(polynomial.subList(0, indexCutter));
        ArrayList<Integer> tempOrder = new ArrayList<>(polynomial.subList(indexCutter, polynomial.size()));
        for(int i = 0; i < tempOrder.size() - 1; i++) {
            for(int b = i + 1; b < tempOrder.size(); b++) {
                if (tempOrder.get(i).equals(tempOrder.get(b)) && b != i) {
                    tempOrder.remove(b);
                    int a = tempPolynomial.get(b);
                    int c = tempPolynomial.get(i);
                    tempPolynomial.remove(b);
                    tempPolynomial.remove(i);
                    tempPolynomial.add( i, a + c);
                    b--;
                }
            }
        }
        return tempPolynomial;
    }

    public static ArrayList<String> prob(ArrayList<Integer> integerArrayList, int exponent) {
        int total = 0;
        ArrayList<String> probArrayList = new ArrayList<>();
        for(int i = 0; i <integerArrayList.size(); i++) {
            total += integerArrayList.get(i);
        }
        for(int i = 0; i <integerArrayList.size(); i++) {
            double exponentVar = Math.pow(10, exponent);
            double probValue = Math.round(((double) integerArrayList.get(i) / (double) total) * exponentVar);
            probValue = probValue / exponentVar;
            String probString = (i + (int) (integerArrayList.size() + 1) / 6 ) + " : " + probValue;
            probArrayList.add(probString);
        }
        return probArrayList;
    }

    public static void main(String[] args) {

        System.out.println(prob(diceSum(2),4));
    }
}

