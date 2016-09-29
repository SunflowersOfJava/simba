<#list rows as row>
	<w:tr w:rsidR="009F5780" w:rsidTr="009F5780">
		<#list row as cell>
			<w:tc><w:tcPr><w:tcW w:w="2841" w:type="dxa"/></w:tcPr><w:p w:rsidR="009F5780" w:rsidRDefault="002528B9"><w:pPr><w:ind w:firstLine="0"/></w:pPr><w:r><w:rPr><w:rFonts w:hint="eastAsia"/></w:rPr><w:t>${cell}</w:t></w:r></w:p></w:tc>
		</#list>
	</w:tr>
</#list>