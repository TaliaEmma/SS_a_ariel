# SS_a_ariel

ideas and information were taken from the following websites:
* https://www.geeksforgeeks.org/dijkstras-algorithm-for-adjacency-list-representation-greedy-algo-8/?ref=lbp
* https://www.geeksforgeeks.org/java-program-for-dijkstras-shortest-path-algorithm-greedy-algo-7/
* https://www.techiedelight.com/check-given-graph-strongly-connected-not/
* https://favtutor.com/blogs/depth-first-search-java


graph class:
* in the graph class we created two constructors (copy constructor and one that build the graph from given json file name).
* we used hash map to store the nodes information (key is the node id and the value is the nodeData itself).
* we used hash map to store the Edges information (key is the String made from the edge src id, comma and the edge dest id. The value is the edgeData).
* integer MC will be use to count changes in the graph.
* we used hash map inside hash map to store the out edges inforamtion (first key is the src id and the second is the dest id).
* we used hash map inside hash map to store the in edges inforamtion (first key is the src id and the second is the dest id).
* we used hash map to store changes of specific node outEdges (key is node id and value is amout of changes we made in edges coming out of the node).

* in order to remove a node from the graph we will use the edges iterator who will go over all the edges and remove them if the src or dest values mach with the selected node.
then we will delete the node from the hash map by using its id number.

* in order to remove an edge we will remove it from the 3 hash map in the graph. (edges hash map, out edges hash map and in edges hash map).


algorithm class:
* in the algo class we have 1 constructer that build the graph from a given json file name.
* the isConnected function use DFS algorithm V times (every time we change the begining indes to a new one in order to check its connectivity with the other nodes)
* the shortestPathDist function uses Dijkstra’s algorithm. The idea is to traverse all vertices of the graph by using BFS algo consept and use a Min Heap to store the vertices not yet included. Min Heap is used as a priority queue to get the minimum distance vertex from set of not yet included vertices. Time complexity of BFS is o(E+V) and complexity of operations like extract-min and decrease-key value is O(LogV) for Min Heap. therefore, overall time complexity is O(E+V)*O(LogV) which is O((E+V)*LogV) = O(ELogV). all the found distances are stored in a hash map (key is the node id). the function will return the requested data from the hash map (by using the dest id as key).
* the second shortestPathDist use the same idea as before but here we alse store in a hash map for every node we go to, the node we were in before (so if we are going in the edge u -> v we will put v as key and u as the value). in the end we will create a list from the hash map values (starting with the dest id we will place every NodeData as the new first object of the list).
* center function will use the same algorithm as we used before but now we will return for every node the max distance found (max distance from the list of min distances given to us by the Dijkstra's algorithm). after that we will find the node that returned the lowest distance and return it (the node).
* tsp function will work as followed: for every node in the given list we will treat as the src node and find for each the best path to reach all other nodes in the list. after that we will simply choose the best path from the pathes we created and return it.
in order to find the path from a selected src node we will find the node from the list with the shortest distance from our current src (we will use shortestPathDist function).
after that we will assume the new node is the next in the list so we will add it to the temp list we created for the selected src node. after that we will find the node from the list with the shortest distance from the node we just add to the list. we will do that again and again until we reached all the nodes from the list (we will save the information on hash map with node id as key and boolean as value). 
in case we cant reach one of the nodes from the given list we will return null.
* the save functuon will create new json file and insert there all the graph data (we will insert the graph data in a JSONObject and inside it we will have two JSONArrarys with the nodes and the edges (each node and edge is a JSONObject of itself).
* the load function use the graph pre made constructer that works with a given json file name.
* the checkPath function is used to rerieve the list from the hash map (second shortestPathDist function).
* the DFS function uses recursive approach to a classic DFS algorithm.
* the dijkstra function operate the dijkstra algoritm we use in the center function.
* the findNextNode function is udes in the tsp function. we will call it every time we want to know the next node to go to from our current stand.
* the function listWeight is used in the tsp function as well. it will provide us the weight information of a given Node list (go over the list and return total weight).



Time results:

* 1000 nodes:

  1000 nodes load time in millisecond: 163
  
  The graph isConnected: true
  
  1000 nodes isConnected time in millisecond: 34
  
  ShortestPathDist from 6 to 300: 206.50674591631812
  
  1000 nodes shortestPathDist(weight) time in millisecond: 18
  
  ShortestPath from 6 to 300: 6, 300, 
  
  1000 nodes shortestPathDist(list) time in millisecond: 3
  
  Center is: 362
  
  1000 nodes center time in millisecond: 823
  
  Check tsp with the list: 6, 300, 
  
  The returned list is: 6, 300, 
  
  1000 nodes tsp time in millisecond: 18


* 10000 nodes:

  10000 nodes load time in millisecond: 566
  
  The graph isConnected: true
  
  10000 nodes isConnected time in millisecond: 10337
  
  ShortestPathDist from 6 to 300: 1157.2836333849277
  
  10000 nodes shortestPathDist(weight) time in millisecond: 49
  
  ShortestPath from 6 to 300: 6, 9204, 552, 6982, 2913, 5185, 3290, 1819, 4131, 3352, 3275, 1619, 300
  
  10000 nodes shortestPathDist(list) time in millisecond: 27
  
  Center is: 3846
  
  10000 nodes center time in millisecond: 150595
  
  Check tsp with the list: 6, 9204, 552, 6982, 2913, 5185, 3290, 1819, 4131, 3352, 3275, 1619, 300
  
  The returned list is: 6, 9204, 552, 6982, 2913, 5185, 3290, 1819, 4131, 3352, 3275, 1619, 300
  
  10000 nodes tsp time in millisecond: 26098


* 100000 nodes:

  100000 nodes load time in millisecond: 8778
  
  The graph isConnected: true

  100000 nodes isConnected time in millisecond: 123317
  
  ShortestPathDist from 6 to 300: 652.8947330353053
  
  100000 nodes shortestPathDist(weight) time in millisecond: 1966
  
  ShortestPath from 6 to 300: 6, 13858, 55451, 34862, 95986, 45065, 98369, 25679, 300
  
  100000 nodes shortestPathDist(list) time in millisecond: 572
  
  Check tsp with the list: 6, 13858, 55451, 34862, 95986, 45065, 98369, 25679, 300
  
  The returned list is: 6, 13858, 55451, 34862, 95986, 45065, 98369, 25679, 300
  
  100000 nodes tsp time in millisecond: 617668


