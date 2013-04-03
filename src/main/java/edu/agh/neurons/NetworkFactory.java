package edu.agh.neurons;

/**
 * Created with IntelliJ IDEA.
 * User: krzysiek
 * Date: 03.04.13
 * Time: 20:33
 * To change this template use File | Settings | File Templates.
 */
public abstract class NetworkFactory {

    public abstract Network createNetwork(String confFile);

    public static NetworkFactory getFactory(String factoryName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return (NetworkFactory) NetworkFactory.class.getClassLoader().loadClass(factoryName).newInstance();
    }
}
