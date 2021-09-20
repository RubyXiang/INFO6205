package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalkProof {
    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        x = x + dx;
        y = y + dy;
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        for(int i = 0; i < m; i++){
            randomMove();
        }
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the sqrt of distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the sqrt of (Euclidean) distance from the origin to the current position.
     */
    public double distanceSqrt() {
        return x * x + y * y;
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalkProof walk = new RandomWalkProof();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distanceSqrt();
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
        int m;
        int n = 500;
        double meanDistance = 0;
        for(int i = 1; i <= 20; i++){
            m = i;
            meanDistance = randomWalkMulti(m, n);
            System.out.println(m + "steps: mean value of sqrt of distance = " + meanDistance );
        }
    }

}
