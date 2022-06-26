import com.spryrocks.kson.JsonObject
import com.spryrocks.kson.MutableJsonObject
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.test.Test

class JsonTests {
    @Test
    fun decodeSimpleJson() {
        val jsonObject = JsonObject.fromJson("""{"test": true}""")
        print(jsonObject)
    }

    @Test
    fun addValuesToMutableJsonObject() {
        val jsonObject = MutableJsonObject()

        jsonObject.put("test", true)

        print(jsonObject)
    }

    @Test
    fun addJsonObjectToJsonObject() {
        val jsonObject2 = MutableJsonObject()
        jsonObject2.put("test", true)

        val jsonObject1 = MutableJsonObject()
        jsonObject1.put("data", jsonObject2)

        print(jsonObject1)
    }

    @Test
    fun testClassToJsonEncoding() {
        val data = JsonObject.fromObject(MyData(false))
        print(data)

        val intData = Json.encodeToJsonElement(111)
        print(intData)
    }
}

@Serializable
class MyData(val test: Boolean) {
}
