<h1>SOAP Integration</h1>
<p>generation classes:</p>
<p>$JAVA_HOME/bin/xjc -d /Users/alekseysamoylov/Documents/GitHub/integration/src/main/java -p com.alekseysamoylov.integration.soap countries.xsd</p>
<p>http://localhost:8080/ws/countries.wsdl</p>
<p>Install the Chrome plugin Wizdler</p>
<p>OR</p>
<p>Execute in the directory: src/test/resources: curl der "content-type: text/xml" -d @request.xml http://localhost:8080/ws</p>
