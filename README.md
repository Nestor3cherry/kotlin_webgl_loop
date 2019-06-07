# Kotlin-based WebGL render loop

![Alt text](https://d2eip9sf3oo6c2.cloudfront.net/tags/images/000/000/936/thumb/webgl.png "Ingame screenshot")


While working on some complex 2D scenes rendered on WebGL, I realized the importance of minimizing the number of batches in order to get good performance on mobile devices

In most cases, the intensive use of additive compositing lead to high amount of composite changes, doing a render call prior each change

I realized that, in some cases this effect can be minimized if the rendering order of the different objects are not important (they don't overlap), getting nice performance improvements


This work is based on a slight modified version of EaselJS WebGL render engine, but probarbly it would work on the official released version.

In this case, the 2D scene is stored as a tree, where the leaves are the actual items to be rendered. Nodes in the middle usually apply transforms (scaling, alpha, position...)

![Alt text](https://raw.githubusercontent.com/Nestorferrando/kotlin_webgl_loop/master/readmeimages/image1.jpg "")

The vanilla EaselJS render just traverses all the scene tree, directly calling the needed WebGL function to render all the objects. Each time the alpha blending of an object change, the current batch is sent to render a new batch starts.

![Alt text](https://raw.githubusercontent.com/Nestorferrando/kotlin_webgl_loop/master/readmeimages/image2.jpg "")


In order to do more complex process of the rendered objects, first I splitted the loop in two stages:

1. Traverse all scene tree and create an array of 'instructions' that renders the scene
2. Traverse the instruction array to actual perform all the render actions:

![Alt text](https://raw.githubusercontent.com/Nestorferrando/kotlin_webgl_loop/master/readmeimages/image3.jpg "")


this additional step allows me to perform modifications over the instructions array, such us reorder and group objects with similar alpha blending


After this, I created an additional instruction: flatten. All nodes tagged with flatten will have independent instructions array. In a separate process, these arrays will be combined in a single one, unifying the elements with the same composite if possible:
![Alt text](https://raw.githubusercontent.com/Nestorferrando/kotlin_webgl_loop/master/readmeimages/image4.jpg "")

As a result, the set of render isntructions becomes optimized.

I succesfully tested this render loop on several scenarios, obtaining significative performance boosts.
