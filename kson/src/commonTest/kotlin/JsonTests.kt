import com.spryrocks.kson.JsonObject
import com.spryrocks.kson.MutableJsonObject
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
}
