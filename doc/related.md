
# File System and I/O Functionality in Clojure

To try to avoid doing extra work (and to steal ideas) (and, more seriously,
to try to get a sense of the state of the world), I searched Clojars and
GitHub for packages that relate to file usage and/or the NIO package.

Here's what I found.

## Clojure Standard

### clojure.java.io

Clojure provides a standard library with polymorphic I/O utility functions,
[clojure.java.io](https://clojure.github.io/clojure/clojure.java.io-api.html).
Because it has been built as part of a platform that requires only Java 6,
it has been limited to using `java.io.File`.

See [my doc](https://github.com/johnv02139/java-nio/blob/master/README.md)
about it.

## Third Party Packages

### fs

The de-facto standard for dealing with files from Clojure has been
[Raynes/fs](http://raynes.github.io/fs/).  As mentioned above, it even includes
support for `nio` classes, via a runtime check.  If they can be imported, it
will use them, and if not, all of its functionality is accessible via
`java.io.File`.

It does appear, however, that this project (per se) has been abandoned.  The
last [deployment](https://clojars.org/me.raynes/fs) of `me.raynes/fs` to Clojars
was June 16, 2014.  Commits to the
[repository](https://github.com/Raynes/fs/commits/master) continued until
October 26, 2016, but those were never deployed by raynes.

Since then, a number of people have created forks and integrated some changes in.
(That's what I meant by the project "per se" had been abandoned.)  It might be
nice to try to pull in the various changes that others have incorporated.

The most recently deployed on Clojars that I find are:

* [akvo/fs](https://clojars.org/akvo/fs)
  * last pushed 2018/09/04
  * [akvo/fs](https://github.com/akvo/fs) at GitHub
* [org.clojars.agilecreativity/fs](https://clojars.org/org.clojars.agilecreativity/fs)
  * last pushed 2017/05/23
  * [agilecreativity/fs](https://github.com/agilecreativity/fs) at GitHub
* [bilus/fs](https://clojars.org/bilus/fs)
  * last pushed 2017/02/09
  * [bilus/fs](https://github.com/bilus/fs) at GitHub
* [circleci/fs](https://clojars.org/circleci/fs)
  * last pushed 2016/08/31
  * [circleci/fs](https://github.com/circleci/fs) at GitHub
  * actually the *progenitor* of `raynes/fs`; documented that it is superseded by
    `raynes/fs`; repository has no commits to master since 2011/12/10
  * nevertheless, pushed to Clojars more recently
* [me.raynes/fs](https://clojars.org/me.raynes/fs) **(official)**
  * last pushed 2014/06/16
  * [Raynes/fs](https://github.com/Raynes/fs) at GitHub
  * 15 [open issues](https://github.com/Raynes/fs/issues)
  * 8 [open pull requests](https://github.com/Raynes/fs/pulls)
  * [user](https://github.com/Raynes?tab=overview&from=2016-12-01&to=2016-12-31)
    inactive since November 2016
  * 685,832 Downloads as of this writing
* [org.clojars.chimpymike/fs](https://clojars.org/org.clojars.chimpymike/fs)
  * last pushed 2014/06/04
  * [chimpymike/fs](https://github.com/chimpymike/fs) at GitHub
* plain old [fs](https://clojars.org/fs)
  * last pushed 2012/12/20
  * previous deployment name of [Raynes/fs](https://github.com/Raynes/fs)
  * 1,359,772 Downloads as of this writing
* [com.gfredericks/fs](https://clojars.org/com.gfredericks/fs)
  * last pushed 2012/04/12
  * [2 commits ahead](https://github.com/gfredericks/fs/commits/master),
    143 commits behind Raynes:master

### Clojure NIO

* [nio](https://clojars.org/nio)
  * updated 2015/10/15
  * [pjstadig/nio](https://github.com/pjstadig/nio) at GitHub
  * 72 stars, 5 forks
* [org.ajoberstar/ike.cljj](https://clojars.org/org.ajoberstar/ike.cljj)
  * updated 2018/05/06
  * last pushed 2018/05/06
  * [ajoberstar/ike.cljj](https://github.com/ajoberstar/ike.cljj) at GitHub
     _(not linked to from Clojars)_
  * 37 stars, 4 forks
  * no idea what "ike" means
  * previously published as [ike/ike.cljj](https://clojars.org/ike/ike.cljj)
  * author has offered patch for above-listed
    [JIRA](https://dev.clojure.org/jira/browse/CLJ-2333)
* juxt/dirwatch
  * updated 2015/12/30
  * [juxt/dirwatch](https://github.com/juxt/dirwatch)
  * 37 stars, 9 forks
* [info.hoetzel/clj-nio2](https://clojars.org/info.hoetzel/clj-nio2)
  * updated 2015/11/07
  * last pushed 2013/06/08
  * [juergenhoetzel/clj-nio2](https://github.com/juergenhoetzel/clj-nio2)
  * 28 stars, 2 forks
* bluemont/kria
  * updated 2016/06/05
  * [bluemont/kria](https://github.com/bluemont/kria)
  * 17 stars, 5 forks
* [https://clojars.org/funcool/datoteka](https://clojars.org/funcool/datoteka)
  * updated 2017/02/07
  * last pushed 2017/02/07
  * [funcool/datoteka](https://github.com/funcool/datoteka) at GitHub
  * "a filesystem toolset and storage implementation for Clojure"
  * [documentation](https://funcool.github.io/datoteka/latest/)
  * has a pull request to ask `raynes/fs` to point users to
    [funcool/fs](https://github.com/funcool/fs), which documents that fs is in
    maintenance mode
  * nevertheless, does not appear to have pushed `funcool/fs` to Clojars
* ToBeReplaced/nio.file
  * updated 2015/04/20
  * [ToBeReplaced/nio.file](https://github.com/ToBeReplaced/nio.file)
  * 8 stars, 2 forks
* [nio2](https://clojars.org/nio2)
  * updated 2015/04/19
  * last pushed 2015/04/20
  * [potetm/nio2](https://github.com/potetm/nio2)
  * 2 stars, 0 forks
* [nio2.file](https://clojars.org/nio2.file)
  * updated 2013/01/08
  * [jbondeson/nio2.file](https://github.com/jbondeson/nio2.file)
  * 1 stars, 0 forks
* Moocar/java.io
  * updated 2015/01/04
  * [Moocar/java.io](https://github.com/Moocar/java.io)
  * 1 stars, 0 forks

### Other projects

* [org.eag.file-access](https://clojars.org/org.eag.file-access)
  * last pushed 2017/10/28
  * [EricGebhart/file-access](https://github.com/EricGebhart/file-access)
     at GitHub
  * "designed to read files from the Local filesystem, SFTP, S3, and github
     with a simple consistent interface."
* [puppetlabs/trapperkeeper-filesystem-watcher](https://clojars.org/puppetlabs/trapperkeeper-filesystem-watcher)
  * last pushed 2017/08/10
  * [puppetlabs/trapperkeeper-filesystem-watcher](https://github.com/puppetlabs/trapperkeeper-filesystem-watcher)
    at GitHub
  * "a Trapperkeeper service which provides an API for watching paths on the
    filesystem"
  * clearly not the same functionality as `fs` or `java.nio`, but related
* [charset](https://clojars.org/charset)
  * last pushed 2014/12/03
  * [clavoie/charset](https://github.com/clavoie/charset)
  * 1 stars, 0 forks
  * "Clojure wrappers around some of the class and instance methods of java.nio.charset.Charset"
* [mkremins/fs](https://clojars.org/mkremins/fs)
  * last pushed 2014/09/11
  * [mkremins/fs](https://github.com/mkremins/fs) at GitHub
  * specifically concerned with ClojureScript
  * does not appear to be based on `raynes/fs`
* [clj-mmap](https://clojars.org/clj-mmap)
  * last pushed 2013/08/16
  * [thebusby/clj-mmap](https://github.com/thebusby/clj-mmap)
  * "easily mmap files via Java's NIO"
* [fswatch](https://clojars.org/fswatch)
  * last pushed 2013/05/07
  * [jerrypnz/fswatch](https://github.com/jerrypnz/fswatch) at GitHub
  * "file system watcher powered by Java 7 FileSystem API"
