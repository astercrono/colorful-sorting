package net.astercrono.sorting;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;

import net.astercrono.colorgen.ColorGeneration;
import net.astercrono.colorgrid.ColorGrid;
import net.astercrono.colorgrid.ColorGridDimensions;

public class SortRenderer {
	private SortHistory history;
	private int scale = 1;

	public SortRenderer(final SortHistory history) {
		this.history = history;
	}

	public void render() {
		int width = calculateWidth();
		int height = calculateHeight();

		final ColorGridDimensions dimensions = new ColorGridDimensions();
		dimensions.setRows(history.getNumberOfIterations());
		dimensions.setColumns(history.getIterationLength());
		dimensions.setWidth(width);
		dimensions.setHeight(height);

		final ColorGrid grid = new ColorGrid(dimensions);

		final JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(new Dimension(width, height + 20));
		window.add(grid);
		window.setVisible(true);

		renderHistory(history, grid);
	}

	public void setScale(int scale) {
		if (scale > 1) {
			this.scale = scale;
		}
	}

	private void renderHistory(final SortHistory history, final ColorGrid grid) {
		final List<Color> referenceColors = generateColors();

		history.forEachIteration((SortIteration iteration) -> {
			renderIteration(iteration, grid, referenceColors);
		});

		grid.repaint();
	}

	private void renderIteration(final SortIteration iteration, final ColorGrid grid,
			final List<Color> referenceColors) {
		final Color[] iterationColors = buildIterationColors(iteration, referenceColors);
		grid.setRowColors(iteration.getIterationNumber(), iterationColors);
	}

	private List<Color> generateColors() {
		ColorGeneration colorGeneration = new ColorGeneration();
		return colorGeneration.generate(history.getIterationLength());
	}

	private Color[] buildIterationColors(final SortIteration iteration, final List<Color> referenceColors) {
		final Color[] colors = new Color[referenceColors.size()];
		final Integer[] values = iteration.getValues();

		int i = 0;
		for (final Integer v : values) {
			colors[i] = referenceColors.get(v);
			i++;
		}

		return colors;
	}

	private int calculateWidth() {
		return (int) (history.getIterationLength() * scale);
	}

	private int calculateHeight() {
		return (int) (history.getNumberOfIterations() * scale);
	}
}
