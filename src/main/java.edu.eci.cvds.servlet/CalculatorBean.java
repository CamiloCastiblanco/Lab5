package edu.eci.cvds.servlet;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
@ManagedBean(name = "juego")
@SessionScoped
public class Calculator{
    private int mean,mode,variance,standartDesviation;
    private ArrayList<Integer> dataSet;
    private ArrayList<String> attemp;

    public Calculator(){
        mean = 0;
        mode = 0;
        variance = 0;
        standartDesviation = 0;
        dataSet = new ArrayList<Integer>();
        attemp = new ArrayList<String>();
    }

    public int getMean(){
        return mean;
    }

    public void setMean(int mean) {
        this.mean = mean;
    }

    public int getMode(){
        return mode;
    }

    public void setMode(int mode){
        this.mode = mode;
    }

    public int getVariance(){
        return variance;
    }

    public void setVariance(int variance){
        this.variance = variance;
    }

    public int getStandartDesviation(){
        return standartDesviation;
    }

    public void setStandartDesviation(int standartDesviation){
        this.standartDesviation = standartDesviation;
    }

    public ArrayList<Integer> getDataSet(){
        return dataSet;
    }

    public void setDataSet(ArrayList<Integer> dataSet){
        this.dataSet = dataSet;
    }

    public ArrayList<String> getAttemp() {
        return attemp;
    }

    public void setAttemp(ArrayList<String> attemp) {
        this.attemp = attemp;
    }

    public int calculateMean(ArrayList<Integer> dataSet) {
        int ans = 0;

        for (Integer i : dataSet) {
            ans += i;
        }

        ans /= dataSet.size();

        return ans;
    }

    public int calculateStandardDeviation(ArrayList<Integer> dataSet) {
        return (int) Math.pow(calculateVariance(dataSet), 0.5);
    }

    public int calculateVariance(ArrayList<Integer> dataSet) {
        int mean = calculateMean(dataSet);
        int value = 0;

        for (Integer i : dataSet) {
            value += Math.pow(i - mean, 2);
        }

        return value / (dataSet.size() - 1);
    }

    public int calculateMode(ArrayList<Integer> dataSet) {
        int number, cont = 0, value = 0, rep = 0;

        for (int i = 0; i < dataSet.size(); i++) {
            number = dataSet.get(i);

            for (int j = i + 1; j < dataSet.size(); j++) {
                if (dataSet.get(j) == number) {
                    cont++;
                }
            }

            if (cont > rep) {
                value = number;
                rep = cont;
            }

            cont = 0;
        }

        return value;
    }

    public void calculate(String dataSet) {
        String value = "";
        ArrayList<Integer> values = new ArrayList<Integer>();

        attemp.add(dataSet);

        dataSet += ",";

        for (int i = 0; i < dataSet.length(); i++) {
            if (dataSet.charAt(i) != ',') {
                value += dataSet.charAt(i) + "";
            } else {
                values.add(Integer.parseInt(value));
                value = "";
            }
        }

        mean = calculateMean(values);
        standartDesviation = calculateStandardDeviation(values);
        variance = calculateVariance(values);
        mode = calculateMode(values);
    }

    public void restart(){
        mean = 0;
        mode = 0;
        variance = 0;
        standartDesviation = 0;
        dataSet = new ArrayList<Integer>();
        attemp = new ArrayList<String>();
    }
}