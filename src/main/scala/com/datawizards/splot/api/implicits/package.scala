package com.datawizards.splot.api

import java.util.Date

import com.datawizards.splot.builders._
import com.datawizards.splot.device.Device
import com.datawizards.splot.model.PlotAxisValues.{XAxisValueType, XAxisValueTypeDate, XAxisValueTypeDouble, XAxisValueTypeInt, XAxisValueTypeString, YAxisValueType, YAxisValueTypeDouble, YAxisValueTypeInt}

package object implicits {

  object SPlot {
    def buildPlot(x:Iterable[XAxisValueType], y:Iterable[YAxisValueType]): PlotBuilderForPairOfXYAxis
      = new PlotBuilderForPairOfXYAxis(x zip y)

    def buildPlot(y:Iterable[Double]): PlotBuilderForDouble
      = new PlotBuilderForDouble(y)

    def plotBar(x:Iterable[XAxisValueType], y:Iterable[YAxisValueType]): Unit =
      buildPlot(x,y).bar().display()

    def plotLine(x:Iterable[XAxisValueType], y:Iterable[YAxisValueType]): Unit =
      buildPlot(x,y).line().display()

    def plotBar(y:Iterable[Double]): Unit = buildPlot(y).bar().display()

    def plotLine(y:Iterable[Double]): Unit = buildPlot(y).line().display()

    def plotScatter(x:Iterable[XAxisValueType], y:Iterable[YAxisValueType]): Unit =
      buildPlot(x,y).scatter().display()

    def plotArea(x:Iterable[XAxisValueType], y:Iterable[YAxisValueType]): Unit =
      buildPlot(x,y).area().display()

    def plotHistogram(y:Iterable[Double], bins: Int): Unit =
      PlotBuilder(y).histogram(y => y, bins).display()
  }

  implicit def convertIterable[T](x: Iterable[T]): IterablePlot[T] =
    new IterablePlot(x)

  implicit def convertIterableDouble(x: Iterable[Double]): IterableDoublePlot =
    new IterableDoublePlot(x)

  implicit def convertIterableInt(x: Iterable[Int]): IterableIntPlot =
    new IterableIntPlot(x)

  implicit def convertIterablePairDoubleDouble(x: Iterable[(Double,Double)]): IterablePairDoubleDoublePlot =
    new IterablePairDoubleDoublePlot(x)

  implicit def convertIterablePairDoubleInt(x: Iterable[(Double,Int)]): IterablePairDoubleIntPlot =
    new IterablePairDoubleIntPlot(x)

  implicit def convertIterablePairIntDouble(x: Iterable[(Int,Double)]): IterablePairIntDoublePlot =
    new IterablePairIntDoublePlot(x)

  implicit def convertIterablePairIntInt(x: Iterable[(Int,Int)]): IterablePairIntIntPlot =
    new IterablePairIntIntPlot(x)

  implicit def convertIterablePairStringDouble(x: Iterable[(String,Double)]): IterablePairStringDoublePlot =
    new IterablePairStringDoublePlot(x)

  implicit def convertIterablePairStringInt(x: Iterable[(String,Int)]): IterablePairStringIntPlot =
    new IterablePairStringIntPlot(x)

  implicit def convertIterablePairDateDouble(x: Iterable[(Date,Double)]): IterablePairDateDoublePlot =
    new IterablePairDateDoublePlot(x)

  implicit def convertIterablePairDateInt(x: Iterable[(Date,Int)]): IterablePairDateIntPlot =
    new IterablePairDateIntPlot(x)

  class IterablePlot[T](iterable: Iterable[T]) {
    private val plotBuilder = new PlotBuilder[T](iterable)

    /**
      * Start building new plot with custom settings
      */
    def buildPlot(): PlotBuilder[T] = plotBuilder

    /**
      * Plot bar chart
      *
      * @param values function mapping element of collection to values
      */
    def plotBar(values: T => YAxisValueType): Unit = plotBuilder.bar(values).display()

    /**
      * Plot bar chart
      *
      * @param x function mapping element of collection to x values
      * @param y function mapping element of collection to y values
      */
    def plotBar(x: T => XAxisValueType, y: T => YAxisValueType): Unit = plotBuilder.bar(x, y).display()

    /**
      * Plot scatter chart
      *
      * @param x function mapping element of collection to x values
      * @param y function mapping element of collection to y values
      */
    def plotScatter(x: T => XAxisValueType, y: T => YAxisValueType): Unit = plotBuilder.scatter(x, y).display()

    /**
      * Plot line chart
      *
      * @param values function mapping element of collection to values
      */
    def plotLine(values: T => YAxisValueType): Unit = plotBuilder.line(values).display()

    /**
      * Plot line chart
      *
      * @param x function mapping element of collection to x values
      * @param y function mapping element of collection to y values
      */
    def plotLine(x: T => XAxisValueType, y: T => YAxisValueType): Unit = plotBuilder.line(x, y).display()

    /**
      * Plot area chart
      *
      * @param values function mapping element of collection to values
      */
    def plotArea(values: T => YAxisValueType): Unit = plotBuilder.area(values).display()

    /**
      * Plot area chart
      *
      * @param x function mapping element of collection to x values
      * @param y function mapping element of collection to y values
      */
    def plotArea(x: T => XAxisValueType, y: T => YAxisValueType): Unit = plotBuilder.area(x, y).display()

    /**
      * Plot histogram chart
      *
      * @param values function mapping element of collection to values
      * @param bins number of bins for histogram
      */
    def plotHistogram(values: T => Double, bins: Int=PlotBuilder.DefaultHistogramBins): Unit =
      plotBuilder.histogram(values, bins).display()

    /**
      * Plot histogram chart
      *
      * @param values function mapping element of collection to values
      */
    def plotHistogramForCategories(values: T => String): Unit =
      plotBuilder.histogramForCategories(values).display()

    /**
      * Plot pie chart
      *
      * @param values function mapping element of collection to values
      */
    def plotPie(values: T => YAxisValueType): Unit = plotBuilder.pie(values).display()

    /**
      * Plot pie chart
      *
      * @param x function mapping element of collection to x values
      * @param y function mapping element of collection to y values
      */
    def plotPie(x: T => XAxisValueType, y: T => YAxisValueType): Unit = plotBuilder.pie(x, y).display()

  }

  class IterableDoublePlot(iterable: Iterable[Double]) {
    private val plotBuilder = new PlotBuilderForDouble(iterable)

    /**
      * Start building new plot with custom settings
      */
    def buildPlot(): PlotBuilderForDouble = plotBuilder

    /**
      * Plot bar chart
      */
    def plotBar(): Unit = plotBuilder.bar().display()

    /**
      * Plot bar chart
      *
      * @param device device that should be used to display plot
      */
    def plotBar(device: Device): Unit = plotBuilder.bar().display(device)

    /**
      * Plot histogram chart
      *
      * @param bins number of bins for histogram
      */
    def plotHistogram(bins: Int=PlotBuilder.DefaultHistogramBins): Unit =
      plotBuilder.histogram(x => x, bins).display()

    /**
      * Plot histogram chart
      *
      * @param bins number of bins for histogram
      * @param device device that should be used to display plot
      */
    def plotHistogram(device: Device, bins: Int): Unit =
      plotBuilder.histogram(x => x, bins).display(device)

    /**
      * Plot pie chart
      */
    def plotPie(): Unit = plotBuilder.pie().display()

    /**
      * Plot bar chart
      *
      * @param device device that should be used to display plot
      */
    def plotPie(device: Device): Unit = plotBuilder.pie().display(device)

    /**
      * Plot line chart
      */
    def plotLine(): Unit = plotBuilder.line().display()

    /**
      * Plot line chart
      *
      * @param device device that should be used to display plot
      */
    def plotLine(device: Device): Unit = plotBuilder.line().display(device)

    /**
      * Plot area chart
      */
    def plotArea(): Unit = plotBuilder.area().display()

    /**
      * Plot area chart
      *
      * @param device device that should be used to display plot
      */
    def plotArea(device: Device): Unit = plotBuilder.area().display(device)

  }

  class IterableIntPlot(iterable: Iterable[Int]) {
    private val plotBuilder = new PlotBuilderForInt(iterable)

    /**
      * Start building new plot with custom settings
      */
    def buildPlot(): PlotBuilderForInt = plotBuilder

    /**
      * Plot bar chart
      */
    def plotBar(): Unit = plotBuilder.bar().display()

    /**
      * Plot bar chart
      *
      * @param device device that should be used to display plot
      */
    def plotBar(device: Device): Unit = plotBuilder.bar().display(device)

    /**
      * Plot histogram chart
      *
      * @param bins number of bins for histogram
      */
    def plotHistogram(bins: Int=PlotBuilder.DefaultHistogramBins): Unit =
      plotBuilder.histogram(x => x, bins).display()

    /**
      * Plot pie chart
      */
    def plotPie(): Unit = plotBuilder.pie().display()

    /**
      * Plot pie chart
      *
      * @param device device that should be used to display plot
      */
    def plotPie(device: Device): Unit = plotBuilder.pie().display(device)

    /**
      * Plot line chart
      */
    def plotLine(): Unit = plotBuilder.line().display()

    /**
      * Plot line chart
      *
      * @param device device that should be used to display plot
      */
    def plotLine(device: Device): Unit = plotBuilder.line().display(device)

    /**
      * Plot area chart
      */
    def plotArea(): Unit = plotBuilder.area().display()

    /**
      * Plot area chart
      *
      * @param device device that should be used to display plot
      */
    def plotArea(device: Device): Unit = plotBuilder.area().display(device)

  }

  trait IterablePairOfXYAxis {
    protected def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)]

    private val plotBuilder = new PlotBuilderForPairOfXYAxis(iterablePairOfXYAxis)

    /**
      * Start building new plot with custom settings
      */
    def buildPlot(): PlotBuilderForPairOfXYAxis = plotBuilder

    /**
      * Plot bar chart
      */
    def plotBar(): Unit = plotBuilder.bar().display()

    /**
      * Plot bar chart
      *
      * @param device device that should be used to display plot
      */
    def plotBar(device: Device): Unit = plotBuilder.bar().display(device)

    /**
      * Plot scatter chart
      */
    def plotScatter(): Unit = plotBuilder.scatter().display()

    /**
      * Plot scatter chart
      *
      * @param device device that should be used to display plot
      */
    def plotScatter(device: Device): Unit = plotBuilder.scatter().display(device)

    /**
      * Plot line chart
      */
    def plotLine(): Unit = plotBuilder.line().display()

    /**
      * Plot line chart
      * @param device device that should be used to display plot
      */
    def plotLine(device: Device): Unit = plotBuilder.line().display(device)

    /**
      * Plot pie chart
      */
    def plotPie(): Unit = plotBuilder.pie().display()

    /**
      * Plot pie chart
      *
      * @param device device that should be used to display plot
      */
    def plotPie(device: Device): Unit = plotBuilder.pie().display(device)


    /**
      * Plot area chart
      */
    def plotArea(): Unit = plotBuilder.area().display()

    /**
      * Plot area chart
      * @param device device that should be used to display plot
      */
    def plotArea(device: Device): Unit = plotBuilder.area().display(device)

  }

  class IterablePairDoubleDoublePlot(iterable: Iterable[(Double, Double)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeDouble(x._1), new YAxisValueTypeDouble(x._2)))
  }

  class IterablePairDoubleIntPlot(iterable: Iterable[(Double, Int)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeDouble(x._1), new YAxisValueTypeInt(x._2)))
  }

  class IterablePairIntDoublePlot(iterable: Iterable[(Int, Double)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeInt(x._1), new YAxisValueTypeDouble(x._2)))
  }

  class IterablePairIntIntPlot(iterable: Iterable[(Int, Int)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeInt(x._1), new YAxisValueTypeInt(x._2)))
  }

  class IterablePairStringDoublePlot(iterable: Iterable[(String, Double)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeString(x._1), new YAxisValueTypeDouble(x._2)))
  }

  class IterablePairStringIntPlot(iterable: Iterable[(String, Int)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeString(x._1), new YAxisValueTypeInt(x._2)))
  }

  class IterablePairDateDoublePlot(iterable: Iterable[(Date, Double)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeDate(x._1), new YAxisValueTypeDouble(x._2)))
  }

  class IterablePairDateIntPlot(iterable: Iterable[(Date, Int)]) extends IterablePairOfXYAxis {
    override def iterablePairOfXYAxis: Iterable[(XAxisValueType, YAxisValueType)] =
      iterable.map(x => (new XAxisValueTypeDate(x._1), new YAxisValueTypeInt(x._2)))
  }

}
