<html>
<body>
<h2>Mario game in Java</h2>
<br>
<h3>Screenshot</h3>
<img src="https://github.com/amirnasri/Java_game/blob/master/Screenshot.png" alt="Screen Shot">

<applet code= "HelloWorld.class">

<section id="main_content">
            <script>
                var attributes = {
                    archive: 'HelloWorld.jar'
                    code: 'HelloWorld.class',
                    width: '800',
                    height: '600'};
                var parameters = {java_arguments: '-Xmx256m'}; // customize per your needs
                var version = '1.7'; // JDK version
                deployJava.runApplet(attributes, parameters, version);
            </script>
</section>
 
<a href="https://github.com/amirnasri/Java_game/blob/master/JNLPExample.jnlp" type="application/x-java-jnlp-file">
play
</a>
</body>
</html>
