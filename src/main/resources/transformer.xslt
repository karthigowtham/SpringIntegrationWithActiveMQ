<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes" />	
	<xsl:template match="CstmrCdtTrfInitn">
		<Document xmlns="urn:iso:std:iso:20022:tech:xsd:pain.001.001.03" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			  <CustomerTransaction>				
				<xsl:copy-of select="//GrpHdr" />														
				<xsl:for-each select="//GrpHdr/PstlAdr">					
					<xsl:choose>
						<xsl:when test="Ctry = 'US'">
							<USMailingAddress><xsl:value-of select="BldgNb"/>,<xsl:value-of select="StrtNm"/>,<xsl:value-of select="PstCd"/>-<xsl:value-of select="TwnNm"/>.</USMailingAddress>
						</xsl:when>
						<xsl:otherwise>
							<MailingAddress>NON-US</MailingAddress>
						</xsl:otherwise>
					</xsl:choose>				
				</xsl:for-each>
			  </CustomerTransaction>
		</Document>
	</xsl:template>
</xsl:stylesheet>

