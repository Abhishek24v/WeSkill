package com.weskill2.network.models.user

data class User(
    val ProfilePhoto: ProfilePhoto,
    val __v: Int,
    val _id: String,
    val blocked: Boolean,
    val comment: String,
    val comments: List<Comment>,
    val confirmed: Boolean,
    val courses_opts: List<CoursesOpt>,
    val createdAt: String,
    val email: String,
    val groups: List<Group>,
    val id: String,
    val learner_profiles: ArrayList<LearnerProfile>,
    //val user_group : UserGroup,
    val like: String,
    val likes: List<Like>,
    val name: String,
    val number: String,
    val posts: List<Post>,
    val provider: String,
    val relation: String,
    val role: Role,
    val trials_bookings: List<TrialsBooking>,
    val updatedAt: String,
    val username: String
)