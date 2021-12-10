import api.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2
{
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file)
    {
        DirectedWeightedGraph ans = new DirectedWeightedGraph_Class(json_file);
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file)
    {
        DirectedWeightedGraphAlgorithms ans = new DirectedWeightedGraphAlgorithms_Class(json_file);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = new DirectedWeightedGraphAlgorithms_Class(json_file);
        //Graph_UI ui = new Graph_UI(alg);
    }
}
