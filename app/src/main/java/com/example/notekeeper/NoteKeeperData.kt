package com.example.notekeeper

data class CourseInfo(val courseId: String, val title: String) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo(val course: CourseInfo, var title: String, var text: String)