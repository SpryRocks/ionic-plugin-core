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

    @Test
    fun decodeJsonObjectWithArray() {
        val json =
            "{\"receipt\":[{\"hahaha\": null},{\"ok\":true},{\"textAlign\":1},{\"font\":0},{\"text\":\"Company name\\nStore Store Name\"},{\"textAlign\":0},{\"text\":\"\\n\"},{\"textAlign\":1},{\"font\":0},{\"text\":\"\\n\\n\"},{\"textSize\":1,\"textSizeHeight\":1},{\"textAlign\":0},{\"font\":0},{\"text\":\"Invoice no.                15-15\\nInvoice ID              81779fbf\\nDate            27.10.2016 14:46\\nService                    Admin\\n\"},{\"font\":0},{\"text\":\"________________________________\\n\\nLorem Ipsum is simply      12.00\\ndummy text of the printing      \\nand typesetting industry.       \\nFirst product             123.00\\nCustom                      3.00\\n________________________________\\n\\n\"},{\"textSize\":2,\"textSizeHeight\":2},{\"textAlign\":2},{\"text\":\"Total CHF 138.00\\n\\n\"},{\"textSize\":1,\"textSizeHeight\":1},{\"font\":0},{\"textAlign\":0},{\"text\":\"Cash CHF                  200.00\\nChange CHF                 62.00\\n\\nVAT no. CHE- 123.456.789\\nRate       Net     VAT     Gross\\n8.00%   127.78   10.22    138.00\\n\\n\\n\"},{\"cut\":\"Nothing\"}]}"

        val data = JsonObject.fromJson(json)
//        print(data)

        val receipt = data.getJsonArray("receipt")
//        print(receipt)

        for (i in 0 until receipt.length()) {
            val item = receipt.getJsonObject(i)
//            print(item)

            for (name in item.names()) {
                val value = item.opt(name)
                println("$name: $value")
            }
        }
    }
}

@Serializable
class MyData(val test: Boolean)
