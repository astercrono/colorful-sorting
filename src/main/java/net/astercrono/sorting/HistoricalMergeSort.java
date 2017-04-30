package net.astercrono.sorting;

public class HistoricalMergeSort implements HistoricalSort {

	@Override
	public SortHistory sort(final Integer[] values) {
		final SortHistory history = new SortHistory();

		if (values.length == 0) {
			return history;
		}

		if (values.length == 1) {
			history.addIteration(new SortIteration(values));
			return history;
		}

		final int leftStart = 0;
		final int leftEnd = values.length / 2 - 1;
		final int rightStart = leftEnd + 1;
		final int rightEnd = values.length - 1;

		mergeSort(values, leftStart, leftEnd, rightStart, rightEnd, history);

		return history;
	}

	private void mergeSort(final Integer[] values, int leftStart, final int leftEnd, final int rightStart,
			final int rightEnd, final SortHistory history) {
		final boolean reorderLeft = leftStart + 1 >= leftEnd;
		final boolean reorderRight = rightStart + 1 >= rightEnd;

		if (reorderLeft) {
			reorder(values, leftStart, leftEnd, history);
		}

		if (reorderRight) {
			reorder(values, rightStart, rightEnd, history);
		}

		if (!reorderLeft) {
			final int leftStartA = leftStart;
			final int leftEndA = leftStart + ((leftEnd - leftStart) / 2);
			final int rightStartA = leftEndA + 1;
			final int rightEndA = leftEnd;

			mergeSort(values, leftStartA, leftEndA, rightStartA, rightEndA, history);
		}

		if (!reorderRight) {
			final int leftStartB = rightStart;
			final int leftEndB = rightStart + ((rightEnd - rightStart) / 2);
			final int rightStartB = leftEndB + 1;
			final int rightEndB = rightEnd;

			mergeSort(values, leftStartB, leftEndB, rightStartB, rightEndB, history);
		}

		merge(values, leftStart, leftEnd, rightStart, rightEnd);
		addToHistory(values, history);
	}

	private void merge(final Integer[] values, int leftStart, int leftEnd, int rightStart, int rightEnd) {
		for (int rightIndex = rightStart; rightIndex <= rightEnd; rightIndex++) {
			insertValue(values, leftStart, rightIndex);

			if (leftEnd + 1 < rightEnd) {
				leftEnd++;
			}
		}
	}

	private void insertValue(final Integer[] values, final int targetIndex, final int valueIndex) {
		int value = values[valueIndex];

		for (int i = targetIndex; i < valueIndex; i++) {
			final int compareValue = values[i];

			if (value <= compareValue) {
				shiftValuesRight(values, i, valueIndex);
				values[i] = value;
				break;
			}
		}
	}

	private void shiftValuesRight(final Integer[] values, final int startIndex, final int endIndex) {
		if (values.length == 1) {
			return;
		}

		int previous = values[startIndex];
		for (int i = startIndex + 1; i <= endIndex; i++) {
			int current = values[i];
			values[i] = previous;
			previous = current;
		}
	}

	private void reorder(final Integer[] values, final int leftIndex, final int rightIndex, final SortHistory history) {
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
