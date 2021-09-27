package com.example.room.models

data class LoginResponse(
    val token: String,
    val user: UserX
)