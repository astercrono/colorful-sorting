package net.astercrono.sorting;

public class HistoricalQuickSort implements HistoricalSort {

	@Override
	public SortHistory sort(final Integer[] values) {
		final SortHistory history = new SortHistory();
		addToHistory(values, history);

		if (values.length <= 1) {
			return history;
		}

		quickSort(values, 0, values.length - 1, history);

		return history;
	}

	private void quickSort(final Integer[] values, final int startIndex, final int endIndex,
			final SortHistory history) {
		if (startIndex == endIndex) {
			return;
		}

		if (startIndex + 1 == endIndex) {
			reorder(values, startIndex, endIndex);
			return;
		}

		final int pivotIndex = pickPivotIndex(values, startIndex, endIndex);
		final int newPivotIndex = shiftValuesOnPivot(values, pivotIndex, startIndex, endIndex);
		addToHistory(values, history);

		if (newPivotIndex > startIndex) {
			quickSort(values, startIndex, newPivotIndex - 1, history);
		}

		if (newPivotIndex < endIndex) {
			quickSort(values, newPivotIndex + 1, endIndex, history);
		}
	}

	private int shiftValuesOnPivot(final Integer[] values, int pivotIndex, final int startIndex, final int endIndex) {
		final int pivotValue = values[pivotIndex];

		for (int i = startIndex; i <= endIndex; i++) {
			final int value = values[i];

			if (i == pivotIndex) {
				continue;
			}
			
			if (value > pivotValue && i <= pivotIndex) {
				swap(values, pivotIndex, i);
				pivotIndex = i;
			}
			else if (i - 1 == pivotIndex && value <= pivotValue) {
				swap(values, pivotIndex, i);
				pivotIndex = pivotIndex + 1;
			}
			else if (i > pivotIndex && value <= pivotValue) {
				swap(values, pivotIndex, pivotIndex + 1);
				swap(values, pivotIndex, i);
				pivotIndex = pivotIndex + 1;
			}
		}

		return pivotIndex;
	}

	private void swap(final Integer[] values, final int indexA, final int indexB) {
		final int valueA = values[indexA];
		values[indexA] = values[indexB];
		values[indexB] = valueA;
	}

	private Integer pickPivotIndex(final Integer[] values, final int startIndex, final int endIndex) {
		if (values.length <= 2) {
			return values[0];
		}

		final int firstIndex = startIndex;
		final int middleIndex = (endIndex - startIndex) / 2;
		final int lastIndex = endIndex - 1;

		final Integer first = values[firstIndex];
		final Integer middle = values[middleIndex];
		final Integer last = values[lastIndex];

		final Integer median = findMedian(first, middle, last);

		if (first == median) {
			return firstIndex;
		}
		else if (middle == median) {
			return middleIndex;
		}
		else {
			return lastIndex;
		}
	}

	private Integer findMedian(final Integer a, final Integer b, final Integer c) {
		if ((a >= b && a <= c) || (a <= b && a >= c)) {
			return a;
		}
		else if ((b >= a && b <= c) || (b <= a && b >= c)) {
			return b;
		}
		else {
			return c;
		}
	}

	private void reorder(final Integer[] values, final int leftIndex, final int rightIndex) {
		final Integer leftValue = values[leftIndex];
		final Integer rightValue = values[rightIndex];

		if (leftValue > rightValue) {
			values[leftIndex] = rightValue;
			values[rightIndex] = leftValue;
		}
	}

	private void addToHistory(final Integer[] values, final SortHistory history) {
		final Integer[] copiedValues = copyValues(values);
		history.addIteration(new SortIteration(copiedValues));
	}

	private Integer[] copyValues(final Integer[] values) {
		final Integer[] newValues = new Integer[values.length];

		for (int i = 0; i < values.length; i++) {
			newValues[i] = values[i];
		}

		return newValues;
	}
}
