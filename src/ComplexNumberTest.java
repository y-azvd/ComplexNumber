public class ComplexNumberTest {
    public static void main(String args[]) {
        ComplexNumber z1 = new ComplexNumber(-0, 0);
        ComplexNumber z2 = new ComplexNumber(8, -60, "polar");
        ComplexNumber z3 = new ComplexNumber(0, 2, "cartesian");
        ComplexNumber z4 = new ComplexNumber(1, 2);
        ComplexNumber z5 = new ComplexNumber(2, 2);
        ComplexNumber z6 = new ComplexNumber(-1, -3);

        System.out.println(z1);
        System.out.println(z2);
        System.out.println(z3);
        System.out.println(z4);
        System.out.println(z5.principalRoot(4));
        System.out.println(z5.nthRoots(4));
        System.out.println(z6.nthRoots(8));
        System.out.println(z6.principalRoot(8));
        System.out.println(z6.log());
        System.out.println(z6.sin());
        System.out.println(z6.cos());
        System.out.println(z6.tan());
        System.out.println(z6.sinh());
        System.out.println(z6.cosh());
    }
}