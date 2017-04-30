package net.astercrono.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SortHistory {
	private final List<SortIteration> iterations = new ArrayList<SortIteration>();
	
	public List<SortIteration> getIterations() {
		return iterations;
	}
	
	public int getIterationLength() {
		int length = 0;
		
		final SortIteration firstIteration = iterations.get(0);
		if (firstIteration != null) {
			length = firstIteration.getValueCount();
		}
		
		return length;
	}
	
	public int getNumberOfIterations() {
		return iterations.size();
	}
	
	public void addIteration(final SortIteration iteration) {
		iteration.setIterationNumber(iterations.size());
		iterations.add(iteration);
	}
	
	public void forEachIteration(Consumer<SortIteration> consumer) {
		iterations.forEach(consumer);
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		
		if (iterations != null && iterations.size() > 0) {
			for (final SortIteration iteration : iterations) {
				sb.append(iteration.toString());
			}
		}
		
		return sb.toString();
	}
}
