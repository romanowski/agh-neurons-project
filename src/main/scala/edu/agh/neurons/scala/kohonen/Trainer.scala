package edu.agh.neurons.scala.kohonen

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 26.04.13
 * Time: 01:18
 * To change this template use File | Settings | File Templates.
 */

import KohonenNetwork._

case class Params(toEpoch: Int, fMove: Double, nMove: Double)

object Trainer {

  def train(minError: Double,
            points: Seq[Point],
            params: Seq[Params],
            errorFunc: Seq[Double] => Double = _.max)(
             network: KohonenNetwork) = {

    val sortedParams = params.sortBy(_.toEpoch)

    def epoch(params: Params): Double = {
      errorFunc(points.map(network.adjustTo(params.fMove, params.nMove)))
    }

    val epochNr = sortedParams.foldLeft(0) {
      case (epochNr, params) =>
        (epochNr to params.toEpoch).dropWhile {
          _ => epoch(params) > minError
        }.headOption.getOrElse(params.toEpoch)
    }

    epochNr -> errorFunc(points.map(p => network.distFunc(p)(network.findBest(p))))
  }


}
