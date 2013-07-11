Cloud Endpoints Example in Java
===============================

Attention! Внимание! Achtung!

This code sample is not quite there yet. That means, while the code itself is generally working to prove the point, 
there is a number of deficencies. In particular:

* There's no decent build system. Everything lives as Eclipse project(s). 

* As a result, you most likely will need to fix these projects to take libraries from the right places 
(that's mostly AppEngine and Android libraries)

* Android app is still somewhat buggy. In particular, changing whole room state doesn't quite work (save isn't stored), 
and rotating the device will result in a crash.

If you feel like fixing any of these or many unlisted problems -- patches are always welcome.
