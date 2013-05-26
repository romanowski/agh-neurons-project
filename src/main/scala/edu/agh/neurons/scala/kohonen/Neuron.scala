package edu.agh.neurons.scala.kohonen

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 26.04.13
 * Time: 00:57
 * To change this template use File | Settings | File Templates.
 */
case class Neuron(var weights: Seq[Double], var neighbours: Seq[Neuron], index: Int) {

  def moveTo(dest: Seq[Double], by: Double) {
    require(dest.size == weights.size)
    weights = weights.zip(dest).map {
      case (w, d) =>
        w + by * (d - w)
    }
  }


  override def toString = "Nwuron(%s) weights: %s".format(index, weights.mkString("[", ", ", "]")
    //  neighbours.map(_.index).mkString(", ")
  )

  println(this)
}
