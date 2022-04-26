class Main {

    private fun transformVotesToResults(totalVotes: Map<Int, List<String>>): List<String> = totalVotes
        .toSortedMap(compareByDescending { votesCount -> votesCount })
        .map { (_, candidates) -> candidates }
        .flatten()

    fun voteCount(votes: List<List<String>>): List<String> {
        val voteOffset = 3
        val count = mutableMapOf<String, Int>()
        val totalVotes = mutableMapOf<Int, MutableList<String>>()

        votes.forEach { vote ->
            vote.forEachIndexed { index, candidate ->
                if (candidate.isEmpty()) return@forEachIndexed

                val previousCount = count.getOrDefault(candidate, 0)
                val currentCount = previousCount + voteOffset - index
                count[candidate] = currentCount

                totalVotes[currentCount]?.add(candidate) ?: totalVotes.put(
                    currentCount,
                    mutableListOf(candidate)
                )

                when (totalVotes[previousCount]?.size) {
                    1 -> totalVotes.remove(previousCount)
                    else -> totalVotes[previousCount]?.remove(candidate)
                }
            }
        }

        return transformVotesToResults(totalVotes)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello program arguments were: ${args.joinToString()}")
        }
    }
}