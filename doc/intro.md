# Introduction to clujnio

## Minimal API

### Creation
* create clj-file

### File Status

As functions, these should likely be able succeed with arguments of various
types.  If given a string, or a `java.io.File`, or a `java.nio.file.Path`,
or anything else that could be converted to a clj-file, the function should
do the conversion and then execute the functionality.

Of course, all of these functions return values that cannot be counted on
to remain valid.

* exists?

   this means we can _confirm_ that the file exists
* does-not-exist?

   this means we can _confirm_ that the file doesn't exist

   It is possible that, for a given argument, both `exists` and `does-not-exist`
   will return false.  Each of them only return true when they can confirm the
   status.  If the status cannot be confirmed, they both return false.
   
   One example of this would be a path `/foo/bar/baz`, where `/foo/bar` is known
   to exist, but which is inaccessible to the user running the process.  We
   certainly can't say it exists, but neither do we say that it does not.
* is-file?

   this implies it exists, and means we can confirm that we can access it as a
   normal file
   
   The path could refer to an existing, ordinary file, or a symbolic link that
   points to an existing, ordinary file.
* is-directory?

   this implies it exists, and means we can confirm that we can access it as a
   directory
   
   The path could refer to an existing directory, or a symbolic link that points
   to an existing directory file.
* is-readable?
* is-writable?
