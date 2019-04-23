import java.util.HashMap;

public class MCCR {
        public static int MCCR(EdgeWeightedGraph G) {
            if (G.numberOfV() == 0 || G.numberOfV() == 1)
                return 0;
            
            IndexPQ<Integer> pq = new IndexPQ<Integer>(G.numberOfV());
            HashMap<Integer, Integer> S = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> pqCosts = new HashMap<Integer, Integer>();
            int cost = 0;
            pq.insert(0, 0);
            pqCosts.put(0, 0);
            for (int i = 1; i < G.numberOfV(); i++) {
                pq.insert(i, 1000);
                pqCosts.put(i, 1000);
            }
            while (!pq.isEmpty()) {
                Integer v = pq.delMin();
                S.put(v, 0);
                cost += pqCosts.get(v);
                for (Edge e : G.edges(v)) {
                    if (!S.containsKey(e.other(v))) {
                        if (pqCosts.get(e.other(v)) > e.weight()) {
                            pq.decreaseKey(e.other(v), e.weight());
                            pqCosts.put(e.other(v), e.weight());
                        }
                    }
                }
            }
            return cost;
        }

        /* public static void main(String[] args) {
            EdgeWeightedGraph G = new EdgeWeightedGraph(4);
            int[] input = {0, 1, 3,
                    0, 3, 1,
                    0, 2, 5,
                    1, 2, 4,
                    2, 3, 7
            };
            int i = 0;
            while (i <= input.length - 3) {
                Edge newEdge = new Edge(input[i], input[i + 1], input[i + 2]);
                G.addEdge(newEdge);
                i = i + 3;
            }
            System.out.println(MCCR.MCCR(G));
        } */

    }

