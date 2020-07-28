package com.developmentaid.task.dtos

data class StackOverflowUser(
       val user:User,
       val tags:List<String>
) {
    override fun toString(): String {
        return "User name: ${user.displayName} " +
                "Location: ${user.location} " +
                "Answer count: ${user.answerCount} " +
                "Question count: ${user.questionCount} " +
                "Tags: ${tags.joinToString()} " +
                "Link to profile: ${user.link} " +
                "Link to avatar: ${user.profileImage}"
    }
}