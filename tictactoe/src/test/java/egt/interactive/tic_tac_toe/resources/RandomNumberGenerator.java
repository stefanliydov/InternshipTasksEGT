package egt.interactive.tic_tac_toe.resources;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {

    public static int generate(final int n) {
	return ThreadLocalRandom.current().nextInt(n);
    }

    public static int generate(final int k, final int n) {
	return ThreadLocalRandom.current().nextInt(k, n);
    }

}
