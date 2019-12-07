object Solution extends App {

  class IO(var input: Seq[Int] = Seq()) {
    def read: Option[Int] = input match {
      case Nil => None
      case l@_ => {
        input = input.tail
        Some(l.head)
      }
    }
    
    def << (v: Int) = {
      input = v +: input
      this
    }
    
    def clear = (input = Seq())
  }

  abstract class Operation {
    def numargs(): Int

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int
  }

  case class Exit() extends Operation {
    def numargs(): Int = 0

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = 0
  }

  trait HasOpcode {
    def opcode(): Int
  }

  trait NumericOperation extends HasOpcode {
    val ops = Seq[(Int, Int) => Int](_ + _, _ * _)

    def numargs(): Int = 3

    def opcode(): Int

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = {
      writer(args(2)._1, ops(opcode() - 1)(reader(args(0)), reader(args(1)))) 
      pos + numargs() + 1
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

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = {
      writer(args(0)._1, stdIo.read.getOrElse(0))
      pos + numargs() + 1
    }
  }

  case class Echo() extends Operation {

    def numargs(): Int = 1

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = {
      stdIo << reader(args(0))
      pos + numargs() + 1
    }
  }

  trait ConditionalJump extends HasOpcode {
    val cond = Seq[(Int) => Boolean](_ != 0, _ == 0)

    def numargs(): Int = 2

    def opcode(): Int

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = {
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

    def execute(pos: Int, args: Seq[(Int, Int)], reader: (((Int, Int)) => Int), writer: (Int, Int) => Unit, stdIo: IO): Int = {
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

  object Operation {
    def apply(pos: Int, memory: Array[Int]) = memory(pos) % 100 match {
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

  def read(memory: Array[Int])(p: Int, mode: Int) =
    if (mode == 0) memory(p) else p // <1>
  def write(memory: Array[Int])(p: Int, v: Int) =
    memory(p) = v

  def extractModes(of: Int) = for (i <- 3 to 5) yield
    of % Math.pow(10, i).toInt / Math.pow(10, i - 1).toInt

  def run(memory: Array[Int], phase: Int, input: Int = 0): Int = {

    val reader = (read(memory) _).tupled 
    val writer = write(memory) _

    val stdIo = new IO()
    stdIo << input
    stdIo << phase
    
    def runImpl : (Int => Unit) = (pos: Int) => {
      val operation = Operation(pos, memory)
      val modes = extractModes(memory(pos))
      val args = (1 to operation.numargs).map(i => (memory(pos + i), modes(i - 1)))
      operation match {
        case Exit() => Nil
        case op@_ => {
          val next = op.execute(pos, args, reader, writer, stdIo)
          runImpl(next)
        }
      }
    }
    runImpl(0)
    stdIo.read.get
  }


  val input = io.Source.fromFile("input.txt")
  val instructions = input.getLines().flatMap( _.split(",")).map(_.trim).map(_.toInt).toSeq
  input.close
  
  val executePhase = (phases: Seq[Int]) => (0 to 4).foldLeft(0)((input, amp) => 
    run(instructions.toArray, phases(amp), input))    
  (0 to 4).permutations.map(executePhase).max
  
  

  println("For star one, enter 1, for start two, 5")
  run(instructions.toArray)
}

val instructions = Seq(3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0)
val instructions = Seq(3,23,3,24,1002,24,10,24,1002,23,-1,23,
101,5,23,23,1,24,23,23,4,23,99,0,0)