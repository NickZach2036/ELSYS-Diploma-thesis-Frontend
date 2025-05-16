package com.example.elsysdiplomathesisfrontend

import androidx.datastore.core.Serializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.InputStream
import java.io.OutputStream

object AppSettingsSerializer : Serializer<HashMap<String, String>> {
    override val defaultValue: HashMap<String, String> = hashMapOf()

    override suspend fun readFrom(input: InputStream): HashMap<String, String> {
        return try {
            val jsonString = input.bufferedReader().use { it.readText() }
            if (jsonString.isEmpty()) hashMapOf()
            else {
                val json = Json.decodeFromString<JsonObject>(jsonString)
                val map = hashMapOf<String, String>()
                json.forEach { (key, value) ->
                    map[key] = value.jsonPrimitive.content
                }
                map
            }
        } catch (e: Exception) {
            e.printStackTrace()
            hashMapOf()
        }
    }

    override suspend fun writeTo(t: HashMap<String, String>, output: OutputStream) {
        val json = Json.encodeToString(
            MapSerializer(String.serializer(), String.serializer()),
            t
        )
        output.bufferedWriter().use { it.write(json) }
    }
}

//@Suppress("BlockingMethodInNonBlockingContext")
//object AppSettingSerializer : Serializer<AppSettings> {
//    override val defaultValue: AppSettings
//        get() = AppSettings()
//
//    override suspend fun readFrom(input: InputStream): AppSettings {
//        return try {
//            Json.decodeFromString(
//                deserializer = AppSettings.serializer(),
//                string = input.readBytes().decodeToString()
//            )
//        } catch (e: SerializationException) {
//            e.printStackTrace()
//            defaultValue
//        }
//    }
//
//    override suspend fun writeTo(t: AppSettings, output: OutputStream) {
//        output.write(
//            Json.encodeToString(
//                serializer = AppSettings.serializer(),
//                value = t
//            ).encodeToByteArray()
//        )
//    }
//}

//@Serializable
//data class AppSettings(
//    @SerializedName("token")
//    val token: String = "",
//    @SerializedName("refreshToken")
//    val refreshToken: String = "",
//)
