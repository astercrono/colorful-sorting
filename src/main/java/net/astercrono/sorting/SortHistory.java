package net.astercrono.sorting;

import java.util.ArrayList;
import java.util.List;

public class SortHistory {
	private final List<SortIteration> iterations = new ArrayList<SortIteration>();
	
	public List<SortIteration> getIterations() {
		return iterations;
	}
	
	public void addIteration(final SortIteration iteration) {
		iteration.setIterationNumber(iterations.size() - 1);
		iterations.add(iteration);
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
