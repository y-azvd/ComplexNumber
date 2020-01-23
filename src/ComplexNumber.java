import java.util.ArrayList;
import static java.lang.Math.*;

/**
 * FOR MEMORY LEAKS
 * - https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/memleaks001.html
 * - https://dzone.com/articles/what-to-do-about-java-memory-leaks-tools-fixes-and
 * Complex Number class implementation. I've remade this several times.
 * Maybe I love complex numbers. If you don't understand the operations
 * made below, read the README.md file. Or go directly to the
 * <a href="https://en.wikipedia.org/wiki/Complex_number">Wiki page</a>
 * about this topic.
 *
 * <p>
 * To understand the advanced mathematics operations read the Churchill
 * book about complex variables.
 *
 * @author Yudi Yamane
 * @version 1.0
 * */
public class ComplexNumber {
    private double re; /** real part*/
    private double im; /** imaginary part*/

    /**
     * Constructs a default complex number (0, 0).
     * */
    ComplexNumber() {
        re = 0;
        im = 0;
    }

    /**
     * Constructs a complex number given the real and imaginary part.
     * @param x real part.
     * @param y imaginary part.
     * */
    ComplexNumber(double x, double y) {
        re = x;
        im = y;
    }

    /**
     * Constructs a complex number in cartesian or polar form.
     * @param x    real component or radius.
     * @param y    imaginary component or angle in degrees (°).
     * @param form "cartesian" or "polar".
     * */
    ComplexNumber(double x, double y, String form) {
        if (form.equals("cartesian")) {
            new ComplexNumber(x, y);
        }
        else if (form.equals("polar") || form.equals("exponential")) {
            re = x*Math.cos(toRadians(y));
            im = x*Math.sin(toRadians(y));
        }
        else {
            System.out.println("Didn't understand you. Initialized to (0, 0).");
            new ComplexNumber();
        }
    }

    /**
     * Compares two complex numbers
     * @param z second complex number
     * */
    public boolean equals(ComplexNumber z) {
        return re == z.re && im == z.re;
    }

    /**
     * Returns the modulus (length) of the complex number.
     * */
    public double modulus() {
        return sqrt(re*re + im*im);
    }

    /**
     * Returns the angle between the x-axis and the complex number counting counter clockwise.
     * @return returns the atan2 division of im by re.
     * */
    public double argument() {
        return atan2(im, re);
    }

    /**
     * Returns the angle in degrees.
     * */
    public double argument(String degrees) {
        if (degrees.equals("degrees"))
            return toDegrees(this.argument());
        return this.argument();
    }

    public ComplexNumber conjugate() {
        return new ComplexNumber(re, -im);
    }

    /**
     * Gets re
     * @return Returns real part of complex number.
     * */
    public double getRe() {
        return re;
    }

    /**
     * Gets im
     * @return Returns imaginary part of complex number.
     * */
    public double getIm() {
        return im;
    }

    /**
     * Returns string form of complex number. I'm not exactly repeating myself.
     * */
    @Override
    public String toString() {
        String stringForm = "";
        Util u = new Util();

        if (re != 0) {
            stringForm += "" + u.round(re, 2);

            if (im > 0)
                stringForm += " + i" + u.round(im, 2);
            if (im < 0)
                stringForm += " - i" + u.round(im*-1, 2);
        }
        else {
            if (im > 0)
                stringForm += "i" + u.round(im, 2);
            if (im < 0)
                stringForm += "-i" + u.round(im*-1, 2);
            if (im == 0)
                stringForm += "0 + i0";
        }

        return stringForm;
    }

    /**
     * Returns cartesian, polar or exponential form of complex number.
     * @param form cartesian, polar or exponential.
     * */
    public String toString(String form) {
        Util u = new Util();

        if (form.equals("cartesian"))
            return this.toString();
        else if (form.equals("polar")) {
            return "I'm lazy";
        }
        else if (form.equals("exponential")) {
            return "" + u.round(this.modulus(), 2) + " ∠ " + u.round(toDegrees(this.argument()), 1) + "°" ;
        }
        else
            return "what?";
    }

    /**
     * Returns the complex number scaled by a factor.
     * @param  factor the scaling number.
     * @return        the complex number multiplied by factor.
     * */
    public ComplexNumber scaledBy(double factor) {
        return new ComplexNumber(factor*re, factor*im);
    }

    /**
     * Adds
     * @param  z the other complex number to add, duh.
     * @return   the sum of this and z.
     * */
    public ComplexNumber plus(ComplexNumber z) {
        return new ComplexNumber(re + z.re, im + z.im);
    }


    /**
     * Subtracts
     * @param  z the other complex number to subtract, duh.
     * @return   the difference between this and z.
     * */
    public ComplexNumber minus(ComplexNumber z) {
        return new ComplexNumber(re - z.re, im - z.im);
    }

    public ComplexNumber times(ComplexNumber z) {
        return new ComplexNumber(re*z.re - im*z.im, re*z.im + im*z.re);
    }

    public ComplexNumber divided(ComplexNumber z) {
        if (z.re == 0 && z.im == 0) {
            System.out.println("Division by zero not possible.");
            return new ComplexNumber();
        }
        return this.times(z.conjugate()).scaledBy(1.0/pow(z.modulus(), 2));
    }

    public ArrayList nthRoots(int n) {
        ArrayList<ComplexNumber> roots = new ArrayList<>();

        if (n <= 0)
            return roots;
        else if (n == 1) {
            roots.add(this);
            return roots;
        }
        else {
            for (int k = 0; k < n; k++) {
                roots.add(new ComplexNumber(pow(this.modulus(), 1.0/n),
                                            toDegrees(this.argument() + 2*k*PI)/n,
                                            "polar"));
            }

            return roots;
        }
    }

    /** WRONG: check Churchill, pg 26*/
    public ComplexNumber principalRoot(int n) {
        if (n <= 0)
            return new ComplexNumber();
        else if (n == 1)
            return this;
        else {
            return new ComplexNumber(pow(this.modulus(), 1.0/n),
                                     toDegrees(this.argument()/n),
                                     "polar");
        }
    }

    public ComplexNumber exp() {
        return new ComplexNumber(Math.exp(re)*Math.cos(im), Math.exp(re)*Math.sin(im));
    }

    public ComplexNumber log() {
        return new ComplexNumber(Math.log(this.modulus()), this.argument());
    }


    public ComplexNumber sin() {
        ComplexNumber first  = new ComplexNumber(-im,  re);
        ComplexNumber second = new ComplexNumber( im, -re);
        ComplexNumber twoI   = new ComplexNumber(  0,  2);
        return first.exp().minus(second.exp()).divided(twoI);
    }

    public ComplexNumber cos() {
        ComplexNumber first  = new ComplexNumber(-im,  re);
        ComplexNumber second = new ComplexNumber( im, -re);
        return first.exp().plus(second.exp()).scaledBy(1/2.0);
    }

    public ComplexNumber tan() {
        return this.sin().divided(this.cos());
    }

    public ComplexNumber sinh() {
        return new ComplexNumber(Math.sinh(re)*Math.cos(im), Math.cosh(re)*Math.sin(im)) ;
    }

    public ComplexNumber cosh() {
        return new ComplexNumber(Math.cosh(re)*Math.cos(im), Math.sinh(re)*Math.sin(im)) ;
    }
}