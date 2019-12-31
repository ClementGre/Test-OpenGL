[![Release](https://img.shields.io/github/release/themsou/Test-OpenGL.svg)](https://github.com/themsou/Test-OpenGL/releases/)

## Test-OpenGL [Février 2019]

**Cette application est ma première application 3D avec OpenGL**

J'ai pu générer une carte (map) à partir d'une mapping image (image Bitmap) puis attribuer des textures aux éléments de la carte.
Vous pouvez vous déplacer dans la carte avec les touches Z, S, Q, D et ESPACE ainsi que SHIFT (Sans oublier la souris). PS: Utilisez CTRL pour aller plus vitte.

J'ai aussi utilisé des shaders (programmés en GLSL) mais je n'ai pas pris le temps de découvrir comment fonctionnait exactement le GLSL et toutes ces matrices et vectors m'ont un peut dégoutés ;)

## Les APIs

J'ai utilisé l'API LWJGL (LightWeight Java Game Library) qui est une adaptation de OpenGL pour Java.
Vous pourez facilement télécharger les dépendances sur internet ou les retrouver dans le dossier lib du repository.

À côté des dépendances sous forme de Jar se trouvent plusieurs natives, différentes selon les plateformes. J'ai donc intégré les natives dans le Jar avec JarSplice 0.40 (.so pour Linux, .dll pour Windows et .dylib pour Mac).

Vous aurez bien sûr besoin d'avoir auparavant installé Java sur votre machine (1.8 et 1.11 testés).