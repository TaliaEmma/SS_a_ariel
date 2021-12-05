package api;

import java.io.IOException;

public class check
{
    public static void main(String[] args) throws IOException {

        DirectedWeightedGraph graph = new DirectedWeightedGraph_Class("Ex2/data/G1.json");
        DirectedWeightedGraphAlgorithms a = new DirectedWeightedGraphAlgorithms_Class();

        a.init(graph);
        System.out.println(a.center().getKey());
        System.out.println(a.load("Ex2/data/G2.json"));
        System.out.println(a.center().getKey());
        System.out.println(a.load("Ex2/data/G3.json"));
        System.out.println(a.center().getKey());



    }
}
