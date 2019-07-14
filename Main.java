import java.util.*;


public class Main {
    
    
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        int numCells = scan.nextInt();
        int numExitCell = scan.nextInt() - 1;
        int timer = scan.nextInt();
        int numConnections = scan.nextInt();
        
        boolean[] visited = new boolean[numCells];
        int[] cells = new int[numCells];
        
        List<ArrayList<Node>> myList = new ArrayList<ArrayList<Node>>();
        for(int i = 0; i < numCells; i++) {
            myList.add(new ArrayList<Node>());
        }
        
        for(int i = 0; i < numConnections; i++) {
            int a = scan.nextInt() - 1;
            int b = scan.nextInt() - 1;
            int weight = scan.nextInt();
            myList.get(b).add(new Node(a, weight));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>(numCells, new Node());
        for(int i = 0; i < numCells; i++) {
            visited[i] = false;
            cells[i] = Integer.MAX_VALUE;
        }
        
        pq.add(new Node(numExitCell, 0));
        cells[numExitCell] = 0;
        
        while(!pq.isEmpty()) {
            Node current = pq.remove();
            for(int c = 0; c < myList.get(current.i).size(); c++) {
                Node destination = myList.get(current.i).get(c);
                if(!visited[destination.i] && cells[current.i] + destination.weight < cells[destination.i] && destination.i != current.i) {
                    cells[destination.i] = cells[current.i] + destination.weight;
                    pq.add(new Node(destination.i, cells[destination.i]));
                }
            }
            visited[current.i] = true;
        }
     int numMice = 0;
     for(int i = 0; i < numCells; i++) {
         if(cells[i] <= timer) 
             numMice++;
     }
     System.out.println(numMice);

    }
}
class Node implements Comparator<Node>{
    public int i;
    public int weight;

   public Node() {
       
   }
    
    public Node(int index, int weight){
        this.i = index;
        this.weight = weight;
    }

    public int compare(Node node1, Node node2){
        if (node1.weight > node2.weight) return 1;
        if (node1.weight < node2.weight) return -1;
        return 0;
    }
}
