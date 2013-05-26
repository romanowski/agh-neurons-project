package edu.agh.neurons.scala.cunterPropagation

import edu.agh.neurons.scala.kohonen.KohonenNetwork
import edu.agh.neurons.scala._


/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 24.05.13
 * Time: 18:58
 * To change this template use File | Settings | File Templates.
 */
case class CPNetwork(kochNetwork: KohonenNetwork, outLayer: Layer) {

  def output(in: Seq[Double]): Seq[Double] = {
    val out = kochNetwork.output(in)
    outLayer.neurons.map(_.activate(out))
  }


  def train(fMove: Double, nMove: Double, nAdj: Double)(in: Seq[Double], out: Seq[Double]) {
    val kochOut = kochNetwork.mapOut(kochNetwork.findBestAndAdjust(fMove, nMove)(in))

    outLayer.neurons.zip(out).foreach {
      case (n, corr) =>
        val current = n.activate(kochOut)
        n.weights = n.weights.zip(kochOut).map {
          case (w, koch) if koch > 0 =>
            w + (corr - current) * nAdj
          case (w, koch) => w
        }
    }
  }
}
