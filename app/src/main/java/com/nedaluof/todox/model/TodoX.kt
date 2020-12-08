package com.nedaluof.todox.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

/**
 * Created by nedaluof on 10/19/2020.
 */
/*
* Entity First Round , will be changed if the  new features depend
* */
@Parcelize
@Entity(tableName = "todox")
data class TodoX(
    var title: String = "unset",
    var description: String = "unset",
    var createdAt: String = LocalDateTime.now().toString(),
    var updatedAt: String? = null
):Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}