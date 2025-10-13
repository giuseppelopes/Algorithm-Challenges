public class MultiplyArray {

    public Integer[] multiply(Integer[] integers) {

        if(integers == null || integers.length == 0) throw new IllegalArgumentException("Array invalid");

        Integer[] newArray = new Integer[integers.length];

        for(int i = 0; i < integers.length; i++) {
            Integer tempValue = 1;
            for (int j = 0; j < integers.length; j++) {
                if(i != j) {
                    tempValue *= integers[j];
                }
            }
            newArray[i] = tempValue;
        }
        return newArray;
    }
}
