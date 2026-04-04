package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService
import retrofit2.Response

class TaskRepository(context: Context) : BaseRepository(context) {
    private val remote = RetrofitClient.getService(TaskService::class.java)

    suspend fun save(task: TaskModel): Response<Boolean> {
        return safeApiCall {
            remote.create(
                task.priorityId,
                task.description,
                task.dueDate,
                task.complete
            )
        }
    }

    suspend fun update(task: TaskModel): Response<Boolean> {
        return safeApiCall {
            remote.update(task.id,
                task.priorityId,
                task.description,
                task.dueDate,
                task.complete
            )
        }
    }

    suspend fun complete(id: Int): Response<Boolean> {
        return safeApiCall { remote.complete(id) }
    }

    suspend fun load(id: Int): Response<TaskModel> {
        return safeApiCall { remote.load(id) }
    }

    suspend fun undo(id: Int): Response<Boolean> {
        return safeApiCall { remote.undo(id) }
    }

    suspend fun delete(id: Int): Response<Boolean> {
        return safeApiCall { remote.delete(id) }
    }

    suspend fun list(): Response<List<TaskModel>> {
        return safeApiCall { remote.list() }
    }

    suspend fun listNext(): Response<List<TaskModel>> {
        return safeApiCall { remote.listNext() }
    }

    suspend fun listOverdue(): Response<List<TaskModel>> {
        return safeApiCall { remote.listOverdue() }
    }

}