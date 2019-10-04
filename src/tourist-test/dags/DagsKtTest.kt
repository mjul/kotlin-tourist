package dags

import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.specs.StringSpec
import tourist.dags.DAG
import tourist.dags.Edge
import tourist.dags.Vertex

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
