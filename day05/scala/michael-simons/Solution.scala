// tag::ceremony[]
object Solution extends App {
  // end::ceremony[]

  // tag::baseOperation[]
  abstract class Operation { // <1>
    def numargs(): Int // <2>

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit): Int
  }
  // end::baseOperation[]

  // tag::exitOperation[]
  case class Exit() extends Operation {
    def numargs(): Int = 0

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit): Int = 0
  }
  // end::exitOperation[]

  // tag::someTraits[]
  trait HasOpcode {
    def opcode(): Int // <1>
  }

  trait NumericOperation extends HasOpcode {
    val ops = Seq[(Int, Int) => Int](_ + _, _ * _) // <2>

    def numargs(): Int = 3 // <3>

    def opcode(): Int

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit): Int = {
      writer(args(2)._1, ops(opcode() - 1)(reader(args(0)), reader(args(1)))) // <4>
      pos + numargs() + 1
    }
  }
  // end::someTraits[]

  // tag::concreteOps[]
  case class Add() extends Operation with NumericOperation {

    def opcode() = 1
  }
  // end::concreteOps[]

  case class Mul() extends Operation with NumericOperation {

    def opcode() = 2
  }

  case class ReadInto() extends Operation {

    def numargs(): Int = 1

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit): Int = {
      print("Input> ")
      val newValue = io.StdIn.readInt
      println(newValue)
      writer(args(0)._1, newValue)
      pos + numargs() + 1
    }
  }

  case class Echo() extends Operation {

    def numargs(): Int = 1

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit): Int = {
      println(reader(args(0)))
      pos + numargs() + 1
    }
  }

  trait ConditionalJump extends HasOpcode {
    val cond = Seq[(Int) => Boolean](_ != 0, _ == 0)

    def numargs(): Int = 2

    def opcode(): Int

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit): Int = {
      val q = reader(args(0))
      if (cond(opcode() % 5)(q)) {
        reader(args(1))
      } else {
        pos + numargs() + 1
      }
    }
  }

  case class JumpIfTrue() extends Operation with ConditionalJump {
    def opcode() = 5
  }

  case class JumpIfFalse() extends Operation with ConditionalJump {
    def opcode() = 6
  }

  trait Comparison extends HasOpcode {
    val comp = Seq[(Int, Int) => Boolean](_ < _, _ == _)

    def numargs(): Int = 3

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit): Int = {
      val v1 = reader(args(0))
      val v2 = reader(args(1))
      writer(args(2)._1, if (comp(opcode() % 7)(v1, v2)) 1 else 0)
      pos + numargs() + 1
    }
  }

  case class IsLessThan() extends Operation with Comparison {
    def opcode() = 7
  }

  case class IsEqual() extends Operation with Comparison {
    def opcode() = 8
  }

  // tag::companionToOperation[]
  object Operation {
    def apply(pos: Int, memory: Array[Int]) = memory(pos) % 100 match {
      case 1 => Add()
      case 2 => Mul()
      case 3 => ReadInto()
      case 4 => Echo()
      // end::companionToOperation[]
      case 5 => JumpIfTrue()
      case 6 => JumpIfFalse()
      case 7 => IsLessThan()
      case 8 => IsEqual()
      // tag::companionToOperation[]
      case 99 => Exit()
      case opcode@_ => throw new Exception(s"Invalid opcode $opcode")
    }
  }

  // end::companionToOperation[]

  // tag::readerAndWriter1[]
  def read(memory: Array[Int])(p: Int, mode: Int) =
    if (mode == 0) memory(p) else p // <1>
  def write(memory: Array[Int])(p: Int, v: Int) =
    memory(p) = v
  // end::readerAndWriter1[]

  // tag::extractModes[]
  def extractModes(of: Int) = for (i <- 3 to 5) yield
    of % Math.pow(10, i).toInt / Math.pow(10, i - 1).toInt
  // end::extractModes[]

  // tag::puttingItTogether[]
  def run(memory: Array[Int], pos: Int = 0): Unit = {

    val operation = Operation(pos, memory)
    val modes = extractModes(memory(pos))
    val args = (1 to operation.numargs).map(i => (memory(pos + i), modes(i - 1))) // <1>

    // tag::readerAndWriter2[]

    val reader = (read(memory) _).tupled // <2>
    val writer = write(memory) _ // <3>
    // end::readerAndWriter2[]

    operation match { // <4>
      case Exit() => Nil
      case op@_ => {
        val next = op.execute(pos, args, reader, writer)
        run(memory, next)
      }
    }
  }
  // end::puttingItTogether[]

  // tag::input[]
  val input = io.Source.fromFile("input.txt")
  val instructions = input.getLines().flatMap( _.split(",")).map(_.trim).map(_.toInt).toSeq
  input.close
  // end::input[]

  // tag::runIt[]
  println("For star one, enter 1, for start two, 5")
  run(instructions.toArray)
  // end::runIt[]
  // tag::ceremony[]
}

// end::ceremony[]