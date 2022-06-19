package com.example.feedapplication.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "news_table")
data class Result(
    @SerializedName("apiUrl")
    val apiUrl: String,
    @SerializedName("id")
    @PrimaryKey
    val id: String,
    @SerializedName("isHosted")
    val isHosted: Boolean,
    @SerializedName("pillarId")
    val pillarId: String,
    @SerializedName("pillarName")
    val pillarName: String,
    @SerializedName("sectionId")
    val sectionId: String,
    @SerializedName("sectionName")
    val sectionName: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("webPublicationDate")
    val webPublicationDate: String,
    @SerializedName("webTitle")
    val webTitle: String,
    @SerializedName("webUrl")
    val webUrl: String,
    @SerializedName("favorit")
    var fav: Boolean = false
):Serializable