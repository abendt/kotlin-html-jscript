import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.stream.appendHTML
import org.junit.Test

class HtmlScriptTest {

    @Test
    fun viaStringBuilder() {
        val html = buildString {
            appendHTML(true).body {
                script {
                    unsafe {
                        raw("function my() {return 1;}")
                    }
                }
            }
        }

        println(html)
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

    @Test
    fun documentBuilderWithScriptWithEntity() {
        val html = createHTMLDocument().html {
            body {
                unsafe {
                    raw("""
                        |<script>
                        |var a = 3;
                        |var b = -2;
                        |console.log(a > 0 && b > 0);
                        |</script>
                        |""".trimMargin())
                }
            }
        }.serialize(true)

        println(html)
    }
}