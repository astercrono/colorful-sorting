package net.astercrono.sorting;

import org.junit.Assert;
import org.junit.Test;

public class SortTest {
	@Test
	public void testMergeSort() {
		final HistoricalSort sort = new HistoricalMergeSort();
		testSort(sort);
	}

	@Test
	public void testQuickSort() {
		final HistoricalSort sort = new HistoricalQuickSort();
		testSort(sort);
	}

	private void testSort(final HistoricalSort sort) {
		final Integer[] unsorted = new Integer[] { 3, 4, 1, 2, 5 };
		final Integer[] expected = new Integer[] { 1, 2, 3, 4, 5 };

		final SortHistory history = sort.sort(unsorted);
		final Integer[] sorted = history.getSortedList();

		Assert.assertArrayEquals(expected, sorted);
	}
}
