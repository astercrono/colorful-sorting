package net.astercrono.sorting;

import java.util.Random;

public class App {
	private static final int MAX_NUMBER = 700;
	private HistoricalSort sort;
	
	public static void main(final String[] args) {
		final HistoricalSort sort = new HistoricalMergeSort();
		final App app = new App(sort);
		app.run();
	}
	
	public App(final HistoricalSort sort) {
		this.sort = sort;
	}
	
	public void run() {
		final Integer[] numbers = generateNumbers();
		final SortHistory history = sort.sort(numbers);
		render(history);
	}
	
	private void render(final SortHistory history) {
		final SortRenderer renderer = new SortRenderer(history);
		renderer.render();
	}
	
	private Integer[] generateNumbers() {
		final Integer[] numbers = new Integer[MAX_NUMBER];
		final Random generator = new Random();
		
		for (int i = 0; i < MAX_NUMBER; i++) {
			numbers[i] = generator.nextInt(MAX_NUMBER);
		}
		
		return numbers;
	}
}
