package Test;

import api.*;

import org.junit.jupiter.api.Test;
import org.json.JSONException;
import java.io.IOException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraph_ClassTest {

    Node n = new Node(1,1,1,1);

    public static DirectedWeightedGraph_Class e()
    {
        DirectedWeightedGraph_Class D= null;

    try
    {
        D = new DirectedWeightedGraph_Class("G1");
        D.getNode(1);

    } catch (IOException exception)
    {

    }
    return D;
}
    @Test
    void getNode() {
        //e();
         //assertEquals(e().getNode(1),2);
    }

    @Test
    void getEdge() {
    }

    @Test
    void addNode() {
    }

    @Test
    void connect() {
    }

    @Test
    void nodeIter() {
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}