class Automate(val matrix: Array[Array[(Int, Character => Boolean)]], val rangeArray: Array[Seq[Char]]) {
	private var currentState = 0

	private def selectFromMatrix(state: Int, c: Character): (Int, Character => Boolean) = {
		for (i <- 0 until rangeArray.length)
			if (rangeArray(i).contains(c))
				return matrix(state)(i)
		return (currentState, c => { println("Error: illegal character '" + c + "'"); false })
	}

	def process(c: Character) = {
		val (newState, callback) = selectFromMatrix(currentState, c)
		currentState = newState
		callback(c)
	}

	def processString(str: String) = {
		var i = 0
		var res = true
		while (i < str.length && res) {
			res = res && process(str.charAt(i))
			i += 1
		}
		res && process('\0')
	}
}