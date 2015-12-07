<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : aboutus.xsl
    Created on : 10 April, 2015, 1:15 PM
    Author     : N.Yuvanesh
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>aboutus.xsl</title>
             </head>
            <body bgcolor="#000033">
                <h1 style="color:white"><xsl value-of select="name"></h1>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
