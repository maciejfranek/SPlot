package com.datawizards.splot.examples.aggregations

import com.datawizards.splot.api.implicits._
import com.datawizards.splot.examples._
import com.datawizards.splot.functions._

object BarChartWithAggregations extends App {

  people
    .buildPlot()
    .barWithAggregations(_.education, count())
    .display()

  people
    .buildPlot()
    .barWithAggregations(_.country, mean(_.income))
    .display()
}
