package com.example

import com.example.entities.NotesEntity
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.ktorm.database.Database
import org.ktorm.dsl.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()

        val database = Database.connect(
            url = "jdbc:mysql://localhost:3306/notes",
            driver = "com.mysql.cj.jdbc.Driver",
            user = "root",
            password = "rootpassword"
        )

        database.update(NotesEntity) {
            set(it.note, "Working out")
            where {
                it.id eq 4
            }
        }


        database.delete(NotesEntity){
            it.id eq 5
        }

    }.start(wait = true)
}
