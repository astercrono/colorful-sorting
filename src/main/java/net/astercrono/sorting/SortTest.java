package net.astercrono.sorting;

public class SortTest {
	public static void main(final String[] args) {
		final HistoricalSort sort = new HistoricalMergeSort();
		final SortHistory history = sort.sort(new Integer[] { 4, 7, 1, 2, 8, 9, 4, 3, 6, 1, 8 });
		System.out.println(history);
	}
}
