package dags

import io.kotlintest.matchers.collections.*
import io.kotlintest.specs.StringSpec
import tourist.dags.DAG
import tourist.dags.Edge
import tourist.dags.Vertex
import tourist.dags.verticesDepthFirstFrom

class DagsStringSpec : StringSpec(
    {
        "An empty DAG can be constructed" {
            val sut = DAG.emptyDAG<String, String>()
            sut.edges.shouldBeEmpty()
            sut.vertices.shouldBeEmpty()
        }
        "A DAG with two vertices and one edge" {
            val a = Vertex("A")
            val b = Vertex("B")
            val ab = Edge(a, b, "is-connected-to")
            val sut = DAG(setOf(a, b), setOf(ab))

            sut.vertices shouldHaveSize 2
            sut.vertices shouldContain a
            sut.vertices shouldContain b

            sut.edges shouldHaveSize 1
            sut.edges shouldContain ab
        }
        "A DAG with multiple vertices and edges" {
            val a = Vertex("A")
            val b = Vertex("B")
            val c = Vertex("C")
            val ab = Edge(a, b, "is-connected-to")
            val bc = Edge(b, c, "is-connected-to")
            val ac = Edge(a, c, "is-connected-to")
            val sut = DAG(setOf(a, b, c), setOf(ab, bc, ac))

            sut.vertices shouldHaveSize 3
            sut.vertices shouldContain a
            sut.vertices shouldContain b
            sut.vertices shouldContain c

            sut.edges shouldHaveSize 3
            sut.edges shouldContain ab
            sut.edges shouldContain bc
            sut.edges shouldContain ac
        }
    })


val jylland = Vertex("Jylland")
val fyn = Vertex("Fyn")
val sjaelland = Vertex("Sjælland")
val bridges = setOf(
    Edge(jylland, fyn, "Lillebæltsbroen"),
    Edge(jylland, fyn, "Den gamle Lillebæltsbro"),
    Edge(fyn, sjaelland, "Storebæltsbroen")
)
val bridgesDAG = DAG(setOf(jylland, fyn, sjaelland), bridges)

object Vertices {
    val A = Vertex("A")
    val B = Vertex("B")
    val C = Vertex("C")
    val D = Vertex("D")
}

class DAGFactoryEmptyDAG : StringSpec(
    {
        "emptyDAG should be valid" {
            val actual = DAG.emptyDAG<String, String>()
            actual.edges.shouldBeEmpty()
            actual.vertices.shouldBeEmpty()
        }
    }
)

class DAGFactoryFromEdges : StringSpec(
    {
        "fromEdges of empty set should be valid" {
            val actual = DAG.fromEdges<String, String>(emptySet())
            actual.vertices.shouldBeEmpty()
            actual.edges.shouldBeEmpty()
        }
        "fromEdges of multiple edges from a vertex" {
            val ab = Edge(Vertices.A, Vertices.B, "is-a")
            val ac = Edge(Vertices.A, Vertices.C, "is-a")
            val actual = DAG.fromEdges(setOf(ab, ac))
            actual.vertices shouldContainExactlyInAnyOrder listOf(Vertices.A, Vertices.B, Vertices.C)
            actual.edges shouldContainExactlyInAnyOrder listOf(ab, ac)
        }
        "fromEdges of multiple edges to a vertex" {
            val ac = Edge(Vertices.A, Vertices.C, "is-a")
            val bc = Edge(Vertices.B, Vertices.C, "is-a")
            val actual = DAG.fromEdges(setOf(ac, bc))
            actual.vertices shouldContainExactlyInAnyOrder listOf(Vertices.A, Vertices.B, Vertices.C)
            actual.edges shouldContainExactlyInAnyOrder listOf(ac, bc)
        }
    }
)

class VerticesDepthFirstFrom : StringSpec(
    {
        "Empty DAG should have empty connected vertices" {
            val sut = DAG.emptyDAG<String, String>()

            val actual = verticesDepthFirstFrom(sut, Vertex("A"))

            val actualList = actual.toList()
            actualList.shouldBeEmpty()
        }
        "A DAG should have single element vertices {A}" {
            val a = Vertex("A")
            val sut = DAG<String, String>(setOf(a), emptySet())

            val actual = verticesDepthFirstFrom(sut, a).toList()

            actual shouldHaveSize 1
            actual shouldHaveSingleElement a

        }
        "A->B DAG should have two vertices {A,B}" {
            val ab = Edge(Vertices.A, Vertices.B, "is-connected-to")
            val sut = DAG.fromEdges(setOf(ab))

            val actual = verticesDepthFirstFrom(sut, Vertices.A).toList()

            actual shouldContainExactly listOf(Vertices.A, Vertices.B)
        }
        "A->B->C DAG should have vertices {A,B,C}" {
            val ab = Edge(Vertices.A, Vertices.B, "is-a")
            val bc = Edge(Vertices.B, Vertices.C, "is-a")
            val sut = DAG.fromEdges(setOf(ab, bc))

            val actual = verticesDepthFirstFrom(sut, Vertices.A).toList()

            actual shouldContainExactly listOf(Vertices.A, Vertices.B, Vertices.C)
        }
        "A->B->C A->D DAG should have vertices {A,B,C,D}" {
            val ab = Edge(Vertices.A, Vertices.B, "is-a")
            val bc = Edge(Vertices.B, Vertices.C, "is-a")
            val ad = Edge(Vertices.A, Vertices.D, "is-a")
            val sut = DAG.fromEdges(setOf(ab, bc, ad))

            val actual = verticesDepthFirstFrom(sut, Vertices.A).toList()

            actual shouldContainExactly listOf(Vertices.A, Vertices.B, Vertices.C, Vertices.D)
        }
    }
)
