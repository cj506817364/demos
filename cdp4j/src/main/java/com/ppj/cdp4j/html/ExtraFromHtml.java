package com.ppj.cdp4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author pipi
 * @date 2020/12/24 11:48
 */
public class ExtraFromHtml {

  static String html = "<html>\n"
      + " <head></head>\n"
      + " <body>  <tbody>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">ascii</code></td>\n"
      + "<td><code class=\"literal\">US-ASCII</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">big5</code></td>\n"
      + "<td><code class=\"literal\">Big5</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">gbk</code></td>\n"
      + "<td><code class=\"literal\">GBK</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">sjis</code></td>\n"
      + "<td><code class=\"literal\">SJIS (or Cp932 or MS932 for MySQL Server &lt; 4.1.11)</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">cp932</code></td>\n"
      + "<td><code class=\"literal\">Cp932 or MS932 (MySQL Server &gt; 4.1.11)</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">gb2312</code></td>\n"
      + "<td><code class=\"literal\">EUC_CN</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">ujis</code></td>\n"
      + "<td><code class=\"literal\">EUC_JP</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">euckr</code></td>\n"
      + "<td><code class=\"literal\">EUC_KR</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">latin1</code></td>\n"
      + "<td><code class=\"literal\">Cp1252</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">latin2</code></td>\n"
      + "<td><code class=\"literal\">ISO8859_2</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">greek</code></td>\n"
      + "<td><code class=\"literal\">ISO8859_7</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">hebrew</code></td>\n"
      + "<td><code class=\"literal\">ISO8859_8</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">cp866</code></td>\n"
      + "<td><code class=\"literal\">Cp866</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">tis620</code></td>\n"
      + "<td><code class=\"literal\">TIS620</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">cp1250</code></td>\n"
      + "<td><code class=\"literal\">Cp1250</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">cp1251</code></td>\n"
      + "<td><code class=\"literal\">Cp1251</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">cp1257</code></td>\n"
      + "<td><code class=\"literal\">Cp1257</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">macroman</code></td>\n"
      + "<td><code class=\"literal\">MacRoman</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">macce</code></td>\n"
      + "<td><code class=\"literal\">MacCentralEurope</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">utf8</code></td>\n"
      + "<td><code class=\"literal\">UTF-8</code></td>\n"
      + "</tr>\n"
      + "<tr>\n"
      + "<td scope=\"row\"><code class=\"literal\">ucs2</code></td>\n"
      + "<td><code class=\"literal\">UnicodeBig<br>\n"
      + "<br>\n"
      + "</code></td>\n"
      + "</tr>\n"
      + "</tbody> </body>\n"
      + "</html>";


  public static void main(String[] args) {
    final Document parse = Jsoup.parse(html);
    final Elements codes = parse.getElementsByTag("code");
    boolean extra = true;
    for (Element code : codes) {
      if (extra) {
        System.out.print(code.text() + ",");
      }
      extra = !extra;
    }
  }
}
