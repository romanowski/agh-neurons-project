package edu.agh.neurons.scala


case class Network(layers: Seq[Layer]) {

  lazy val inLayer: Layer = layers.head
  lazy val outLayer: Layer = layers.last

  def compute(inputs: Seq[Double]): Seq[Double] = {
    layers.foldLeft(inputs) {
      case (values, net) =>
        //println(values)
        net.neurons.map {
          neuron =>
            if (values.size != neuron.weights.size) {
              throw new IllegalArgumentException("weight vector size differ form inputs size")
            }
            neuron.activate(values)
        }
    }
  }
}