# clujnio

Currently some wrappers around Java file functionality.

Will migrate to something which provides similar functionality, but also a lot
more, in a richer way.

## Direction

CLojure Unified Java New I/O.

A Clojure library designed to make it easy to use files from Clojure, without
knowing or caring which Java objects are supporting them.

Yes, it's properly pronounced "KLUDGE-knee-oh".  That doesn't make it a kludge,
though.

## Motivation

It's always seemed Clojure didn't care too much about working with files.
Better convenience functions can help.

But a much bigger issue is that Java finally got around to improving their
functionality around files, as well as other I/O, in Java 7 with the java.nio
package.

Accessing this package from Clojure can be painful, though.  Clojure's core
packages (specifically clojure.java.io) deal with java.io.File objects.  There
is no plan to change this any time in the near future.  Clojure required Java 6,
and to change it to require Java 7 would cause a lot of pain.  So, they won't do
it soon, and may not do it ever.

Of course, you can still run _your_ Clojure program with Java 8, and take
advantage of its features.  A couple of issues:
- a lot of nio uses variadic methods, which can be painful to call from Clojure
- if you tie your library to Java 7, there are Clojure programs which can't use you

So, the idea, ultimately, is that you'll be able to depend on this library, and
use common functions, and you'll get the benefits of nio when the underlying JVM
supports it, or you'll get plain old java.io.File objects when it doesn't.

## Status

I'm just getting started.  I am pretty sure what I describe is possible :-) but
I'm actually not worrying about it just yet.  Initially, I'm kind of assuming
Java 8, and just trying to build an interface that allows you to use objects
interchangeably to represent files on disk.

## License

Copyright (c) 2018, John Valente

Distributed under the MIT Public License
