package tourist.dags

// Lets try to build a simple DAG (directed, acyclic graph)

data class Vertex<TVertexName>(val name: TVertexName)
data class Edge<TVertexName, TEdgeLabel>(
    val from: Vertex<TVertexName>,
    val to: Vertex<TVertexName>,
    val label: TEdgeLabel
)

data class DAG<TVertexName, TEdgeLabel>(
    val vertices: Set<Vertex<TVertexName>>,
    val edges: Set<Edge<TVertexName, TEdgeLabel>>
) {
    companion object {
        fun <TVertexName, TEdgeLabel> emptyDAG(): DAG<TVertexName, TEdgeLabel> {
            return DAG(emptySet(), emptySet())
        }

        fun <TVertexName, TEdgeLabel> fromEdges(edges: Set<Edge<TVertexName, TEdgeLabel>>)
                : DAG<TVertexName, TEdgeLabel> {
            val vertices = edges.flatMap { listOf(it.from, it.to) }.toSet()
            return DAG(vertices, edges)
        }
    }

}

private fun <TVertexName, TEdgeLabel> verticesDepthFirstFrom(
    dag: DAG<TVertexName, TEdgeLabel>,
    startingPoint: Vertex<TVertexName>,
    visited: MutableSet<Vertex<TVertexName>>
): Sequence<Vertex<TVertexName>> {
    return sequence {
        if (dag.vertices.contains(startingPoint)) {
            if (!visited.contains(startingPoint)) {
                yield(startingPoint)
                visited.add(startingPoint)
                val outboundEdges = dag.edges.filter { it.from == startingPoint }
                for (edge in outboundEdges) {
                    yieldAll(verticesDepthFirstFrom(dag, edge.to, visited))
                }
            }
        }
    }
}

fun <TVertexName, TEdgeLabel> verticesDepthFirstFrom(
    dag: DAG<TVertexName, TEdgeLabel>,
    startingPoint: Vertex<TVertexName>
)
        : Sequence<Vertex<TVertexName>> {
    return verticesDepthFirstFrom(dag, startingPoint, mutableSetOf())
}

// If we have no DSL we can build it like so
fun createBridgesManually(): DAG<String, String> {
    val jylland = Vertex("Jylland")
    val fyn = Vertex("Fyn")
    val sjaelland = Vertex("Sjælland")
    val bridges = setOf(
        Edge(jylland, fyn, "Lillebæltsbroen"),
        Edge(jylland, fyn, "Den gamle Lillebæltsbro"),
        Edge(fyn, sjaelland, "Storebæltsbroen")
    )
    return DAG(setOf(jylland, fyn, sjaelland), bridges)
}

// Note how there is no guarantee that the vertex edges are pointing to vertices in the DAG



