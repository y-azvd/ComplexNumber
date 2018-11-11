public class Util {
    public double round(double number, int places) {
        double n = Math.round(number*Math.pow(10, places))/Math.pow(10, places);
        return n;
    }
}
