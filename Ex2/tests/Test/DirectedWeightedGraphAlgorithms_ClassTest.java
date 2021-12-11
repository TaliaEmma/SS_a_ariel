package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import api.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphAlgorithms_ClassTest {

    DirectedWeightedGraphAlgorithms algo;

    @BeforeEach
    void init()
    {
        algo = new DirectedWeightedGraphAlgorithms_Class("Ex2/data/G1.json");
    }


    @Test
    void copy()
    {
        DirectedWeightedGraph test = algo.copy();
        assert (!test.equals(algo.getGraph()));
        Iterator<NodeData> nodeIter = algo.getGraph().nodeIter();
        while (nodeIter.hasNext())
        {
            int id = nodeIter.next().getKey();
            assert (!algo.getGraph().getNode(id).equals(test.getNode(id)));
        }
        Iterator<EdgeData> edgeIter = algo.getGraph().edgeIter();
        while (edgeIter.hasNext())
        {
            EdgeData e = edgeIter.next();
            assert (!algo.getGraph().getEdge(e.getSrc(), e.getDest()).equals(test.getEdge(e.getSrc(), e.getDest())));
        }
    }

    @Test
    void isConnected()
    {
        boolean test1 = algo.isConnected();
        algo.getGraph().removeNode(1);
        algo.getGraph().removeNode(16);
        boolean test2 = algo.isConnected();
        assert (test2 != test1);
    }


    @Test
    void shortestPathDist()
    {
        double test1 = algo.shortestPathDist(0,5);
        algo.getGraph().connect(0,5,0.0004);
        double test2 = algo.shortestPathDist(0,5);
        assert (test1 > test2);
    }

    @Test
    void shortestPath()
    {
        List<NodeData> test1 = algo.shortestPath(0,5);
        algo.getGraph().connect(0,5,0.0004);
        List<NodeData> test2 = algo.shortestPath(0,5);
        assert (test1.size() > test2.size());
        assert (test1.contains(algo.getGraph().getNode(0)) && test1.contains(algo.getGraph().getNode(5)));
        assert (test2.contains(algo.getGraph().getNode(0)) && test2.contains(algo.getGraph().getNode(5)));
    }

    @Test
    void center()
    {
        NodeData test1 = algo.center();
        algo.getGraph().removeNode(1);
        algo.getGraph().removeNode(16);
        NodeData test2 = algo.center();
        assert (test1 != test2);
        assert (test2 == null);
    }

    @Test
    void tsp()
    {
        List<NodeData> c = new LinkedList<>();
        c.add(algo.getGraph().getNode(0));
        c.add(algo.getGraph().getNode(6));
        c.add(algo.getGraph().getNode(2));
        c.add(algo.getGraph().getNode(11));

        List<NodeData> test = algo.tsp(c);

        for(NodeData node : c)
        {
            assert (test.contains(node));
        }
    }
}