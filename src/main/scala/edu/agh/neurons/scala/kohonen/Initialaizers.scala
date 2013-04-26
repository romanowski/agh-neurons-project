package edu.agh.neurons.scala.kohonen

import neighbours.NeighbourBuilder

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 26.04.13
 * Time: 01:33
 * To change this template use File | Settings | File Templates.
 */
object Initialaizers {

  def initNetwork(genFun: Int => Double, distFunc: KohonenNetwork.DistFun)(amount: Int, inputs: Int, nBuilder: NeighbourBuilder) = {
    KohonenNetwork(distFunc, nBuilder.buildNeighbour((1 to amount).map(_ => Neuron((1 to inputs).map(genFun), Nil))))
  }

}
