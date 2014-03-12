object Main {

	var collectedAddr = new StringBuilder()

	def cont(char: Character) = true
	def fail(char: Character) = {
		println("Error")
		false
	}

	def coll(char: Character) = {
		collectedAddr.append(char)
		true
	}

	def puts(char: Character) = {
		println(collectedAddr)
		collectedAddr.clear()
		true
	}

	def ueof(char: Character) = {
		println("Error: unexpected end of input")
		false
	}

	def uchr(char: Character) = {
		println("Error: unexpected character '" + char + "'")
		false
	}

	def main(args: Array[String]) {
		val ranges = Array[Seq[Char]](
			'a' to 'z',
			'A' to 'Z',
			'0' to '9',
			'_' to '_',
			'@' to '@',
			'.' to '.',
			'-' to '-',
			Array(' ', '\n', '\t'),
			'\0' to '\0'
		)

		val matrix = Array(
			Array((1, coll _), (1, coll _), (1, coll _), (1, coll _), (0, uchr _), (0, uchr _), (0, uchr _), (0, cont _), (0, cont _)),
			Array((1, coll _), (1, coll _), (1, coll _), (1, coll _), (2, coll _), (1, uchr _), (1, uchr _), (1, uchr _), (1, ueof _)),
			Array((3, coll _), (3, coll _), (3, coll _), (2, uchr _), (2, uchr _), (2, uchr _), (2, uchr _), (2, uchr _), (2, ueof _)),
			Array((3, coll _), (3, coll _), (3, coll _), (3, uchr _), (3, uchr _), (2, coll _), (4, coll _), (0, puts _), (3, puts _)),
			Array((3, coll _), (3, coll _), (3, coll _), (4, uchr _), (4, uchr _), (4, uchr _), (4, coll _), (4, uchr _), (4, ueof _))
		)
		val automate = new Automate(matrix, ranges)
		val lines = scala.io.Source.fromFile("file.txt").mkString
		automate.processString(lines);
	}
}