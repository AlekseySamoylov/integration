<h1>Integration Server</h1>
<h2>SOAP Server</h2>
<p>Client: https://github.com/AlekseySamoylov/iclient</p>
<p>generation classes:</p>
<p>$JAVA_HOME/bin/xjc -d /Users/alekseysamoylov/Documents/GitHub/integration/src/main/java -p com.alekseysamoylov.integration.soap countries.xsd</p>
<p>http://localhost:8080/ws/countries.wsdl</p>
<p>Install the Chrome plugin Wizdler</p>
<p>OR</p>
<p>Execute in the directory: src/test/resources: curl der "content-type: text/xml" -d @request.xml http://localhost:8080/ws</p>

<h2>Histrix Circuit Breaker & Feign client</h2>
