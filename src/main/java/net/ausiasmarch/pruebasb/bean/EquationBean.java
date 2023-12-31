package net.ausiasmarch.pruebasb.bean;

public class EquationBean {
    private double a;
    private double b;
    private double c;

    public EquationBean() {
        // Constructor por defecto
    }

    public EquationBean(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // Getters y setters para "a", "b" y "c"

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }
}
