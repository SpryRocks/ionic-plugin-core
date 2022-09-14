import com.spryrocks.kson.JsonArray
import com.spryrocks.kson.JsonObject
import com.spryrocks.kson.MutableJsonArray
import com.spryrocks.kson.MutableJsonObject
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.test.Test
import kotlin.test.expect

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

    @Test
    fun decodeJsonObjectWithArray() {
        val json = SimpleJsonObjectString

        val data = JsonObject.fromJson(json)
//        print(data)

        val receipt = data.getJsonArray("receipt")
//        print(receipt)

        for (i in 0 until receipt.size) {
            val item = receipt.getJsonObject(i)
//            print(item)

            for (name in item.names) {
                val value = item.opt(name)
                println("$name: $value")
            }
        }
    }

    @Test
    fun testNullableValues() {
        val json = JsonArrayWithNullableValuesString
        val jsonArray = JsonArray.fromJson(json)
        for(i in 0 until jsonArray.size) {
            val jsonItem = jsonArray.get(i)
            if(jsonItem is JsonObject) {
                jsonItem.names.forEach { name ->
                    val value = jsonItem.opt(name)
                    value.toString()
                }
            }
        }
        jsonArray.toString()
    }

    @Test
    fun testPutNullableToObject() {
        val jsonObject = MutableJsonObject()
        jsonObject.putNull("nullField")
        if (jsonObject.opt("nullField") != null) throw Error()
    }

    @Test
    fun testPutNullableToArray() {
        val jsonArray = MutableJsonArray()
        jsonArray.putNull()
        if (jsonArray.opt(0) != null) throw Error()
    }

    @Test
    fun testArrayIterator() {
        val jsonArray = MutableJsonArray()
        jsonArray.put("aaa")
        jsonArray.put(111)

        jsonArray.forEach { it -> println(it) }
    }
}

@Serializable
class MyData(val test: Boolean)
