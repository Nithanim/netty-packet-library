<h1>Description</h1>
This is a packet-library for <a href="https://github.com/netty/netty">Netty</a> (currently for version 5.0.0.Alpha1) which is designed to transport data efficiently over the network.
Essentially, data-objects (Packets) are defined which can transform themselves to and from a byte-representation.

As an example, a packet which contains a single integer with the value 8 and the id of 99 will be converted to:
```
99, 0, 4, 0, 0, 0, 8
```

<h1>Examples</h1>
Refer to the folder examples where you can find basic usage of this library.

<h1>Legal</h1>
This library is released under the Apache License Version 2.0. See LICENSE for detailed information.

This library uses <a href="https://github.com/jhalterman/typetools">TypeTools by Jonathan Halterman</a>. Thank you!