package edu.agh.neurons.scala

import scala.collection.JavaConversions._
import io.Source

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 05.04.13
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */
object Main extends App {

  val split = " +"
  val file :: ans :: _ = args.toList
  val net = (new NetworkBuilder).createNetwork(file)
  Source.fromFile(ans).getLines().foreach {
    line => {
      val ans :: inputs = line.split(split).toList.map(_.toDouble: java.lang.Double)
      println("for: %s got %s expecting: %s".format(
        inputs.mkString("[", ", ", "]"),
        net.compute(inputs),
        ans
      ))
    }
  }
}
