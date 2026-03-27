package com.devmasterteam.tasks.service.repository

import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService

class TaskRepository {
    private val remote = RetrofitClient.getService(TaskService::class.java)

    suspend fun save(task: TaskModel) {
        remote.create(task.priorityId, task.description, task.dueDate, task.complete)
    }
}