class Main {
//    data class Vote(
//        val first: String,
//        val second: String,
//        val third: String
//    )

    val VOTE_OFFSET = 3

    private fun transformVotesToResults(voteCount: Map<String, Int>, totalVotes: Map<Int, List<String>>): List<String> = voteCount.toList()
        .sortedByDescending { it.second }
        .filter { it.first.isNotEmpty() }
        .map { it.first }

//
//    [
//       100, B, C
//        99, A,
//    ]

    fun voteCount(votes: List<List<String>>): List<String> {
        val count = mutableMapOf<String, Int>()
        val totalVotes = mutableMapOf<Int, MutableList<String>>() // TODO: refactor to only use this

        votes.forEach { vote ->
            vote.forEachIndexed { index, candidate ->
                val voteWeight = VOTE_OFFSET - index
                // TODO: Cleaner implementation, less branch option?
                if (count.containsKey(candidate)) {
                    count[candidate] = count[candidate]!!.plus(voteWeight)
                } else {
                    count.putIfAbsent(candidate, voteWeight)
                }

                val candidateCurrentCount = count[candidate]!!

                if (totalVotes.containsKey(candidateCurrentCount)) {
                    totalVotes[candidateCurrentCount]!!.add(candidate)
                } else {
                    totalVotes[candidateCurrentCount] = mutableListOf(candidate)
                }
            }
        }

        return transformVotesToResults(count, totalVotes)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello program arguments were: ${args.joinToString()}")
        }
    }
}