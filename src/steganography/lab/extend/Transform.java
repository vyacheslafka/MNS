package steganography.lab.extend;

import java.text.DecimalFormat;

public class Transform {

    private int size;
    private int[][] mas;
    private double[][] dcp;
    private double[][] idcp;

    public Transform(int[][] mas){
        this.mas = mas;
        size = mas.length;
        dcp = DCP(mas);
    }

    private double valueCoefficient(int value){
        if (value == 0) return 1.0/Math.sqrt(2);
        else return 1;
    }

    private double[][] DCP(int[][] mas){
        double[][] res = new double[size][size];
        double U, V, temp;
        for (int v =0; v<size; v++) {
            for (int u = 0; u < size; u++) {
                V = valueCoefficient(v);
                U = valueCoefficient(u);
                temp = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        temp += mas[i][j] * Math.cos(Math.PI * v * (2 * i + 1) / (2 * size)) *
                                Math.cos(Math.PI * u * (2 * j + 1) / (2 * size));
                    }
                }
                res[v][u] = U * V * temp / (Math.sqrt(2 * size));
            }
        }
        return res;
    }

    private double[][] iDCP(){
        double[][] res = new double[size][size];
        double U, V, temp;
        for (int v=0; v<size; v++) {
            for (int u = 0; u < size; u++) {
                temp = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        V = valueCoefficient(i);
                        U = valueCoefficient(j);
                        temp += U * V * dcp[i][j] * Math.cos(Math.PI * i * (2 * v + 1) / (2 * size)) *
                                Math.cos(Math.PI * j * (2 * u + 1) / (2 * size));
                    }
                }
                double num = temp / (Math.sqrt(2 * size));
                String run = new DecimalFormat("#0").format(num);
                res[v][u] = Integer.valueOf(run);
            }
        }
        return res;
    }

    public void startInverseDCP(){
        if (dcp != null) idcp = iDCP();
        else System.out.println("ДКП = " + dcp);
    }
    public void setDcp(double[][] dcp) {
        this.dcp = dcp;
    }
    public int[][] getMas(){
        return mas;
    }
    public double[][] getDcp() {
        return dcp;
    }
    public double[][] getIdcp() {
        return idcp;
    }
}
