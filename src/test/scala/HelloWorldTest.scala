import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * @author syamantak
 */
class HelloWorldTest {

  @Test
  def testSomething(): Unit = {
    assertEquals(1, 1)
  }

  def max(x: Int, y: Int ) = if (x > y) x else y

  @Test
  def testMax() {
    assertEquals(max(1, 3), 3)
    assertEquals(max(1, -3), 1)
    assertEquals(max(3, 3), 3)
    assertEquals(max(4, 3), 4)
    assertEquals(max(5, 3), 5)
  }

  def v(x: Int) = List(x - 1, x, x + 1)

  @Test
  def testIteration(): Unit = {
    val list: List[Int] = List(1, 2, 3, 4)
    println("Basic list")
    list.foreach(println)

    println("Applying map")
    val list1: List[Int] = list.map(x => x*2)
    list1.foreach(println)

    println("Applying map => v")
    val listOfList: List[List[Int]] = list.map(x => v(x))
    listOfList.foreach(x => x.foreach(println))

    println("Applying flatMap => v")
    list.flatMap(x => v(x)).foreach(println)
  }

  @Test
  def testMap(): Unit = {
    println("testMap : flatMap")
    val map: Map[Int, Int] = Map(1 -> 2, 2 -> 4, 3 -> 6)
    map.toList.flatMap(x => List(x._1, x._2)).foreach(println(_))

    println("testMap : mapValues")
    map.mapValues(_*2).foreach(e => println(e._1 + ":" + e._2))
  }
}
