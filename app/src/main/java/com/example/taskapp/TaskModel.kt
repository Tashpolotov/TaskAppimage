package com.example.taskapp


import java.io.Serializable

data class TaskModel(
    var imgUri: String,
    var title: String,
    var descriptor: String

): Serializable