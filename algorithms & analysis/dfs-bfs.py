from collections import defaultdict

class Graph:
    def __init__(self):
        self.nodes = set()
        self.edges = defaultdict(set)
        
    def addNode(self, node):
        self.nodes.add(node)
    
    def addEdge(self, from_node, to_node):
        self.addNode(from_node)
        self.addNode(to_node)
        self.edges[from_node].add(to_node)

    def search(self, algo, start, end):
        algo.start_at(start)
        curr_node = start
        while curr_node != end:
            yield curr_node
            algo.add_neighbors(self.edges[curr_node])
            try:
                curr_node = algo.next_to_search()
            except:
                break
class BFS:
    def __init__(self):
        self.queue = []
        self.visited = set()
    
    def start_at(self, node):
        self.queue.append(node)
        self.visited.add(node)

    def add_neighbors(self, neighbors):
        to_add = [
            node 
            for node in neighbors 
            if node not in self.visited
        ]

        self.queue += to_add
        self.visited.update(to_add)

    def next_to_search(self):
        return self.queue.pop(0)

class DFS:
    def __init__(self):
        self.queue = []
        self.visited = set()
    
    def start_at(self, node):
        self.queue.append(node)
        self.visited.add(node)

    def add_neighbors(self, neighbors):
        for to_add in neighbors:
            if to_add not in self.visited:
                self.queue = [to_add] + self.queue
                self.visited.add(to_add)
                return

    def next_to_search(self):
        return self.queue.pop(0)

if __name__ == '__main__':
    graph = Graph()
    graph.addEdge(1, 2)
    graph.addEdge(2, 3)
    graph.addEdge(2, 4)
    graph.addEdge(3, 5)
    graph.addEdge(4, 5)
    graph.addEdge(5, 1)

    dfs = list(graph.search(DFS(), 1, 5))
    bfs = list(graph.search(BFS(), 1, 5))

    print('dfs:', dfs)
    print('bfs:', bfs)