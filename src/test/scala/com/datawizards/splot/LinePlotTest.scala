package com.datawizards.splot

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.model.PlotType
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LinePlotTest extends SPlotBaseTest {
  test("Scatter") {
    val xs = Seq(1.0, 2.0, 3.0)
    val ys = xs.map(x => x*x)
    val data = xs zip ys

    data.plotLine()

    val plot = getLastPlot

    assertResult(PlotType.Line) {
      plot.plotType
    }

    assertResult(xs, "x values") {
      plot.xValues
    }

    assertResult(ys, "y values") {
      plot.yValues
    }
  }
}
