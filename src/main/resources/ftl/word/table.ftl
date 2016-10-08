<w:tr w:rsidR="00C554B7" w:rsidRPr="00D5719A" w:rsidTr="008A7656"><w:trPr><w:trHeight w:val="285"/></w:trPr>
<#list header as cell>
<w:tc><w:tcPr><w:tcW w:w="2235" w:type="dxa"/><w:tcBorders><w:top w:val="single" w:sz="4" w:space="0" w:color="auto"/><w:left w:val="single" w:sz="4" w:space="0" w:color="auto"/><w:bottom w:val="single" w:sz="4" w:space="0" w:color="auto"/><w:right w:val="single" w:sz="4" w:space="0" w:color="auto"/></w:tcBorders><w:shd w:val="clear" w:color="000000" w:fill="8DB4E3"/><w:noWrap/><w:vAlign w:val="bottom"/><w:hideMark/></w:tcPr><w:p w:rsidR="00C554B7" w:rsidRPr="00D5719A" w:rsidRDefault="00AB74D9" w:rsidP="008A7656"><w:pPr><w:widowControl/><w:jc w:val="center"/><w:rPr><w:rFonts w:ascii="宋体" w:cs="宋体"/><w:kern w:val="0"/><w:sz w:val="24"/><w:szCs w:val="24"/></w:rPr></w:pPr><w:r><w:rPr><w:rFonts w:ascii="宋体" w:hAnsi="宋体" w:cs="宋体" w:hint="eastAsia"/><w:kern w:val="0"/><w:sz w:val="24"/><w:szCs w:val="24"/></w:rPr><w:t>${cell}</w:t></w:r></w:p></w:tc>
</#list>
</w:tr>

<#list rows as row>
<w:tr w:rsidR="00C554B7" w:rsidRPr="00D5719A" w:rsidTr="008A7656"><w:trPr><w:trHeight w:val="285"/></w:trPr>
<#list row as cell>
<w:tc><w:tcPr><w:tcW w:w="2235" w:type="dxa"/><w:tcBorders><w:top w:val="nil"/><w:left w:val="single" w:sz="4" w:space="0" w:color="auto"/><w:bottom w:val="single" w:sz="4" w:space="0" w:color="auto"/><w:right w:val="single" w:sz="4" w:space="0" w:color="auto"/></w:tcBorders><w:vAlign w:val="bottom"/></w:tcPr><w:p w:rsidR="00C554B7" w:rsidRPr="00D5719A" w:rsidRDefault="004B73E4" w:rsidP="008A7656"><w:pPr><w:widowControl/><w:jc w:val="center"/><w:rPr><w:rFonts w:ascii="宋体" w:cs="宋体"/><w:kern w:val="0"/><w:sz w:val="24"/><w:szCs w:val="24"/></w:rPr></w:pPr><w:r><w:rPr><w:rFonts w:ascii="宋体" w:cs="宋体" w:hint="eastAsia"/><w:kern w:val="0"/><w:sz w:val="24"/><w:szCs w:val="24"/></w:rPr><w:t>${cell}</w:t></w:r></w:p></w:tc>
</#list>
</w:tr>
</#list>