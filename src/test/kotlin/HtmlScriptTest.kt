import kotlinx.html.ScriptType
import kotlinx.html.body
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.script
import kotlinx.html.stream.appendHTML
import kotlinx.html.unsafe
import org.junit.Test

class HtmlScriptTest {

    @Test
    fun viaStringBuilder() {
        val sb = StringBuilder()
        sb.appendHTML(true).body {
            script {
                unsafe {
                    raw("function my() {return 1;}")
                }
            }
        }

        println(sb.toString())
    }

    @Test
    fun documentBuilderWithScript1() {
        val html = createHTMLDocument().body {
            script(type = ScriptType.textJScript) {
                unsafe {
                    raw("""
                        |<![CDATA[
                        | function my() {return 1;}
                        |]]]]>""".trimMargin())
                }
            }
        }.serialize(true)

        println(html)
    }

    @Test
    fun documentBuilderWithScript2() {
        val html = createHTMLDocument().body {
            script(type = ScriptType.textJScript) {
                unsafe {
                    raw("""
                        |// <!--
                        | function my() {return 1;}
                        |// -->""".trimMargin())
                }
            }
        }.serialize(true)

        println(html)
    }
}