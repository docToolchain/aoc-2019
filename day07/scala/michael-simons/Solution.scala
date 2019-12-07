object Solution extends App {

  // tag::newIoClass[]
  class IO(private var input: Seq[Int] = Seq()) {
    def read: Option[Int] = input match {
      case Nil => None
      case l@_ => {
        input = input.tail
        Some(l.head)
      }
    }

    def <<(v: Int) = { // <1>
      input = v +: input
      this
    }
  }

  // end::newIoClass[]

  abstract class Operation {
    def numargs(): Int

    def execute(ptr: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int
  }

  case class Exit() extends Operation {
    def numargs(): Int = 0

    def execute(ptr: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = 0
  }

  trait HasOpcode {
    def opcode(): Int
  }

  trait NumericOperation extends HasOpcode {
    val ops = Seq[(Int, Int) => Int](_ + _, _ * _)

    def numargs(): Int = 3

    def opcode(): Int

    def execute(ptr: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = {
      writer(args(2)._1, ops(opcode() - 1)(reader(args(0)), reader(args(1))))
      ptr + numargs() + 1
    }
  }

  case class Add() extends Operation with NumericOperation {

    def opcode() = 1
  }

  case class Mul() extends Operation with NumericOperation {

    def opcode() = 2
  }

  case class ReadInto() extends Operation {

    def numargs(): Int = 1

    def execute(ptr: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = {
      writer(args(0)._1, stdIo.read.get)
      ptr + numargs() + 1
    }
  }

  case class Echo() extends Operation {

    def numargs(): Int = 1

    def execute(ptr: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = {
      stdIo << reader(args(0))
      ptr + numargs() + 1
    }
  }

  trait ConditionalJump extends HasOpcode {
    val cond = Seq[(Int) => Boolean](_ != 0, _ == 0)

    def numargs(): Int = 2

    def opcode(): Int

    def execute(ptr: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = {
      val q = reader(args(0))
      if (cond(opcode() % 5)(q)) {
        reader(args(1))
      } else {
        ptr + numargs() + 1
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

    def execute(ptr: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = {
      val v1 = reader(args(0))
      val v2 = reader(args(1))
      writer(args(2)._1, if (comp(opcode() % 7)(v1, v2)) 1 else 0)
      ptr + numargs() + 1
    }
  }

  case class IsLessThan() extends Operation with Comparison {
    def opcode() = 7
  }

  case class IsEqual() extends Operation with Comparison {
    def opcode() = 8
  }

  object Operation {
    def apply(ptr: Int, memory: Array[Int]) = memory(ptr) % 100 match {
      case 1 => Add()
      case 2 => Mul()
      case 3 => ReadInto()
      case 4 => Echo()
      case 5 => JumpIfTrue()
      case 6 => JumpIfFalse()
      case 7 => IsLessThan()
      case 8 => IsEqual()
      case 99 => Exit()
      case opcode@_ => throw new Exception(s"Invalid opcode $opcode")
    }
  }

  // tag::theComputer[]
  class Computer(private val memory: Array[Int]) { // <1>

    private def extractModes(of: Int) = for (i <- 3 to 5) yield
      of % Math.pow(10, i).toInt / Math.pow(10, i - 1).toInt

    val stdIo = new IO // <2>

    private var next: Option[Int] = Some(0)

    private def load(p: Int, mode: Int) = if (mode == 0) memory(p) else p

    private def store(p: Int, v: Int) = memory(p) = v

    def run(): Option[Int] = {

      val reader = (load _).tupled
      val writer = store _

      def executionInstruction: (Int => Option[Int]) = (ptr: Int) => { // <3>
        val operation = Operation(ptr, memory)
        val modes = extractModes(memory(ptr))
        val args = (1 to operation.numargs).map(i => (memory(ptr + i), modes(i - 1)))
        operation match {
          case Exit() => None
          case op@Echo() => Some(op.execute(ptr, args, reader, writer, stdIo)) // <4>
          case op@_ => {
            val next = op.execute(ptr, args, reader, writer, stdIo)
            executionInstruction(next)
          }
        }
      }

      next = next.flatMap(executionInstruction)
      stdIo.read // <5>
    }

    def expectsInput() = next.isDefined
  }

  object Computer {
    def loadProgram(instructions: Seq[Int]) = new Computer(instructions.toArray) // <6>
  }

  // end::theComputer[]

  val input = io.Source.fromFile("input.txt")
  val instructions = input.getLines().flatMap(_.split(",")).map(_.trim).map(_.toInt).toSeq
  input.close

  // tag::starOne[]
  def runProgram = (input: Int, computerAndPhase: (Computer, Option[Int])) => {
    val c = computerAndPhase._1
    c.stdIo << input
    computerAndPhase._2 match {
      case v@Some(_) => c.stdIo << v.get
      case _ =>
    }
    c.run().get
  } // <1>

  def runWithoutFeedback(instructions: Seq[Int]): Int = {
    val executeSequence = (computers: Seq[(Computer, Option[Int])]) =>
      computers.foldLeft(0)(runProgram) // <2>
    (0 to 4).map(Option(_))
      .permutations // <3>
      .map((1 to 5).map(_ => Computer.loadProgram(instructions)).zip(_)) // <4>
      .map(executeSequence).max // <5>
  }

  val starOne = runWithoutFeedback(instructions)
  println(s"Star one ${starOne}")
  // end::starOne[]

  // tag::starTwo[]
  def runWithFeedback(instructions: Seq[Int]): Int = {
    val executeSequence2 = (phases: Seq[Option[Int]]) => {
      val computers = (1 to 5).map(_ => Computer.loadProgram(instructions))
      val availablePhases = (phases ++: LazyList.continually(None)).iterator // <1>
      val nextRun = () => computers.last.expectsInput()
      var lastOutput = 0
      while (nextRun()) {
        lastOutput = computers
          .zip(availablePhases)
          .foldLeft(lastOutput)(runProgram) // <2>
      }
      lastOutput
    }
    (5 to 9).map(Option(_)).permutations.map(executeSequence2).max
  }

  val starTwo = runWithFeedback(instructions)
  println(s"Star two ${starTwo}")
  // end::starTwo[]
}
