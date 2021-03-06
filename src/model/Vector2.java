package model;

import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector2{

    private float v0; // y component
    private float v1; // x component

    public Vector2(boolean random){
        if (random) {
            randomizeVector();
        } else {
            this.v0 = 0;
            this.v1 = 0;
        }
    }
    public Vector2(float v0, float v1){
        this.v0 = v0;
        this.v1 = v1;
    }
    private void randomizeVector(){
        Random rand = new Random();
        int v = rand.nextInt(3);
        switch(v){
            case 0:
                this.v0 = 1;
                this.v1 = 1;
                break;
            case 1:
                this.v0 = -1;
                this.v1= 1;
                break;
            case 2:
                this.v0 = 1;
                this.v1 = -1;
                break;
            case 3:
                this.v0 = -1;
                this.v1 = -1;
                break;
        }
    }
    public double distance(Vector2 target){
        return (sqrt(pow((target.getv0()-v0), 2) + pow((target.getv1()-v1), 2)));
    }

    @Override
    public String toString() {
        return Integer.toString((int)getv0()) + ", " + Integer.toString((int)getv1());
    }

    //Getters And Setters
    public void setV0(float v0) {
        this.v0 = v0;
    }
    public void setV1(float v1) {
        this.v1 = v1;
    }
    public float getv1(){
        return this.v1;
    }
    public float getv0(){
        return this.v0;
    }
    public Vector2 getDisplacementVector(int yDispMax, int xDispMax){
        Random rand = new Random();
        int xDisp = rand.nextInt(xDispMax-1)+1;
        int yDisp = rand.nextInt(yDispMax-1)+1;
        switch(rand.nextInt(3)) {
            case 1:
                xDisp = -xDisp;
                break;
            case 2:
                yDisp = -yDisp;
                break;
            case 3:
                xDisp = -xDisp;
                yDisp = -yDisp;
                break;
            default:
        }
        return new Vector2(getv0()+yDisp, getv1()+xDisp);
    }
}
