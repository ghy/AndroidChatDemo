package org.achartengine.chartdemo.demo.chart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class MyChart extends AbstractDemoChart {
	private static final long HOUR = 3600 * 1000;

	private static final long DAY = HOUR * 24;

	private static final int HOURS = 24;

	@Override
	public String getName() {
		return "My Chart";
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return "My Chart";
	}

	@Override
	public Intent execute(Context context) {
		String[] titles = new String[] { "In", "Out", "Bandwidth" };
		// long now = Math.round(new Date().getTime() / DAY) * DAY;
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		List<Date[]> dateList = new ArrayList<Date[]>();
		for (int i = 0; i < titles.length; i++) {
			Date[] dates = new Date[HOURS];
			for (int j = 0; j < HOURS; j++) {
				dates[j] = new Date(curDate.getYear(), curDate.getMonth(),
						curDate.getDate(), j, 0, 0);
			}
			dateList.add(dates);
		}
		List<double[]> values = new ArrayList<double[]>();

		values.add(new double[] { 1.2, 1.5, 1.7, 1.5, 1.4, 1.4, 1.3, 1.1, 0.3,
				0.2, 2.9, 2.7, 2.6, 2.9, 0.3, 0.6, 0.9, 1.2, 1.6, 1.9, 2.1,
				1.7, 1.5, 2.0 });

		values.add(new double[] { 1.9, 1.2, 0.9, 0.5, 0.1, 0.5, 0.6, 1.9, 1.9,
				1.8, 0.3, 1.4, 2.4, 2.9, 3.0, 1.4, 2.4, 2.0, 1.5, 0.9, 0.5,
				1.9, 1.9, 2.5 });

		values.add(new double[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
				2, 2, 2, 2, 2, 2, 2, 2, 2 });

		int[] colors = new int[] { Color.GREEN, Color.BLUE, Color.YELLOW };

		PointStyle[] styles = new PointStyle[] { PointStyle.POINT,
				PointStyle.POINT, PointStyle.POINT };

		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer
					.getSeriesRendererAt(i);
			seriesRenderer.setFillPoints(true);
			seriesRenderer.setFillBelowLine(i == 0);
			seriesRenderer.setFillBelowLineColor(colors[i]);
			if (i == 2) {
				seriesRenderer.setLineWidth(5.0f);
			} else {
				seriesRenderer.setLineWidth(1.0f);
			}
		}

		setChartSettings(renderer, "Utilization and Status graph", "Hour",
				"bits pre second", dateList.get(0)[0].getTime(),
				dateList.get(0)[HOURS - 1].getTime(), 0, 3, Color.LTGRAY,
				Color.LTGRAY);
		renderer.setXLabels(12);
		renderer.setYLabels(10);
		renderer.setShowGrid(true);
		renderer.setXLabelsAlign(Align.CENTER);
		renderer.setYLabelsAlign(Align.RIGHT);
		renderer.setBackgroundColor(Color.GRAY);
		renderer.setApplyBackgroundColor(true);

		Intent intent = ChartFactory.getTimeChartIntent(context,
				buildDateDataset(titles, dateList, values), renderer, "HH:mm");
		return intent;
	}

}
