package net.astercrono.sorting;

public class SortIteration {
	private Integer[] values;
	private int iterationNumber = 0;

	public SortIteration(final Integer[] values) {
		this.values = values;
	}

	public Integer[] getValues() {
		return values;
	}
	
	public void setIterationNumber(final int number) {
		this.iterationNumber = number;
	}

	public int getIterationNumber() {
		return iterationNumber;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("[");
		
		if (values != null && values.length > 0) {
			for (int i = 0; i < values.length; i++)
			{
				final Integer value = values[i];
				sb.append(value);
				
				if (i + 1 < values.length) {
					sb.append(",");
				}
			}
		}
		
		sb.append("]");
		return sb.toString();
	}
}
