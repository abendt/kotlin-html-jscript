import kotlinx.html.ScriptType
import kotlinx.html.body
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.script
import kotlinx.html.unsafe
import org.junit.Test

class HtmlScriptTest {
    
    @Test
    fun documentBuilderWithScript() {
        val html = createHTMLDocument().body {
            script(type = ScriptType.textJScript) {
                unsafe {
                    raw("""
                        function my() {return 1;}
                        """)
                }
            }
        }.serialize(true)

        println(html)
    }
}